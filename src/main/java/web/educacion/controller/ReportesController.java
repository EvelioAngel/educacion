/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.educacion.repository.CargoRepo;
import web.educacion.repository.CategoriaCientificaRepo;
import web.educacion.repository.CategoriaOcupacionalRepo;
import web.educacion.repository.EnsenanzaRepo;
import web.educacion.repository.EntidadRepo;
import web.educacion.repository.EspecialidadRepo;
import web.educacion.repository.MunicipioRepo;
import web.educacion.repository.NivelPreparacionRepo;
import web.educacion.util;

/**
 *
 * @author Evelio
 */
@Controller
public class ReportesController {

    @Autowired
    DataSource datasource;
    @Autowired
    MunicipioRepo repoMun;
    @Autowired
    EntidadRepo repoEntidad;
    @Autowired
    EnsenanzaRepo repoEnsenanza;
    @Autowired
    CargoRepo repoCargo;
    @Autowired
    EspecialidadRepo repoEspecialidad;
    @Autowired
    CategoriaCientificaRepo repoCategoriaCientifica;
    @Autowired
    NivelPreparacionRepo repoNivelPreparacion;
    @Autowired
    CategoriaOcupacionalRepo repoCategoriaOcupacional;
//////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_mun_ent", method = RequestMethod.GET)
    public String viewTrabMunEnt(Model model) {
        //Iterable<Municipio>
        model.addAttribute("municipios", repoMun.findAll());
        //model.addAttribute("entidades", repoEntidad.findAll());
        return "reportes/trabajadores_mun";
    }

    @RequestMapping(value = "/reportes/option/trab_mun_ent")
    public String optionMunicipios(@RequestParam("municipio") Integer municipio, Model model) {        
        model.addAttribute("entidades", repoEntidad.findByIdMunicipioIdMunicipio(municipio));
        return "reportes/trabajadores_mun :: entidades";
    }

    @RequestMapping(value = "/reportes/trab_mun_ent", method = RequestMethod.GET)
    public @ResponseBody
    void trabMunEnt(Integer municipio, Integer entidad, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trabajadores_mun.jasper";
        String name = "TrabajadorMunicipioEntidad-";
        Connection con = datasource.getConnection();
        HashMap<String, Object> params = new HashMap<>();        
        params.put("municipio", municipio);
        params.put("entidad", entidad);

        util.exportar(params, response, con, url, name);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/cant_trab_mun", method = RequestMethod.GET)
    public String viewCantTrabMun(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        
        return "reportes/trab_mun_ent";
    }
    
    @RequestMapping(value = "/reportes/cant_trab_mun", method = RequestMethod.GET)
    public @ResponseBody
    void cantTrabMun(Integer municipio, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_mun_ent.jasper";
        String name = "CantidadTrabajadoresMun-";        
        Connection con = datasource.getConnection();
        HashMap<String, Object> params = new HashMap<>();
        params.put("municipio", municipio);

        util.exportar(params, response, con, url, name);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value = "/reportes/view/cant_trab_ens", method = RequestMethod.GET)
    public String viewCantTrabEns(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        model.addAttribute("cargo", repoCargo.findAll());
        model.addAttribute("especialidad", repoEspecialidad.findAll());
        
        return "reportes/trab_mun_otros";
    }
    
    @RequestMapping(value = "/reportes/cant_trab_otros", method = RequestMethod.GET)
    public @ResponseBody
    void cantTrabOtros(Integer municipio, Integer ensenanza, Integer cargo , Integer especialidad, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_mun_otros.jasper";
        String name = "CantidadTrabajadoresOtros-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();
        params.put("municipio", municipio);
        params.put("ensenanza",ensenanza);
        params.put("SUBREPORT_DIR","templates/reportes/");
        params.put("cargo",cargo);
        params.put("especialidad",especialidad);
        
        util.exportar(params, response, con, url, name);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_mun_ent_ens", method = RequestMethod.GET)
    public String viewTrabMunEntEns(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        return "reportes/trab_mun_ent_ens";
    }
    
    @RequestMapping(value = "/reportes/trab_mun_ent_ens", method = RequestMethod.GET)
    public @ResponseBody
    void trabMunEntEns(Integer municipio, Integer entidad ,Integer ensenanza, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_mun_ent_ens.jasper";
        String name = "trab_mun_ent_ens-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();
        params.put("municipio", municipio);
        params.put("entidad",entidad);
        params.put("ensenanza",ensenanza);
        
        util.exportar(params, response, con, url, name);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_mun_ent_cargo", method = RequestMethod.GET)
    public String viewTrabMunEntCargo(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("cargo", repoCargo.findAll());
        return "reportes/trab_mun_ent_cargo";
    }
    
    @RequestMapping(value = "/reportes/trab_mun_ent_cargo", method = RequestMethod.GET)
    public @ResponseBody
    void trabMunEntCargo(Integer municipio, Integer entidad ,Integer cargo, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_mun_ent_cargo.jasper";
        String name = "trab_mun_ent_cargo-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();
        params.put("municipio", municipio);
        params.put("entidad",entidad);
        params.put("cargo",cargo);
        
        util.exportar(params, response, con, url, name);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value = "/reportes/view/trab_mun_ent_esp", method = RequestMethod.GET)
    public String viewTrabMunEntEsp(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("especialidad", repoEspecialidad.findAll());
        return "reportes/trab_mun_ent_esp";
    }
    
    @RequestMapping(value = "/reportes/trab_mun_ent_esp", method = RequestMethod.GET)
    public @ResponseBody
    void trabMunEntEsp(Integer municipio, Integer entidad ,Integer especialidad, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_mun_ent_esp.jasper";
        String name = "trab_mun_ent_esp-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();
        params.put("municipio", municipio);
        params.put("entidad",entidad);
        params.put("especialidad",especialidad);
        
        util.exportar(params, response, con, url, name);
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(value = "/reportes/view/trab_docencia", method = RequestMethod.GET)
    public String viewTrabDocencia(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("ensenanza", repoEnsenanza.findAll());
        return "reportes/trab_docencia";
    }
    
    @RequestMapping(value = "/reportes/trab_docencia", method = RequestMethod.GET)
    public @ResponseBody
    void trabDocencia(Integer municipio, Integer ensenanza, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_docencia.jasper";
        String name = "trab_docencia-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();
        params.put("ensenanza",ensenanza);
        params.put("municipio", municipio);        
        
        util.exportar(params, response, con, url, name);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_categoria_cientifica", method = RequestMethod.GET)
    public String viewTrabCatCient(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("categoriaCientifica", repoCategoriaCientifica.findAll());
        return "reportes/trab_categoria_cientifica";
    }
    
    @RequestMapping(value = "/reportes/trab_categoria_cientifica", method = RequestMethod.GET)
    public @ResponseBody
    void trabCatCient(Integer municipio, Integer entidad, Integer categoriaCientifica, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_categoria_cientifica.jasper";
        String name = "trab_categoria_cientifica-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();        
        params.put("municipio", municipio); 
        params.put("entidad",entidad);
        params.put("categoria_cientifica", categoriaCientifica);
        
        util.exportar(params, response, con, url, name);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_nivel_preparacion", method = RequestMethod.GET)
    public String viewTrabNivelPrep(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("nivelPreparacion", repoNivelPreparacion.findAll());
        return "reportes/trab_nivel_preparacion";
    }
    
    @RequestMapping(value = "/reportes/trab_nivel_preparacion", method = RequestMethod.GET)
    public @ResponseBody
    void trabNivelPrep(Integer municipio, Integer entidad, Integer nivelPreparacion, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_nivel_preparacion.jasper";
        String name = "trab_nivel_preparacion-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();        
        params.put("municipio", municipio); 
        params.put("entidad",entidad);
        params.put("nivel_preparacion", nivelPreparacion);
        
        util.exportar(params, response, con, url, name);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/reportes/view/trab_categoria_ocupacional", method = RequestMethod.GET)
    public String viewTrabCatOcup(Model model) {        
        model.addAttribute("municipios", repoMun.findAll());
        model.addAttribute("categoriaOcupacional", repoCategoriaOcupacional.findAll());
        return "reportes/trab_categoria_ocupacional";
    }
    
    @RequestMapping(value = "/reportes/trab_categoria_ocupacional", method = RequestMethod.GET)
    public @ResponseBody
    void trabCatOcup(Integer municipio, Integer entidad, Integer categoriaOcupacional, HttpServletResponse response) throws SQLException {
        String url = "templates/reportes/trab_categoria_ocupacional.jasper";
        String name = "trab_categoria_ocupacional-";
        HashMap<String, Object> params = new HashMap<>();
        Connection con = datasource.getConnection();        
        params.put("municipio", municipio); 
        params.put("entidad",entidad);
        params.put("categoria_ocupacional", categoriaOcupacional);
        
        util.exportar(params, response, con, url, name);
    }
}
