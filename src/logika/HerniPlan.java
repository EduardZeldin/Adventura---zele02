package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá: vytváří všechny
 * prostory, propojuje je vzájemně pomocí východů a pamatuje si aktuální
 * prostor, ve kterém se hráč právě nachází. vytvaří všechny věci, umisťuje je
 * do mistnosti nebo do jiných veci(truhl), rozhoduje o jejích přenositelnosti a
 * hmotnosti.
 *
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @author Eduard Zeldin
 * @version pro školní rok 2016/2017
 * @version 6.5.2017
 */
public class HerniPlan implements Subject {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Batoh batuzek;
    private Hra hra;
    private boolean kouzlo;
    private boolean pes;
    private boolean vez;
    private boolean vyhra; // Pouze pro testovací účely ve třídě HraTest

    private List<Observer> listObserver = new ArrayList<Observer>();

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí
     * východů. Jako výchozí aktuální prostor nastaví halu.
     *
     * @param hra
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batuzek = new Batoh();
        this.hra = hra;
        kouzlo = true;
        pes = true;
        vez = true;
        vyhra = false;
    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů. Jako výchozí
     * aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor loznice = new Prostor("Loznice", "Pánská ložnice Griffindoru", 200, 20);
        Prostor obyvak = new Prostor("Obyvak", "Obývací místnost Griffindoru", 20, 20);
        Prostor patro5 = new Prostor("5.patro", "Páté patro. Procházíté mimo školníka Filče a jeho kočky missis Noris", 20, 80);
        Prostor patro4 = new Prostor("4.patro", "Čtvrté patro. Oooch, schodiště pořad mění směr", 20, 150);
        Prostor patro3 = new Prostor("3.patro", "Třetí patro", 20, 230);
        Prostor komora = new Prostor("Komora", "Komora. Vstup do další mistnosti je překrýt obrovským tříhlavým psem", 185, 230);
        Prostor vez = new Prostor("Vez", "Věž.Prostor je plný létajících klíčů", 290, 230);
        Prostor podzemi = new Prostor("Podzemi", "Tajemná komnata Voldemorta. Voldemort vynucuje Vás podívat se do zrcadla. \n A zvedá kouzelnou svoji hůl!", 300, 290);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        loznice.setVychod(obyvak);
        obyvak.setVychod(loznice);
        obyvak.setVychod(patro5);
        patro5.setVychod(obyvak);
        patro5.setVychod(patro4);
        patro4.setVychod(patro5);
        patro4.setVychod(patro3);
        patro3.setVychod(patro4);
        patro3.setVychod(komora);
        komora.setVychod(patro3);
        komora.setVychod(vez);
        vez.setVychod(komora);
        vez.setVychod(podzemi);

        aktualniProstor = obyvak;  // hra začíná v místnosti ložnice

        Vec koste = new Vec("letajici_koste", true, 100);
        Vec gauc = new Vec("gauc", false, 0);
        Vec skrinO = new Vec("skrinka", false, 0);
        Vec lektvarZ = new Vec("lektvar_zdravi", true, 200);
        Vec lektvarM = new Vec("lektvar_moci", true, 200);

        Vec skrinL = new Vec("skrin", false, 0);
        Vec kouzelnaHul = new Vec("kouzelna_hul", true, 100);
        Vec plast = new Vec("plast_neviditelnosti", true, 100);
        Vec postel = new Vec("postel", false, 0);
        Vec kufr = new Vec("kufr", false, 0);

        Vec harfa = new Vec("harfa", false, 0);

        Vec klic1 = new Vec("velky_letajici_klic", true, 50);
        Vec klic2 = new Vec("stredni_letajici_klic", true, 30);
        Vec klic3 = new Vec("maly_letajici_klic", true, 20);

        Vec zrcadlo = new Vec("zrcadlo", false, 0);
        Vec kamen = new Vec("kamen_mudrcu", true, 0);

        Vec boty = new Vec("kouzelne_boty", true, 100);
        Vec sala = new Vec("nebelvirska_sala", true, 100);
        Vec pero = new Vec("pero", true, 50);
        Vec svetr = new Vec("svetr", true, 100);
        Vec rukavice = new Vec("rukavice", true, 100);

        obyvak.vlozVec(gauc);
        obyvak.vlozVec(skrinO);
        obyvak.vlozVec(koste);

        loznice.vlozVec(postel);
        loznice.vlozVec(kufr);
        loznice.vlozVec(skrinL);

        komora.vlozVec(harfa);

        vez.vlozVec(klic1);
        vez.vlozVec(klic2);
        vez.vlozVec(klic3);

        podzemi.vlozVec(zrcadlo);

        skrinL.vlozVec(sala);
        skrinL.vlozVec(svetr);
        skrinL.vlozVec(rukavice);
        skrinL.vlozVec(boty);

        skrinO.vlozVec(lektvarZ);
        skrinO.vlozVec(lektvarM);
        skrinO.vlozVec(pero);
        skrinO.vlozVec(kouzelnaHul);
        kufr.vlozVec(plast);

        zrcadlo.vlozVec(kamen);
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi
     * prostory a notifikuje observery
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObserver();
    }

    // Metoda vrací batoh
    public Batoh getBatoh() {
        return this.batuzek;
    }

    // Metoda vrací hru kterou hrajeme
    public Hra getHra() {
        return this.hra;
    }

    // Metoda otevírá místnost
    public void Caruj() {
        this.kouzlo = false;
    }

    // Metoda vrací stav zavřené místnosti
    public boolean getKouzlo() {
        return this.kouzlo;
    }

    // Metoda uspí psa
    public void Uspi() {
        this.pes = false;
    }

    // Metoda vrací stav psa
    public boolean getPes() {
        return this.pes;
    }

    // Metoda otvírá věž
    public void otevriVez() {
        this.vez = false;
    }

    // Metoda vrací stav věže
    public boolean getVez() {
        return this.vez;
    }

    // Následující dvě metody fungují pouze pro účely testování ve třídě HraTest abychom zjistili zda hra skončila výhrou, nebo ne.
    // Metoda nastavuje že jsme vyhráli
    public void setVyhra() {
        this.vyhra = true;
    }

    // Metoda vrací zda jsme na konci vyhráli ( true ) nebo prohráli ( false ) 
    public boolean getVyhra() {
        return this.vyhra;
    }

    /**
     * Registrace observeru
     *
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserver.add(observer);
    }

    /**
     * Smazani observeru
     *
     * @param observer
     */
    @Override
    public void removeObserver(Observer observer) {
        listObserver.remove(observer);

    }

    /**
     * Oznameni poslouchajicich observeru
     */
    @Override
    public void notifyObserver() {
        for (Observer observer : listObserver) {
            observer.update();
        }

    }
}
