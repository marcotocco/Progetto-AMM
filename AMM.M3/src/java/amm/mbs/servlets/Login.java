package amm.mbs.servlets;

import amm.mbs.classes.ClienteC;
import amm.mbs.factories.ClienteFactory;
import amm.mbs.classes.VenditoreC;
import amm.mbs.factories.VenditoreFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true); 

        if (request.getParameter("login") != null) {

            /* dati dal form */
            String username = request.getParameter("user");
            String password = request.getParameter("pswd");

            ArrayList<ClienteC> listaClienti = ClienteFactory.getInstance().getClientiList(); //carica lista

            if (listaClienti != null) { //controlla che la lista non sia vuota
                for (ClienteC cliente : listaClienti) {
                    if (cliente.getUser().equals(username) && cliente.getPass().equals(password)) {
                        session.setAttribute("loggedIn", true);
                        session.setAttribute("cliente", cliente);
                        request.getRequestDispatcher("cliente.html").forward(request, response);
                        return;
                    }
                }
            }

            ArrayList<VenditoreC> listaVenditori = VenditoreFactory.getInstance().getVenditoriList(); //carica lista

            if (listaVenditori != null) { //controlla che la lista non sia vuota
                for (VenditoreC venditore : listaVenditori) {
                    if (venditore.getUser().equals(username) && venditore.getPass().equals(password)) {
                        session.setAttribute("loggedIn", true);
                        session.setAttribute("venditore", venditore);
                        request.getRequestDispatcher("venditore.html").forward(request, response);
                        return;
                    }
                }
            }
            request.setAttribute("errore", true);
            request.setAttribute("username", username);
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
        request.getRequestDispatcher("comuni/sidebar.jsp").forward(request, response);

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
