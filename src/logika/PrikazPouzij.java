/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třída Příkaz Použij
 *
 * @author    Eduard Zeldin
 * @version   8.5.2017
 * Příkaz "použij" použije zadanou věc podle parametrů uvedených v hlavní metodě.
 */
public class PrikazPouzij implements IPrikaz{
    private static final String NAZEV = "pouzij";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    */    
    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  
     * Metoda podle parametrů zadaných niže provede příkaz použij
	 * Pokud parametry nebudou splněné, vypíše se chybové hlašení.                   
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
        if (parametry.length == 0) {//ověři zda je zadan nazev věci
            return "Co mám použít? Musíš zadat název věci";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh pomocny = plan.getBatoh();
        if(aktualniProstor.getNazev().equals("Komora") && nazev.equals("harfa"))
        {
            plan.Uspi();
            return "Úspěšně jsi uspal psa harfou!";
        }
        if(aktualniProstor.getNazev().equals("Vez"))
        {
            if(nazev.equals("stredni_letajici_klic"))
            {
                plan.otevriVez();
                return "Úspěšně jsi otevřel lokaci Podzemí!";
            }
            else
            {
                return "Špatný klíč";
            }
        }
        return "Taková věc tu buď není, nebo se nedá použít";
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
