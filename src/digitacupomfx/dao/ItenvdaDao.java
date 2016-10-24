/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;


import digitacupomfx.entidades.Itenvda;
import digitacupomfx.entidades.Produto;
import digitacupomfx.entidades.Tributacao;
import digitacupomfx.utils.ConnectionFatory;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author glerison
 */
public class ItenvdaDao {

    public void insereItenvda(Itenvda mod) {
            Connection conn = null;
        try {
            conn  = new ConnectionFatory().getConection();
            String sql = ("INSERT INTO ITEVDA (TRNSEQ, CXANUM, TRNDAT, LOJCOD, FUNCOD, PROCOD, "
                    + "ITVQTDVDA, ITVVLRUNI, ITVTIP,ITVTRBID, ITVVLRDCN, ITVVLRACR, ITVVLRTOT,ITVSEQ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, mod.getTxtrnseq());
            pst.setString(2, mod.getTxcxanum());
            pst.setString(3, mod.getTxtrndat());
            pst.setString(4, mod.getTxlojcod());
            pst.setString(5, mod.getTxfuncod());
            pst.setString(6, mod.getTxprocod());
            pst.setBigDecimal(7, mod.getTxitenquant());
            pst.setBigDecimal(8, mod.getTxvlrunit());
            pst.setString(9, mod.getItvtip());
            pst.setString(10, mod.getTxtributacao());
            pst.setBigDecimal(11, mod.getTxdesconto());
            pst.setBigDecimal(12, mod.getTxacrecimo());
            pst.setBigDecimal(13, mod.getTxtotal());
            pst.setString(14, mod.getItvseq());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItenvdaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Produto na Tabela Itevda" + ex.getMessage());
            return;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Conexão - " + ex.getMessage());
            }
        }

    }

    public List<Itenvda> localizarItens(Itenvda i) {
        Connection conn = null;
        List<Itenvda> lista = new LinkedList<Itenvda>();
        try {
            conn  = new ConnectionFatory().getConection();
            String sql = "select * from itevda inner join produto on itevda.procod=produto.procod WHERE TRNSEQ=? AND CXANUM=? AND TRNDAT=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, i.getTxtrnseq());
            pstm.setString(2, i.getTxcxanum());
            pstm.setString(3, i.getTxtrndat());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Itenvda it = new Itenvda();
                it.setTxcxanum(rs.getString("CXANUM"));
                it.setItvtip(rs.getString("itvtip"));
                it.setTxprodes(rs.getString("prodes"));
                it.setTxacrecimo(rs.getBigDecimal("itvvlracr"));
                it.setTxdesconto(rs.getBigDecimal("itvvlrdcn"));
                it.setTxfuncod(rs.getString("funcod"));
                it.setTxitenquant(rs.getBigDecimal("itvqtdvda"));
                it.setTxlojcod(rs.getString("lojcod"));
                it.setTxprocod(rs.getString("procod"));
                it.setTxtotal(rs.getBigDecimal("itvvlrtot"));
                it.setTxtributacao(rs.getString("itvtrbid"));
                it.setTxtrndat(rs.getString("trndat"));
                it.setTxtrnseq(rs.getString("trnseq"));
                it.setTxvlrunit(rs.getBigDecimal("itvvlruni"));
                lista.add(it);
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Conexão - " + ex.getMessage());
            }
            return lista;
        }

    }
    
    public List<Tributacao> buscarTributacao(){
        Tributacao trib = new Tributacao();
        List<Tributacao> lista = new LinkedList<>();
        Connection conn = null;
        try{
            conn  = new ConnectionFatory().getConection();
            String sql = "select trbid from tributacao";
            PreparedStatement pstm = conn.prepareStatement(sql);            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                trib = new Tributacao();
                trib.setTrbid(rs.getString("trbid"));
                lista.add(trib);
               
            } 
            rs.close();
            pstm.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erros ao consultar Transacao :( - "+e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Conexão - " + ex.getMessage());
            }
            return lista;
        }
    }
    
    public Produto buscarProdutoCodigo(String codigo){
        Produto p = new Produto();
        try {
            Connection conn = new ConnectionFatory().getConection();
            String SQL = "select produto.procod, produto.prodesrdz,produto.proprc1,produtoaux.procodaux from produto inner join produtoaux on produto.procod = produtoaux.procod where produto.procod=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){                
                p.setCodigo(rs.getString("procod"));
                p.setCodigoAux(rs.getString("procodaux"));
                p.setDescricao(rs.getString("prodesrdz"));
                p.setPreco(rs.getBigDecimal("proprc1"));        
            }else {
                String SQL2 = "select produto.procod, produto.prodesrdz,produto.proprc1,produtoaux.procodaux from produto inner join produtoaux on produto.procod = produtoaux.procod where produtoaux.procodaux=?";
                PreparedStatement pstm2 = conn.prepareStatement(SQL2);
                pstm2.setString(1, codigo);
                ResultSet rs2 = pstm.executeQuery();
                if(rs2.next()){                
                    p.setCodigo(rs2.getString("procod"));
                    p.setCodigoAux(rs2.getString("procodaux"));
                    p.setDescricao(rs2.getString("prodesrdz"));
                    p.setPreco(rs2.getBigDecimal("proprc1"));        
                }
            }
            rs.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItenvdaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public Produto buscarProdutoCodBarras(String codigo){
        Produto p = new Produto();
        try {
            Connection conn = new ConnectionFatory().getConection();
            String SQL = "select produto.procod, produto.prodesrdz,produto.proprc1,produtoaux.procodaux from produto inner join produtoaux on produto.procod = produtoaux.procod where produtoaux.procodaux=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                
                p.setCodigo(rs.getString("procod"));
                p.setCodigoAux(rs.getString("procodaux"));
                p.setDescricao(rs.getString("prodesrdz"));
                p.setPreco(rs.getBigDecimal("proprc1"));
                
            
            }
            rs.close();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItenvdaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    
}
