package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================


    @Test
    public void testVyhry() // Je to test výhry i prohry, tady je maximum co hráč může udělat pro výhru, ale má ji jistou jen z cca 75%
    {
        hra.zpracujPrikaz("otevři kufr");
        hra.zpracujPrikaz("otevři skříň");
        hra.zpracujPrikaz("seber kouzelné_boty");
        hra.zpracujPrikaz("seber kouzelná_hůl");
        hra.zpracujPrikaz("seber plášť_neviditelnosti");
        hra.zpracujPrikaz("jdi Obývák");
        hra.zpracujPrikaz("otevři skříň");
        hra.zpracujPrikaz("seber lektvar_zdraví");
        hra.zpracujPrikaz("seber lektvar_moci");
        hra.zpracujPrikaz("jdi 1.patro");
        hra.zpracujPrikaz("jdi 2.patro");
        hra.zpracujPrikaz("vyhoď plášť_neviditelnosti");
        hra.zpracujPrikaz("jdi 3.patro");
        hra.zpracujPrikaz("kouzlo Alochomora");
        hra.zpracujPrikaz("jdi Komora");
        hra.zpracujPrikaz("použij harfa");
        hra.zpracujPrikaz("jdi Věž");
        hra.zpracujPrikaz("seber střední_létající_klíč");
        hra.zpracujPrikaz("použij střední_létající_klíč");
        hra.zpracujPrikaz("jdi Podzemí");
        hra.zpracujPrikaz("otevři zrcadlo");
        hra.zpracujPrikaz("seber kámen_mudrců");
        hra.zpracujPrikaz("boj");
        assertEquals(true, hra.getHerniPlan().getVyhra());
        //assertEquals(2, hra.getHerniPlan().getBatoh().vratObsahBatohu().size());
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testJisteProhry() // Hráč si neveme plášť neviditelnosti a školník ho chytí - konec hry
    {
        hra.zpracujPrikaz("otevři kufr");
        hra.zpracujPrikaz("otevři skříň");
        hra.zpracujPrikaz("seber kouzelné_boty");
        hra.zpracujPrikaz("seber kouzelná_hůl");
        
        hra.zpracujPrikaz("jdi Obývák");
        hra.zpracujPrikaz("otevři skříň");
        hra.zpracujPrikaz("seber lektvar_zdraví");
        hra.zpracujPrikaz("seber lektvar_moci");
        hra.zpracujPrikaz("jdi 1.patro");
        
        assertEquals(true, hra.konecHry());
    }
    @Test
    public void testVeci() // Testování věcí - sbírání / vyhazování / otevírání
    {
        assertEquals(0, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // Začínáme s prázdným baťohem
        hra.zpracujPrikaz("seber kouzelné_boty"); // Věc je zavřená ve skříni, nepujde ji vzít
        assertEquals(0, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // Stále tedy máme 0 věcí
        hra.zpracujPrikaz("otevři skříň"); // Všechny věci jsme ze skříně vyhodili na zem
        hra.zpracujPrikaz("seber kouzelné_boty"); // // Sebrali jsme ze země boty
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // Máme v batohu první věc - boty
        
        hra.zpracujPrikaz("otevři skříň");   // Zkoušíme otevřít skříň znova
        hra.zpracujPrikaz("seber kouzelné_boty"); // Zkoušíme zda boty vypadnou znova
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // Nevypadli, vypadnou jen jednou
        
        hra.zpracujPrikaz("vyhoď kouzelné_boty"); // Vyhodili jsme boty
        assertEquals(0, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // Batoh máme opět prázdný
        hra.zpracujPrikaz("seber kouzelné_boty"); // Boty jsme opět sebrali
        assertEquals(1, hra.getHerniPlan().getBatoh().vratObsahBatohu().size()); // A máme je zas v batohu
    }
    
}
