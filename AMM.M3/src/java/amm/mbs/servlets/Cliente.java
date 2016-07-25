package amm.mbs.servlets;

import amm.mbs.classes.Oggetti;
import amm.mbs.classes.ClienteC;
import amm.mbs.factories.OggettiFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        int idCurrObj = -1; // Variabile per l'oggetto selezionato
        ArrayList<Oggetti> lista = OggettiFactory.getInstance().getOggettiList(); // Genero la lista degli oggetti
        Oggetti currObj;
        Double spesa;


            /**
             * crea la pagina conferma dopo aver cliccato sul carrello. *
             */
            if (request.getParameter("objId") != null) {

                /* controllo che l'id sia un intero */
                try {
                    idCurrObj = Integer.parseInt(request.getParameter("objId"));
                } catch (RuntimeException e) {
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }

                currObj = OggettiFactory.getInstance().getOggettiById(idCurrObj); // inizializzo con l'ID selezionato
                session.setAttribute("prezzo", currObj.getPrezzo());

                /* controllo che sia un ID esistente, in caso negativo do errore e ritorno indietro, 
                altrimenti proseguo alla pagina di conferma
                 */
                if (currObj != null) {
                    // Imposto gli attributi necessari per la jsp del riepilogo
                    session.setAttribute("currObj", currObj);
                    request.setAttribute("obj", session.getAttribute("currObj"));
                    request.getRequestDispatcher("conferma.jsp").forward(request, response);
                } else {
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }
            }
            
                    /* Processa il pagamento una volta confermato */
        if (request.getParameter("conferma") != null) {
            ClienteC currC = (ClienteC) session.getAttribute("cliente");
            Double saldo = currC.getSaldo();
            Double objValue = (Double) session.getAttribute("prezzo");
            if (saldo >= objValue) {
                spesa = objValue;
                session.setAttribute("spesa", objValue);
                request.setAttribute("terminato", true);
            } else { 
                request.setAttribute("terminato", false);
            }
            //request.getRequestDispatcher("conferma.jsp").forward(request, response);
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
            return;
        }

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo

        
            () {
        return "Short description";
        }// </editor-fold>

    
    }
