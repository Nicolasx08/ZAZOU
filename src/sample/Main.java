package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modele.Personnage;

import javax.naming.ldap.Control;

public class Main extends Application {
    public static int tour = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("sample.fxml").openStream());
        Controller controller = (Controller) loader.getController();
        primaryStage.setTitle("Zazou");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
        Personnage personnage = new Personnage();
        Personnage personnage1 = new Personnage();
        root.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.A){
                if (tour==0){
                    personnage.setAPressed(true);
                    controller.deplacerGaucheEtDroite(personnage);
                }else {
                    personnage1.setAPressed(true);
                    controller.deplacerGaucheEtDroite(personnage1);
                }

            }
            if (event.getCode() == KeyCode.D){
                if (tour==0){
                    personnage.setDPressed(true);
                    controller.deplacerGaucheEtDroite(personnage);
                }
                else {
                    personnage1.setDPressed(true);
                    controller.deplacerGaucheEtDroite(personnage1);
                }



            }
        });
        root.setOnKeyReleased(event -> {
            if (event.getCode()== KeyCode.A){
                if (tour==0){
                    personnage.setAPressed(false);
                }else {
                    personnage1.setAPressed(false);
                }
            }
            if (event.getCode() == KeyCode.D){
                if (tour==0){
                    personnage.setDPressed(false);
                }else {
                    personnage1.setDPressed(false);
                }
            }
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}
