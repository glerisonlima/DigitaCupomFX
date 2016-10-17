/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;

import digitacupomfx.entidades.Impressora;
import digitacupomfx.utils.ConnectionFatory;
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
 * @author glerisonlima
 */
public class ImpressoraDAO {
    
    public List<Impressora> listaImpressoras(){
    Connection conn = null;
        List<Impressora> lista = new LinkedList<>();
        try{
        conn = new ConnectionFatory().getConection();    
        String sql = "select * from ecf_modelo";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
            Impressora imp = new Impressora();
            imp.setEcfcod(rs.getInt("ecfcod"));
            imp.setEcfdes(rs.getString("ecfdes"));
            lista.add(imp);
            }
            pstm.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImpressoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}
