/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;

import digitacupomfx.entidades.Finalizacao;
import digitacupomfx.utils.ConnectionFatory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.resource.cci.ConnectionFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author glerisonlima
 */
public class FinalizacaoDAO {
    
    
    public void insereFinalizacao(Finalizacao ent) {
        Connection conn = null;
        try {
            conn = new ConnectionFatory().getConection();
            String sql = ("INSERT INTO FINALIZACAO (TRNSEQ, CXANUM, FZDCOD, TRNDAT, FZDSEQ, FZDVLR, "
                    + "FZDCTRVAL) VALUES (?,?,?,?,?,?,?)");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, ent.getTRNSEQ());
            pst.setString(2, ent.getCXANUM());
            pst.setString(3, ent.getFZDCOD());
            pst.setString(4, ent.getTRNDAT());
            pst.setString(5, ent.getFZDSEQ());
            pst.setBigDecimal(6, ent.getFZDVLR());
            pst.setString(7, ent.getFZDTRCCTR());
            pst.execute();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Finalizadora " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Conexão - " + ex.getMessage());
            }
        }
    }
}
