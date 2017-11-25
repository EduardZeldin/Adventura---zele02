package logika;
import java.util.*;
/**
 * Příkaz Info
 * @author    Eduard Zeldin
 * @version   8.5.2017
 * Třída info se stará o výpis informací důležitých pro hráče.
 * Vypisuje se obsah batohu, místnost ve které se nachází a seznam veci v této mistnosti.
 * 
 */
class PrikazInfo implements IPrikaz {
    private static final String NAZEV = "info";
    private HerniPlan plan;
    private String Text;
    
    /**
     * Konstruktor, nastavení herního plánu.
    */    
    public PrikazInfo(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Tato metoda se stará o veškerou funkcionalitu uvedenou v základním popisu třídy.
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        Text = "";
        Map<String, Vec> seznam;
        seznam = plan.getBatoh().vratObsahBatohu();
        
     if(seznam.size()==0)
     {
         Text = " nic - batoh je prázdný ";
     }
     else
     {
         for (String key : seznam.keySet()) 
         {
            Text += " " + key;
         }
     }
                return " U sebe máte: " + Text +""
                        + "\n Nalézáte se v lokaci: " 
                + plan.getAktualniProstor().getNazev()
                + "\n V lokaci jsou " + plan.getAktualniProstor().popisVeci()
                + "\n Východy: " + plan.getAktualniProstor().popisVychodu();
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}

