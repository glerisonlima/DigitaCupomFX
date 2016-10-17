/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author glerison
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private JFXButton btnCupom;

    @FXML
    private JFXButton btnTransacao;

    @FXML
    private JFXButton btnItens;

    @FXML
    private JFXButton btnFinalizacao;

    @FXML
    private JFXButton btnBackup;

    @FXML
    private JFXButton btnUtilitarios;

    @FXML
    private JFXButton btnSair;
    
    @FXML
    private AnchorPane pane;

    @FXML
    void AbrirCupom(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLCupomController.class.getResource("/digitacupomfx/viwes/FXMLCupom.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());      
    }

    @FXML
    void abrirBackup(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLBackupController.class.getResource("/digitacupomfx/viwes/FXMLBackup.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }

    @FXML
    void abrirFinalizacao(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLFinalizacaoController.class.getResource("/digitacupomfx/viwes/FXMLFinalizacao.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }

    @FXML
    void abrirItens(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLItensController.class.getResource("/digitacupomfx/viwes/FXMLItens.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }

    @FXML
    void abrirTransacao(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLTransacaoController.class.getResource("/digitacupomfx/viwes/FXMLTransacao.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }

    @FXML
    void abrirUtilitarios(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLUtilitariosController.class.getResource("/digitacupomfx/viwes/FXMLUtilitarios.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }
    
    public void LimparTela(String caminho) throws IOException{
        FXMLLoader load = new FXMLLoader();
        load.setLocation(FXMLUtilitariosController.class.getResource(caminho));
        this.pane.getChildren().clear();
        this.pane.getChildren().addAll((AnchorPane) load.load());
    }

    @FXML
    void sair(ActionEvent event) {
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
