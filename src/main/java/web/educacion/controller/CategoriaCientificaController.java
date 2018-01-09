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
import web.educacion.model.CategoriaCientifica;
import web.educacion.repository.CategoriaCientificaRepo;

/**
 *
 * @author Evelio
 */
@Controller
public class CategoriaCientificaController {

    @Autowired
    CategoriaCientificaRepo repo;

    @RequestMapping(value = "/categoriacientifica", method = RequestMethod.GET)
    public String index(Model model, @SortDefault("descripcion") Pageable pageable) {

        model.addAttribute("page", repo.findAll(pageable));
        return "categoriacientifica/index";
    }

    @RequestMapping(value = "/categoriacientifica/new", method = RequestMethod.GET)
    public String nuevo(Model model) {

        model.addAttribute("data", new CategoriaCientifica());
        return "categoriacientifica/form";
    }

    @RequestMapping(value = "/categoriacientifica/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable Integer id) {

        model.addAttribute("data", repo.findOne(id));
        return "categoriacientifica/form";
    }

    @RequestMapping(value = "/categoriacientifica", method = RequestMethod.POST)
    public String save(Model model, CategoriaCientifica categoria, @SortDefault("descripcion") Pageable pageable) {
        try {
            if (categoria.getIdCategoriaCientifica() != null) {
                repo.save(categoria);
                model.addAttribute("page", repo.findAll(pageable));
                return "categoriacientifica/index";
            }
            repo.save(categoria);
            model.addAttribute("notices", "Insertado correctamente");
            model.addAttribute("data", new CategoriaCientifica());
            return "categoriacientifica/form";
        } catch (Exception e) {
            model.addAttribute("notices", "Ya existe esa Descripcion");
            CategoriaCientifica c = new CategoriaCientifica();
            c.setDescripcion(categoria.getDescripcion());
            model.addAttribute("data", c);
            return "categoriacientifica/form";
        }
    }

    @RequestMapping(value = "/categoriacientifica/{id}/{page}/{sort}", method = RequestMethod.DELETE)
    public String delete(final RedirectAttributes redAtrib, @PathVariable Integer id, @PathVariable Integer page, @PathVariable String sort) {
        sort = sort.replace(" ", "");
        sort = sort.replace(":", ",");
        try {
            repo.delete(id);
        } catch (Exception e) {
            redAtrib.addFlashAttribute("notices", "Hay datos que dependen de esta Categoria");
        }
        return "redirect:/categoriacientifica?page=" + page + "&sort=" + sort;
    }
}
