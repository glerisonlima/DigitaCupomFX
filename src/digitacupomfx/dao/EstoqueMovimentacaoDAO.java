/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;

import digitacupomfx.entidades.Estoque;
import digitacupomfx.utils.ConnectionFatory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author glerisonlima
 */
public class EstoqueMovimentacaoDAO {
    public void movEstoque(Estoque estoque){
        Connection conn = null;
        try {
            conn = new ConnectionFatory().getConection();
            String sql = ("INSERT INTO ESTOQUE_MOVIMENTACAO (LOCCOD, PROCOD, MOVDAT, MOVQTD, MOVTIP, MOVPRC, "
                    + "MOVDOC) VALUES (?,?,?,?,?,?,?)");
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, estoque.getLoccod());
            pst.setString(2, estoque.getProcod());
            pst.setString(3, estoque.getMovdat());
            pst.setString(4, estoque.getMovqtd());
            pst.setString(5, estoque.getMovtip());
            pst.setString(6, estoque.getMovprc());
            pst.setString(7, estoque.getMovdoc());
            pst.execute();
            pst.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Estoque_movimentação " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Conexão - " + ex.getMessage());
            }
        }
    }
}
