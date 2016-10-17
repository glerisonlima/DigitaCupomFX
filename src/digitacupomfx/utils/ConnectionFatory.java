/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author glerisonlima
 */
public class ConnectionFatory {
    public Connection getConection(){
        try{
            return DriverManager.getConnection("jdbc:firebirdsql://localhost:3050/C:/Syspdv/SYSPDV_SRV.FDB", "SYSDBA", "masterkey");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
