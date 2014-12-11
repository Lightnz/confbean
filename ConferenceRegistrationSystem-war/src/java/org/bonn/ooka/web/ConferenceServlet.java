/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bonn.ooka.conference.ejb.ConferenceSearchRemote;

/**
 *
 * @author alex
 */
public class ConferenceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private ConferenceSearchRemote conferenceSearch;
    private List<String> conferenceList;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConferenceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConferenceServlet at " + request.getContextPath() + "</h1>");
          
            out.println("<p align='center'>");
              
            for(String conference : conferenceList) {
            out.println(conference + "<br>");
        }
             out.println("</p>");           
                        
            out.println("</body>");
            out.println("</html>");
            
            //probeweise aenderung d. Liste nach jedem Zugriff:
            conferenceSearch.addConference("ADDED CONFERENCE");
            
            conferenceList = conferenceSearch.getConferenceList();
            
            
            
            
            
            //Session Zugriff
            HttpSession mysession = request.getSession();
            //Session beenden: durch timeout oder extern 
            mysession.invalidate();
        /*
            int interval = 30;
            mysession.setMaxInactiveInterval(interval);
            //zeigen: wenn session ausläuft, Problem: beansession immernoch aktiv
            //lösung zeigen: beansession in http session einfügen
              */      
                    
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @PostConstruct
    private void initConferenceEJB(){
        try {
            conferenceSearch = (ConferenceSearchRemote) new InitialContext().lookup(
                    "java:global/ConferenceRegistrationSystem/ConferenceRegistrationSystem-ejb/ConferenceSearch"
                            + "!org.bonn.ooka.conference.ejb.ConferenceSearchRemote"
                    
            );
        } catch (NamingException ex) {
            Logger.getLogger(ConferenceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conferenceList = conferenceSearch.getConferenceList();
    
}
    
    

}
