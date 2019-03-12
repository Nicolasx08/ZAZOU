package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import modele.Personnage;


import javafx.animation.Animation;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicInteger;

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
    @FXML
    public Circle ball;
    @FXML
    public Button butLance;

    public void demarrage(){
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("backgroundAnimeWithCactus3fps.gif").toExternalForm());
        imageBackground.setImage(image);
        imageBackground.setFitHeight(1100);
        imageBackground.setFitWidth(1900);
        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("perso1PasFini.png").toExternalForm());
        Main.personnage.setApparence(image1);
        ImageView imageView = new ImageView(Main.personnage.getApparence());
        Main.personnage.setApparenceVue(imageView);
        bpPerso.setLeft(Main.personnage.getApparenceVue());
        bpPerso.getLeft().setScaleX(0.2);
        bpPerso.getLeft().setScaleY(0.2);
        bpPerso.getLeft().setTranslateY(563);
        Main.personnage.setPosition(bpPerso.getLeft().getTranslateX());
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("photo.png").toExternalForm());
        Main.personnage1.setApparence(image2);
        ImageView imageView1 = new ImageView(Main.personnage1.getApparence());
        Main.personnage1.setApparenceVue(imageView1);
        bpPerso.setRight(Main.personnage1.getApparenceVue());
        bpPerso.getRight().setScaleX(5);
        bpPerso.getRight().setScaleY(5);
        bpPerso.getRight().setTranslateY(875);
        bpPerso.getRight().setTranslateX(-200);
        Main.personnage1.setPosition(-200);
        butDemarrage.setVisible(false);
        vb1.setVisible(true);
        vb1.setTranslateY(75);
        vb2.setVisible(true);
        vb2.setTranslateY(75);
        butFDT.setVisible(true);
        ball.setVisible(true);
        butLance.setVisible(true);

    }
    public void deplacerGaucheEtDroite(Personnage personne){
        final Timeline timeline = new Timeline();
        if (personne.isAPressed()){
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                if (Main.tour ==0){
                    if (personne.getPosition()>= -250){
                        personne.setPosition(personne.getPosition()-5);
                        bpPerso.getLeft().setTranslateX(personne.getPosition());
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()>=-550){
                        personne.setPosition(personne.getPosition()-5);
                        bpPerso.getRight().setTranslateX(personne.getPosition());
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
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
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()<=-30){
                        personne.setPosition(personne.getPosition()+3);
                        bpPerso.getRight().setTranslateX(personne.getPosition());
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
                    }
                }


            }));
            timeline.play();
        }
        /*if (!personne.isDPressed()){
            if (Main.tour==0){
                javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("perso1PasFini.png").toExternalForm());
                personne.setApparence(image1);
                ImageView imageView = new ImageView(personne.getApparence());
                personne.setApparenceVue(imageView);
            }
            if (Main.tour==1){
                javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource().toExternalForm());
                personne.setApparence(image2);
                ImageView imageView = new ImageView(personne.getApparence());
                personne.setApparenceVue(imageView);
            }
        }*/


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
    //set deplacement de personne false quand on lance
    @FXML
    public void lancer(){
        double vitesse =90;
        double angle=50;
        int[] counter={0};
        angle=angle*0.0174533;
        double vitesseX=vitesse*(double)(Math.cos(angle));
        double vitesseYIni=vitesse*(double)(Math.sin(angle));
        double gravite=-14.8;
        AtomicInteger leftRight=new AtomicInteger(0);

        SimpleDoubleProperty stepX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty stepY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosY=new SimpleDoubleProperty(0);

        if (Main.tour==0){
            ball.setCenterX(bpPerso.getLeft().getTranslateX()-325);
            ball.setCenterY(bpPerso.getLeft().getTranslateY()-730);
            leftRight.set(1);
        }
        if (Main.tour==1){
            ball.setCenterX(bpPerso.getRight().getTranslateX()+1305);
            ball.setCenterY(bpPerso.getRight().getTranslateY()-1042);
            leftRight.set(-1);
        }
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
            ball.setTranslateX(ball.getCenterX()+leftRight.get()*(stepX.get()));
            ball.setCenterX(ball.getCenterX()+leftRight.get()*(stepX.get()));
            ball.setTranslateY(ball.getCenterY()-(stepY.get()));
            ball.setCenterY(ball.getCenterY()-(stepY.get()));
            counter[0]++;
            if (ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-695){
                timeline.stop();
            }

        }));
        timeline.play();

    }
}
