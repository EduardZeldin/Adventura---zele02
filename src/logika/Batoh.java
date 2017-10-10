/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package logika;
import java.util.*;
/*******************************************************************************
 * Třída Batoh
 * @author    Eduard Zeldin
 * @version   8.5.2017
 * Batoh představuje třídu, do které se ukládají jednotlivé věci - nese záznam toho co má člověk  u sebe.
 */
public class Batoh
{
    private Map<String, Vec> seznamVeci;
    private int kapacita = 920;
    private int soucasnaKapacita = 0;
    // konstruktor
    public Batoh()
    {
    seznamVeci = new HashMap<>();
    }
    // přidání věci
    public void pridejVec(Vec vec)
    {
     this.seznamVeci.put(vec.getNazev(),vec);   
    }
    // vrátí seznam věcí z batohu
    public Map<String, Vec> vratObsahBatohu()
    {
        return this.seznamVeci;
    }
    // vyhodí věc z batohu
    public Vec vyhodVec(String nazev)
    {
        return seznamVeci.remove(nazev);
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
    // metodá přidává kapacitu
    public void pridejKapacitu(int kapacita)
    {
        this.soucasnaKapacita += kapacita;
    }
    // metoda ubírá kapacitu
    public void odeberKapacitu(int kapacita)
    {
        this.soucasnaKapacita -= kapacita;
    }
}
