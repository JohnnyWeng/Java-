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

@WebServlet(name = "AddLeague", urlPatterns = {"/admin/AddLeague"}) // Annotation 標註描述作部屬描述, urlPatterns 在 admin 底下 AddLeague
public class AddLeague extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Keep a set of strings to record form processing errors.
        // 因為錯誤可能不只有一個,所以我建立一個泛型為 String 的 LinkedList
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to send the ErrorPage.
        request.setAttribute("errorMsgs", errorMsgs);
        try {
            // Retrieve form parameters and Verify form parameters. 也就是取得使用者輸入的東西(request.getParameter)
            // 使用內建的隱含變數 request 取得請求參數
            String yearStr = request.getParameter("year").trim();
            String season = request.getParameter("season").trim();
            String title = request.getParameter("title").trim();
            // Perform data conversions.
            int year = -1;
            try {
                year = Integer.parseInt(yearStr);
            } catch (NumberFormatException nfe) { // 假如使用者輸入的是 abc..., 不是正整數就會被捕捉到 
                // 把 Message 放到 errorMsgs
                errorMsgs.add("The 'year' field must be a positive integer.");
            }
            // Verify form parameters: 設定年份的條件,例如:年份必須是 2010 到 2025 之間,否則會顯示下面錯誤訊息
            if ( (year != -1) && ((year < 2010) || (year > 2025)) ) {
                errorMsgs.add("The 'year' field must within 2010 to 2025.");
            }
            if ( season.equals("UNKNOWN") ) { // 如果季節沒輸入則會出現下面錯誤訊息
                errorMsgs.add("Please select a league season.");
            }
            if ( title.length() == 0 ) {
                errorMsgs.add("Please enter the title of the league.");
            }
            // Send the ErrorPage view if there were errors
            // 如果有錯誤(errorMsgs 不是空的), 傳回 add_league.jsp
            if ( ! errorMsgs.isEmpty() ) {
                RequestDispatcher view
                        = request.getRequestDispatcher("/admin/add_league.jsp");
                view.forward(request, response);
                return;
            }
             // Perform business logic
            LeagueService leagueSvc = new LeagueService();
            // 呼叫 LeagueService 的 createLeague 方法
            League league = leagueSvc.createLeague(year, season, title);
            
            // Store the new league in the leagueList context-scope attribute
            ServletContext context = getServletContext(); 
            // 從 context 取得 leagueList, 必須做強制轉型。 這裡是假設已經有了 leagueList, "如果一開始沒有的話, 要在下面加入 context。"
            List leagueList = (List) context.getAttribute("leagueList"); 
            if(leagueList==null) {
                leagueList = new LinkedList(); 
                context.setAttribute("leagueList", leagueList);
            }
            leagueList.add(league); // 把 league 放進 league 這個 LinkedList() 
            // Store the new league in the "request-scope"
            request.setAttribute("league", league); 

            // Send the Success view
            RequestDispatcher view = request.getRequestDispatcher("/admin/success.jsp");
            view.forward(request, response);
            
        } catch (RuntimeException e) {
            errorMsgs.add(e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("/admin/add_league.jsp");
            view.forward(request, response);
            // Log stack trace (把 Message 放到 errorMsgs )
            this.log(e.toString());
        } // END of try-catch block
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
