/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logika.PrikazJdi;
import logika.Prostor;
import utils.Observer;
import logika.ISpravceHry;

/**
 * Třída SeznamVychodu vytváří seznam názvů sousedních místností. Aktualizuje se
 * při nové hře nebo přejití do jiného prostoru.
 *
 * @author Eduard Zeldin
 */
public class SeznamVychodu implements Observer {

    private Pane listaProstoru;
    private Pane prostory;
    private ISpravceHry spravceHry;

    /**
     * Konstruktor třídy
     *
     * @param spravceHry
     */
    public SeznamVychodu(ISpravceHry spravceHry) {
        this.spravceHry = spravceHry;
        this.prostory = new VBox();
        this.prostory.setPrefWidth(100);

        this.listaProstoru = new VBox();

        Label nazev = new Label();
        nazev.setText("Sousedni prostory:");
        this.listaProstoru.getChildren().add(nazev);
        this.listaProstoru.getChildren().add(prostory);
    }

    /**
     *
     * @return
     */
    public Pane getListaProstoru() {
        return this.listaProstoru;
    }

    /*
    * Udalost ktera se stane pri kliknuti na tlacitko veci v batohu
     */
    private void prejdiDoProstoruEventHandler(ActionEvent event) {

        String nazevProstoru = ((Button) event.getSource()).idProperty().get();
        this.spravceHry.provedPrikaz(PrikazJdi.getNazevStatic() + " " + nazevProstoru);

    }

    /**
     * vycisti udaje o prostoru
     */
    private void smaz() {

        this.prostory.getChildren().clear();

    }

    /**
     * obnovi informace o prostoru, prida sousedni
     */
    private void obnov() {

        for (Prostor prostor : spravceHry.getHra().getHerniPlan().getAktualniProstor().getVychody()) {

            Button b = new Button();
            b.setText(prostor.getNazev());
            b.idProperty().setValue(prostor.getNazev());
            b.setOnAction(this::prejdiDoProstoruEventHandler);

            prostory.getChildren().add(b);
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
