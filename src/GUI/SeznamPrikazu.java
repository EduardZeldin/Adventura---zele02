/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import utils.Observer;
import logika.IHra;

/**
 *
 * @author Eduard Zeldin
 */
public class SeznamPrikazu extends FlowPane implements Observer {

    private IHra hra;
    private TextArea centralText;

    /**
     * Konstruktor třídy
     *
     * @param hra
     * @param centralText
     */
    public SeznamPrikazu(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    /**
     * Úvodní nastavení příkazů
     *
     */
    private void init() {
        this.setPrefWidth(100);
        this.setPrefHeight(300);
        update();
    }

    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra hra) {
        hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Update seznamu prikazu
     */
    @Override
    public void update() {

    }

}
