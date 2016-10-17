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

public class Tipotransacao implements java.io.Serializable{
    
    private String tptrcod;
    
    private String tptrdes;

    /**
     * @return the tptrcod
     */
    public String getTptrcod() {
        return tptrcod;
    }

    /**
     * @param tptrcod the tptrcod to set
     */
    public void setTptrcod(String tptrcod) {
        this.tptrcod = tptrcod;
    }

    /**
     * @return the tptrdes
     */
    public String getTptrdes() {
        return tptrdes;
    }

    /**
     * @param tptrdes the tptrdes to set
     */
    public void setTptrdes(String tptrdes) {
        this.tptrdes = tptrdes;
    }
    
    
}
