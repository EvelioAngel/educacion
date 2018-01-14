/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Evelio
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
                
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
                
        return "login";
    }
    
    @RequestMapping(value = "/acerca", method = RequestMethod.GET)
    public String acerca(Model model) {
                
        return "acerca";
    }
}
