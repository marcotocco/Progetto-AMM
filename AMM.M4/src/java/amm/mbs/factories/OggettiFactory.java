package amm.mbs.factories;

import amm.mbs.classes.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OggettiFactory {

    private static OggettiFactory oFactory;
    private ArrayList<Oggetti> listaOggetti;
    private String connectionString;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public static OggettiFactory getInstance() {
        if (oFactory == null) {
            oFactory = new OggettiFactory();
        }
        return oFactory;
    }

    private OggettiFactory() {

    }

    /*
     * recupero la lista degli oggetti in vendita
     */
    public ArrayList<Oggetti> getOggettiList() {
        ArrayList<Oggetti> lista = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM  oggetti"; // query SQL
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String immagine = set.getString("immagine");
                String descrizione = set.getString("descrizione");
                int quantita = set.getInt("quantita");
                Double prezzo = set.getDouble("prezzo");
                int idVenditore = set.getInt("idVenditore");

                Oggetti obj = new Oggetti(id, nome, immagine, descrizione, quantita, prezzo, idVenditore);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OggettiFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /*
     * Recupero gli oggetti in vendita di un detterminato venditore (partendo
     * dal suo ID)
     */
    public ArrayList<Oggetti> getOggettiByVenditore(int idVenditore) {
        ArrayList<Oggetti> lista = new ArrayList<>();
        String sql = "SELECT * FROM oggetti WHERE idVenditore = ?"; //query SQL

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenditore);

            ResultSet set = stmt.executeQuery();
            while (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String immagine = set.getString("immagine");
                String descrizione = set.getString("descrizione");
                int quantita = set.getInt("quantita");
                Double prezzo = set.getDouble("prezzo");
                idVenditore = set.getInt("idVenditore");

                Oggetti obj = new Oggetti(id, nome, immagine, descrizione, quantita, prezzo, idVenditore);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OggettiFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    /*
     * recupero un oggetto a partire dal suo ID
     */
    public Oggetti getOggettiById(int id) {
        Oggetti obj = null;
        String sql = "SELECT * FROM oggetti WHERE id = ?"; //query SQL

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                String nome = set.getString("nome");
                String immagine = set.getString("immagine");
                String descrizione = set.getString("descrizione");
                int quantita = set.getInt("quantita");
                Double prezzo = set.getDouble("prezzo");
                int idVenditore = set.getInt("idVenditore");
                obj = new Oggetti(id, nome, immagine, descrizione, quantita, prezzo, idVenditore);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OggettiFactory.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    /*
     * Inserimento di un nuovo oggetto in vendita nel DB
     */
    public boolean nuovoOggetto(Oggetti obj) {
        boolean controllo = false;
        String sql = "INSERT INTO oggetti (id, nome, immagine, descrizione, quantita, prezzo, idvenditore) VALUES (default,?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getImmagine());
            stmt.setString(3, obj.getDescrizione());
            stmt.setDouble(4, obj.getPrezzo());
            stmt.setInt(5, obj.getQuantita());
            stmt.setInt(6, obj.getIdVenditore());
            int rows = stmt.executeUpdate();
            if (rows == 1) {
                controllo = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OggettiFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controllo;
    }

    public boolean rimuoviOggetto(int idOggetto) {
        boolean controllo = false;
        String sql = "DELETE FROM oggetti WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOggetto);

            int rows = stmt.executeUpdate();

            if (rows == 1) {
                controllo = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OggettiFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controllo;
    }

    /*
    * Modifica Oggetto da parte del venditore
     */
    public void modificaOggetto(Oggetti obj) {

        // da completare
    }

    /*
    * Conferma Acquisto Oggetto
     */
    public boolean compraOggetto(int idOggetto, int idCliente, int idVenditore) throws SQLException {

        boolean controllo = false;
        Connection conn = null;
        PreparedStatement togliOggetto = null;
        PreparedStatement sottraiSaldoCliente = null;
        PreparedStatement aggiungiSaldoVenditore = null;

        //recupero le informazioni che userò
        Oggetti obj = OggettiFactory.getInstance().getOggettiById(idOggetto);
        int quantita = obj.getQuantita();
        double prezzo = obj.getPrezzo();
        ClienteC cliente = ClienteFactory.getInstance().getClienteById(idCliente);
        VenditoreC venditore = VenditoreFactory.getInstance().getVenditoreById(idVenditore);
        double saldoCliente = cliente.getSaldo();
        double saldoVenditore = venditore.getSaldo();

        // Se il sando è insufficiente esco subito (controllo rimane a false)
        if (saldoCliente - prezzo < 0) {
            return controllo;
        }

        String sql1 = null;

        if (quantita == 1) {
            sql1 = " DELETE FROM oggetti WHERE id = ? "; // query SQL nel caso ci sia solo un oggetto, che lo elimina dal DB
        } else {
            sql1 = " UPDATE oggetti SET quantita = ?  WHERE id = ? "; // query SQL che modifica la quantità in caso di 2 o più oggetti.
        }

        String sql2 = " UPDATE cliente SET saldo = ?  WHERE id = ? ";  // query SQL modifica saldo del cliente
        String sql3 = " UPDATE venditore SET saldo = ?  WHERE id = ? "; // query SQL modifica saldo venditore

        try {
            conn = DriverManager.getConnection(connectionString, "usr", "pwd");

            conn.setAutoCommit(false); // in modo che le modifiche non siano permanenti fino a quando non diamo l'OK

            togliOggetto = conn.prepareStatement(sql1);
            sottraiSaldoCliente = conn.prepareStatement(sql2);
            aggiungiSaldoVenditore = conn.prepareStatement(sql3);

            if (quantita == 1) {
                togliOggetto.setInt(1, idOggetto);
            } else {
                togliOggetto.setInt(1, quantita - 1);
                togliOggetto.setInt(2, idOggetto);
            }

            /* diamo valori alle variabili delle query svolgendo le difficilissime operazioni necessarie di somma e sottrazione! */
            sottraiSaldoCliente.setDouble(1, saldoCliente - prezzo);
            sottraiSaldoCliente.setInt(2, idCliente);
            aggiungiSaldoVenditore.setDouble(1, saldoVenditore + prezzo);
            aggiungiSaldoVenditore.setInt(2, idVenditore);

            /* Fire in the hole */
            int c1 = togliOggetto.executeUpdate();
            int c2 = sottraiSaldoCliente.executeUpdate();
            int c3 = aggiungiSaldoVenditore.executeUpdate();

            if (c1 != 1 || c2 != 1 || c2 != 1) {
                conn.rollback(); // se tutto non è andato a buon fine torno allo stato iniziale
            } else {
                conn.commit(); // tutto OK, rendi permamente
                controllo = true;
            }
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    Logger.getLogger(OggettiFactory.class.getName()).log(Level.SEVERE, null, e);
                }
                controllo = false;
            }
        } finally {
            if (togliOggetto != null) {
                togliOggetto.close();
            }
            if (sottraiSaldoCliente != null) {
                sottraiSaldoCliente.close();
            }
            if (aggiungiSaldoVenditore != null) {
                aggiungiSaldoVenditore.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return controllo;
    }

}
