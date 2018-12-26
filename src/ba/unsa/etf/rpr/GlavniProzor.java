package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GlavniProzor {
    public RadioButton izmjenaGrada;


    public static RadioButton brisanjeDrzave;
    public RadioButton ispisGradova;


static boolean brisanje(){
        boolean selected = brisanjeDrzave.isSelected();
        return selected;}
        static boolean citanje(){
    return citanjeDrzave.isSelected();
        }
    public static RadioButton citanjeDrzave;


    public RadioButton dodavanjeDrzave;


    public void dodajDrzavu(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("prozorZaUnos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dodavanje drzave");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void citajDrzavu(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("unosNaziva.fxml"));
                loader.setController(new UnosNaziva());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Ispis podataka o drzavi");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){

        }
    }
    public void brisiDrzavu(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("unosNaziva.fxml"));
                loader.setController(new UnosNaziva2());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Brisanje drzave");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }catch (Exception e){

        }
    }

    public void ispisiGradove(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ispis gradova iz baze");
        alert.setHeaderText("Gradovi prisutni u bazi su:");
        String s="";
        ArrayList<Grad> g=GeografijaDAO.getInstance().gradovi();
        for (int i = 0; i < g.size(); i++) {
            s+=g.get(i).getNaziv();
            s+=" (";
            s+=g.get(i).getDrzava().getNaziv();
            s+=") - ";
            s+=g.get(i).getBrojStanovnika();
            s+="\n";
        }
        alert.setContentText(s);
        alert.show();
    }


    public void izmijeniGrad(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("izmjena.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Izmjena grada");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
