package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modele.Personnage;


public class Main extends Application {
    public static int tour = 0;
    public  static Personnage personnage = new Personnage();
    public static Personnage personnage1 = new Personnage();

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
