/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Příkaz Seber
 *
 * @author    Eduard Zeldin
 * @version   8.5.2017
 * Příkaz seber sbírá věc z prostoru a přídává ji do batohu. Výpočítává se také hmotnost věci a zbývající kapacita batohu.
 */
public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". Zkouší se sebrat zadanou věc. 
	 *  Pokud věc se nachází v prostoru, je přenositelna a jeji hmotnost nepřesahuje zbyvající kapacitu batohu,
     *  věc se vloží do batohu a zmizí z mistnosti. 
     *  Jinak - vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje název věci,
     *                          která se má sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            
            return "Co mám sebrat? Musíš zadat název věci";
        }

        String nazevSbiraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbirana = aktualniProstor.odeberVec(nazevSbiraneVeci);
        Batoh pomocny = plan.getBatoh();
        if (sbirana == null) {
            return "To tu není!";
        }
        else 
        {
            if(sbirana.jePrenositelna())
            {
                if( ( pomocny.getKapacita() - sbirana.getHmotnost() ) >= 0)
                {
                    if(aktualniProstor.getNazev().equals("Vez") && !pomocny.obsahujeVec("letajici_koste"))
                    {
                        return "Předměty levitují ve vzduchu, muste mít s sebou létající_koště abyste tu mohli sbírat věci";
                    }
                    pomocny.pridejVec(sbirana);
                    pomocny.pridejKapacitu(sbirana.getHmotnost());
                    return "Úspěšně jste vložili do batohu " + sbirana.getNazev() +" zbývající kapacita "+ pomocny.getKapacita();
                }   
                else
                {
                aktualniProstor.vlozVec(sbirana);
                return "Batoh je plný";
                }
            }
            else{
                aktualniProstor.vlozVec(sbirana);
                return "To neuzvednete!";
            }
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
