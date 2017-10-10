/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 * Instance třídy vec představují ...
 *
 * @author    Eduard Zeldin
 * @version   8.5.2017
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private int hmotnost;
    private Map<String, Vec> seznamVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost,int hmotnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.hmotnost = hmotnost;
        seznamVeci = new HashMap<>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }

    public boolean jePrenositelna(){
        return prenositelnost;
    }
    
    public int getHmotnost()
    {
        return this.hmotnost;
    }
    
    public Map<String, Vec> vratObsahVeci()
    {
        return this.seznamVeci;
    }
    
    public void vlozVec(Vec vec)
    {
     this.seznamVeci.put(vec.getNazev(),vec);   
    }
    // vybere věc z věci
    public Vec vyhodVec(String nazev)
    {
        return seznamVeci.get(nazev);
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
