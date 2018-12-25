package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class GeografijaDAO {
    private static GeografijaDAO instanca=null;

    public static Connection getConn() {
        return conn;
    }

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
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:sqlite:resources\\baza.db";
        try{
            conn=DriverManager.getConnection(url);
            Statement s =conn.createStatement();
            s.execute("SELECT * from grad");
        }catch (Exception e){
            System.out.println("nema tabela, kreirat cemo iih");
            Statement s = null;
            Statement s2=null;
            try {
                s = conn.createStatement();
                s.execute("CREATE TABLE drzava(id INTEGER primary key,naziv TEXT, glavni_grad INTEGER)");
                s2=conn.createStatement();
                s2.execute("CREATE TABLE grad (id INTEGER primary key, naziv TEXT,broj_stanovnika INTEGER,drzava INTEGER)");
                Statement stmt = conn.createStatement();
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement();
                Statement stmt3 = conn.createStatement();
                Statement stmt4 = conn.createStatement();
                Statement stmt5 = conn.createStatement();
                Statement stmt6 = conn.createStatement();
                Statement stmt7 = conn.createStatement();
                Statement stmt8 = conn.createStatement();
                Statement stmt9 = conn.createStatement();
                stmt.execute("DELETE FROM grad");
                stmt1.execute("delete from drzava");
                stmt3.execute("INSERT INTO grad(id,naziv,broj_stanovnika,drzava) VALUES (1,'London',8825000,1)");
                stmt4.execute("INSERT INTO grad(id,naziv,broj_stanovnika,drzava) VALUES (2,'Beč',1899055,2)");
                stmt5.execute("INSERT INTO grad(id,naziv,broj_stanovnika,drzava) VALUES (3,'Pariz',2206488,3)");
                stmt2.execute("INSERT INTO grad(id,naziv,broj_stanovnika,drzava) VALUES (4,'Manchester', 545500,1)");
                stmt6.execute("INSERT INTO grad(id,naziv,broj_stanovnika,drzava) VALUES (5,'Graz',280200,2)");
                stmt7.execute("INSERT INTO drzava(id,naziv,glavni_grad) VALUES (1,'Velika Britanija', 1)");
                stmt8.execute("INSERT INTO drzava(id,naziv,glavni_grad) VALUES (2,'Austrija',2)");
                stmt9.execute("INSERT INTO drzava(id,naziv,glavni_grad) VALUES (3,'Francuska',3)");
            } catch (SQLException e1) {
                System.out.println("kreiranje greska");            }

        }
        try {
            //conn=DriverManager.getConnection(url);
            glavniG = conn.prepareStatement("SELECT id from drzava WHERE naziv=?");
            obrisiDr = conn.prepareStatement("SELECT id from drzava WHERE naziv=?");
            dodajDr = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES (?,?)");
            dodajG = conn.prepareStatement("INSERT INTO grad (naziv,broj_stanovnika,drzava) VALUES(?,?,?)");
            izmijeniGr = conn.prepareStatement("UPDATE grad SET naziv =?, broj_stanovnika = ?,drzava=? WHERE id=?");
            nadjiDr=conn.prepareStatement("SELECT id FROM drzava WHERE naziv=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void removeInstance()  {
        try {
           if(conn!=null) conn.close();
           conn=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            String query="SELECT naziv, broj_stanovnika, drzava,id FROM grad ORDER BY broj_stanovnika DESC";
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
                int id2=0;
                if(!t.isClosed())
                id2 = t.getInt(2);
                String query3="SELECT naziv,broj_stanovnika from grad where id="+id2;
                ResultSet tg=stmt3.executeQuery(query3);
                int brg=0; String imeg = null;
                if(!tg.isClosed()){
                brg=tg.getInt(2);
                imeg=tg.getString(1);}
                System.out.println(imeg);
                Grad glavni=new Grad();
                glavni.setBrojStanovnika(brg);
                glavni.setNaziv(imeg);
                g.setDrzava(d);
                g.setNaziv(ime);
                g.setId(r.getInt(4));
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
                String query="SELECT naziv, broj_stanovnika ,id FROM grad WHERE drzava="+result.getInt(1);
                ResultSet r=stmt.executeQuery(query);

                String ime=r.getString(1);
                int brG=r.getInt(2);
                g.setBrojStanovnika(brG);
                g.setNaziv(ime);
                g.setId(r.getInt(3));
                Drzava d=new Drzava();
                d.setNaziv(drzava);
                d.setGlavniGrad(g);
                g.setDrzava(d);
                d.setId(result.getInt(1));
                return g;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return g;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            glavniG.setString(1,drzava);
            obrisiDr.setString(1, drzava);
            ResultSet result = glavniG.executeQuery();
            if(!result.next()) {
                System.out.println("NISTA");

                return;
            }
            int id=result.getInt(1);
            System.out.println("ID: "+id);
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            String query="DELETE FROM grad WHERE drzava="+id;
            String query2="DELETE FROM drzava WHERE id="+id;
            stmt.executeUpdate(query);
            stmt2.executeUpdate(query2);
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
               d.setId(id);
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
        //Random r=new Random();
        try {
          //  dodajG.setInt(1, grad.getId());
            dodajG.setString(1,grad.getNaziv());
            dodajG.setInt(2,grad.getBrojStanovnika());
            dodajG.setInt(3, grad.getDrzava().getId());
            dodajG.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public void dodajDrzavu(Drzava drzava) {
        //Random r=new Random();
        try {
            //dodajDr.setInt(1,drzava.getId());
            dodajDr.setString(1,drzava.getNaziv());
            dodajDr.setInt(2, drzava.getGlavniGrad().getId());
            dodajDr.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            izmijeniGr.setString(1,grad.getNaziv());
            izmijeniGr.setInt(2,grad.getBrojStanovnika());
            izmijeniGr.setInt(3, grad.getDrzava().getId());
            izmijeniGr.setInt(4, grad.getId());
            izmijeniGr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
    }
}
