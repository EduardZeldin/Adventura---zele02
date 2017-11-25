/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;
//import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Subject;
import utils.Observer;

/*******************************************************************************
 * Třída Batoh
 * @author    Eduard Zeldin
 * @version   8.5.2017
 * Batoh představuje třídu, do které se ukládají jednotlivé věci - nese záznam toho co má člověk  u sebe.
 */
public class Batoh implements Subject
{
    private Map<String, Vec> seznamVeci;
    private int kapacita = 920;
    private int soucasnaKapacita = 0;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    // konstruktor
    public Batoh()
    {
    seznamVeci = new HashMap<>();
    }
    
     //== NESOUKROMÉ METODY INSTANCÍ ====================================
    /**
     *  Metoda vypíše názvy věcí v batohu
     *
     *@return  názvy věcí
     */ 
    public String veciVBatohu() {
        String veci = "V batohu mate: ";
        for (String nazevVeci : seznamVeci.keySet()) {
            veci += nazevVeci + " ";
        }
        return veci;
    }
    // přidání věci
    public void pridejVec(Vec vec)
    {
     this.seznamVeci.put(vec.getNazev(),vec);
     this.pridejKapacitu(vec.getHmotnost());
     this.notifyObserver();
    }
    // vrátí seznam věcí z batohu
    public Map<String, Vec> vratObsahBatohu()
    {
        return this.seznamVeci;
    }
    // vyhodí věc z batohu
    public Vec vyhodVec(String nazev)
    {
        
        Vec vec = seznamVeci.remove(nazev);
        this.odeberKapacitu(vec.getHmotnost());
        this.notifyObserver();
        return vec;
    }
    // Detekovací metoda, která řekne jestli věc v batohu je či není.
    public Boolean obsahujeVec(String nazev)
    {
      boolean hledana = false;
      Vec pomocna = null;
      pomocna = this.seznamVeci.get(nazev);
      if(pomocna != null)
      {
          hledana = true;
      }
     return hledana;
    }
    // Vrací momentální kapacitu batohu.
    public int getKapacita()
    {
        return this.kapacita - soucasnaKapacita;
    }
    // metodá přidává kapacitu vnitrni metoda
    private void pridejKapacitu(int kapacita)
    {
        this.soucasnaKapacita += kapacita;
    }
    // metoda ubírá kapacitu
    private void odeberKapacitu(int kapacita)
    {
        this.soucasnaKapacita -= kapacita;
    }

    @Override
    public void registerObserver(utils.Observer observer) {
        this.listObserveru.add(observer);
        
    }

    @Override
    public void removeObserver(utils.Observer observer) {
        
        this.listObserveru.remove(observer);

    }

    @Override
    public void notifyObserver() {
 for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
            
        }
    }
}
