/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.Observer;
import logika.ISpravceHry;
import logika.PrikazSeber;
import logika.Vec;

/**
 * Třída VeciVInventari vytváří seznam obrázků vecí, které jsou v prostoru.
 * Aktualizuje se při nové hře, přejití do jiného prostoru a
 * sebrání/vyhozeni/otevreni věci.
 *
 * @author Eduard Zeldin
 */
public class VeciVProstoru implements Observer {

    private Pane listaVeciProstoru;
    private Pane veciVProstoru;
    private ISpravceHry spravceHry;

    /**
     * Konstruktor tridy
     *
     * @param spravceHry
     */
    public VeciVProstoru(ISpravceHry spravceHry) {

        this.spravceHry = spravceHry;
        this.veciVProstoru = new HBox();
        this.veciVProstoru.setPrefHeight(80);

        this.listaVeciProstoru = new VBox();

        Label nazev = new Label();
        nazev.setText("Veci v Prostoru:");
        this.listaVeciProstoru.getChildren().add(nazev);
        this.listaVeciProstoru.getChildren().add(veciVProstoru);

    }

    public Pane getListaVeciVProstoru() {

        return this.listaVeciProstoru;

    }

    /**
     * Udalost ktera se stane pri kliknuti na tlacitko veci v prostoru
     */
    private void pridejVecDoBatohuEventHandler(ActionEvent event) {

        String nazevVeci = ((Button) event.getSource()).idProperty().get();
        this.spravceHry.provedPrikaz(PrikazSeber.getNazevStatic() + " " + nazevVeci);

    }

    /**
     * Metoda vycisti seznam veci v prostoru
     */
    private void smaz() {
        this.veciVProstoru.getChildren().clear();

    }

    /**
     * Metoda obnovi seznam veci v prostoru(prochazi vsechny)
     */
    private void obnov() {

        for (Vec vec : spravceHry.getHra().getHerniPlan().getAktualniProstor().vratVeciVProstoru().values()) {

            Button b = new Button();
            b.setGraphic(vec.getObrazek());
            b.idProperty().setValue(vec.getNazev());
            b.setOnAction(this::pridejVecDoBatohuEventHandler);

            veciVProstoru.getChildren().add(b);

        }
    }

    /**
     * Zavola metody smaz a obnov
     */
    @Override
    public void update() {

        this.smaz();
        this.obnov();

    }

}
