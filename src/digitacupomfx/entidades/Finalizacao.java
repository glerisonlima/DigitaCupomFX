/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package digitacupomfx.entidades;


public class Finalizacao {
    String FZDCOD, FZDSEQ, AGECOD, FZDTRCCTR, TRNSEQ, CXANUM, TRNDAT;
    double FZDCTRVAL, FZDVLR;

    public String getTRNDAT() {
        return TRNDAT;
    }

    public void setTRNDAT(String TRNDAT) {
        this.TRNDAT = TRNDAT;
    }

    public double getFZDVLR() {
        return FZDVLR;
    }

    public void setFZDVLR(double FZDVLR) {
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

    public double getFZDCTRVAL() {
        return FZDCTRVAL;
    }

    public void setFZDCTRVAL(double FZDCTRVAL) {
        this.FZDCTRVAL = FZDCTRVAL;
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
    
    
}
