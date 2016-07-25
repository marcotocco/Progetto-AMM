package amm.mbs.servlets;

import amm.mbs.classes.Oggetti;
import amm.mbs.classes.ClienteC;
import amm.mbs.factories.*;
import amm.mbs.classes.VenditoreC;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet(name = "ClienteC", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

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
        
        /* 
        * VARIABILI VARIE
        */

        int idCurrObj = -1; 
        int idVenditore;
        int idCliente;     
        ArrayList<Oggetti> lista = OggettiFactory.getInstance().getOggettiList(); 
        Oggetti currObj;    // oggetto in esame
        ClienteC currC;     // cliente in esame
        VenditoreC currV;   // venditore in esame
        Double spesa;

        /**
         *  AGGIUNTA CARRELLO E CREAZIONE PAGINA DI CONFERMA
         */
        while (request.getParameter("objId") != null) {

            /* se l'id non è intero ritorno alla lista (es. malintezionati) */
            try {
                idCurrObj = Integer.parseInt(request.getParameter("objId"));               
            } catch (RuntimeException e) {
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cliente.jsp").forward(request, response);
            }

            currObj = OggettiFactory.getInstance().getOggettiById(idCurrObj); // inizializzo con l'ID selezionato


            /* se l'ID non esiste ritorno sempre alla lista, altrimenti vado alla pagina di conferma */
            if (currObj != null) {
                session.setAttribute("prezzo", currObj.getPrezzo());
                session.setAttribute("currObj", currObj);
                request.setAttribute("obj", session.getAttribute("currObj"));
                request.getRequestDispatcher("conferma.jsp").forward(request, response);
            } else {
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cliente.jsp").forward(request, response);
            }
        }

        
        /** 
         * CONFERMA ACQUISTO         
         **/
        while (request.getParameter("conferma") != null) {

            currC = (ClienteC) session.getAttribute("cliente");
            
            /* solito controllo per confermare che l'id dell'oggetto sia un intero */
            try {
                idCurrObj = Integer.parseInt(request.getParameter("acqId"));
            } catch (RuntimeException e) {
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cliente.jsp").forward(request, response);
                return;
            }

            currObj = OggettiFactory.getInstance().getOggettiById(idCurrObj);

            /* verifico che l'id, che siamo sicuri essere un intero, sia di un oggetto esistente */
            if (currObj == null) {
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cliente.jsp").forward(request, response);
                return;
            }

            idVenditore = currObj.getIdVenditore();
            idCliente = currC.getId();
            currV = (VenditoreC) VenditoreFactory.getInstance().getVenditoreById(idVenditore);
            double prezzo = currObj.getPrezzo();

            /* passo i valori alla funzione ch si occuperà di gestire la transazione e aggiornare il database */
            try {
                if (OggettiFactory.getInstance().compraOggetto(idCurrObj, idCliente, idVenditore)) { 
                    request.setAttribute("finito", true);
                } else {
                    request.setAttribute("finito", false);
                }
            } catch (SQLException e) {
                request.setAttribute("errore", true);
                request.setAttribute("finito", false);
            }
            request.setAttribute("conferma", true);
            lista = OggettiFactory.getInstance().getOggettiList();
            request.setAttribute("lista", lista);
            currC = ClienteFactory.getInstance().getClienteById(idCliente);
            session.setAttribute("saldo", currC.saldo);
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
            return;
        }

        
        /**
         * VISUALIZZAZIONE LISTA STANDARD        
         **/
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("cliente.jsp").forward(request, response);

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
