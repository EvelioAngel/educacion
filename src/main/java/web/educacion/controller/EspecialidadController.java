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
import web.educacion.model.Especialidad;
import web.educacion.repository.EspecialidadRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class EspecialidadController {

    @Autowired
    EspecialidadRepo repo;

    @RequestMapping(value = "/especialidad", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "especialidad/index";
    }

    @RequestMapping(value = "/especialidad/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("data", new Especialidad());
        return "especialidad/form";
    }

    @RequestMapping(value = "/especialidad/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("data", repo.findOne(id));
        return "especialidad/form";
    }

    @RequestMapping(value = "/especialidad", method = RequestMethod.POST)
    public String save(Model model, Especialidad obj) {
        try {
            if (obj.getIdEspecialidad() != null) {
                repo.save(obj);
                return "redirect:/especialidad";
            }
            repo.save(obj);
            model.addAttribute("notices", "Insertado Correctamente");
            model.addAttribute("data", new Especialidad());
            return "especialidad/form";
        } catch (Exception e) {
            model.addAttribute("notices", "Ya existe esa Descripcion");
            model.addAttribute("data", obj);
            return "especialidad/form";
        }
    }

    @RequestMapping(value = "/especialidad/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de esta Especialidad");
        }

        return "redirect:/especialidad?page=" + page + "&sort=" + sort;
    }
}
