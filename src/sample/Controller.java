package sample;


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
