/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import web.educacion.model.Usuarios;
import web.educacion.repository.MunicipioRepo;
import web.educacion.repository.UsuariosRepo;
import web.educacion.util;

/**
 *
 * @author Evelio
 */
@Controller
public class UsuariosController {
    
    @Autowired
    UsuariosRepo usuariosRepo;
    
    @Autowired
    MunicipioRepo repoMunicipio;
    
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String index(Model model,@SortDefault("username") Pageable pageable) {
        
        model.addAttribute("page", usuariosRepo.findAll(pageable));
        return "usuarios/index";
    }
    
    @RequestMapping(value = "/usuarios/nuevo", method = RequestMethod.GET)
    public String nuevo(Model model) {        
        model.addAttribute("municipio", repoMunicipio.findAll());
        return "usuarios/form";
    }
    
    @RequestMapping(value = "/usuarios/{id}/edit", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable String id) {
        model.addAttribute("municipio", repoMunicipio.findAll());
        model.addAttribute("user", usuariosRepo.findOne(id));
        return "usuarios/edit";
    }
    
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public String save(Model model,Usuarios user) {
        
        user.setActivo(true);
        try {
            user.setPassword(util.md5(user.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        usuariosRepo.save(user);
        model.addAttribute("message", "true");
        return "usuarios/form";
    }
    
    @RequestMapping(value = "/usuarios/edit", method = RequestMethod.POST)
    public String edit(Model model,Usuarios user) {
        
        if(user.getActivo()==null)
            user.setActivo(false);
        
        try {
            user.setPassword(util.md5(user.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuariosRepo.save(user); 
                
        
        return "redirect:/usuarios";
    }
    
    @RequestMapping(value = "/usuarios/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(String id) {
        usuariosRepo.delete(id);
    }
    
}
