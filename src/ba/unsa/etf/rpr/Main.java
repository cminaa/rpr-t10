package ba.unsa.etf.rpr;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String ispisiGradove(){
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO geo=GeografijaDAO.getInstance();
        String s="";
        ArrayList<Grad> g=geo.gradovi();
        for (int i = 0; i < g.size(); i++) {
            s+=g.get(i).getNaziv();
            s+=" (";
            s+=g.get(i).getDrzava().getNaziv();
            s+=") - ";
            s+=g.get(i).getBrojStanovnika();
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args) {
       // System.out.println("Gradovi su:\n" + ispisiGradove());
       //glavniGrad();
        GeografijaDAO geo=GeografijaDAO.getInstance();

    }


}
