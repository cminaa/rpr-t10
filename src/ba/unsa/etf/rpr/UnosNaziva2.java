package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UnosNaziva2 {
    public TextField naziv;
    public Button dugme;
    boolean n;
    boolean sadrziBroj(String s){
        for (int i = 0; i <s.length() ; i++) {
            if(!java.lang.Character.isLetter(
                    s.charAt(i)
            )&& s.charAt(i)!=' ')return true;
        }
        return false;
    }
    public void unosNaziva(ActionEvent actionEvent) {
        if (!naziv.getText().trim().isEmpty() &&
                !sadrziBroj(naziv.getText())) {
            naziv.getStyleClass().removeAll("poljeNijeIspravno");
            naziv.getStyleClass().add("poljeIspravno");
            n=true;
        } else {
            naziv.getStyleClass().removeAll("poljeIspravno");
            naziv.getStyleClass().add("poljeNijeIspravno");
            n=false;
        }
    }

    public void OK(ActionEvent actionEvent) {
        if(!n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GRESKA");
            alert.setHeaderText("Formular nije validan!");
            alert.setContentText("Unesite ispravan naziv.");
            alert.show();
        }
         GeografijaDAO.getInstance().obrisiDrzavu(naziv.getText());
                if((Boolean) GeografijaDAO.isImaDrzave()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Brisanje");
                    alert.setHeaderText("Drzava obrisana");
                    alert.show();
                    Stage stage = (Stage) dugme.getScene().getWindow();
                    stage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("GRESKA");
                    alert.setHeaderText("Drzava ne postoji u bazi!");
                    alert.show();
                    Stage stage = (Stage) dugme.getScene().getWindow();
                    stage.close();
                }
            }
        }


