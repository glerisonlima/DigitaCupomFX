/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;

import digitacupomfx.entidades.Transacao;
import digitacupomfx.utils.ConnectionFatory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author glerisonlima
 */
public class TransacaoDAO {
    
    public boolean cadastrar(Transacao transacao){
        try {           
            
            Connection conn  = new ConnectionFatory().getConection();
            System.out.println("Conectado");
            String sql = ("INSERT INTO TRANSACAO (CXANUM,TRNSEQ,TRNDAT,CLICOD,LOJCOD,VERSAO,TRNHOR,CXANUMEQP,FUNCOD,TRNCTREST,TRNTRF,TRNFUNAUT"
                    + ",TRNLOGWEB,TRNLOG,TRNIMPCOD,TRNTIP,TRNVLR,TRNSEQEQP,TRNSEREQP, TRNDCN, TRNACR,TRNNFCENUM,TRNNFCECHVNFE,TRNNFCENUMPROT,TRNNFCESER,TRNCHVCFE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, transacao.getTxCaixa());
            pst.setString(2, transacao.getTxSequencial());
            pst.setString(3, transacao.getTxData());
            pst.setString(4, transacao.getTxClicod());
            pst.setString(5, transacao.getTxLoja());
            pst.setString(6, transacao.getTxVersao());
            pst.setString(7, transacao.getTxHora());
            pst.setString(8, transacao.getTxEcf());
            pst.setString(9, transacao.getTxFuncod());
            pst.setString(10, transacao.getRdMovEstoque());
            pst.setString(11, transacao.getTxTrntrf());
            pst.setString(12, transacao.getTxFuncod());
            pst.setString(13, transacao.getRdLogVarejo());
            pst.setString(14, transacao.getRdLogMilenio());
            pst.setString(15, transacao.getCbImpressora());
            pst.setString(16, transacao.getCbTipoTransacao());
            pst.setBigDecimal(17, transacao.getTxValor());
            pst.setString(18, transacao.getTxCoo());
            pst.setString(19, transacao.getTxSerieEcf());
            pst.setBigDecimal(20, transacao.getTxDesconto());
            pst.setBigDecimal(21, transacao.getTxAcrecimo());
            pst.setString(22, transacao.getTxNfceNum());
            pst.setString(23, transacao.getTxNfceChave());
            pst.setString(24, transacao.getTxNfceProtocolo());
            pst.setString(25, transacao.getTxNfceNumSerie());
            pst.setString(26, transacao.getTxChaveSat());               
            
            pst.execute();
            pst.close();     
            conn.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar transação! Erro: "+ex.getMessage());
            return false;            
        }
    }
    
    public String versao(){
        String versao = "";
        try {
            Connection conn  = new ConnectionFatory().getConection();
            String sql = "SELECT PRPVER FROM PROPRIO";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            versao = rs.getString("PRPVER");
            }
            pst.execute();
            pst.close();     
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return versao;
    }
    
    public String clienteByCodigo(String codigo){
        String cliente = "";
        try {
            Connection conn  = new ConnectionFatory().getConection();
            String sql = "SELECT CLIDES FROM CLIENTE WHERE CLICOD=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            cliente = rs.getString("CLIDES");
            }else{
                JOptionPane.showMessageDialog(null, "Cliente não localizado!");
            }
            pst.execute();
            pst.close();     
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public String funcionarioByCodigo(String codigo){
        String funcionario = "";
        try {
            Connection conn  = new ConnectionFatory().getConection();
            String sql = "SELECT FUNAPE FROM FUNCIONARIO WHERE FUNCOD=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            funcionario = rs.getString("FUNAPE");
            }else{
                JOptionPane.showMessageDialog(null, "Funcionario não localizado!");
            }
            pst.execute();
            pst.close();     
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionario;
    }
    
    
    
}
