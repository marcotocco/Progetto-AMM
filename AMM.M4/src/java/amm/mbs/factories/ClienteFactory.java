package amm.mbs.factories;

import amm.mbs.classes.ClienteC;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteFactory {

    private static ClienteFactory cFactory;
    private ArrayList<ClienteC> listaClienti;
    private String connectionString;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public static ClienteFactory getInstance() {
        if (cFactory == null) {
            cFactory = new ClienteFactory();
        }
        return cFactory;
    }

    private ClienteFactory() {

    }

    /**
     * cerca nel db l'utente che tenta il login 
     */
    public ClienteC getClienteLogin(String username, String password) {
        ClienteC cliente = null;

        String query = "SELECT * FROM cliente WHERE username = ? AND password = ?"; // query SQL
        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet set = stmt.executeQuery(); // invio la query

            /* inserisco i valori dal database se trovo un match */
            if (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String user = set.getString("username");
                String pass = set.getString("password");
                Double saldo = set.getDouble("saldo");
                cliente = new ClienteC(id, nome, cognome, user, pass, saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    /**
     * risalgo all'utente dal suo ID 
     */
    public ClienteC getClienteById(int id) {

        ClienteC cliente = null;
        String sql = "SELECT * FROM cliente WHERE id = ?"; // query SQL
        try (Connection conn = DriverManager.getConnection(connectionString, "usr", "pwd");
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery(); // invio la query
            
            /* inserisco i valori dal database se trovo un match */
            if (set.next()) {
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String username = set.getString("username");
                String password = set.getString("password");
                Double saldo = set.getDouble("saldo");
                cliente = new ClienteC(id, nome, cognome, username, password, saldo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

}
