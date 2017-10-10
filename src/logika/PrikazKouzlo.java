/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Příkaz Kouzlo
 *
 * @author    Eduard Zeldin
 * @version   18.5.2017
 * Příkaz "kouzlo" otevře dveře do Komory ktará je zamčena zaklínadlem. Vykouzlí se "Alochomora".
 * Pro úspěšné provedení musí spl%novat podmínky, uvedené níže.
 */
public class PrikazKouzlo implements IPrikaz{
    private static final String NAZEV = "kouzlo";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    */    
    public PrikazKouzlo(HerniPlan plan) {
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
        if (parametry.length == 0) {//ověři zda je vypsano zaklínadlo
            
            return "Jaké zaklínadlo mám použít? Musíš zadat název zaklínadla";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if(aktualniProstor.getNazev().equals("3.patro"))//ověří zda se hráč nachází ve 3.patře
        {
            if(nazev.equals("Alochomora"))//ověří zda je zaklínadlo zadáno správně
            {
                if(plan.getBatoh().obsahujeVec("kouzelna_hul"))//ověří jestli Harry kouzelnou hůl
                {
                    plan.Caruj();
                    return "Úspěšně jsi otevřel Komoru!";
                }
                else
                {
                    return "Zaklínat můžeš jen s hůlkou - tu u sebe nemáš";
                }
            }
            else
            {
                return "Takové zaklínadlo neznám";
            }
        }
        else
        {
            return "Zaklínat můžeš jen ve 3.patře";
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
