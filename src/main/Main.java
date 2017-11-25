/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.SeznamVychodu;
import GUI.VeciVBatohu;
import GUI.SeznamPrikazu;
import GUI.VeciVProstoru;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;
import logika.ISpravceHry;

/**
 *
 * @author zele02
 */
public class Main extends Application implements ISpravceHry {

    private Stage stage;
    private TextArea centralText = new TextArea();
    private IHra hra;
    private TextField zadejPrikazTextArea;
    private Mapa mapa;
    private MenuLista menuLista;
    private SeznamVychodu seznamVychodu;
    private VeciVBatohu veciVBatohu;
    private VeciVProstoru veciVProstoru;
    //private PostavyVProstoru postavyVProstoru;
    private SeznamPrikazu seznamPrikazu;

    public void setHra(IHra hra) {
        this.hra = hra;
    }

    /**
     * Metoda slouží ke spuštění grafického rozhraní hry a nastavení základního
     * layoutu
     */
    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);

        mapa = new Mapa(this);
        menuLista = new MenuLista(this);
        seznamVychodu = new SeznamVychodu(this);
        veciVBatohu = new VeciVBatohu(this);
        veciVProstoru = new VeciVProstoru(this);

        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField();

        this.novaHra();

        BorderPane borderPane = new BorderPane();

        //Text s prubehem hry
        centralText = new TextArea();

        centralText.setEditable(false);
        borderPane.setCenter(centralText);

        borderPane.setTop(menuLista);
        //label s textem "Zadej prikaz"
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        zadejPrikazTextArea.setOnAction(this::provedPrikazEventHandler);

        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);

        Pane dolniPane = new VBox();
        dolniPane.getChildren().add(dolniLista);
        dolniPane.getChildren().add(veciVBatohu.getListaBatohu());
        dolniPane.getChildren().add(veciVProstoru.getListaVeciVProstoru());

        Pane levyPane = new HBox();
        levyPane.getChildren().add(seznamVychodu.getListaProstoru());
        levyPane.getChildren().add(mapa);

        borderPane.setLeft(levyPane);
        borderPane.setBottom(dolniPane);
        borderPane.setTop(menuLista);

        centralText.setText(hra.vratUvitani());

        Scene scene = new Scene(borderPane, 950, 600);

        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public void novaHra() {

        //TODO odregistruj observerz
        if (hra != null) {

            hra.getHerniPlan().removeObserver(seznamVychodu);
            hra.getBatoh().removeObserver(veciVBatohu);
            hra.getHerniPlan().removeObserver(mapa);
            hra.getHerniPlan().removeObserver(veciVProstoru);
            hra.getBatoh().removeObserver(veciVProstoru);

        }

        hra = new Hra();

        hra.getHerniPlan().registerObserver(seznamVychodu);
        hra.getBatoh().registerObserver(veciVBatohu);
        hra.getHerniPlan().registerObserver(mapa);
        hra.getHerniPlan().registerObserver(veciVProstoru);
        hra.getBatoh().registerObserver(veciVProstoru);

        centralText.setText(hra.vratUvitani());

        zadejPrikazTextArea.setText("...");

        this.veciVBatohu.update();
        this.seznamVychodu.update();
        this.mapa.update();
        this.veciVProstoru.update();

        zadejPrikazTextArea.requestFocus();
    }

    private void provedPrikazEventHandler(ActionEvent event) {
        String vstupniPrikaz = zadejPrikazTextArea.getText();
        provedPrikaz(vstupniPrikaz);
    }

    @Override
    public void provedPrikaz(String vstupniPrikaz) {

        String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

        getCentralText().appendText("\n" + vstupniPrikaz + "\n");
        getCentralText().appendText("\n" + odpovedHry + "\n");

        zadejPrikazTextArea.setText("");

        if (hra.konecHry()) {
            zadejPrikazTextArea.setEditable(false);
            getCentralText().appendText(hra.vratEpilog());
        }

    }

    /**
     * private AnchorPane nastaveniMapy() { AnchorPane obrazekPane = new
     * AnchorPane();
     *
     * ImageView obrazekImageView = new ImageView(new
     * Image(Main.class.getResourceAsStream("/zdroje/b76167d939ee50ec61a4659c64057cbc--pirate-treasure-maps-buried-treasure.jpg"),
     * 300,300,false,true));
     *
     *
     * Circle tecka = new Circle(10, Paint.valueOf("red"));
     *
     * obrazekPane.setTopAnchor(tecka, 25.0); obrazekPane.setLeftAnchor(tecka,
     * 100.0);
     *
     * obrazekPane.getChildren().addAll(obrazekImageView, tecka);
     *
     * return obrazekPane;
     *
     * }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            } else {
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

    private void setStage(Stage stage) {
        this.stage = stage;

    }

    @Override
    public IHra getHra() {

        return this.hra;

    }
}
