/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package digitacupomfx.entidades;

import java.math.BigDecimal;

/**
 *
 * @author glerison
 */
public class Itenvda {
    
    private String Txprocod;
    private String Txcxanum;
    private String Txprodes;    
    private String Txfuncod;
    private String Txlojcod;
    private String Itvtip; 
    private String Txtrndat;
    private String Txtrnseq;
    private String Txtributacao;
    private String itvseq;
    private BigDecimal Txitenquant,Txvlrunit, Txdesconto, Txacrecimo,Txtotal;

    
    public Itenvda() {
       
    }

    public Itenvda(String Txprocod, String Txcxanum, String Txprodes, String Txfuncod, BigDecimal Txitenquant, String Txlojcod, String Itvtip, String Txtrndat, String Txtrnseq, String Txtributacao, BigDecimal Txvlrunit, BigDecimal Txdesconto, BigDecimal Txacrecimo, BigDecimal Txtotal) {
        this.Txprocod = Txprocod;
        this.Txcxanum = Txcxanum;
        this.Txprodes = Txprodes;
        this.Txfuncod = Txfuncod;
        this.Txitenquant = Txitenquant;
        this.Txlojcod = Txlojcod;
        this.Itvtip = Itvtip;
        this.Txtrndat = Txtrndat;
        this.Txtrnseq = Txtrnseq;
        this.Txtributacao = Txtributacao;
        this.Txvlrunit = Txvlrunit;
        this.Txdesconto = Txdesconto;
        this.Txacrecimo = Txacrecimo;
        this.Txtotal = Txtotal;
    }
    
    
    

    public BigDecimal getTxtotal() {
        return Txtotal;
    }

    public void setTxtotal(BigDecimal Txtotal) {
        this.Txtotal = Txtotal;
    }
       
    public BigDecimal getTxacrecimo() {
        return Txacrecimo;
    }

    public void setTxacrecimo(BigDecimal Txacrecimo) {
        this.Txacrecimo = Txacrecimo;
    }

    public String getTxtributacao() {
        return Txtributacao;
    }

    public void setTxtributacao(String Txtributacao) {
        this.Txtributacao = Txtributacao;
    }
    

    public BigDecimal getTxdesconto() {
        return Txdesconto;
    }

    public void setTxdesconto(BigDecimal Txdesconto) {
        this.Txdesconto = Txdesconto;
    }
    
    public String getTxprodes() {
        return Txprodes;
    }    

    public void setTxprodes(String Txprodes) {
        this.Txprodes = Txprodes;
    }
    
     public String getItvtip() {
        return Itvtip;
    }

    public void setItvtip(String Itvtip) {
        this.Itvtip = Itvtip;
    }

    public String getTxcxanum() {
        return Txcxanum;
    }

    public void setTxcxanum(String Txcxanum) {
        this.Txcxanum = Txcxanum;
    }

    public String getTxfuncod() {
        return Txfuncod;
    }

    public void setTxfuncod(String Txfuncod) {
        this.Txfuncod = Txfuncod;
    }

    public BigDecimal getTxitenquant() {
        return Txitenquant;
    }

    public void setTxitenquant(BigDecimal Txitenquant) {
        this.Txitenquant = Txitenquant;
    }

    public String getTxlojcod() {
        return Txlojcod;
    }

    public void setTxlojcod(String Txlojcod) {
        this.Txlojcod = Txlojcod;
    }

    public String getTxprocod() {
        return Txprocod;
    }

    public void setTxprocod(String Txprocod) {
        this.Txprocod = Txprocod;
    }

    

    public String getTxtrndat() {
        return Txtrndat;
    }

    public void setTxtrndat(String Txtrndat) {
        this.Txtrndat = Txtrndat;
    }

    public String getTxtrnseq() {
        return Txtrnseq;
    }

    public void setTxtrnseq(String Txtrnseq) {
        this.Txtrnseq = Txtrnseq;
    }

    public BigDecimal getTxvlrunit() {
        return Txvlrunit;
    }

    public void setTxvlrunit(BigDecimal Txvlrunit) {
        this.Txvlrunit = Txvlrunit;
    }

    /**
     * @return the itvseq
     */
    public String getItvseq() {
        return itvseq;
    }

    /**
     * @param itvseq the itvseq to set
     */
    public void setItvseq(String itvseq) {
        this.itvseq = itvseq;
    }
    
    
}
