package amm.mbs.servlets;

import amm.mbs.classes.*;
import amm.mbs.factories.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {

    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";

    @Override
    public void init() {
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClienteFactory.getInstance().setConnectionString(dbConnection);
        VenditoreFactory.getInstance().setConnectionString(dbConnection);
        OggettiFactory.getInstance().setConnectionString(dbConnection);

    }

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
        HttpSession session = request.getSession(false);

        if (request.getParameter("login") != null) {

            /* dati dal form */
            String username = request.getParameter("user");
            String password = request.getParameter("pswd");

            /* controllo che esista un Cliente con quei dati e imposto gli attributi usati nella jsp */  
            ClienteC loginC = ClienteFactory.getInstance().getClienteLogin(username, password);
            if (loginC != null) {

                session.setAttribute("loggedIn", true);
                session.setAttribute("cliente", loginC);
                session.setAttribute("saldo", loginC.saldo );
                request.getRequestDispatcher("cliente.html").forward(request, response);
                return;
            }
            
            /* controllo che esista un Venditore con quei dati e imposto gli attributi usati nella jsp */  
            VenditoreC loginV = VenditoreFactory.getInstance().getVenditoreLogin(username, password);
            if (loginV != null) {

                session.setAttribute("loggedIn", true);
                session.setAttribute("venditore", loginV);
                int id = loginV.getId();
                ArrayList<Oggetti> lista = OggettiFactory.getInstance().getOggettiByVenditore(id);
                session.setAttribute("listaV", lista);
                request.getRequestDispatcher("venditore.html").forward(request, response);
                return;
            }

            /* se non esiste un Cliente o un Venditore con i dati inseriti imposto gli attributi che mostreranno l'errore nella jsp */
            request.setAttribute("errore", true);
            request.setAttribute("username", username);

        }
        request.getRequestDispatcher("login.jsp").forward(request, response);

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
