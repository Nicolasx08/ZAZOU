package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modele.Personnage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ZAZOU");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Personnage personnage = new Personnage();
        root.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.A){
                personnage.setAPressed(true);
            }
            if (event.getCode() == KeyCode.D){
                personnage.setDPressed(true);
            }
        });
        root.setOnKeyReleased(event -> {
            if (event.getCode()== KeyCode.A){
                personnage.setAPressed(false);
            }
            if (event.getCode() == KeyCode.D){
                personnage.setDPressed(false);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
