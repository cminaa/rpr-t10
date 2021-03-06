package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
        alert.setTitle( "Ispis gradova iz baze");
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

    private void PromijeniStage(Locale jezik){
        Stage primaryStage = (Stage) izmjenaGrada.getScene().getWindow();
        Locale.setDefault(jezik);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("glavniProzor.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
    }
    public void bosanski(ActionEvent actionEvent) {
        PromijeniStage(new Locale("bs","BA"));
    }

    public void engleski(ActionEvent actionEvent) {
       PromijeniStage(new Locale("en","US"));
    }

    public void francuski(ActionEvent actionEvent) {
        PromijeniStage(new Locale("fr","FR"));
    }

    public void njemacki(ActionEvent actionEvent) {
        PromijeniStage(Locale.GERMAN);
    }

    public void sacuvaj(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter xslmExtenizija = new FileChooser.ExtensionFilter("XSLX", "*.xslx");
        fc.getExtensionFilters().add( xslmExtenizija );
        FileChooser.ExtensionFilter docxExtenzija = new FileChooser.ExtensionFilter("DOCX", "*.docx");
        fc.getExtensionFilters().add( docxExtenzija );
        FileChooser.ExtensionFilter pdfExtenzija = new FileChooser.ExtensionFilter("PDF", "*.pdf");
        fc.getExtensionFilters().add( pdfExtenzija );
        fc.setTitle("Saving a file");
        File selectedFile = fc.showSaveDialog(null);

        if (selectedFile != null)
            sacuvajFajl(selectedFile);
    }

    private void sacuvajFajl(File selectedFile) {

        try {
            new GradoviReport().save(selectedFile.getAbsolutePath(), GeografijaDAO.getConn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
