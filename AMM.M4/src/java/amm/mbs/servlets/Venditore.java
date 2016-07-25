package amm.mbs.servlets;

import amm.mbs.classes.*;
import amm.mbs.factories.OggettiFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
        
        while (request.getParameter("modifica")!= null){
            int objId = -1;
            try {
                objId = Integer.parseInt(request.getParameter("idoggetto"));
            } catch (RuntimeException e) {
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
                return;
            }
            
            
        }

        while (request.getParameter("elimina") != null) {
            int objId = -1;
            try {
                objId = Integer.parseInt(request.getParameter("idoggetto"));
            } catch (RuntimeException e) {
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
                return;
            }
            Oggetti currObj = OggettiFactory.getInstance().getOggettiById(objId);
            /* verifico che l'id, che siamo sicuri essere un intero, sia di un oggetto esistente */
            if (currObj != null) {
                OggettiFactory.getInstance().rimuoviOggetto(objId);
                request.setAttribute("confermaElimina", true);
                int id = ((VenditoreC) session.getAttribute("venditore")).getId();
                ArrayList<Oggetti> lista = OggettiFactory.getInstance().getOggettiByVenditore(id);
                session.setAttribute("listaV", lista);
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
            } else {
                request.setAttribute("erroreAggiunta", true);
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
            }
        }

        if (request.getParameter("aggiungi") != null) {
            boolean nomeCheck;
            boolean immagineCheck;
            boolean descrizioneCheck;
            boolean quantitaCheck = true;
            boolean prezzoCheck = true;

            Oggetti obj = new Oggetti();

            int quantita = 0;
            double prezzo = 0.0;

            /**
             * CONTROLLI SULL'INSERIMENTO NEL FORM *
             */
            if (request.getParameter("NomeOggetto") == null) { // nel caso l'indirizzo venga modificato e non vengano passati detterminati parametri
                nomeCheck = false;
            } else if (!(request.getParameter("NomeOggetto").equals("")) && request.getParameter("NomeOggetto") != null) { // se viene inserito qualcosa
                obj.setNome(request.getParameter("NomeOggetto"));
                nomeCheck = true;
            } else { // se non viene inserito nulla
                nomeCheck = false;
            }

            if (request.getParameter("DescrizioneOggetto") == null) {
                descrizioneCheck = false;
            } else if (!(request.getParameter("DescrizioneOggetto").equals("")) && request.getParameter("DescrizioneOggetto") != null) {
                obj.setDescrizione(request.getParameter("DescrizioneOggetto"));
                descrizioneCheck = true;
            } else {
                descrizioneCheck = false;
            }

            if (request.getParameter("ImmagineOggetto") == null) {
                immagineCheck = false;
            } else if (!(request.getParameter("ImmagineOggetto").equals("")) && request.getParameter("ImmagineOggetto") != null) {
                obj.setImmagine(request.getParameter("ImmagineOggetto"));
                immagineCheck = true;
            } else {
                immagineCheck = false;
            }

            if (request.getParameter("QuantitaOggetto") == null) {
                quantitaCheck = false;
            } else {
                try {
                    quantita = Integer.parseInt(request.getParameter("QuantitaOggetto"));
                } catch (RuntimeException e) {
                    quantitaCheck = false;
                }
            }

            if (request.getParameter("PrezzoOggetto") == null) {
                prezzoCheck = false;
            } else {
                try {
                    prezzo = Double.parseDouble(request.getParameter("PrezzoOggetto"));
                } catch (RuntimeException e) {
                    prezzoCheck = false;
                }
            }

            if (quantita > 0) {
                obj.setQuantita(quantita);
            } else {
                quantitaCheck = false;
            }

            if (prezzo > 0) {
                obj.setPrezzo(prezzo);
            } else {
                prezzoCheck = false;
            }

            obj.setIdVenditore(((VenditoreC) session.getAttribute("venditore")).getId());

            /* setto le variabili per la jsp in caso di errore*/
            if (nomeCheck == true) {
                request.setAttribute("nomeCheck", true);
            } else {
                request.setAttribute("nomeCheck", false);
            }

            if (immagineCheck == true) {
                request.setAttribute("immagineCheck", true);
            } else {
                request.setAttribute("immagineCheck", false);
            }

            if (descrizioneCheck == true) {
                request.setAttribute("descrizioneCheck", true);
            } else {
                request.setAttribute("descrizioneCheck", false);
            }

            if (quantitaCheck == true) {
                request.setAttribute("quantitaCheck", true);
            } else {
                request.setAttribute("quantitaCheck", false);
            }

            if (prezzoCheck == true) {
                request.setAttribute("prezzoCheck", true);
            } else {
                request.setAttribute("prezzoCheck", false);
            }
            session.setAttribute("addObj", obj);
            // Se controllo è ancora true i campi sono stati tutti validati e posso inserire l'oggetto      
            if (nomeCheck == true && immagineCheck == true && descrizioneCheck == true && quantitaCheck == true && prezzoCheck == true) {
                request.getRequestDispatcher("conferma.jsp").forward(request, response);
            } else {
                request.setAttribute("erroreAggiunta", true);
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
            }
        }

        if (request.getParameter("confermaAggiunta") != null) {
            Oggetti obj = (Oggetti) session.getAttribute("addObj");
            OggettiFactory.getInstance().nuovoOggetto(obj);
            request.setAttribute("confermaAggiunta", true);
            int id = ((VenditoreC) session.getAttribute("venditore")).getId();
            ArrayList<Oggetti> lista = OggettiFactory.getInstance().getOggettiByVenditore(id);
            session.setAttribute("listaV", lista);
            request.getRequestDispatcher("venditore.jsp").forward(request, response);
        }

        if (session.getAttribute("venditore") != null) {
            int id = ((VenditoreC) session.getAttribute("venditore")).getId();
            session.setAttribute("idVenditore", id);
            ArrayList<Oggetti> lista = (ArrayList<Oggetti>) session.getAttribute("listaV");
            if (lista != null) {
                request.setAttribute("listaSize", lista.size());
            } else {
                request.setAttribute("listaSize", 0);
            }

            request.getRequestDispatcher("venditore.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("venditore.jsp").forward(request, response);
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
