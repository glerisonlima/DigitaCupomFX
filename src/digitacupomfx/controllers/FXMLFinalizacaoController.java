/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import digitacupomfx.entidades.Finalizacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLFinalizacaoController implements Initializable {
    
    @FXML
    private TextField txSequencial;

    @FXML
    private JFXDatePicker txTrndat;

    @FXML
    private TextField txCxanum;

    @FXML
    private TextField txSeqFinalizadora;

    @FXML
    private JFXComboBox cbFinalizadora;

    @FXML
    private TextField txVlrRecebido;

    @FXML
    private JFXButton btnInserirFinalizacao;

    @FXML
    private JFXButton btnRemoverFinalizacao;

    @FXML
    private TextField txTroco;

    @FXML
    private TableView<Finalizacao> tbFinalizacao;

    @FXML
    private JFXButton btnGravarFinalização;

    @FXML
    private Label lbTotalFinalizacao;

    @FXML
    void gravarFinalizacao(ActionEvent event) {

    }

    @FXML
    void inserirFinalizacao(ActionEvent event) {

    }

    @FXML
    void removerFinalizacao(ActionEvent event) {

    }

    @FXML
    void teclaEnterSequencial(KeyEvent event) {

    }

    @FXML
    void teclaEnterTroco(KeyEvent event) {

    }

    @FXML
    void teclaEnterValorRecebido(KeyEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
