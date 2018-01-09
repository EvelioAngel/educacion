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
import web.educacion.model.Provincia;
import web.educacion.repository.ProvinciaRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class ProvinciaController {

    @Autowired
    ProvinciaRepo repo;

    @RequestMapping(value = "/provincia", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "provincia/index";
    }

    @RequestMapping(value = "/provincia/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("data", new Provincia());
        return "provincia/form";
    }

    @RequestMapping(value = "/provincia/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("data", repo.findOne(id));
        return "provincia/form";
    }

    @RequestMapping(value = "/provincia", method = RequestMethod.POST)
    public String save(Model model, Provincia prov) {
        try {
            if (prov.getIdProvincia() != null) {
                repo.save(prov);
                return "redirect:/provincia";
            }

            repo.save(prov);
            model.addAttribute("notices", "Insertado Correctamente");
            model.addAttribute("data", new Provincia());
            return "provincia/form";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("notices", "Ya existe ese Código");
            model.addAttribute("data", prov);
            return "provincia/form";
        }
    }

    @RequestMapping(value = "/provincia/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");

        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de esta Provincia");
        }

        return "redirect:/provincia?page=" + page + "&sort=" + sort;
    }
}
