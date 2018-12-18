package ba.unsa.etf.rpr;

public class Drzava {
    private String naziv;
    private int brojStanovnika;
    private Grad glavniGrad;
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    this.naziv=naziv;
    }

    public void setGlavniGrad(Grad grad) {
    glavniGrad=grad;
    }
}
