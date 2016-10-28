/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.events.JFXDialogEvent;
import digitacupomfx.dao.UtilitariosDAO;
import digitacupomfx.utils.RealizaBackup;
import digitacupomfx.utils.RealizaBakupThread;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLUtilitariosController implements Initializable {

    @FXML
    private StackPane stackPane;
    
    @FXML
    private JFXButton btnBackup;

    @FXML
    private JFXButton btnZerarChave;
    
    @FXML
    private JFXProgressBar barra;
    
    @FXML
    private Label labelCarregando;
    
    @FXML
    private Label labelCaminho;

    @FXML
    void fazerBackup(ActionEvent event) { 
        
        File origem = new File("c:/syspdv/syspdv_srv.fdb");
        File destino = new File("c:/syspdv/backup_srv.fdb");
        
        try{
            Runtime.getRuntime().exec("NET STOP 'Firebird Guardian - DefaultInstance'");
            Runtime.getRuntime().exec("NET STOP 'FireBird Server - DefaultInstance'");
            System.out.println("Serviço parado com sucesso!");
        }catch(Exception e){
            System.out.println("Erro ao parrar serviço do firebird, Erro: "+e.getMessage());
        }
               
        
        try {          
            
            //criando um classe anônima Service que cria uma Task que também é anônima
            //a classe Service serve para gerenciar threads em JavaFX 
            
            Service<Void> servico = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Void call() throws Exception {
                            barra.setVisible(true);
                            if(destino.exists())
                            destino.delete();   

                        InputStream in = new FileInputStream(origem);

                        OutputStream out = new FileOutputStream(destino);
                        long tamanho = origem.length();
                        System.out.println("Tamanho: "+tamanho);
                        updateMessage("Iniciando...");
                        byte[] buf = new byte[1024];
                        int len;
                        long progresso = 0;
                            updateProgress(1, tamanho);
                        while((len = in.read(buf))>0){
                            out.write(buf, 0, len);
                            progresso += buf.length;
                            //System.out.println("Processo: "+buf.length);
                            System.out.println("Processo: "+progresso);
                            updateProgress(progresso, tamanho);
                            updateMessage("Realizado "+progresso*100/tamanho+"%");
                            //dialog.show();
                        }
                        in.close();
                        out.close();
                            //Task tem duas property interessantes para usar junto a um ProgressBar
                            //a messageProperty, que pode ser ligada a outra StringProperty
                            //para transmitir uma mensagem,
                            //e a progressProperty, que serve para mandar valores númericos a uma
                            //ProgressBar ou ProgressIndicator
                            updateMessage("Carregando...");
                            /*
                            Thread.sleep(300);
                            updateProgress(1, 10);
                            for (int i = 0; i < 10; i++) {
                                updateProgress(i + 1, 10);
                                updateMessage("Carregando " + (i + 1) + " de 10");
                                Thread.sleep(300);
                            }*/
                            barra.setVisible(false);
                            updateMessage("Concluido: c:/syspdv/backup_srv.fdb");
                                try{
                                    Runtime.getRuntime().exec("NET START 'FireBird Server - DefaultInstance'");
                                    Runtime.getRuntime().exec("NET START 'Firebird Guardian - DefaultInstance'");
                                    System.out.println("Serviço iniciado com sucesso!");
                                }catch(Exception e){
                                    System.out.println("Erro ao parrar serviço do firebird, Erro: "+e.getMessage());
                                }
                            return null;
                        }
                        
                    };
                    
                }
                
            };
            //fazendo o bind (ligando) nas proprety
            
            labelCarregando.textProperty().bind(servico.messageProperty());
            labelCarregando.setTextAlignment(TextAlignment.CENTER);
            barra.progressProperty().bind(servico.progressProperty());
            //precisa inicializar o Service
            servico.restart();
            
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLUtilitariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private class BackupTask extends Task<Void>{

        private String url;
        
        public BackupTask(String url) {
            this.url = url;
        }    

        public BackupTask() {
        }
        
        
        
        @Override
        protected Void call() throws Exception {
            /*String ext = url;
            URLConnection connection = new URL(url).openConnection();
            long fileLength = connection.getContentLengthLong();
            File src = new File("c:/syspdv/syspdv_srv.fdb");
            File dst = new File("c:/syspdv/backup_srv.fdb");
            FileInputStream  in = new FileInputStream(src);
            FileOutputStream  out = new FileOutputStream(dst);
            FileChannel inChanel = in.getChannel();
            FileChannel outChanel = out.getChannel();
            try(InputStream is = in.ge;
                    outChanel.transferFrom(inChanel, 0, inChanel.size())){
                //Files.copy(is, Paths.get("downloadedfile"+ext));
                
                long nread = 0L;
                byte[] buf = new byte[8192];
                int n;
                while ((n = is.read(buf)) > 0) {
                    os.write(buf, 0, n);
                    nread += n;
                    updateProgress(nread, fileLength);
                }
            }*/
            String src = "c:/syspdv/syspdv_srv.fdb";
            String dst = "c:/syspdv/backup_srv.fdb";
            Path path1 = Paths.get(src);
            Path path2 = Paths.get(dst);

            try {
                Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException ex) {
                Logger.getLogger(FXMLUtilitariosController.class.getName()).log(Level.SEVERE, null, ex);

            }
            
            return null;
        }

        @Override
        protected void failed() {
            System.out.println("Falha");
        }

        @Override
        protected void succeeded() {
            System.out.println("concluido!");
            mensagemConfirma("Backup Concluido!", "Backup realizado com sucesso");
        }
        
        
    }
    
    private void mostrar(JFXDialog dialog){
        dialog.show();
    }
    private void fechar(JFXDialog dialog){
        dialog.close();
    }
    
    
    

    @FXML
    void zerarChave(ActionEvent event) {
        UtilitariosDAO dao = new UtilitariosDAO();
        if(dao.ZerarChave()){
            mensagemConfirma("Zerar Chave", "Chave foi excluida com sucesso!");
        }else{
            mensagemAlerta("Zerar Chave", "Problema ao excluir chave do banco de dados");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void mensagemAlerta(String titulo, String mensagem){
        Image img = new Image("digitacupomfx/imagens/alert-octagon.png");
        JFXDialogLayout context = new JFXDialogLayout();
        context.setHeading(new ImageView(img),new Text("               "+titulo));
        context.setBody(new Text(mensagem));
        JFXDialog dialog = new JFXDialog(stackPane, context, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        context.setActions(button);
        dialog.show();
    }
    
    public void mensagemConfirma(String titulo, String mensagem){
        Image img = new Image("digitacupomfx/imagens/thumb-up.png");
        JFXDialogLayout context = new JFXDialogLayout();
        context.setHeading(new ImageView(img),new Text("               "+titulo));
        context.setBody(new Text(mensagem));
        JFXDialog dialog = new JFXDialog(stackPane, context, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        context.setActions(button);
        dialog.show();
    }
    
    public void mensagemCarregando(){
        Image img = new Image("digitacupomfx/imagens/carregando.gif");
        JFXDialogLayout context = new JFXDialogLayout();
        context.setHeading(new ImageView(img));
        context.setBody(new Text());
        JFXDialog dialog = new JFXDialog(stackPane, context, JFXDialog.DialogTransition.CENTER);        
        dialog.show();
    }
    
    
    
}
