package amm.mbs.factories;

import amm.mbs.classes.Oggetti;
import java.util.ArrayList;

public class OggettiFactory {

    private static OggettiFactory oFactory;
    private ArrayList<Oggetti> listaOggetti;

    public static OggettiFactory getInstance() {
        if (oFactory == null) {
            oFactory = new OggettiFactory();
        }
        return oFactory;
    }

    private OggettiFactory() {

        listaOggetti = new ArrayList<>();

        Oggetti oggetto1 = new Oggetti();
        oggetto1.setId(1);
        oggetto1.setNome("The Brighton Beard Company: Black Pepper & Grapefruit");
        oggetto1.setImmagine("img/olio/bbc_olio.png");
        oggetto1.setQuantita(10);
        oggetto1.setPrezzo(27.0);
        listaOggetti.add(oggetto1);

        Oggetti oggetto2 = new Oggetti();
        oggetto2.setId(2);
        oggetto2.setNome("Mr. Bear: Wilderness");
        oggetto2.setImmagine("img/olio/bear_olio.png");
        oggetto2.setQuantita(10);
        oggetto2.setPrezzo(15.5);
        listaOggetti.add(oggetto2);

        Oggetti oggetto3 = new Oggetti();
        oggetto3.setId(3);
        oggetto3.setNome("Captain Fawcett's: Beard Oil Private Stock");
        oggetto3.setImmagine("img/olio/capfaw_olio.png");
        oggetto3.setQuantita(15);
        oggetto3.setPrezzo(49.0);
        listaOggetti.add(oggetto3);

        Oggetti oggetto4 = new Oggetti();
        oggetto4.setId(4);
        oggetto4.setNome("Morgan's Beard Oil");
        oggetto4.setImmagine("img/olio/morgans_olio.png");
        oggetto4.setQuantita(17);
        oggetto4.setPrezzo(20.90);
        listaOggetti.add(oggetto4);

        Oggetti oggetto5 = new Oggetti();
        oggetto5.setId(5);
        oggetto5.setNome("Mr. Natty: Sunshine Beard Elixir");
        oggetto5.setImmagine("img/olio/natty_olio.png");
        oggetto5.setQuantita(19);
        oggetto5.setPrezzo(14.0);
        listaOggetti.add(oggetto5);

    }

    public ArrayList<Oggetti> getOggettiList() {
        return listaOggetti;
    }
    
    	public Oggetti getOggettiById(int id){
        for(Oggetti obj : getOggettiList()){
            if(obj.getId() == id)
                return obj;
        }
        return null;
    }

}
