package logika;
import java.util.*;
import java.util.Random;
/**
 * Instance třídy PřikazBoj
 * @author    Eduard Zeldin
 * @version   20.5.2017
 * Třida obsahuje metody výpočtu výhry nebo prohry v souboji mezi Harry a Voldemortem.
 * Pokud Harry nemá u sebe kámen můdrců k okámžíku začatku souboje tak hned ho Voldemort zabije.
 * Nasbírané předem kouzelné věci jsou schopné pomoci zvětšit šance výhry, ale stále jsou zhruba 75%.
 *
 */
class PrikazBoj implements IPrikaz {
    private static final String NAZEV = "boj";
    private HerniPlan plan; 
    
    /**
     * Konstruktor, nastavení herního plánu.
    */    
    public PrikazBoj(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Tato metoda se stará o veškerou funkcionalitu uvedenou v základním popisu třídy.
     */ 
    @Override
    public String provedPrikaz(String... parametry) 
    {
      Batoh pomocny = plan.getBatoh();
      int score = 0;//na začátku je skore 0
      int nahodneCislo;
      if( plan.getAktualniProstor().getNazev().equals("Podzemi") )
      {
          if(pomocny.obsahujeVec("kamen_mudrcu"))//pokud nemá Harry kámen můdrců tak prohraje
          {
              for( String nazev : pomocny.vratObsahBatohu().keySet() )//výpočet skore, které následně ovlivní šance výhry v souboji
              {
                  if(nazev.equals("lektvar_zdravi") || nazev.equals("lektvar_moci") || nazev.equals("kouzelne_boty") || nazev.equals("kouzelna_hůl"))
                  {
                      score++;
                  }
              }
              Random random = new Random();//vytvoří se náhodné číslo
              int pomocnaPromena = 7 - score;//vypočítá se maximální počet možných variant
              nahodneCislo = random.nextInt(pomocnaPromena - 1 + 1) + 1;//vypočítji se možné varianty
              if(nahodneCislo < 3)// z možných vypočítaných nahodných čísel se nám hodí je ty co jsou menší než 3 - 1 a 2
              {
                  plan.getHra().setKonecHry(true); // Tento příkaz zastavuje hru - konec hry
                  plan.setVyhra();  // Tento příkaz je více méně nadbytečný - funguje pouze pro účely testování zda hráč výhral nebo ne
                  return "Porazil jsi Voldemorta! Vyhrál jsi!";
              }
              else
              {
                  plan.getHra().setKonecHry(true);
                  return "Voldemort tě porazil - Prohrál jsi";
              }
          }
          else
          {
              plan.getHra().setKonecHry(true);
              return "Bez kamene mudrců tě Voldemort snadno porazil a zabil - Konec Hry";
          }
      }
      else
      {
          return "Bojovat můžeš jen s Voldemortem v lokaci: Podzemí";
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

