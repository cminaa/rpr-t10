package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class aplikacija extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GeografijaDAO geo=GeografijaDAO.getInstance();
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("glavniProzor.fxml"), bundle);
        primaryStage.setResizable(false);
        primaryStage.setTitle("START");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
