package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Statement;

public class ProzorZaUnos {
    public TextField nazivD;
    public TextField gradD;
    public Button dugme;
    public TextField brojS;
    boolean naziv, grad,br;
 boolean sadrziBroj(String s){
     for (int i = 0; i <s.length() ; i++) {
         if(!java.lang.Character.isLetter(
                 s.charAt(i)
         )&& s.charAt(i)!=' ')return true;
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

    public void naziv(ActionEvent actionEvent) {
        if (!nazivD.getText().trim().isEmpty() &&
        !sadrziBroj(nazivD.getText())) {
            nazivD.getStyleClass().removeAll("poljeNijeIspravno");
            nazivD.getStyleClass().add("poljeIspravno");
            naziv=true;
        } else {
            nazivD.getStyleClass().removeAll("poljeIspravno");
            nazivD.getStyleClass().add("poljeNijeIspravno");
            naziv=false;
        }
    }

    public void glavniG(ActionEvent actionEvent) {
        if (!gradD.getText().trim().isEmpty()) {
            gradD.getStyleClass().removeAll("poljeNijeIspravno");
            gradD.getStyleClass().add("poljeIspravno");
            grad=true;
        } else {
            gradD.getStyleClass().removeAll("poljeIspravno");
            gradD.getStyleClass().add("poljeNijeIspravno");
            grad=false;
        }
    }

    public void dodaj(ActionEvent actionEvent) {
     if(!grad || !naziv || !br){
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("GRESKA");
         alert.setHeaderText("Formular nije validan!");
         alert.setContentText("Unesite ispravne podatke.");
         alert.show();
     }else{
Drzava d=new Drzava();
d.setNaziv(nazivD.getText());
Grad g=new Grad();
g.setBrojStanovnika(Integer.parseInt(brojS.getText()));
g.setNaziv(gradD.getText());
d.setGlavniGrad(g);
g.setDrzava(d);
GeografijaDAO.getInstance().dodajDrzavu(d);
GeografijaDAO.getInstance().dodajGrad(g);
         Stage stage = (Stage) dugme.getScene().getWindow();
         stage.close();
     }
    }

    public void stanovnika(ActionEvent actionEvent) {
        if (!brojS.getText().trim().isEmpty() && neSadrziBroj(brojS.getText())) {
            brojS.getStyleClass().removeAll("poljeNijeIspravno");
            brojS.getStyleClass().add("poljeIspravno");
            br=true;
        } else {
            brojS.getStyleClass().removeAll("poljeIspravno");
            brojS.getStyleClass().add("poljeNijeIspravno");
            br=false;
        }
    }
}
