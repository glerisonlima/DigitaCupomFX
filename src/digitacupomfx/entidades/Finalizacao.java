/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package digitacupomfx.entidades;

import java.math.BigDecimal;


public class Finalizacao {
    private String FZDCOD, FZDSEQ, AGECOD, FZDTRCCTR, TRNSEQ, CXANUM, TRNDAT,SEQFZD;
    private String FZDDES;
    private BigDecimal FZDVLR, FZDTRO,FZDCTRVAL;
    
    
    
    

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }

    public BigDecimal getFZDVLR() {
        return FZDVLR;
    }

    public void setFZDVLR(BigDecimal FZDVLR) {
        this.FZDVLR = FZDVLR;
    }
    
    
    public String getFZDCOD() {
        return FZDCOD;
    }

    public String getTRNSEQ() {
        return TRNSEQ;
    }

    public void setTRNSEQ(String TRNSEQ) {
        this.TRNSEQ = TRNSEQ;
    }

    public String getCXANUM() {
        return CXANUM;
    }

    public void setCXANUM(String CXANUM) {
        this.CXANUM = CXANUM;
    }

    public void setFZDCOD(String FZDCOD) {
        this.FZDCOD = FZDCOD;
    }

    public String getFZDSEQ() {
        return FZDSEQ;
    }

    public void setFZDSEQ(String FZDSEQ) {
        this.FZDSEQ = FZDSEQ;
    }

    public String getAGECOD() {
        return AGECOD;
    }

    public void setAGECOD(String AGECOD) {
        this.AGECOD = AGECOD;
    }

    public String getFZDTRCCTR() {
        return FZDTRCCTR;
    }

    public void setFZDTRCCTR(String FZDTRCCTR) {
        this.FZDTRCCTR = FZDTRCCTR;
    }

    /**
     * @return the FZDDES
     */
    public String getFZDDES() {
        return FZDDES;
    }

    /**
     * @param FZDDES the FZDDES to set
     */
    public void setFZDDES(String FZDDES) {
        this.FZDDES = FZDDES;
    }

    /**
     * @return the FZDTRO
     */
    public BigDecimal getFZDTRO() {
        return FZDTRO;
    }

    /**
     * @param FZDTRO the FZDTRO to set
     */
    public void setFZDTRO(BigDecimal FZDTRO) {
        this.FZDTRO = FZDTRO;
    }

    /**
     * @return the SEQFZD
     */
    public String getSEQFZD() {
        return SEQFZD;
    }

    /**
     * @param SEQFZD the SEQFZD to set
     */
    public void setSEQFZD(String SEQFZD) {
        this.SEQFZD = SEQFZD;
    }

    /**
     * @return the FZDCTRVAL
     */
    public BigDecimal getFZDCTRVAL() {
        return FZDCTRVAL;
    }

    /**
     * @param FZDCTRVAL the FZDCTRVAL to set
     */
    public void setFZDCTRVAL(BigDecimal FZDCTRVAL) {
        this.FZDCTRVAL = FZDCTRVAL;
    }
    
    
}
