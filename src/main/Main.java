/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author User
 */


public class Main extends Application {
    
    private TextArea centralText;
    private IHra hra;
    
    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    
    private TextField zadejPrikazTextArea;
    
    private Mapa mapa;
    private MenuLista menuLista;
    
     private Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        
        hra = new Hra();
        
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        
        
        BorderPane borderPane = new BorderPane();
        
        centralText = new TextArea();
        getCentralText().setText(hra.vratUvitani());
        getCentralText().setEditable(false);
        borderPane.setCenter(getCentralText());
        
        borderPane.setTop(menuLista);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        zadejPrikazTextArea = new TextField("...");
        
        zadejPrikazTextArea.setOnAction((ActionEvent event) -> {
            String vstupniPrikaz = zadejPrikazTextArea.getText();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
            
            getCentralText().appendText("/n" + vstupniPrikaz + "/n");
            getCentralText().appendText("/n" + odpovedHry + "/n");
            
            zadejPrikazTextArea.setText("");
            
            if(hra.konecHry()) {
                
                zadejPrikazTextArea.setEditable(false);
                getCentralText().appendText(hra.vratEpilog());
                
            }
        });
        
        // obrazek pro mapu
        FlowPane obrazekFlowPane = new FlowPane();
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/b76167d939ee50ec61a4659c64057cbc--pirate-treasure-maps-buried-treasure.jpg"), 300,300,false,true));
        
        Circle tecka = new Circle(10, Paint.valueOf("red"));
        
        obrazekFlowPane.setAlignment(Pos.CENTER);
        obrazekFlowPane.getChildren().add(obrazekImageView);
        
       
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
        borderPane.setLeft(mapa);
        
        borderPane.setLeft(obrazekFlowPane); 
        borderPane.setBottom(dolniLista);
        
       // root.getChildren().add(tlacitko);
        
        Scene scene = new Scene(borderPane, 500, 370);
        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
        }
    
    
    
    private AnchorPane nastaveniMapy() {
        AnchorPane obrazekPane = new AnchorPane();
         
         ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/b76167d939ee50ec61a4659c64057cbc--pirate-treasure-maps-buried-treasure.jpg"), 300,300,false,true));
        
        
         Circle tecka = new Circle(10, Paint.valueOf("red"));
         
         obrazekPane.setTopAnchor(tecka, 25.0);
         obrazekPane.setLeftAnchor(tecka, 100.0);
         
        obrazekPane.getChildren().addAll(obrazekImageView, tecka);
        
        return obrazekPane;
        
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

    /**
     * @return the mapa
     */
    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @return the centralText
     */
    public TextArea getCentralText() {
        return centralText;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    private void setStage(Stage primaryStage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
