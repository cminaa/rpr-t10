package ba.unsa.etf.rpr;

public class Grad {
    private String naziv;
    private  int brojStanovnika;

    public void setId(int id) {
        this.id = id;
    }

    public Grad() {

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
