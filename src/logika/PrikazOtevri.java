/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Třída Přikaz Otevři 
 *
 * @author    Eduard Zeldin
 * @version   18.5.2017
 * Příkaz otevři ukáže obsah věci(truhly) ve které jsou uložené jiné sbíratelné věci.
 * Pokud truhla je prázdná, nebo nejde ji otevřit - vypíše se chybové hlašení.
 */
public class PrikazOtevri implements IPrikaz{
    private static final String NAZEV = "otevri";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazOtevri(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  
     *                       
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) {
            
            return "Co mám otevřít? Musíš zadat název věci";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        Vec oteviraci = aktualniProstor.odeberVec(nazev);
        if(oteviraci != null)//ověři zda je zadana věc v prostoru
        {
            aktualniProstor.vlozVec(oteviraci);
            if( oteviraci.vratObsahVeci().size() > 0 )//ověři zda není otevírací věc prázdná
            {
                String text = "";
                for(String x : oteviraci.vratObsahVeci().keySet())//vrácí obsah věci
                {
                    Vec pomocna = oteviraci.vyhodVec(x);
                    text += " \n" +x;
                    aktualniProstor.vlozVec(pomocna);
                    // neni nejcistejsi reseni
                    plan.getBatoh().notifyObserver();
                }
                oteviraci.vratObsahVeci().clear();
                return "Úspěšně jsi otevřel " + nazev + " na zem se vysypalo:" + text;
            }
            else
            {
                return "Tento předmět nelze otevřít nebo je prázdný";
            }
        }
        else
        {
            return "Taková věc zde není";
        }
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
