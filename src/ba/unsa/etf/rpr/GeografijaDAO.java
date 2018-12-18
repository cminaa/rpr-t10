package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



public class GeografijaDAO {
    private static GeografijaDAO instanca=null;
    private Connection conn;
    private static initialize() {
        instanca= new GeografijaDAO();
    }

    private GeografijaDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:sqlite:baza.db";
        Connection conn;
        try {
            conn=DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void removeInstance() {
    instanca=null;
    }

    public static GeografijaDAO getInstance() {
        if (instanca == null) initialize();
        return instanca;
    }

    public ArrayList<Grad> gradovi() {
        return null;
    }

    public Grad glavniGrad(String drzava) {
        return null;
    }

    public void obrisiDrzavu(String drzava) {

    }

    public Drzava nadjiDrzavu(String drzava) {
        return null;
    }

    public void dodajGrad(Grad grad) {

    }

    public void dodajDrzavu(Drzava drzava) {

    }

    public void izmijeniGrad(Grad grad) {

    }
}
