/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLTransacaoPaneController implements Initializable {

    @FXML
    private JFXDatePicker txData;

    @FXML
    private JFXDatePicker txHora;

    @FXML
    private TextField txClicod;

    @FXML
    private TextField txFuncod;

    @FXML
    private TextField txSequencial;

    @FXML
    private TextField txCaixa;

    @FXML
    private TextField txCoo;

    @FXML
    private TextField txLoja;

    @FXML
    private TextField txEcf;

    @FXML
    private TextField txSerieEcf;

    @FXML
    private Label lbNomeCliente;

    @FXML
    private TextField txNomeCliente;

    @FXML
    private TextField txNomeFuncionario;

    @FXML
    private JFXComboBox<?> cbImpressora;

    @FXML
    private JFXComboBox<?> cbTipoTransacao;

    @FXML
    private TextField txChaveSat;

    @FXML
    private TextField txNfceNum;

    @FXML
    private TextField txNfceChave;

    @FXML
    private TextField txNfceProtocolo;

    @FXML
    private TextField txNfceNumSerie;

    @FXML
    private JFXCheckBox rdLogVarejo;

    @FXML
    private JFXCheckBox rdLogMilenio;

    @FXML
    private JFXCheckBox rdMovEstoque;

    @FXML
    private TextField txValor;

    @FXML
    private TextField txDesconto;

    @FXML
    private TextField txAcrecimo;

    @FXML
    private JFXButton btnGravarTransacao;

    @FXML
    private JFXButton btnLimparCampos;

    @FXML
    void gravarTransacao(ActionEvent event) {

    }

    @FXML
    void limparCampos(ActionEvent event) {

    }

    @FXML
    void teclaEnterAcrecimo(KeyEvent event) {

    }

    @FXML
    void teclaEnterCaixa(KeyEvent event) {

    }

    @FXML
    void teclaEnterChaveCfe(KeyEvent event) {

    }

    @FXML
    void teclaEnterChaveNfce(KeyEvent event) {

    }

    @FXML
    void teclaEnterCliente(KeyEvent event) {

    }

    @FXML
    void teclaEnterCoo(KeyEvent event) {

    }

    @FXML
    void teclaEnterDesconto(KeyEvent event) {

    }

    @FXML
    void teclaEnterEcf(KeyEvent event) {

    }

    @FXML
    void teclaEnterFuncionario(KeyEvent event) {

    }

    @FXML
    void teclaEnterLoja(KeyEvent event) {

    }

    @FXML
    void teclaEnterNumNfce(KeyEvent event) {

    }

    @FXML
    void teclaEnterNumSerieNfce(KeyEvent event) {

    }

    @FXML
    void teclaEnterProtocoloNfce(KeyEvent event) {

    }

    @FXML
    void teclaEnterSequencial(KeyEvent event) {

    }

    @FXML
    void teclaEnterSerieEcf(KeyEvent event) {

    }

    @FXML
    void teclaEnterValor(KeyEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
