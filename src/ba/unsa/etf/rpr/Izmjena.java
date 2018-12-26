package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Izmjena {
    public TextField stari;
    public TextField br;
    public TextField drzava;
    public TextField novi;
    public Button dugme;
    boolean a,b,c,d;
    boolean sadrziBroj(String s){
        for (int i = 0; i <s.length() ; i++) {
            if(!java.lang.Character.isLetter(
                    s.charAt(i)
            ) && s.charAt(i)!=' ')return true;
        }
        return false;
    }
    boolean neSadrziBroj(String s){
        for (int i = 0; i <s.length() ; i++) {
            if(!java.lang.Character.isDigit(
                    s.charAt(i)
            ))return false;
        }
        return true;
    }

    public void stariN(ActionEvent actionEvent) {
        if (!stari.getText().trim().isEmpty() &&
                !sadrziBroj(stari.getText())) {
            stari.getStyleClass().removeAll("poljeNijeIspravno");
            stari.getStyleClass().add("poljeIspravno");
            a=true;
        } else {
            stari.getStyleClass().removeAll("poljeIspravno");
            stari.getStyleClass().add("poljeNijeIspravno");
            a=false;
        }
    }

    public void brS(ActionEvent actionEvent) {
        if (!br.getText().trim().isEmpty() && neSadrziBroj(br.getText())) {
            br.getStyleClass().removeAll("poljeNijeIspravno");
            br.getStyleClass().add("poljeIspravno");
            d=true;
        } else {
            br.getStyleClass().removeAll("poljeIspravno");
            br.getStyleClass().add("poljeNijeIspravno");
            d=false;
        }
    }

    public void DD(ActionEvent actionEvent) {
        if (!drzava.getText().trim().isEmpty() &&
                !sadrziBroj(drzava.getText())) {
            drzava.getStyleClass().removeAll("poljeNijeIspravno");
            drzava.getStyleClass().add("poljeIspravno");
            b=true;
        } else {
            drzava.getStyleClass().removeAll("poljeIspravno");
            drzava.getStyleClass().add("poljeNijeIspravno");
            b=false;
        }
    }

    public void noviN(ActionEvent actionEvent) {  if (!novi.getText().trim().isEmpty() &&
            !sadrziBroj(novi.getText())) {
        novi.getStyleClass().removeAll("poljeNijeIspravno");
        novi.getStyleClass().add("poljeIspravno");
        c=true;
    } else {
        novi.getStyleClass().removeAll("poljeIspravno");
        novi.getStyleClass().add("poljeNijeIspravno");
        c=false;
    }
    }

    public void izmijeni(ActionEvent actionEvent) {
        if(!a || !b|| !c||!d){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GRESKA");
            alert.setHeaderText("Formular nije validan!");
            alert.setContentText("Unesite ispravne podatke.");
            alert.show();
        }else{
            boolean imaG=false;
//            boolean imaD=false;
            int index=0;
            Drzava d=GeografijaDAO.getInstance().nadjiDrzavu(drzava.getText());
            ArrayList<Grad> gradovi=GeografijaDAO.getInstance().gradovi();
            for (int i = 0; i <gradovi.size() ; i++) {
               if(gradovi.get(i).getNaziv().equals(stari.getText())) {
                   imaG=true;
               index=i;
               }
            }
            if(!imaG){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GRESKA");
                alert.setHeaderText("Grad ne postoji u bazi!");
                alert.show();
                Stage stage = (Stage) dugme.getScene().getWindow();
                stage.close();
            }else if(d==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("GRESKA");
                alert.setHeaderText("Drzava ne postoji u bazi!");
                alert.show();
                Stage stage = (Stage) dugme.getScene().getWindow();
                stage.close();
            }
            else{
                gradovi.get(index).setNaziv(novi.getText());
                gradovi.get(index).setBrojStanovnika(Integer.parseInt(br.getText()));
                gradovi.get(index).setDrzava(d);
                GeografijaDAO.getInstance().izmijeniGrad(gradovi.get(index));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Upjeseno obavljen update");
                alert.show();
            }
        }
    }
}
