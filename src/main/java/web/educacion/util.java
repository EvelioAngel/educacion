/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.educacion;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import web.educacion.controller.ReportesController;

/**
 *
 * @author Evelio
 */
public class util {
    
    static public String md5(String cadena) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(cadena.getBytes());
        BigInteger number = new BigInteger(1,messageDigest);
        String hashtext = number.toString(16);
        
        while(hashtext.length() < 32){
            hashtext = "0"+hashtext;
        }
        
        return hashtext;
    }
    
    static public void exportar(HashMap<String, Object> params, HttpServletResponse response, Connection con, 
            String url, String name) throws SQLException{
        try {
            JasperReport report;
            Resource resource = new ClassPathResource(url);

            report = (JasperReport) JRLoader.loadObject(resource.getInputStream());

            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String fechaActual = hourdateFormat.format(date);

            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, con);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename="+ name + fechaActual + ".pdf");

            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
