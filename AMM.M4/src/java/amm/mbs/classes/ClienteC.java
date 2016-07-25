package amm.mbs.classes;

public class ClienteC {

    public int id;
    public String nome;
    public String cognome;
    public String user;
    public String pass;
    public Double saldo;

    public ClienteC(int id, String nome, String cognome, String user, String pass, Double saldo) {

        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.user = user;
        this.pass = pass;
        this.saldo = saldo;
    }

    /**
     * getter // setter
     */
    
    /* id */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* nome */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /* cognome */
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /* Username */
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /* password */
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /* saldo*/
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
