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
import web.educacion.model.Ensenanza;
import web.educacion.repository.EnsenanzaRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class EnsenanzaController {

    @Autowired
    EnsenanzaRepo repo;

    @RequestMapping(value = "/ensenanza", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "ensenanza/index";
    }

    @RequestMapping(value = "/ensenanza/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("data", new Ensenanza());
        return "ensenanza/form";
    }

    @RequestMapping(value = "/ensenanza/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("data", repo.findOne(id));
        return "ensenanza/form";
    }

    @RequestMapping(value = "/ensenanza", method = RequestMethod.POST)
    public String save(Model model, Ensenanza obj) {
        try {
            if (obj.getIdEnsenanza() != null) {
                repo.save(obj);                
                return "redirect:/ensenanza";
            }
            repo.save(obj);
            model.addAttribute("notices", "Insertado Correctamente");
            model.addAttribute("data", new Ensenanza());
            return "ensenanza/form";
        } catch (Exception e) {
            model.addAttribute("notices", "Ya existe esa Descripcion");
            model.addAttribute("data", obj);
            return "ensenanza/form";
        }
    }

    @RequestMapping(value = "/ensenanza/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de esta Enseñanza");
        }

        return "redirect:/ensenanza?page=" + page + "&sort=" + sort;
    }
}
