/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import digitacupomfx.dao.FinalizadoraDAO;
import digitacupomfx.entidades.Finalizacao;
import digitacupomfx.entidades.Finalizadora;
import digitacupomfx.utils.MaskFieldUtil;
import digitacupomfx.utils.MaskTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLFinalizacaoController implements Initializable {
    
    
    private List<Finalizacao> listFinalizacao = new ArrayList();
    
    private ObservableList<Finalizacao> ObservableListFinalizacao;    
    
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
    private TableColumn<Finalizacao, String> seq;

    @FXML
    private TableColumn<Finalizacao, String> finalizadora;

    @FXML
    private TableColumn<Finalizacao, BigDecimal> recebido;

    @FXML
    private TableColumn<Finalizacao, BigDecimal> troco;

    @FXML
    private JFXButton btnGravarFinalização;

    @FXML
    private Label lbTotalFinalizacao;

    @FXML
    void gravarFinalizacao(ActionEvent event) {

    }

    @FXML
    void inserirFinalizacao(ActionEvent event) {
        Finalizacao finalizacao = new Finalizacao();
        finalizacao.setTRNDAT(txTrndat.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        finalizacao.setTRNSEQ(txSequencial.getText());
        finalizacao.setCXANUM(txCxanum.getText());
        finalizacao.setFZDSEQ(txSeqFinalizadora.getText());
        finalizacao.setFZDCOD(cbFinalizadora.getValue().toString().substring(0,3));
        finalizacao.setFZDDES(cbFinalizadora.getValue().toString().substring(4));
        finalizacao.setFZDVLR(new BigDecimal(txVlrRecebido.getText().replace(",", ".")));
        finalizacao.setFZDTRO(new BigDecimal(txTroco.getText().replace(",", ".")));
        
        preencherTabela(finalizacao);
    }

    @FXML
    void removerFinalizacao(ActionEvent event) {

    }

    @FXML
    void teclaEnterSequencial(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seq = txSequencial.getText();
            txSequencial.setText(StringUtils.leftPad(seq, 6, "0"));
            txCxanum.requestFocus();
        }
    }

    @FXML
    void teclaEnterTroco(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txTroco.getText().equals("")){
                txTroco.setText("0,00");
            }
        }
    }
    
    @FXML
    void teclaEnterCxanum(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String cxa = txCxanum.getText();
            txCxanum.setText(StringUtils.leftPad(cxa, 3, "0"));
            txSeqFinalizadora.requestFocus();
        }
    }
    
    @FXML
    void teclaEnterSeqFinalizadora(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seqFin = txSeqFinalizadora.getText();
            txSeqFinalizadora.setText(StringUtils.leftPad(seqFin, 3, "0"));
            cbFinalizadora.requestFocus();
        }
    }

    @FXML
    void teclaEnterValorRecebido(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txVlrRecebido.getText().equals("")){
                txVlrRecebido.setText("0,00");
            }
            txTroco.requestFocus();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.monetaryField(txTroco);
        MaskFieldUtil.monetaryField(txVlrRecebido);
        MaskFieldUtil.numericField(txCxanum);
        MaskFieldUtil.numericField(txSeqFinalizadora);
        
        txTroco.setText("0,00");
        txVlrRecebido.setText("0,00");
        
        preencherFinalizadoras();
    }    

    private void preencherTabela(Finalizacao finalizacao) {
        seq.setCellValueFactory(new PropertyValueFactory<>("FZDSEQ"));
        finalizadora.setCellValueFactory(new PropertyValueFactory<>("FZDDES"));
        recebido.setCellValueFactory(new PropertyValueFactory<>("FZDVLR"));
        troco.setCellValueFactory(new PropertyValueFactory<>("FZDTRO"));
        
        listFinalizacao.add(finalizacao);
        
        ObservableListFinalizacao = FXCollections.observableArrayList(listFinalizacao);
        tbFinalizacao.setItems(ObservableListFinalizacao);
    }

    private void preencherFinalizadoras() {
        FinalizadoraDAO dao = new FinalizadoraDAO();
        List<Finalizadora> listFinalizadora = dao.listarFinalizadora();
        for (Finalizadora finalizadora : listFinalizadora) {
            cbFinalizadora.getItems().add(finalizadora.getFzdcod()+"-"+finalizadora.getFzddes());
        }
    }
    
}
