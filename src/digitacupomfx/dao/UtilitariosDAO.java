/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.dao;


import digitacupomfx.utils.ConnectionFatory;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author glerisonlima
 */
public class UtilitariosDAO {
    
    public boolean ZerarChave(){
        try {
            Connection conn  = new ConnectionFatory().getConection();
            String sql = "UPDATE PROPRIO SET PRPCHVANT='', PRPCHV=''";
            PreparedStatement pst = conn.prepareStatement(sql);            
            pst.execute();
            pst.close();     
            conn.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao Limpar Chave, Erro: "+ex.getMessage());
            return false;
        }
    }
    
    
}
