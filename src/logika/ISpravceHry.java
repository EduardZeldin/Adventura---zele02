/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import logika.IHra;

/**
 *
 * @author User
 */
public interface ISpravceHry {
  
    /**
     * Vykonava prikazy hry v GUI
     * @param vstupniPrikaz
     */
    public void provedPrikaz(String vstupniPrikaz);
    
    public IHra getHra();
}
