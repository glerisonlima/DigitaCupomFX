/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.utils;

import digitacupomfx.controllers.FXMLUtilitariosController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author glerisonlima
 */
public class RealizaBakupThread extends Thread{

    @Override
    public void run() {
        String src = "c:/syspdv/syspdv_srv.fdb";
        String dst = "c:/syspdv/backup_srv.fdb";
        Path path1 = Paths.get(src);
        Path path2 = Paths.get(dst);
        
        try {
            Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
            
        } catch (IOException ex) {
            System.out.println("Erro ao fazer bakup, Erro: "+ex.getMessage());
            
        }
    }
    
}
