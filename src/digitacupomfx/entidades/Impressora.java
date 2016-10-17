/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.entidades;


/**
 *
 * @author glerisonlima
 */

public class Impressora implements java.io.Serializable{
    
    
    private int ecfcod;
    
    private String ecfdes;    

    
    public int getEcfcod() {
        return ecfcod;
    }

    
    public void setEcfcod(int ecfcod) {
        this.ecfcod = ecfcod;
    }

    
    public String getEcfdes() {
        return ecfdes;
    }

    
    public void setEcfdes(String ecfdes) {
        this.ecfdes = ecfdes;
    }
    
    
}
