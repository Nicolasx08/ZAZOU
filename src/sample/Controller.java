package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import modele.Personnage;
import javafx.animation.Animation;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import java.io.File;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    @FXML
    private ImageView imageBackground;
    @FXML
    public ImageView imageStart;
    @FXML
    private BorderPane bpPerso;
    @FXML
    private Button butDemarrage;
    @FXML
    private VBox vb1;
    @FXML
    private VBox vb2;
    @FXML
    public Button butFDT;
    @FXML
    private Circle ball;
    @FXML
    private BorderPane bPane;
    @FXML
    private ProgressBar viePerso;
    @FXML
    private ProgressBar viePerso1;
    @FXML
    private Label PDV1;
    @FXML
    private Label PDV2;
    @FXML
    private Label vitDonnee1;
    @FXML
    private Label angleDonnee1;
    @FXML
    private Label vitDonnee2;
    @FXML
    private Label angleDonnee2;
    @FXML
    private Button instruction;
    @FXML
    private Button seeya;
    @FXML
    private Button retour;
    @FXML
    private Label victoryRoyal;
    @FXML
    private Label victoryRoyal1;
    @FXML
    private TextArea textarea;
    @FXML
    private Label nom1;
    @FXML
    private Label nom2;
    @FXML
    private Button nope;
    @FXML
    private Button yeah;
    @FXML
    private VBox vb3;
    @FXML
    private Label labWin;
    @FXML
    public Slider slidVol;
    @FXML
    private Slider masseProjectile;
    @FXML
    private Slider masseProjectile1;
    @FXML
    public ImageView imageZAZOU;
    @FXML
    public ImageView imageFin;
    @FXML
    private Button butChangerMap;
    @FXML
    private Label vie1;
    @FXML
    private Label Mp1;
    @FXML
    private Label vie2;
    @FXML
    private Label Mp2;
    @FXML
    private Label volume;

    private static Rectangle rectanglePerso1 = new Rectangle(255,840,50,100);
    private static Rectangle rectanglePerso2 = new Rectangle(1655,840,50,100);

    private static AtomicInteger angle = new AtomicInteger();
    private static AtomicInteger forceIni = new AtomicInteger();
    private static Boolean checkClick=false;
    private static Boolean lancerDone=false;
    private static Line ligne = new Line();
    private static boolean animation = false;
    private static int nbeVictoire=0;
    private static int nbeVictoire1=0;
    private static AtomicBoolean max = new AtomicBoolean(false);
    private static String musicFile2 = "opGGMax.mp3";
    private static Media sound2 = new Media(new File(musicFile2).toURI().toString());
    private static MediaPlayer mPlayer2 = new MediaPlayer(sound2);
    private static double gravite=0;
    private static boolean changerMap= true;

    public void demarrage(){
        if ((nbeVictoire==0 && nbeVictoire1==0 )|| changerMap){
            choixZoneCombat();
        }
        if (nbeVictoire==0 && nbeVictoire1==0) {
            askNomJ12EtSetNom();
            setPosPersoEtImage();
        }
        setElementBackground();
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
     void deplacerGaucheEtDroite(Personnage personne){
        if (!butDemarrage.isVisible()){
            if (!animation){
                if (Main.tour==0){
                    animePersoGauche();
                }
                if (Main.tour==1){
                    animePersoDroit(personne);
                }
            }
            if (animation && !personne.isAPressed() && !personne.isDPressed()){
                if (Main.tour==0){
                   persoGaucheStop();
                }
                if (Main.tour==1){
                    persoDroitStop();
                }
                animation=false;
            }
            if (personne.isAPressed()){
                final Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), even -> {
                    if (Main.tour ==0){
                        if (personne.getPosition()>= -250){
                            deplacementGaucheGauche(personne);
                        }
                    }
                    if (Main.tour==1){
                        if (personne.getPosition()>=-410){
                            deplacementGaucheDroit(personne);
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
                            deplacementDroitGauche(personne);
                        }
                    }
                    if (Main.tour==1){
                        if (personne.getPosition()<=145){
                            deplacementDroitDroit(personne);
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
    private void lancer(){
        String musicFile3 = "splash.mp3";
        Media sound3 = new Media(new File(musicFile3).toURI().toString());
        MediaPlayer mPlayer3 = new MediaPlayer(sound3);
        mPlayer3.setVolume(slidVol.getValue());
        ball.setVisible(true);
        double force= forceIni.get();
        double angle2=angle.get();
        int[] counter={0};
        angle2=angle2*0.0174533;

        AtomicInteger leftRight=new AtomicInteger(0);

        SimpleDoubleProperty stepX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty stepY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty posY=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosX=new SimpleDoubleProperty(0);
        SimpleDoubleProperty lastPosY=new SimpleDoubleProperty(0);

        if (Main.tour==0){
            setBallGauche();
            leftRight.set(1);
        }
        if (Main.tour==1){
            setBallDroit();
            leftRight.set(-1);
        }
        double vitesse =(force/ball.getRadius())*10;
        double vitesseX=vitesse*(Math.cos(angle2));
        double vitesseYIni=vitesse*(Math.sin(angle2));
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1),event -> {
            bigCalcul(counter,leftRight,stepX,stepY,posX,posY,lastPosX,lastPosY,vitesseX,vitesseYIni);
            if (ball.getCenterY()<=-810 && ball.getCenterX()<=-90 && ball.getCenterX()>=-110 && ball.getCenterY()<= -830 && !max.get()){
                easterEggZ(timeline);
            }
            if (ball.getCenterY()>=-128){//splash dans l'eau
                if (ball.getCenterX()>=12 && ball.getCenterX()<=675){
                    mPlayer3.play();
                }
                timeline.stop();
                butFDT.setVisible(true);
            }
            if (ball.getCenterX()<= 360 && ball.getCenterX()>= 285 && ball.getCenterY()>= -250){//Collider cactus
                timeline.stop();
                butFDT.setVisible(true);
            }
            if (Main.tour==0 && (ball.getCenterX()>= Main.personnage1.getPosition()+1140-rectanglePerso2.getWidth())&& (ball.getCenterX()<= Main.personnage1.getPosition()+1140)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-787){
                perso1Hit(timeline);
            }
            if (Main.tour==1 && (ball.getCenterX()>= Main.personnage.getPosition()-294-rectanglePerso1.getWidth())&& (ball.getCenterX()+(leftRight.get()*ball.getRadius())<= Main.personnage.getPosition()-290)&& ball.getCenterY()>=bpPerso.getLeft().getTranslateY()-800){
                persoHit(timeline);
            }
        }));
        timeline.play();

    }
    private void writeDonnee(){
        if (Main.tour==0){
            vitDonnee1.setText("Force initiale : "+forceIni.get());
            angleDonnee1.setText("Angle : "+angle.get());
        }
        else {
            vitDonnee2.setText("Force initiale : "+forceIni.get());
            angleDonnee2.setText("Angle : "+angle.get());
        }
    }
    private void transfo(){
        forceIni.set((int)(Math.sqrt(Math.pow((ligne.getEndX()-ligne.getStartX()),2)+Math.pow((ligne.getEndY()-ligne.getStartY()),2))));
        angle.set((int)Math.toDegrees(Math.asin((ligne.getEndY()-ligne.getStartY())/(forceIni.get()))));
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
        imageZAZOU.setVisible(false);

    }
    @FXML
    public void back(){
        butDemarrage.setVisible(true);
        instruction.setVisible(true);
        seeya.setVisible(true);
        textarea.setVisible(false);
        retour.setVisible(false);
        imageZAZOU.setVisible(true);
    }
    @FXML
    public void changerMap(){
        changerMap= true;
        demarrage();
    }
    private void setUpColorTerre(){
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("backgroundOfficiel.gif").toExternalForm());
        imageBackground.setVisible(false);
        imageBackground.setImage(image);
        nom1.setStyle("-fx-text-fill: black");
        PDV1.setStyle("-fx-text-fill: black");
        vie1.setStyle("-fx-text-fill: black");
        Mp1.setStyle("-fx-text-fill: black");
        vitDonnee1.setStyle("-fx-text-fill: black");
        angleDonnee1.setStyle("-fx-text-fill: black");
        victoryRoyal.setStyle("-fx-text-fill: black");
        nom2.setStyle("-fx-text-fill: black");
        PDV2.setStyle("-fx-text-fill: black");
        vie2.setStyle("-fx-text-fill: black");
        Mp2.setStyle("-fx-text-fill: black");
        vitDonnee2.setStyle("-fx-text-fill: black");
        angleDonnee2.setStyle("-fx-text-fill: black");
        victoryRoyal1.setStyle("-fx-text-fill: black");
        volume.setStyle("-fx-text-fill: black");
    }
    private void setUpColorLune(){
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("lune.gif").toExternalForm());
        imageBackground.setVisible(false);
        imageBackground.setImage(image);
        nom1.setStyle("-fx-text-fill: white");
        PDV1.setStyle("-fx-text-fill: white");
        vie1.setStyle("-fx-text-fill: white");
        Mp1.setStyle("-fx-text-fill: white");
        vitDonnee1.setStyle("-fx-text-fill: white");
        angleDonnee1.setStyle("-fx-text-fill: white");
        victoryRoyal.setStyle("-fx-text-fill: white");
        nom2.setStyle("-fx-text-fill: white");
        PDV2.setStyle("-fx-text-fill: white");
        vie2.setStyle("-fx-text-fill: white");
        Mp2.setStyle("-fx-text-fill: white");
        vitDonnee2.setStyle("-fx-text-fill: white");
        angleDonnee2.setStyle("-fx-text-fill: white");
        victoryRoyal1.setStyle("-fx-text-fill: white");
        volume.setStyle("-fx-text-fill: red");
    }
    private void askNomJ12EtSetNom(){
        TextInputDialog tID = new TextInputDialog("Nom joueur 1");
        tID.setTitle("Joueur 1");tID.setHeaderText("Veuillez entrer votre nom");
        tID.setContentText("Votre nom: ");
        Main.personnage.setNom(tID.showAndWait().get());
        TextInputDialog tID1 = new TextInputDialog("Nom joueur 2");
        tID1.setTitle("Joueur 2");
        tID1.setHeaderText("Veuillez entrer votre nom");
        tID1.setContentText("Votre nom: ");
        Main.personnage1.setNom(tID1.showAndWait().get());
        nom1.setText(Main.personnage.getNom());
        nom2.setText(Main.personnage1.getNom());
    }
    private void setPosPersoEtImage(){
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
    private void setElementBackground(){
        imageStart.setVisible(false);
        imageZAZOU.setVisible(false);
        imageFin.setVisible(false);
        imageFin.setFitHeight(1100);
        imageFin.setFitWidth(1900);

        imageBackground.setFitHeight(1100);
        imageBackground.setFitWidth(1900);
        imageBackground.setVisible(true);

        ball.setVisible(false);
        Main.personnage.setVie(100);
        Main.personnage1.setVie(100);
        PDV1.setText("100/100");
        PDV2.setText("100/100");
        viePerso.setProgress(1);
        viePerso1.setProgress(1);

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
        butChangerMap.setVisible(false);
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

    }
    private void choixZoneCombat(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choix de la zone de combat");
        alert.setHeaderText("La gravité variera selon la zone de combat");
        alert.setContentText("Choisissez votre zone de combat.");
        ButtonType buttonTypeTerre = new ButtonType("Terre");
        ButtonType buttonTypeLune = new ButtonType("Lune");
        alert.getButtonTypes().setAll(buttonTypeTerre, buttonTypeLune);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeTerre){
            setUpColorTerre();
            gravite=-20;
        } else if (result.get() == buttonTypeLune) {
            setUpColorLune();
            gravite=-10;
        }
        changerMap=false;
    }
    private void animePersoGauche(){
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
    private void animePersoDroit(Personnage personne){
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
    private void persoGaucheStop(){
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
    private void persoDroitStop(){
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
    private void deplacementGaucheGauche(Personnage personne){
        personne.setPosition(personne.getPosition()-5);
        bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()-5);
        bpPerso.getLeft().setTranslateX(personne.getPosition());
    }
    private void deplacementGaucheDroit(Personnage personne){
        personne.setPosition(personne.getPosition()-5);
        bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()-5);
        bpPerso.getRight().setTranslateX(personne.getPosition());
    }
    private void deplacementDroitGauche(Personnage personne){
        personne.setPosition(personne.getPosition()+5);
        bPane.getChildren().get(4).setTranslateX(bPane.getChildren().get(4).getTranslateX()+5);
        bpPerso.getLeft().setTranslateX(personne.getPosition());
    }
    private void deplacementDroitDroit(Personnage personne){
        personne.setPosition(personne.getPosition()+5);
        bPane.getChildren().get(5).setTranslateX(bPane.getChildren().get(5).getTranslateX()+5);
        bpPerso.getRight().setTranslateX(personne.getPosition());
    }
    private void setBallGauche(){
        ball.setRadius(masseProjectile.getValue());
        ball.setCenterX(bpPerso.getLeft().getTranslateX()-325);
        ball.setCenterY(bpPerso.getLeft().getTranslateY()-730);
    }
    private void setBallDroit(){
        ball.setRadius(masseProjectile1.getValue());
        ball.setCenterX(bpPerso.getRight().getTranslateX()+1100);
        ball.setCenterY(bpPerso.getRight().getTranslateY()-845);
    }
    private void bigCalcul(int [] counter,AtomicInteger leftRight,SimpleDoubleProperty stepX,SimpleDoubleProperty stepY, SimpleDoubleProperty posX, SimpleDoubleProperty posY, SimpleDoubleProperty lastPosX, SimpleDoubleProperty lastPosY,double vitesseX, double vitesseYIni ){
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
    }
    private void easterEggZ(Timeline timeline){
        timeline.stop();
        butFDT.setVisible(true);
        max.set(true);
        mPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        slidVol.valueProperty().addListener((event2)-> mPlayer2.setVolume(slidVol.getValue()));
        mPlayer2.play();
        Main.mediaPlayer.stop();
    }
    private void perso1Hit(Timeline timeline){
        timeline.stop();
        Main.personnage1.setVie(((Main.personnage1.getVie()-(((int)(ball.getRadius()*1.2)+(int)((128-ball.getCenterY())/40))+(forceIni.get()/20))))+10);
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
            butChangerMap.setVisible(true);
            labWin.setVisible(true);
            labWin.setText(Main.personnage.getNom()+ " a gagné!!");
            nbeVictoire=nbeVictoire+1;
            imageFin.setVisible(true);
            finDeTour();
        }
    }
    private void persoHit(Timeline timeline){
        timeline.stop();
        Main.personnage.setVie((Main.personnage.getVie()-(((int)(ball.getRadius()*1.2)+(int)((128-ball.getCenterY())/40))+(forceIni.get()/20)))+10);
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
            butChangerMap.setVisible(true);
            labWin.setVisible(true);
            labWin.setText(Main.personnage1.getNom()+ " a gagné!!");
            nbeVictoire1=nbeVictoire1+1;
            imageFin.setVisible(true);
            finDeTour();
        }
    }
}
