package amm.mbs.factories;

import amm.mbs.classes.ClienteC;
import amm.mbs.classes.Saldo;
import java.util.ArrayList;

public class ClienteFactory {

    private static ClienteFactory cFactory;
    private ArrayList<ClienteC> listaClienti;

    public static ClienteFactory getInstance() {
        if (cFactory == null) {
            cFactory = new ClienteFactory();
        }
        return cFactory;
    }

    private ClienteFactory() {

        listaClienti = new ArrayList<>();

        ClienteC cliente1 = new ClienteC();
        cliente1.setNome("Mario");
        cliente1.setCognome("Rossi");
        cliente1.setUser("ricco");
        cliente1.setPass("1");
        cliente1.setSaldo(1000.0);
        listaClienti.add(cliente1);
        
        ClienteC cliente2 = new ClienteC();
        cliente2.setNome("Luca");
        cliente2.setCognome("Gialli");
        cliente2.setUser("povero");
        cliente2.setPass("1");
        cliente2.setSaldo(1.0);
        listaClienti.add(cliente2);

    }

    public ArrayList<ClienteC> getClientiList() {
        return listaClienti;
    }

}
