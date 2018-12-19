package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class GeografijaDAO {
    private static GeografijaDAO instanca=null;
    private static Connection conn;
    PreparedStatement glavniG;
    PreparedStatement obrisiDr;
    PreparedStatement dodajG;
    PreparedStatement dodajDr;
    PreparedStatement izmijeniGr;
    PreparedStatement nadjiDr;
    private static void initialize() {
        instanca= new GeografijaDAO();
    }

    private GeografijaDAO() {
        /*try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        String url="jdbc:sqlite:src\\baza.db";
        try {
            conn=DriverManager.getConnection(url);
            glavniG = conn.prepareStatement("SELECT id from drzava WHERE naziv=?");
            obrisiDr = conn.prepareStatement("SELECT id from drzava WHERE naziv=?");
            dodajDr = conn.prepareStatement("INSERT INTO drzava(id,naziv,glavni_grad) VALUES (?,?,?)");
            dodajG = conn.prepareStatement("INSERT INTO grad (id,naziv,broj_stanovnika,drzava) VALUES(?,?,?,?)");
            izmijeniGr = conn.prepareStatement("UPDATE grad\n" +
                    "SET naziv =?, broj_stanovnika = ?,drzava=? \n" +
                    "WHERE naziv=?");
            nadjiDr=conn.prepareStatement("SELECT id FROM drzava WHERE naziv=?");
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
        ArrayList<Grad> gradovi=new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query="SELECT naziv, broj_stanovnika, drzava FROM grad ORDER BY broj_stanovnika DESC";
            ResultSet r=stmt.executeQuery(query);
            while(r.next()){
                String ime=r.getString(1);
                System.out.println(ime);
                int br=r.getInt(2);
                System.out.println(br);
                int id=r.getInt(3);
                System.out.println(id);
                Drzava d=new Drzava();
                Grad g=new Grad();
                Statement stmt2=conn.createStatement();
                Statement stmt3=conn.createStatement();
                String query2="SELECT naziv, glavni_grad FROM drzava where id="+id;
                ResultSet t=stmt2.executeQuery(query2);
                int id2=t.getInt(2);
                String query3="SELECT naziv,broj_stanovnika from grad where id="+id2;
                ResultSet tg=stmt3.executeQuery(query3);
                int brg=tg.getInt(2);
                String imeg=tg.getString(1);
                System.out.println(imeg);
                Grad glavni=new Grad();
                glavni.setBrojStanovnika(brg);
                glavni.setNaziv(imeg);
                g.setDrzava(d);
                g.setNaziv(ime);
                g.setBrojStanovnika(br);
                d.setGlavniGrad(glavni);
                d.setNaziv(t.getString(1));
                System.out.println(d.getNaziv());
                gradovi.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gradovi;
    }

    public Grad glavniGrad(String drzava) {
        Grad g=new Grad();
        try {
            glavniG.setString(1, drzava);
            ResultSet result = glavniG.executeQuery();
            if(!result.next())return  null;
            else{
                Statement stmt = conn.createStatement();
                String query="SELECT naziv, broj_stanovnika FROM grad WHERE drzava="+result.getInt(1);
                ResultSet r=stmt.executeQuery(query);

                String ime=r.getString(1);
                int brG=r.getInt(2);
                g.setBrojStanovnika(brG);
                g.setNaziv(ime);
                Drzava d=new Drzava();
                d.setNaziv(drzava);
                d.setGlavniGrad(g);
                g.setDrzava(d);
                return g;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return g;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            obrisiDr.setString(1, drzava);
            ResultSet result = glavniG.executeQuery();
            if(!result.isClosed()){
            int id=result.getInt(1);
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            String query="DELETE FROM grad WHERE drzava="+id;
            String query2="DELETE FROM drzava WHERE id="+id;
            stmt.execute(query);
            stmt2.execute(query2);}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        Drzava d=new Drzava();
        try {
            nadjiDr.setString(1,drzava);
            ResultSet result=nadjiDr.executeQuery();
            if(!result.next()) return null;
            else {
               int id =result.getInt(1);
                Statement stmt = conn.createStatement();
                String query="SELECT naziv, broj_stanovnika FROM grad WHERE drzava="+id;
                ResultSet r=stmt.executeQuery(query);
                Grad g=new Grad();
                String imeG=r.getString(1);
                int brG=r.getInt(2);
                g.setNaziv(imeG);
                g.setBrojStanovnika(brG);
                d.setNaziv(drzava);
                d.setGlavniGrad(g);
                g.setDrzava(d);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return d;
    }

    public void dodajGrad(Grad grad) {
        Random r=new Random();
        try {
            String zaDrzavu ="SELECT id FROM drzava WHERE naziv=\'"+grad.getDrzava().getNaziv()+"\'";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(zaDrzavu);
            int id=rs.getInt(1);
            dodajG.setInt(1,r.nextInt());
            dodajG.setString(2,grad.getNaziv());
            dodajG.setInt(3,grad.getBrojStanovnika());
            dodajG.setInt(4, id);
            dodajG.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public void dodajDrzavu(Drzava drzava) {
        Random r=new Random();
        try {
            String zaGrad ="SELECT id FROM grad WHERE naziv="+"\'"+drzava.getGlavniGrad().getNaziv()+"\'";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(zaGrad);
            int id=rs.getInt(1);
            dodajDr.setInt(1,r.nextInt());
            dodajDr.setString(2,drzava.getNaziv());
            dodajDr.setInt(3, id);
            dodajDr.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            izmijeniGr.setString(1,grad.getNaziv());
            izmijeniGr.setInt(2,grad.getBrojStanovnika());
            String zaDrzavu ="SELECT id FROM drzava WHERE naziv=\'"+grad.getDrzava().getNaziv()+"\'";
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(zaDrzavu);
            int id=rs.getInt(1);
            izmijeniGr.setInt(3, id);
            izmijeniGr.setString(4, grad.getNaziv());
            izmijeniGr.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }
}
