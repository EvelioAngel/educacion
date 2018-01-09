/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.educacion.model.Municipio;
import web.educacion.repository.MunicipioRepo;
import web.educacion.repository.ProvinciaRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class MunicipioController {

    @Autowired
    MunicipioRepo repo;

    @Autowired
    ProvinciaRepo repoProv;

    @RequestMapping(value = "/municipio", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "municipio/index";
    }

    @RequestMapping(value = "/municipio/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("data", new Municipio());
        model.addAttribute("provincias", repoProv.findAll());
        return "municipio/form";
    }

    @RequestMapping(value = "/municipio/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("data", repo.findOne(id));
        model.addAttribute("provincias", repoProv.findAll());
        return "municipio/form";
    }

    @RequestMapping(value = "/municipio", method = RequestMethod.POST)
    public String save(Model model, Municipio mun) {
        try {
            if (mun.getIdMunicipio() != null) {
                repo.save(mun);
                return "redirect:/municipio";
            }

            repo.save(mun);
            model.addAttribute("notices", "Insertado correctamente");
            model.addAttribute("data", new Municipio());
            return "municipio/form";
        } catch (Exception e) {
            model.addAttribute("notices", "Ya existe ese Codigo");
            model.addAttribute("data", mun);
            return "municipio/form";
        }
    }

    @RequestMapping(value = "/municipio/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");

        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de este Cargo");
        }

        return "redirect:/municipio?page=" + page + "&sort=" + sort;
    }
}
