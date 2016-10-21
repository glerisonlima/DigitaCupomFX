/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;

import digitacupomfx.entidades.Finalizadora;
import digitacupomfx.utils.ConnectionFatory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author glerison
 */
public class FinalizadoraDAO {

    public List<Finalizadora> listarFinalizadora() {
        Connection conn = null;
        List<Finalizadora> lista = new LinkedList<>();
        try{
        conn = new ConnectionFatory().getConection();    
        String sql = "select fzdcod, fzddes from finalizadora order by fzdcod";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
            Finalizadora fin = new Finalizadora();
            fin.setFzdcod(rs.getString("FZDCOD"));
            fin.setFzddes(rs.getString("FZDDES"));
            lista.add(fin);
            }
            pstm.close();
            rs.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
        }finally{
            try {
                conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: "+ex.getMessage());
            }
        }
        return lista;
    }
    
}
