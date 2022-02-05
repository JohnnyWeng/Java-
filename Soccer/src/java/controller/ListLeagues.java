package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import model.*;

//  設定 URL
@WebServlet(name = "ListLeagues", urlPatterns = {"/ListLeagues"})
public class ListLeagues extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
       
        LeagueService leagueSvc = new LeagueService(); 
        
        List<League> leagueList = leagueSvc.getAllLeagues(); // 使用服務元件(LeagueService) 呼叫 "DAO 的 getAllLeagues() 方法" 取得 leagueList
       
       // 頁面跳轉常常也會有參數的傳遞,使用到的方法為 "setAttribute("key",value)"
       context.setAttribute("leagueList", leagueList);// 把 leagueList 設進去 Servlet Context
       context.log("The league list has been loaded."+leagueList);

        RequestDispatcher rd = request.getRequestDispatcher("list_leagues.jsp"); // 使用隱含物件 request 取得 "請求轉送器" 
        
       // 上面的 context.setAttribute("leagueList", leagueList);
        rd.forward(request, response);
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
