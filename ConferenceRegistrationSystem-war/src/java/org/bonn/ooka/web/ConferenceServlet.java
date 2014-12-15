/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.ooka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import static javax.print.PrintServiceLookup.registerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bonn.ooka.conference.dtos.Konferenz;
import org.bonn.ooka.conference.ejb.ConferenceRegisterStatefulEJBLocal;


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
    
        
    
    
    private List<Konferenz> conferenceList;
   
    
    
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
          
              
            
            //Session Zugriff
            HttpSession mysession = request.getSession();
                       
        
            if (request.getParameter("Invalidate")!=null){
                //Session beenden: durch timeout oder extern 
                mysession.invalidate();
                out.println("Ihre Session wurde beendet.<br/>");
            } else {
            
                
                out.println("Session ID: " + mysession.getId()+ "<br/>");
                out.println("EJB (pre-lookup): " + mysession.getAttribute("ejb")+ "<br/>");

                if(mysession.getAttribute("ejb")!=null && request.getParameter("Login")!=null){
                    out.println("Es war noch ein alter Login aktiv. Diese Session wird nun beendet.<br/>Bitte melden Sie sich neu an!");
                    mysession.invalidate();
                } else {
                
                    if(mysession.getAttribute("ejb")==null && request.getParameter("Login")!=null){
                        out.println("EJB was empty, will lookup and create now!");
                        ConferenceRegisterStatefulEJBLocal registerService = null;
                        try {
                            registerService = (ConferenceRegisterStatefulEJBLocal) new InitialContext().lookup(
                                    "java:global/ConferenceRegistrationSystem/ConferenceRegistrationSystem-ejb/ConferenceRegisterStatefulEJB"
                                            + "!org.bonn.ooka.conference.ejb.ConferenceRegisterStatefulEJBLocal"

                            );
                        } catch (NamingException ex) {
                            Logger.getLogger(ConferenceServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        mysession.setAttribute("ejb", registerService);

                        if(!(request.getParameter("Login").equals("Skip Login"))){
                            int userID = 99999;
                            if (request.getParameter("Login").equals("Login as User 0")) userID = 0;
                            if (request.getParameter("Login").equals("Login as User 1")) userID = 1;
                            registerService.login(userID);
                            out.println("<br/>***Successfully logged in as User "+ userID + ": "+ registerService.getUser().getName() + " ***<br/><br/>");
                        }
                    }


                    ConferenceRegisterStatefulEJBLocal registerService;
                    registerService = (ConferenceRegisterStatefulEJBLocal) mysession.getAttribute("ejb");


                    int interval = 30;

                    mysession.setMaxInactiveInterval(interval);
                    out.println("Session Time Out is set to " + interval + "s !<br/><br/>");
                    //zeigen: wenn session ausläuft, Problem: beansession immernoch aktiv
                    //lösung zeigen: beansession in http session einfügen

                    if (registerService != null){
                        out.println("Willkommen " + registerService.getUser().getName());
                        out.println("<br/>Sie sind zu folgenden Konferenzen angemeldet:<br/><br/>");


                        conferenceList = registerService.getAngemeldeteKonferenzen();
                        for(Konferenz conference : conferenceList) {
                            out.println(conference.getTitel() + "<br>");
                        }
                    }

                    //Invalidate Button:
                    out.println("<br/><br/>");
                    out.println("<form action=\"ConferenceServlet\" method=\"GET\">");
                    out.println("<p>");
                    out.println(" <input type=\"submit\" name=\"Invalidate\" value=\"Invalidate Session\"/>");
                    out.println("</p>");
                    out.println("</form>");
                }
            }
            
           
            //Zurück zur Startseite Button
            out.println("<br/><br/>");
            out.println("<form action=\"servletTest.jsp\" method=\"GET\">");
            out.println("<p>");
            out.println(" <input type=\"submit\" value=\"Zurück zu Startseite\"/>");
            out.println("</p>");
            out.println("</form>");
            
            
            
               
            
            out.println("</body>");
            out.println("</html>");
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
    
    
   
    
    
    

}
