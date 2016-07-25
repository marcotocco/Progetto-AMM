package amm.mbs.factories;

import amm.mbs.classes.VenditoreC;
import java.util.ArrayList;

public class VenditoreFactory {

    private static VenditoreFactory vFactory;
    private ArrayList<VenditoreC> listaVenditori;

    public static VenditoreFactory getInstance() {
        if (vFactory == null) {
            vFactory = new VenditoreFactory();
        }
        return vFactory;
    }

    private VenditoreFactory() {

        listaVenditori = new ArrayList<>();
        VenditoreC venditore1 = new VenditoreC();
        venditore1.setNome("carlo");
        venditore1.setCognome("bianchi");
        venditore1.setUser("carlo");
        venditore1.setPass("bianchi");
        venditore1.saldo = 100.0;
        listaVenditori.add(venditore1);

    }

    public ArrayList<VenditoreC> getVenditoriList() {
        return listaVenditori;
    }

}
