package modele;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import sample.Controller;

import java.util.concurrent.atomic.AtomicInteger;

public class Personnage {
    int vie=100;
    String nom;
    Image apparence;
    ImageView apparenceVue;
    Roche objetLance;
    Roche [] rochesdisponibles= new Roche[5];
    double position;
    boolean APressed= false;
    boolean DPressed = false;

    public ImageView getApparenceVue() { return apparenceVue;}
    public void setApparenceVue(ImageView apparenceVue) { this.apparenceVue = apparenceVue;}
    public boolean isAPressed() { return APressed;}
    public void setAPressed(boolean APressed) { this.APressed = APressed;}
    public boolean isDPressed() { return DPressed;}
    public void setDPressed(boolean DPressed) { this.DPressed = DPressed;}
    public int getVie() { return vie; }
    public void setVie(int vie) { this.vie = vie; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Image getApparence() { return apparence; }
    public void setApparence(Image apparence) { this.apparence = apparence; }
    public Roche getObjetLance() { return objetLance; }
    public void setObjetLance(Roche objetLance) { this.objetLance = objetLance; }
    public Roche[] getRochesdisponibles() { return rochesdisponibles; }
    public void setRochesdisponibles(Roche[] rochesdisponibles) { this.rochesdisponibles = rochesdisponibles; }
    public double getPosition() { return position; }
    public void setPosition(double position) { this.position = position; }
    public static double deplacement = 0;

}
