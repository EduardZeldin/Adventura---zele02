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
import utils.IVykonavatelPrikazu;
import utils.Observer;



/**
 * Třída SeznamVychodu vytváří seznam názvů sousedních místností. Aktualizuje se
 * při nové hře nebo přejití do jiného prostoru.
 * 
 * @author User
 */
public class SeznamVychodu implements Observer{
    
    private Pane listaProstoru;
    private Pane prostory;
    public IHra hra;
    private IVykonavatelPrikazu vykonavatelPrikazu;
    

    public SeznamVychodu(IHra hra, IVykonavatelPrikazu vykonavatelPrikazu) {
        this.hra = hra;
        this.vykonavatelPrikazu = vykonavatelPrikazu;
        
        this.prostory = new VBox();
        this.prostory.setPrefWidth(100);
        
        this.listaProstoru = new VBox();
        
        Label nazev = new Label();
        nazev.setText("Sousedni prostory:");
        this.listaProstoru.getChildren().add(nazev);
        this.listaProstoru.getChildren().add(prostory);
        this.update();
    }
   
    
     public Pane getListaProstoru() {
        
        return this.listaProstoru;
        
    }
    /*
    * Udalost ktera se stane pri kliknuti na tlacitko veci v batohu
    */
    private void prejdiDoProstoruEventHandler(ActionEvent event) {
        
        String nazevProstoru = ((Button)event.getSource()).idProperty().get();
        this.vykonavatelPrikazu.provedPrikaz(PrikazJdi.getNazevStatic() +" "+ nazevProstoru);
        
    }
    
    private void smaz() {
        this.prostory.getChildren().clear();
        
    }
    
    private void obnov() {
        
        for (Prostor prostor : hra.getHerniPlan().getAktualniProstor().getVychody()) {
            
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
