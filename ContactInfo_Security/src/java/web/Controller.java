package web;

import domain.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="Controller", urlPatterns={"/SelectCustomer"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                                                throws ServletException, IOException {
        //   先取得 Session
        HttpSession session = request.getSession();
        
        // 第一次應該是沒有 user 的,所以才要後面去創建,設定 user
        // 看看有沒有 "user", 如果拿到 null, 也就是沒有 user
        if(session.getAttribute("user")==null){
            
            // 我就用 request 的 getUserPrincipal 去 getName, 型態是:String
            // getUserPrincipal:  Returns a java.security.Principal object containing the name of the current authenticated user.
            String user = request.getUserPrincipal().getName();
            
            // 加到 session 用 "user" 這個 key
            session.setAttribute("user", user);
        }
        // 取得使用者在 index.jsp 輸入的的編號(custid)
        int custid = Integer.parseInt(request.getParameter("custid"));
        RequestDispatcher rd = null;
        // 如果 custid 是 0, 則傳送到 "customerList.jsp
        if(custid==0){
            request.setAttribute("customers", Customer.getCustomers());
            rd = request.getRequestDispatcher("customerList.jsp");
        } else {
        // 如果 custid 是 1 或 2, 在 Customer.java 呼叫 getCustomer()方法
            request.setAttribute("customer", Customer.getCustomer(custid));
            rd = request.getRequestDispatcher("customerView.jsp");
        }
        rd.forward(request, response);
    } 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}


