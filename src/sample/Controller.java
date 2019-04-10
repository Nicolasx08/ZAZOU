package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Personnage;
import javafx.animation.Animation;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

import java.io.File;
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
    public BorderPane bPane;
    @FXML
    public ProgressBar viePerso;
    @FXML
    public ProgressBar viePerso1;
    @FXML
    public Label PDV1;
    @FXML
    public Label PDV2;
    @FXML
    public Label vitDonnee1;
    @FXML
    public Label angleDonnee1;
    @FXML
    public Label vitDonnee2;
    @FXML
    public Label angleDonnee2;
    @FXML
    public Button instruction;
    @FXML
    public Button seeya;
    @FXML
    public Button retour;
    @FXML
    public Label victoryRoyal;
    @FXML
    public Label victoryRoyal1;
    @FXML
    public TextArea textarea;
    @FXML
    public Label nom1;
    @FXML
    public Label nom2;
    @FXML
    public Button nope;
    @FXML
    public Button yeah;
    @FXML
    public VBox vb3;
    @FXML
    public Label labWin;
    @FXML
    public Slider slidVol;

    public static Rectangle rectanglePerso1 = new Rectangle(255,840,50,100);
    public static Rectangle rectanglePerso2 = new Rectangle(1655,840,50,100);

    public static AtomicInteger angle = new AtomicInteger();
    public static AtomicInteger vitesseIni = new AtomicInteger();
    public static Boolean checkClick=false;
    public static Boolean lancerDone=false;
    public static Line ligne = new Line();
    public static boolean animation = false;
    public static int nbeVictoire=0;
    public static int nbeVictoire1=0;

    public void demarrage(){
        if (nbeVictoire==0 && nbeVictoire1==0) {
            TextInputDialog tID = new TextInputDialog("Nom joueur 1");
            tID.setTitle("Joueur 1");
            tID.setHeaderText("Veuillez entrer votre nom");
            tID.setContentText("Votre nom: ");
            Main.personnage.setNom(tID.showAndWait().get());

            TextInputDialog tID1 = new TextInputDialog("Nom joueur 2");
            tID1.setTitle("Joueur 2");
            tID1.setHeaderText("Veuillez entrer votre nom");
            tID1.setContentText("Votre nom: ");
            Main.personnage1.setNom(tID1.showAndWait().get());

            nom1.setText(Main.personnage.getNom());
            nom2.setText(Main.personnage1.getNom());

            javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("samourai_David.png").toExternalForm());
            Main.personnage1.setApparence(image2);
            ImageView imageView1 = new ImageView(Main.personnage1.getApparence());
            Main.personnage1.setApparenceVue(imageView1);
            javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("perso1PasFini.png").toExternalForm());
            Main.personnage.setApparence(image1);
            ImageView imageView = new ImageView(Main.personnage.getApparence());
            Main.personnage.setApparenceVue(imageView);
            bPane.getChildren().add(rectanglePerso1);
            bPane.getChildren().add(rectanglePerso2);
            bpPerso.setRight(Main.personnage1.getApparenceVue());
            bpPerso.setLeft(Main.personnage.getApparenceVue());
            bpPerso.getRight().setTranslateX(-45);
            Main.personnage1.setPosition(-45);
            Main.personnage.setPosition(bpPerso.getLeft().getTranslateX());
        }
        ball.setVisible(false);
        Main.personnage.setVie(100);
        Main.personnage1.setVie(100);
        PDV1.setText("100/100");
        PDV2.setText("100/100");
        viePerso.setProgress(1);
        viePerso1.setProgress(1);

        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("backgroundOfficiel.gif").toExternalForm());
        imageBackground.setImage(image);
        imageBackground.setFitHeight(1100);
        imageBackground.setFitWidth(1900);
        imageBackground.setVisible(true);

        bpPerso.getLeft().setScaleX(0.2);
        bpPerso.getLeft().setScaleY(0.2);
        bpPerso.getLeft().setTranslateY(563);

        bpPerso.getRight().setScaleX(0.4);
        bpPerso.getRight().setScaleY(0.4);
        bpPerso.getRight().setTranslateY(680);
        bpPerso.setVisible(true);


        labWin.setVisible(false);
        nope.setVisible(false);
        yeah.setVisible(false);
        butDemarrage.setVisible(false);
        vb1.setVisible(true);
        vb1.setTranslateY(75);
        vb2.setVisible(true);
        vb2.setTranslateY(75);
        vb3.setVisible(true);
        seeya.setVisible(false);
        instruction.setVisible(false);
        victoryRoyal.setText("Victoire(s): "+nbeVictoire);
        victoryRoyal1.setText("Victoire(s): "+nbeVictoire1);

        javafx.scene.image.Image image3 = new javafx.scene.image.Image(getClass().getResource("caillou.png").toExternalForm());
        ball.setFill(new ImagePattern(image3));

        rectanglePerso1.setOpacity(0);
        rectanglePerso2.setOpacity(0);


        rectanglePerso1.setOnMousePressed(event -> {
            if (Main.tour==0&&!lancerDone){
                checkClick=true;
                ligne.setStartX(event.getSceneX());
                ligne.setStartY(event.getSceneY());
            }

        });
        rectanglePerso2.setOnMousePressed(event -> {
            if (Main.tour==1&&!lancerDone){
                checkClick=true;
                ligne.setStartX(event.getSceneX());
                ligne.setStartY(event.getSceneY());
            }

        });
        bPane.setOnMouseDragged(event -> {
            if (checkClick){
                ligne.setEndX(event.getSceneX());
                ligne.setEndY(event.getSceneY());
                transfo();
                writeDonnee();
            }
        });
        bPane.setOnMouseReleased(event -> {
            if (checkClick){
                lancerDone=true;
                checkClick=false;
                ligne.setEndX(event.getSceneX());
                ligne.setEndY(event.getSceneY());
                transfo();
                writeDonnee();
                lancer();
            }


        });
    }
    public void deplacerGaucheEtDroite(Personnage personne){
        if (!butDemarrage.isVisible()){
            if (!animation){
                if (Main.tour==0){
                    javafx.scene.image.Image imageAnime = new javafx.scene.image.Image(getClass().getResource("perso1animationMarcher.gif").toExternalForm());
                    Main.personnage.setApparence(imageAnime);
                    ImageView imageview = new ImageView(Main.personnage.getApparence());
                    Main.personnage.setApparenceVue(imageview);
                    bpPerso.setLeft(Main.personnage.getApparenceVue());
                    bpPerso.getLeft().setScaleX(0.2);
                    bpPerso.getLeft().setScaleY(0.2);
                    bpPerso.getLeft().setTranslateY(563);
                    bpPerso.getLeft().setTranslateX(Main.personnage.getPosition());
                    animation=true;
                }
                if (Main.tour==1){
                    javafx.scene.image.Image imageAnime1 = new javafx.scene.image.Image(getClass().getResource("samouraiMarchant.gif").toExternalForm());
                    Main.personnage1.setApparence(imageAnime1);
                    ImageView imageview1 = new ImageView(Main.personnage1.getApparence());
                    personne.setApparenceVue(imageview1);
                    bpPerso.setRight(Main.personnage1.getApparenceVue());
                    bpPerso.getRight().setScaleX(0.4);
                    bpPerso.getRight().setScaleY(0.4);
                    bpPerso.getRight().setTranslateY(680);
                    bpPerso.getRight().setTranslateX(Main.personnage1.getPosition());
                    animation=true;
                }
            }
            if (animation && !personne.isAPressed() && !personne.isDPressed()){
                if (Main.tour==0){
                    javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("perso1PasFini.png").toExternalForm());
                    Main.personnage.setApparence(image1);
                    ImageView imageView3 = new ImageView(Main.personnage.getApparence());
                    Main.personnage.setApparenceVue(imageView3);
                    bpPerso.setLeft(Main.personnage.getApparenceVue());
                    bpPerso.getLeft().setScaleX(0.2);
                    bpPerso.getLeft().setScaleY(0.2);
                    bpPerso.getLeft().setTranslateY(563);
                    bpPerso.getLeft().setTranslateX(Main.personnage.getPosition());
                }
                if (Main.tour==1){
                    javafx.scene.image.Image image2 = new javafx.scene.image.Image(getClass().getResource("samourai_David.png").toExternalForm());
                    Main.personnage1.setApparence(image2);
                    ImageView imageView4 = new ImageView(Main.personnage1.getApparence());
                    Main.personnage1.setApparenceVue(imageView4);
                    bpPerso.setRight(Main.personnage1.getApparenceVue());
                    bpPerso.getRight().setScaleX(0.4);
                    bpPerso.getRight().setScaleY(0.4);
                    bpPerso.getRight().setTranslateY(680);
                    bpPerso.getRight().setTranslateX(Main.personnage1.getPosition());
                }
                animation=false;
            }
            if (personne.isAPressed()){
                final Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                    if (Main.tour ==0){
                        if (personne.getPosition()>= -250){
                            personne.setPosition(personne.getPosition()-5);
                            bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()-5);
                            bpPerso.getLeft().setTranslateX(personne.getPosition());
                        }
                    }
                    if (Main.tour==1){
                        if (personne.getPosition()>=-410){
                            personne.setPosition(personne.getPosition()-5);
                            bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()-5);
                            bpPerso.getRight().setTranslateX(personne.getPosition());
                        }
                    }
                }));
                timeline.play();
            }
            if (personne.isDPressed()){
                final Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                    if (Main.tour==0){
                        if (personne.getPosition()<=310){
                            personne.setPosition(personne.getPosition()+5);
                            bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()+5);
                            bpPerso.getLeft().setTranslateX(personne.getPosition());
                        }
                    }
                    if (Main.tour==1){
                        if (personne.getPosition()<=145){
                            personne.setPosition(personne.getPosition()+5);
                            bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()+5);
                            bpPerso.getRight().setTranslateX(personne.getPosition());
                        }
                    }
                }));
                timeline.play();

            }

        }

    }
    public void finDeTour(){
        butFDT.setVisible(false);
        ball.setVisible(false);
        lancerDone=false;
        if (Main.tour==0){
            Main.tour=1;
        }else {
            Main.tour=0;
        }
    }
    @FXML
    public void lancer(){
        ball.setVisible(true);
        double vitesse =vitesseIni.get();
        double angle2=angle.get();
        int[] counter={0};
        angle2=angle2*0.0174533;
        double vitesseX=vitesse*(double)(Math.cos(angle2));
        double vitesseYIni=vitesse*(double)(Math.sin(angle2));
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
            if (ball.getCenterY()<=-820 && ball.getCenterX()<=-95 && ball.getCenterX()>=-105 && ball.getCenterY()<= -825){
                timeline.stop();
                butFDT.setVisible(true);
                Main.musicFile="opGGMax.mp3";
            }
            if (ball.getCenterY()>=-128){
                timeline.stop();
                butFDT.setVisible(true);
            }
            if (ball.getCenterX()<= 360 && ball.getCenterX()>= 285 && ball.getCenterY()>= -250){
                timeline.stop();
                butFDT.setVisible(true);
            }

            if (Main.tour==0 && (ball.getCenterX()>= Main.personnage1.getPosition()+1140-rectanglePerso2.getWidth())&& (ball.getCenterX()<= Main.personnage1.getPosition()+1140)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-787){
                timeline.stop();
                Main.personnage1.setVie(Main.personnage1.getVie()-(((int)(ball.getRadius()*1.2)+(int)((128-ball.getCenterY())/40))+(vitesseIni.get()/20)));
                PDV2.setText(Main.personnage1.getVie()+"/100");
                if(Main.personnage1.getVie()<0){
                    Main.personnage1.setVie(0);
                }
                viePerso1.setProgress((double)(Main.personnage1.getVie())/100);
                butFDT.setVisible(true);
                if (Main.personnage1.getVie()==0){
                    bpPerso.setVisible(false);
                    imageBackground.setVisible(false);
                    vb1.setVisible(false);
                    vb2.setVisible(false);
                    vb3.setVisible(false);
                    butFDT.setVisible(false);
                    nope.setVisible(true);
                    yeah.setVisible(true);
                    labWin.setVisible(true);
                    labWin.setText(Main.personnage.getNom()+ " a gagné!!");
                    nbeVictoire=nbeVictoire+1;
                    finDeTour();
                }
            }
            if (Main.tour==1 && (ball.getCenterX()>= Main.personnage.getPosition()-294-rectanglePerso1.getWidth())&& (ball.getCenterX()+(leftRight.get()*ball.getRadius())<= Main.personnage.getPosition()-290)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-800){
                timeline.stop();
                Main.personnage.setVie(Main.personnage.getVie()-(((int)(ball.getRadius()*1.2)+(int)((128-ball.getCenterY())/40))+(vitesseIni.get()/20)));
                PDV1.setText(Main.personnage.getVie()+"/100");
                if(Main.personnage.getVie()<0){
                    Main.personnage.setVie(0);
                }
                viePerso.setProgress((double)(Main.personnage.getVie())/100);
                butFDT.setVisible(true);
                if (Main.personnage.getVie()==0){
                    bpPerso.setVisible(false);
                    imageBackground.setVisible(false);
                    vb1.setVisible(false);
                    vb2.setVisible(false);
                    vb3.setVisible(false);
                    butFDT.setVisible(false);
                    nope.setVisible(true);
                    yeah.setVisible(true);
                    labWin.setVisible(true);
                    labWin.setText(Main.personnage1.getNom()+ " a gagné!!");
                    nbeVictoire1=nbeVictoire1+1;
                    finDeTour();
                }
            }
        }));
        timeline.play();

    }
    public void writeDonnee(){
        if (Main.tour==0){
            vitDonnee1.setText("Vitesse initiale : "+vitesseIni.get());
            angleDonnee1.setText("Angle : "+angle.get());
        }
        else {
            vitDonnee2.setText("Vitesse initiale : "+vitesseIni.get());
            angleDonnee2.setText("Angle : "+angle.get());
        }
    }
    public void transfo(){
        vitesseIni.set((int)(Math.sqrt(Math.pow((ligne.getEndX()-ligne.getStartX()),2)+Math.pow((ligne.getEndY()-ligne.getStartY()),2))));
        angle.set((int)Math.toDegrees(Math.asin((ligne.getEndY()-ligne.getStartY())/(vitesseIni.get()))));
        if (Main.tour==0){
            if (ligne.getStartX()<ligne.getEndX()){
                angle.set(180-angle.get());
            }
            else if (angle.get()<0){
                angle.set(360+angle.get());
            }
        }
        else {
            if (ligne.getStartX()>ligne.getEndX()){
                angle.set(180-angle.get());
            }
            else if (angle.get()<0){
                angle.set(360+angle.get());
            }
        }
    }
    @FXML
    public void byeBye(){
        System.exit(0);
    }
    @FXML
    public void learn(){
        butDemarrage.setVisible(false);
        instruction.setVisible(false);
        seeya.setVisible(false);
        textarea.setVisible(true);
        retour.setVisible(true);
    }
    @FXML
    public void back(){
        butDemarrage.setVisible(true);
        instruction.setVisible(true);
        seeya.setVisible(true);
        textarea.setVisible(false);
        retour.setVisible(false);
    }
}
