package logika;

/**
 *  Třída PrikazNapoveda
 *  
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 *@author    Eduard Zeldin
 *@version   8.5.2017
 * Příkaz "nápověda" zobrazí hráčovi co je jeho úkolem, 
 * a ukáže které příkazy může pro splnění tohoto ukolu zadavát.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Vaším úkolem je vybavit se co nejlépe\n"
        + "a dojít do tajemné komnaty a zabít Voldemorta\n"
        + "\n"
        + "Můžete zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
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
