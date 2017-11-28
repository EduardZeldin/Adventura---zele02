/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import main.Main;
import utils.Observer;
import logika.ISpravceHry;

/**
 *
 * @author Eduard Zeldin
 */
public class Mapa extends AnchorPane implements Observer {

    private ISpravceHry spravceHry;
    private Circle tecka;

    /**
     * Konstruktor třídy
     *
     * @param spravceHry
     */
    public Mapa(ISpravceHry spravceHry) {
        this.spravceHry = spravceHry;
        init();
    }

    private void init() {

        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"), 400, 340, false, true));

        tecka = new Circle(5, Paint.valueOf("red"));

        this.setTopAnchor(tecka, 0.0);
        this.setLeftAnchor(tecka, 0.0);

        this.getChildren().addAll(obrazekImageView, tecka);

    }

    /**
     *
     */
    @Override
    public void update() {
        this.setTopAnchor(tecka, spravceHry.getHra().getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, spravceHry.getHra().getHerniPlan().getAktualniProstor().getPosLeft());
    }
}
