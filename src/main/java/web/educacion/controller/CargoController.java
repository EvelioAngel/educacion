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
import web.educacion.model.Cargo;
import web.educacion.repository.CargoRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class CargoController {

    @Autowired
    CargoRepo repo;

    @RequestMapping(value = "/cargo", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {
        model.addAttribute("page", repo.findAll(pageable));
        return "cargo/index";
    }

    @RequestMapping(value = "/cargo/new", method = RequestMethod.GET)
    public String nuevo(Model model) {
        model.addAttribute("cargo", new Cargo());
        return "cargo/form";
    }

    @RequestMapping(value = "/cargo/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("cargo", repo.findOne(id));
        return "cargo/form";
    }

    @RequestMapping(value = "/cargo", method = RequestMethod.POST)
    public String save(Model model, Cargo cargo) {        
        try {
            if (cargo.getIdCargo() != null) {
                repo.save(cargo);
                return "redirect:/provincia";
            }
            repo.save(cargo);
            model.addAttribute("notices", "Insertado Correctamente");
            model.addAttribute("cargo", new Cargo());
            return "cargo/form";
        } 
        catch (Exception e) {
            model.addAttribute("notices", "Ya existe esa Descripcion");
            Cargo c = new Cargo();
            c.setDescripcion(cargo.getDescripcion());
            model.addAttribute("cargo", c);
            return "cargo/form";
        }
    }

    @RequestMapping(value = "/cargo/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de este Cargo");
        }

        return "redirect:/cargo?page=" + page + "&sort=" + sort;
    }
}
