package sample;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controller {
    //code inspiration
    //https://stackoverflow.com/questions/21783858/how-to-code-the-projectile-of-a-ball-of-different-force-and-angle-in-java-swing
    @FXML
    public BorderPane bPane;
    @FXML
    public Circle ball;
    @FXML
    public void lancer(){

        int[] counter={0};
        double vitesseIni=50;
        double angle=20;
        double vitesseX=vitesseIni*Math.cos(angle);
        double vitesseYIni=vitesseIni*Math.sin(angle);
        double gravite=-9.8;
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50),event -> {
            double lastX=0;
            double lastY=0;
            double y = (vitesseYIni*(counter[0]*0.01))+((gravite*(counter[0]*0.01)*(counter[0]*0.01))/2);
            double x = (vitesseX*(counter[0]*0.01));
            ball.setTranslateX(ball.getCenterX()+(x-lastX));
            ball.setCenterX(ball.getCenterX()+(x-lastX));
            ball.setTranslateY(ball.getCenterY()-(y-lastY));
            ball.setCenterY(ball.getCenterY()-(y-lastY));
            lastX=x;
            lastY=y;
            counter[0]++;

        }));
        timeline.play();

    }
}
