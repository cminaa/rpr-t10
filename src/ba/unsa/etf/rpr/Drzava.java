package ba.unsa.etf.rpr;


public class Drzava {
    private String naziv;
    private Grad glavniGrad;
    private int id;
private int d=3;
    public Drzava() {
    id=d+1;
    d++;
    }

    public Drzava(String naziv, Grad glavniGrad) {

        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return naziv+" - glavni grad: "+glavniGrad.getNaziv();
    }
}
