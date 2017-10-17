/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author User
 */


public class NewFXMain extends Application {
    
    private TextArea centralText;
    private IHra hra;
    private TextField zadejPrikazTextArea;
    
    
    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        BorderPane borderPane = new BorderPane();
        
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        zadejPrikazTextArea = new TextField("...");
        
        zadejPrikazTextArea.setOnAction((ActionEvent event) -> {
            String vstupniPrikaz = zadejPrikazTextArea.getText();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
            
            centralText.appendText("/n" + vstupniPrikaz + "/n");
            centralText.appendText("/n" + odpovedHry + "/n");
            
            zadejPrikazTextArea.setText("");
            
            if(hra.konecHry()) {
                
                zadejPrikazTextArea.setEditable(false);
                centralText.appendText(hra.vratEpilog());
                
            }
        });
        
        // obrazek pro mapu
        FlowPane obrazekFlowPane = new FlowPane();
        ImageView obrazekImageView = new ImageView(new Image(NewFXMain.class.getResourceAsStream("/zdroje/b76167d939ee50ec61a4659c64057cbc--pirate-treasure-maps-buried-treasure.jpg"), 300,300,false,true));
        obrazekFlowPane.setAlignment(Pos.CENTER);
        obrazekFlowPane.getChildren().add(obrazekImageView);
        
        
       
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
        borderPane.setLeft(obrazekFlowPane); 
        borderPane.setBottom(dolniLista);
        
       // root.getChildren().add(tlacitko);
        
        Scene scene = new Scene(borderPane, 500, 370);
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
        
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0){
        launch(args);
    }
        else{
            if (args[0].equals(".txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else {
                System.err.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }
}
