package amm.mbs.classes;

public class Oggetti {

    private int id;
    private String nome;
    private String immagine;
    private String descrizione;
    private int quantita;
    private Double prezzo;
    private int idVenditore;

    public Oggetti() {

        this.id = 0;
        this.nome = "";
        this.immagine = "";
        this.descrizione = "";
        this.quantita = 0;
        this.prezzo = 0.0;
        this.idVenditore = 0;

    }

    public Oggetti(int id, String nome, String immagine, String descrizione, int quantita, Double prezzo, int idVenditore) {

        this.id = id;
        this.nome = nome;
        this.immagine = immagine;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.idVenditore = idVenditore;
    }

    /**
     * getter // setter 
     *
     */
    /* id */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*  nome */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    /* immagine */
    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /* descrizione */
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /* quantità */
    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    /* prezzo */
    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    /* idVenditore */
    public int getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

}
