package amm.mbs.classes;

public class VenditoreC {

    public String nome;
    public String cognome;
    public String user;
    public String pass;
    public Double saldo;


    /**
     * getter // setter 
     */

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

    /* saldo */
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
