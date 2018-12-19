package ba.unsa.etf.rpr;

public class Drzava {
    private String naziv;
    private Grad glavniGrad;

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

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
