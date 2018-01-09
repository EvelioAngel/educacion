/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.educacion.model.Entidad;
import web.educacion.repository.EntidadRepo;
import web.educacion.repository.MunicipioRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class EntidadController {

    @Autowired
    EntidadRepo repo;

    @Autowired
    MunicipioRepo repoMunicipio;

    @RequestMapping(value = "/entidad", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "entidad/index";
    }

    @RequestMapping(value = "/entidad/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("data", new Entidad());
        model.addAttribute("municipio", repoMunicipio.findAll());
        model.addAttribute("entidad", repo.findAll());
        return "entidad/form";
    }

    @RequestMapping(value = "/entidad/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("data", repo.findOne(id));
        model.addAttribute("municipio", repoMunicipio.findAll());
        model.addAttribute("entidad", repo.findAll());
        return "entidad/form";
    }

    @RequestMapping(value = "/entidad", method = RequestMethod.POST)
    public String save(Model model, Entidad ent) {
        if (!ent.getSubordinada()) {
            ent.setEntidadSubordina(null);
        }

        try {
            if (ent.getIdEntidad() != null) {
                repo.save(ent);
                return "redirect:/entidad";
            }

            repo.save(ent);
            model.addAttribute("notices", "Insertado Correctamente");
            model.addAttribute("data", new Entidad());
            model.addAttribute("municipio", repoMunicipio.findAll());
            model.addAttribute("entidad", repo.findAll());
            return "entidad/form";
        } 
        catch (Exception e) {
            model.addAttribute("notices", "Ya existe ese Código");
            model.addAttribute("data", ent);
            model.addAttribute("municipio", repoMunicipio.findAll());
            model.addAttribute("entidad", repo.findAll());
            return "entidad/form";
        }
    }

    @RequestMapping(value = "/entidad/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort, final RedirectAttributes redAtrib) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de la entidad");
        }

        return "redirect:/entidad?page=" + page + "&sort=" + sort;
    }
}
