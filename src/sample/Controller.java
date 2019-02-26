package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import modele.Personnage;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Controller {
    @FXML
    public ImageView imageBackground;
    @FXML
    public BorderPane bpPerso;
    @FXML
    public Button butDemarrage;
    @FXML
    public VBox vb1;
    @FXML
    public VBox vb2;
    @FXML
    public Button butFDT;

    public void demarrage(){
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("backgroundAnimeWithCactusBon.gif").toExternalForm());
        imageBackground.setImage(image);
        imageBackground.setFitHeight(1100);
        imageBackground.setFitWidth(1900);
        Personnage personnage = new Personnage();
        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("perso1PasFini.png").toExternalForm());
        personnage.setApparence(image1);
        ImageView imageView = new ImageView(personnage.getApparence());
        personnage.setApparenceVue(imageView);
        personnage.getApparenceVue().setId("x");
        bpPerso.setLeft(personnage.getApparenceVue());
        bpPerso.getLeft().setScaleX(0.2);
        bpPerso.getLeft().setScaleY(0.2);
        bpPerso.getLeft().setTranslateY(563);
        personnage.setPosition(bpPerso.getLeft().getTranslateX());
        Personnage personnage1 = new Personnage();
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("photo.png").toExternalForm());
        personnage1.setApparence(image2);
        ImageView imageView1 = new ImageView(personnage1.getApparence());
        personnage1.setApparenceVue(imageView1);
        personnage1.getApparenceVue().setId("o");
        bpPerso.setRight(personnage1.getApparenceVue());
        bpPerso.getRight().setScaleX(5);
        bpPerso.getRight().setScaleY(5);
        bpPerso.getRight().setTranslateY(875);
        butDemarrage.setVisible(false);
        vb1.setVisible(true);
        vb1.setTranslateY(75);
        vb2.setVisible(true);
        vb2.setTranslateY(75);
        butFDT.setVisible(true);
        butFDT.setTranslateX(-25);
        butFDT.setTranslateY(1000);
    }
    public void deplacerGaucheEtDroite(Personnage personne){
        final Timeline timeline = new Timeline();
        if (personne.isAPressed()){
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                if (Main.tour ==0){
                    if (personne.getPosition()>= -250){
                        personne.setPosition(personne.getPosition()-3);
                        bpPerso.getLeft().setTranslateX(personne.getPosition());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()>=-550){
                        personne.setPosition(personne.getPosition()-3);
                        bpPerso.getRight().setTranslateX(personne.getPosition());
                    }
                }


            }));
            timeline.play();
        }
        if (personne.isDPressed()){
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                if (Main.tour==0){
                    if (personne.getPosition()<=310){
                        personne.setPosition(personne.getPosition()+3);
                        bpPerso.getLeft().setTranslateX(personne.getPosition());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()<=-30){
                        personne.setPosition(personne.getPosition()+3);
                        bpPerso.getRight().setTranslateX(personne.getPosition());
                    }
                }


            }));
            timeline.play();
        }


    }
    public void finDeTour(){
        if (Main.tour==0){
            Main.tour=1;
        }else {
            Main.tour=0;
        }
    }

    //code inspiration
    //https://stackoverflow.com/questions/21783858/how-to-code-the-projectile-of-a-ball-of-different-force-and-angle-in-java-swing
    @FXML
    public BorderPane bPane;
    @FXML
    public Circle ball;
    @FXML
    public void lancer(){
        double vitesse =150;
        double angle=20;
        int[] counter={0};
        angle=angle*0.0174533;
        double vitesseX=vitesse*(double)(Math.cos(angle));
        double vitesseYIni=vitesse*(double)(Math.sin(angle));
        double gravite=-9.8;

        SimpleDoubleProperty stepX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty stepY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosY=new SimpleDoubleProperty(0);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1),event -> {
            posX.set(vitesseX*(counter[0]*0.004));
            posY.set((vitesseYIni*(counter[0]*0.004))+((gravite*(counter[0]*0.004)*(counter[0]*0.004))/2));
            stepX.set((posX.get()- lastPosX.get()));
            stepY.set(posY.get()-lastPosY.get());
            lastPosX.set(posX.get());
            lastPosY.set(posY.get());
            ball.setTranslateX(ball.getCenterX()+(stepX.get()));
            ball.setCenterX(ball.getCenterX()+(stepX.get()));
            ball.setTranslateY(ball.getCenterY()-(stepY.get()));
            ball.setCenterY(ball.getCenterY()-(stepY.get()));
            counter[0]++;

        }));
        timeline.play();

    }
}
