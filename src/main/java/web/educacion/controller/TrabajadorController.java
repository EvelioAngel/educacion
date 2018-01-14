/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.educacion.model.Trabajador;
import web.educacion.model.TrabajadorHistorico;
import web.educacion.model.Usuarios;
import web.educacion.repository.CargoRepo;
import web.educacion.repository.CategoriaCientificaRepo;
import web.educacion.repository.CategoriaOcupacionalRepo;
import web.educacion.repository.EnsenanzaRepo;
import web.educacion.repository.EntidadRepo;
import web.educacion.repository.EspecialidadRepo;
import web.educacion.repository.NivelPreparacionRepo;
import web.educacion.repository.TrabajadorHistoricoRepo;
import web.educacion.repository.TrabajadorRepo;
import web.educacion.repository.UsuariosRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class TrabajadorController {
    @Autowired
    TrabajadorRepo repo;
    @Autowired
    TrabajadorHistoricoRepo repoTrabajadorHistorico;
    @Autowired
    EntidadRepo repoEntidad;
    @Autowired
    CargoRepo repoCargo;
    @Autowired
    EnsenanzaRepo repoEnsenanza;
    @Autowired
    EspecialidadRepo repoEspecialidad;
    @Autowired
    CategoriaCientificaRepo repoCategoriaCientifica;
    @Autowired
    NivelPreparacionRepo repoNivel;
    @Autowired
    CategoriaOcupacionalRepo repoCategoriaOcupacional;
    @Autowired
    UsuariosRepo repoUser;
    
    @RequestMapping(value = "/trabajador", method = RequestMethod.GET)
    public String index(@RequestParam(required= false, defaultValue="") String nombre,
                       @RequestParam(required= false, defaultValue="") String ci,
                       Model model,@SortDefault("nombre") Pageable pageable) { 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Usuarios user = repoUser.findOne(name); 
        
        if(user.getRol().equalsIgnoreCase("admin"))
            model.addAttribute("page", repo.findByNombreIgnoreCaseLikeAndCiLike("%"+nombre+"%","%"+ci+"%",pageable));
        else{
            Integer municipio = user.getIdMunicipio().getIdMunicipio();
            model.addAttribute("page", repo.findByNombreIgnoreCaseLikeAndCiLikeAndIdEntidadIdMunicipioIdMunicipio("%"+nombre+"%","%"+ci+"%", municipio,pageable));
        }                 
        
        model.addAttribute("nombre", nombre);
        model.addAttribute("ci", ci);
        return "trabajador/index";
    }
    
    @RequestMapping(value = "/trabajador/new", method = RequestMethod.GET)
    public String nuevo(Model model) {         
        model.addAttribute("data", new Trabajador());
        model.addAttribute("entidad", repoEntidad.findAll());
        model.addAttribute("cargo", repoCargo.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        model.addAttribute("especialidad", repoEspecialidad.findAll());
        model.addAttribute("categoriaCientifica", repoCategoriaCientifica.findAll());
        model.addAttribute("nivel", repoNivel.findAll());
        model.addAttribute("categoriaOcupacional", repoCategoriaOcupacional.findAll());
        return "trabajador/form";
    }
    
    @RequestMapping(value = "/trabajador/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable Integer id) {         
        model.addAttribute("data", repo.findOne(id));
        model.addAttribute("entidad", repoEntidad.findAll());
        model.addAttribute("cargo", repoCargo.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        model.addAttribute("especialidad", repoEspecialidad.findAll());
        model.addAttribute("categoriaCientifica", repoCategoriaCientifica.findAll());
        model.addAttribute("nivel", repoNivel.findAll());
        model.addAttribute("categoriaOcupacional", repoCategoriaOcupacional.findAll());
        return "trabajador/form";
    }
    
    @Transactional
    @RequestMapping(value = "/trabajador", method = RequestMethod.POST)
    public String save(Model model, Trabajador trab ) { 
        //System.out.println(fechaAlta);
        TrabajadorHistorico hist;
        if(trab.getIdTrabajador()!= null){//if exist then update
            
            String dbCi = repo.findOne(trab.getIdTrabajador()).getCi();//Ci of worker from DB
            if(!trab.getCi().equals(dbCi)){//if change ci update historic
                repoTrabajadorHistorico.setNumeroCi(trab.getCi(), dbCi);
            }
            
            trab = repo.save(trab);            
            hist = new TrabajadorHistorico(trab);
            Integer anno = Integer.parseInt( new SimpleDateFormat("YYYY").format(hist.getFecha()) );
            Integer mes = Integer.parseInt( new SimpleDateFormat("MM").format(hist.getFecha()) ); 
            
            try {
                repoTrabajadorHistorico.borrarPorIdYFecha(trab.getIdTrabajador(), anno, mes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }            
            
            repoTrabajadorHistorico.save(hist);
            return "redirect:/trabajador";
        }        
        repo.save(trab); 
        hist = new TrabajadorHistorico(trab);
        repoTrabajadorHistorico.save(hist);
        model.addAttribute("message", true);
        model.addAttribute("data", new Trabajador());
        model.addAttribute("entidad", repoEntidad.findAll());
        model.addAttribute("cargo", repoCargo.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        model.addAttribute("especialidad", repoEspecialidad.findAll());
        model.addAttribute("categoriaCientifica", repoCategoriaCientifica.findAll());
        model.addAttribute("nivel", repoNivel.findAll());
        model.addAttribute("categoriaOcupacional", repoCategoriaOcupacional.findAll());
        return "trabajador/form";
    }
    
    @RequestMapping(value = "/trabajador/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort){
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        
        repo.delete(id);
        return "redirect:/cargo?page=" + page + "&sort=" + sort;
    }
}
