/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.educacion.model.Municipio;
import web.educacion.repository.CargoRepo;
import web.educacion.repository.EnsenanzaRepo;
import web.educacion.repository.EntidadRepo;
import web.educacion.repository.EspecialidadRepo;
import web.educacion.repository.MunicipioRepo;
import web.educacion.util;

/**
 *
 * @author Evelio
 */
@Controller
public class ReportesController {

    @Autowired
    private JdbcTemplate jdbctemplate;
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
        Connection con = jdbctemplate.getDataSource().getConnection();
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
        Connection con = jdbctemplate.getDataSource().getConnection();
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
        Connection con = jdbctemplate.getDataSource().getConnection();
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
        Connection con = jdbctemplate.getDataSource().getConnection();
        params.put("municipio", municipio);
        params.put("entidad",entidad);
        params.put("ensenanza",ensenanza);
        
        util.exportar(params, response, con, url, name);
    }
    
}
