/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logika.Batoh;
import logika.IHra;
import utils.Observer;
import logika.PrikazVyhod;

/**
 * Třída VeciVInventari vytváří seznam obrázků vecí, které jsou v inventáři.
 * Aktualizuje se při nové hře, přejití do jiného prostoru a sebrání/položení
 * věci.
 *
 * @author Eduard Zeldin
 */

public class VeciVBatohu implements Observer {
    
    private Pane listaBatohu;
    private Pane veciVBatohu;
    public IHra hra;
    

    public VeciVBatohu(IHra hra) {
        this.hra = hra;
        this.veciVBatohu = new HBox();
        this.veciVBatohu.setPrefHeight(80);
        
        
        this.listaBatohu = new VBox();
        
    
        Label nazev = new Label();
        nazev.setText("Veci v Batohu:");
        this.listaBatohu.getChildren().add(nazev);
        this.listaBatohu.getChildren().add(veciVBatohu);
        
    }
    
    public Pane getListaBatohu() {
        
        return this.listaBatohu;
        
    }
    /*
    * Udalost ktera se stane pri kliknuti na tlacitko veci v batohu
    */
    private void vyhodVecEventHandler(ActionEvent event) {
        
        String nazevVeci = ((Button)event.getSource()).idProperty().get();
        this.hra.zpracujPrikaz(PrikazVyhod.getNazevStatic() +" "+ nazevVeci);
        
    }
    
    private void smaz() {
        this.veciVBatohu.getChildren().clear();
        
    }
    
    private void obnov() {
        
        for (String string : hra.getBatoh().vratObsahBatohu().keySet()) {
            
            Button b = new Button();
            b.setText(string);
            b.idProperty().setValue(string);
            b.setOnAction(this::vyhodVecEventHandler);
            
            
            
            veciVBatohu.getChildren().add(b);
            
        }
    }

    
    @Override
    public void update() {
        
        this.smaz();
        this.obnov();
        
    }
    
    
    
}
