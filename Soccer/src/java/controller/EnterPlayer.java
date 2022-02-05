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

@WebServlet(name = "EnterPlayer", urlPatterns = {"/EnterPlayer"})

public class EnterPlayer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Keep a set of strings to record form processing errors.
        List errorMsgs = new LinkedList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        try {
            // Retrieve form parameters.
            String name = request.getParameter("name").trim();
            String address = request.getParameter("address").trim();
            String city = request.getParameter("city").trim();
            String province = request.getParameter("province").trim();
            String postalCode = request.getParameter("postalCode").trim();
            
            // Verify 'Enter Player Information' form fields
            if ( name.length() == 0 ) {
                errorMsgs.add("You must enter your full name.");
            }
            if ( (address.length() == 0)  || (city.length() == 0)
            || (province.length() == 0) || (postalCode.length() == 0) ) {
                errorMsgs.add("You must enter your full address.");
            }
            if ( ! errorMsgs.isEmpty() ) {
                RequestDispatcher view  = request.getRequestDispatcher("enter_player.jsp");
                view.forward(request, response);
                return;
            }
            // Perform business logic            
            // get the registration service object in the session-scope
            Player player = new Player(-1, name, address, city, province, postalCode);
            HttpSession session = request.getSession();
            session.setAttribute("player", player);

            // Send the Success view
            RequestDispatcher view = request.getRequestDispatcher("select_league.jsp");
            view.forward(request, response);
            
        } catch (RuntimeException e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("enter_player.jsp");
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
