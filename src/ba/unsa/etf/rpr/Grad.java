package ba.unsa.etf.rpr;

import static ba.unsa.etf.rpr.GeografijaDAO.getG;
import static ba.unsa.etf.rpr.GeografijaDAO.setG;

public class Grad {
    private String naziv;
    private  int brojStanovnika;

    public void setId(int id) {
        this.id = id;
    }

    public Grad() {

    }

    public Grad(String naziv, int brojStanovnika, Drzava drzava) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.id = getG()+1;
        setG(id);
        this.drzava = drzava;
    }

    private  int id;
    public int getId() {
      return id;
    }

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
