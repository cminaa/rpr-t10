package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static javafx.application.Application.launch;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    /*static String ispisiGradove(){
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
    }*/


        @Override
        public void start(Stage primaryStage) throws Exception{
            GeografijaDAO geo=GeografijaDAO.getInstance();
            Parent root = FXMLLoader.load(getClass().getResource("glavniProzor.fxml"));
            primaryStage.setResizable(false);
            primaryStage.setTitle("Odabir željene akcije");
            primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            primaryStage.show();
        }

        public static void main(String[] args) {
        launch(args);
    }
}
