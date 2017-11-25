/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logika.IHra;
import logika.PrikazJdi;

import logika.Prostor;
import utils.Observer;
import logika.ISpravceHry;



/**
 * Třída SeznamVychodu vytváří seznam názvů sousedních místností. Aktualizuje se
 * při nové hře nebo přejití do jiného prostoru.
 * 
 * @author User
 */
public class SeznamVychodu implements Observer{
    
    private Pane listaProstoru;
    private Pane prostory;
    private ISpravceHry spravceHry;
    

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
   
    
     public Pane getListaProstoru() {
        
        return this.listaProstoru;
        
    }
    /*
    * Udalost ktera se stane pri kliknuti na tlacitko veci v batohu
    */
    private void prejdiDoProstoruEventHandler(ActionEvent event) {
        
        String nazevProstoru = ((Button)event.getSource()).idProperty().get();
        this.spravceHry.provedPrikaz(PrikazJdi.getNazevStatic() +" "+ nazevProstoru);
        
    }
    
    private void smaz() {
        this.prostory.getChildren().clear();
        
    }
    
    private void obnov() {
        
        for (Prostor prostor : spravceHry.getHra().getHerniPlan().getAktualniProstor().getVychody()) {
            
            Button b = new Button();
            b.setText(prostor.getNazev());
            b.idProperty().setValue(prostor.getNazev());
            b.setOnAction(this::prejdiDoProstoruEventHandler);
            
            prostory.getChildren().add(b);
        }
    }

    
    @Override
    public void update() {
        
        this.smaz();
        this.obnov();
        
    }
}
