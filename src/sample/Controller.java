package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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
    @FXML
    public BorderPane bPane;
    @FXML
    public ProgressBar viePerso;
    @FXML
    public ProgressBar viePerso1;
    public static Rectangle rectanglePerso1 = new Rectangle(255,840,50,100);
    public static Rectangle rectanglePerso2 = new Rectangle(1655,840,50,100);

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
        javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("samourai_David.png").toExternalForm());
        Main.personnage1.setApparence(image2);
        ImageView imageView1 = new ImageView(Main.personnage1.getApparence());
        Main.personnage1.setApparenceVue(imageView1);
        bpPerso.setRight(Main.personnage1.getApparenceVue());
        bpPerso.getRight().setScaleX(0.4);
        bpPerso.getRight().setScaleY(0.4);
        bpPerso.getRight().setTranslateY(680);
        bpPerso.getRight().setTranslateX(-45);
        Main.personnage1.setPosition(-45);
        butDemarrage.setVisible(false);
        vb1.setVisible(true);
        vb1.setTranslateY(75);
        vb2.setVisible(true);
        vb2.setTranslateY(75);
        ball.setVisible(true);
        javafx.scene.image.Image image3 = new javafx.scene.image.Image(getClass().getResource("caillou.png").toExternalForm());
        ball.setFill(new ImagePattern(image3));
        butLance.setVisible(true);
        rectanglePerso1.setOpacity(0);
        bPane.getChildren().add(rectanglePerso1);
        rectanglePerso2.setOpacity(0);
        bPane.getChildren().add(rectanglePerso2);

    }
    public void deplacerGaucheEtDroite(Personnage personne){
        final Timeline timeline = new Timeline();
        if (personne.isAPressed()){
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                if (Main.tour ==0){
                    if (personne.getPosition()>= -250){
                        personne.setPosition(personne.getPosition()-5);
                        bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()-5);
                        bpPerso.getLeft().setTranslateX(personne.getPosition());
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()>=-390){
                        personne.setPosition(personne.getPosition()-5);
                        bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()-5);
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
                        personne.setPosition(personne.getPosition()+5);
                        bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()+5);
                        bpPerso.getLeft().setTranslateX(personne.getPosition());
                        //personne.setApparence(gif animation);
                        //personne.setApparenceVue(personne.getApparence());
                    }
                }
                if (Main.tour==1){
                    if (personne.getPosition()<=145){
                        personne.setPosition(personne.getPosition()+5);
                        bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()+5);
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
        butFDT.setVisible(false);
        butLance.setVisible(true);
        if (Main.tour==0){
            Main.tour=1;
        }else {
            Main.tour=0;
        }
    }
    //code inspiration
    //https://stackoverflow.com/questions/21783858/how-to-code-the-projectile-of-a-ball-of-different-force-and-angle-in-java-swing
    @FXML
    public void lancer(){
        butLance.setVisible(false);
        double vitesse =150;
        double angle=35;
        int[] counter={0};
        angle=angle*0.0174533;
        double vitesseX=vitesse*(double)(Math.cos(angle));
        double vitesseYIni=vitesse*(double)(Math.sin(angle));
        double gravite=-20;
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
            ball.setCenterX(bpPerso.getRight().getTranslateX()+1100);
            ball.setCenterY(bpPerso.getRight().getTranslateY()-845);
            leftRight.set(-1);
        }
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1),event -> {
            ball.rotateProperty().setValue(ball.getRotate()+0.2*leftRight.get());
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
            if (ball.getCenterY()-ball.getRadius()>=-128-ball.getRadius()){
                timeline.stop();
                butFDT.setVisible(true);
            }
            if (Main.tour==0 && (ball.getCenterX()>= Main.personnage1.getPosition()+1140-rectanglePerso2.getWidth())&& (ball.getCenterX()<= Main.personnage1.getPosition()+1140)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-787){
                timeline.stop();
                Main.personnage1.setVie(Main.personnage1.getVie()+15+((int)(((bpPerso.getLeft().getTranslateY()-730)+((ball.getCenterY())/10)))));
                if(Main.personnage1.getVie()<0){
                    Main.personnage1.setVie(0);
                }
                viePerso1.setProgress((double)(Main.personnage1.getVie())/100);
                butFDT.setVisible(true);
                if (Main.personnage1.getVie()==0){
                    bpPerso.setVisible(false);
                    bPane.setVisible(false);
                    imageBackground.setVisible(false);
                    //Mettre joueur 1 a gagné
                    //plus demander de rejoué
                }
            }
            if (Main.tour==1 && (ball.getCenterX()>= Main.personnage.getPosition()-294-rectanglePerso1.getWidth())&& (ball.getCenterX()<= Main.personnage.getPosition()-290)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-800){
                timeline.stop();
                Main.personnage.setVie(Main.personnage.getVie()+15+((int)(((bpPerso.getRight().getTranslateY()-730)+ball.getCenterY())/10)));
                if(Main.personnage.getVie()<0){
                    Main.personnage.setVie(0);
                }
                viePerso.setProgress((double)(Main.personnage.getVie())/100);
                butFDT.setVisible(true);
                if (Main.personnage.getVie()==0){
                    bpPerso.setVisible(false);
                    bPane.setVisible(false);
                    imageBackground.setVisible(false);
                    //Mettre joueur 2 a gagné
                    //plus demander de rejoué
                }
            }
        }));
        timeline.play();

    }
}
