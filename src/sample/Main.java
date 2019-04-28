package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import modele.Personnage;
import java.io.File;


public class Main extends Application {
    public static int tour = 0;
    public  static Personnage personnage = new Personnage();
    public static Personnage personnage1 = new Personnage();
    public static String musicFile = "touneIni.mp3";
    public static Media sound = new Media(new File(musicFile).toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(sound);


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

        javafx.scene.image.Image imageDebut = new javafx.scene.image.Image(getClass().getResource("imageStart.png").toExternalForm());
        controller.imageStart.setImage(imageDebut);
        controller.imageStart.setFitHeight(1100);
        controller.imageStart.setFitWidth(1900);
        javafx.scene.image.Image imageZAZOU = new javafx.scene.image.Image(getClass().getResource("zazouStart.png").toExternalForm());
        controller.imageZAZOU.setImage(imageZAZOU);
        javafx.scene.image.Image imageFinVictoire = new javafx.scene.image.Image(getClass().getResource("victoryPhoto.jpg").toExternalForm());
        controller.imageFin.setImage(imageFinVictoire);


        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        controller.slidVol.valueProperty().addListener((event)->{
            mediaPlayer.setVolume(controller.slidVol.getValue());
        });
        mediaPlayer.play();

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

            if (event.getCode() == KeyCode.SPACE){
                if (controller.butFDT.isVisible()){
                    controller.finDeTour();
                }
            }


        });
        root.setOnKeyReleased(event -> {
            if (event.getCode()== KeyCode.A){
                if (tour==0){
                    personnage.setAPressed(false);
                    controller.deplacerGaucheEtDroite(personnage);
                }else {
                    personnage1.setAPressed(false);
                    controller.deplacerGaucheEtDroite(personnage1);
                }
            }
            if (event.getCode() == KeyCode.D){
                if (tour==0){
                    personnage.setDPressed(false);
                    controller.deplacerGaucheEtDroite(personnage);
                }else {
                    personnage1.setDPressed(false);
                    controller.deplacerGaucheEtDroite(personnage1);
                }
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
