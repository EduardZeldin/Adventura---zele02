package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 * @author    Eduard Zeldin
 * @version   18.5.2017
 *Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor existuje, vstoupí se do nového prostoru. 
 *Pokud zadaný sousední prostor(východ) není, vypíše se chybové hlášení.
 *
 *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
                           do kterého se má jít.
 *@return zpráva, kterou vypíše hra hráči
 
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  /**
     * Tato metoda se stará o veškerou funkcionalitu uvedenou v základním popisu třídy.
     */ 
    
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        Batoh pomocny = plan.getBatoh();
        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else 
        {
                //podud nemáte na sobě plášť neviditelnosti, chytí Vás Filč a nestihnete zastavit Voldemorta
				if(smer.equals("1.patro"))
                {
                    if(pomocny.obsahujeVec("plast_neviditelnosti"))
                    {
                        plan.setAktualniProstor(sousedniProstor);
                        return sousedniProstor.dlouhyPopis();
                    }
                    else
                    {
                        plan.getHra().setKonecHry(true);
                        return "Bez neviditelného pláště vás chytil školník a Voldemorta už nikdo nezastaví \n Konec hry - prohráli jste";
                    }
                }
				//nemužete projit schodami pokud jste neviditelní
                if(smer.equals("3.patro"))
                {
                    if(plan.getAktualniProstor().getNazev().equals("2.patro"))
                    {
                        if(pomocny.obsahujeVec("plast_neviditelnosti"))
                        {
                            return "Musíte vyhodit plášť - schody vás nevidí.";
                        }
                        else
                        {
                            plan.setAktualniProstor(sousedniProstor);
                            return sousedniProstor.dlouhyPopis();
                        }
                    }
                }
                //pro otevření dveři vedoucí do komory, musíte použit zaklínadlo "Alochomora"
                if(smer.equals("Komora"))
                {
                    if(plan.getKouzlo())
                    {
                        return "Komora je zavřená kouzlem! (kouzlo Alochomora)";
                    }
                    else
                    {
                        plan.setAktualniProstor(sousedniProstor);
                        return sousedniProstor.dlouhyPopis();
                    }
                }
                //Abyste mohl projít kolem psa, musíte ho nejdříve uspat
                if(smer.equals("Vez"))
                {
                    if(plan.getPes())
                    {
                        return "Abyste mohli projít kolem psa, musíš ho nejdříve uspat! ( použij harfa )";
                    }
                    else
                    {
                        plan.setAktualniProstor(sousedniProstor);
                        return sousedniProstor.dlouhyPopis();
                    }
                }
                //Abyste mohli projít do Podzemí, musíte nejdřív odemknout dveře klíčem ( použij + název klíče )
                if(smer.equals("Podzemi"))
                {
                    if(plan.getVez())
                    {
                        return "Abyste mohli projít do Podzemí, musíte nejdřív odemknout dveře klíčem ( použij + název klíče )";
                    }
                    else
                    {
                        plan.setAktualniProstor(sousedniProstor);
                        return sousedniProstor.dlouhyPopis();
                    }
                }
                
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();   
          
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
