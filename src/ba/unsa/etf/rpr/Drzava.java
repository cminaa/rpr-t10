package ba.unsa.etf.rpr;


import static ba.unsa.etf.rpr.GeografijaDAO.getD;
import static ba.unsa.etf.rpr.GeografijaDAO.setD;

public class Drzava {
    private String naziv;
    private Grad glavniGrad;
    private int id;

    public Drzava() {

    }

    public Drzava(String naziv, Grad glavniGrad) {
        this.id = getD()+1;
        setD(id);
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

}
