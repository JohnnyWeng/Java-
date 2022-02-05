package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import model.*;

@WebServlet(name = "SelectLeague", urlPatterns = {"/SelectLeague"})
public class SelectLeague extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Keep a set of strings to record form processing errors.
        List errorMsgs = new LinkedList();
        // Store this set in the request scope, in case we need to send the ErrorPage.
        request.setAttribute("errorMsgs", errorMsgs);
        try {
            // Retrieve form parameters.
            String yearStr = request.getParameter("year").trim();
            String season = request.getParameter("season").trim();
            // Perform data conversions.
            int year = -1;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException nfe) {
                errorMsgs.add("The 'year' field must be a positive integer.");
            }
            // Verify form parameters
            if ( (year != -1) && ((year < 2010) || (year > 2025)) ) {
                errorMsgs.add("The 'year' field must within 2010 to 2025.");
            }
            if ( season.equals("UNKNOWN") ) {
                errorMsgs.add("Please select a league season.");
            }            
            RegistrationService registration = new RegistrationService();            
            League league = null;
            try {
                league =  registration.getLeague(year, season);                
            }  catch(ObjectNotFoundException oe){
                errorMsgs.add("Please enter valid league.");
            }
            // Send the ErrorPage view if there were errors
            if ( ! errorMsgs.isEmpty() ) {
                RequestDispatcher view  = request.getRequestDispatcher("select_league.jsp");
                view.forward(request, response);
                return;
            }
            // Perform business logic 
            // Get the registration service from session-scope attribute
            HttpSession session = request.getSession();
            session.setAttribute("league", league);
            Player player = (Player)session.getAttribute("player");
            registration.setPlayer(player);
            registration.register(league, player);
            // Send the Success page
            RequestDispatcher view = request.getRequestDispatcher("thank_you.jsp");
            view.forward(request, response);
            
        } catch (RuntimeException e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("select_league.jsp");
            view.forward(request, response);
            // Log stack trace
            this.log(e.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
