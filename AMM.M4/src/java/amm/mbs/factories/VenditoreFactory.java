package amm.mbs.factories;

import amm.mbs.classes.ClienteC;
import amm.mbs.classes.VenditoreC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenditoreFactory {

    private static VenditoreFactory vFactory;
    private ArrayList<VenditoreC> listaVenditori;
    private String connectionString;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public static VenditoreFactory getInstance() {
        if (vFactory == null) {
            vFactory = new VenditoreFactory();
        }
        return vFactory;
    }

    /**
     * cerca nel database il venditore che tenta il login
     */
    public VenditoreC getVenditoreLogin(String username, String password) {
        VenditoreC venditore = null;

        String query = "SELECT * FROM venditore WHERE username = ? AND password = ?"; // query SQL
        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet set = stmt.executeQuery(); 
      
            if (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String user = set.getString("username");
                String pass = set.getString("password");
                Double saldo = set.getDouble("saldo");
                venditore = new VenditoreC(id, nome, cognome, user, pass, saldo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenditoreFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return venditore;
    }

    /**
     * recupero un venditore a partire dal suo ID
     */
    public VenditoreC getVenditoreById(int id) {

        VenditoreC venditore = null;
        String sql = "SELECT * FROM venditore WHERE id = ?"; // query SQL

        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery(); 

            if (set.next()) {
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String username = set.getString("username");
                String password = set.getString("password");
                Double saldo = set.getDouble("saldo");
                venditore = new VenditoreC(id, nome, cognome, username, password, saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venditore;
    }

}
