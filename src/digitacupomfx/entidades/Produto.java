/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.entidades;

import java.math.BigDecimal;

/**
 *
 * @author glerisonlima
 */
public class Produto {
    private String codigo;
    private String codigoAux;
    private String descricao;
    private BigDecimal preco;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoAux
     */
    public String getCodigoAux() {
        return codigoAux;
    }

    /**
     * @param codigoAux the codigoAux to set
     */
    public void setCodigoAux(String codigoAux) {
        this.codigoAux = codigoAux;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    
}
