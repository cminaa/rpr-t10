package ba.unsa.etf.rpr;

public class Grad {
    private String naziv;
    private  int brojStanovnika;
    private Drzava drzava;
    public String getNaziv() {
        return naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setNaziv(String marseille) {
    naziv=marseille;
    }

    public void setBrojStanovnika(int i) {
    brojStanovnika=i;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava=drzava;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }
}
