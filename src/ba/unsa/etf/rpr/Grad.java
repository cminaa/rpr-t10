package ba.unsa.etf.rpr;

public class Grad {
    private String naziv;
    private  int brojStanovnika;
    private int g=5;
    public void setId(int id) {
        this.id = id;
    }

    public Grad() {
id=g+1;
g++;
    }

    public Grad(String naziv, int brojStanovnika, Drzava drzava) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;

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

    @Override
    public String toString() {
        return naziv+"("+drzava.getNaziv()+") - "+brojStanovnika;
    }
}
