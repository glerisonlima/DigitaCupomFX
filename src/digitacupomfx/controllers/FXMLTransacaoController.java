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
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import digitacupomfx.dao.ImpressoraDAO;
import digitacupomfx.dao.TipoTransacaoDAO;
import digitacupomfx.dao.TransacaoDAO;
import digitacupomfx.entidades.Impressora;
import digitacupomfx.entidades.Tipotransacao;
import digitacupomfx.entidades.Transacao;
import digitacupomfx.utils.MaskFieldUtil;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLTransacaoController implements Initializable {

    
    
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
    private TextField txNomeCliente;

    @FXML
    private TextField txNomeFuncionario;

    @FXML
    private JFXComboBox cbImpressora;

    @FXML
    private JFXComboBox cbTipoTransacao;

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
    private Label lbNomeCliente;
    
    @FXML
    private AnchorPane ancorpane;
    
    @FXML
    private StackPane stackPane;
    
    

    @FXML
    void gravarTransacao(ActionEvent event) {
        
        if(validarCampos()){
        try{
        TransacaoDAO dao = new TransacaoDAO();
        Transacao t = new Transacao();
        t.setTxData(txData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        System.out.println(txData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        t.setTxHora(txHora.getTime().format(DateTimeFormatter.ofPattern("HHmm")));
        t.setTxSequencial(txSequencial.getText());
        t.setTxCaixa(txCaixa.getText());
        t.setTxCoo(txCoo.getText());
        t.setTxLoja(txLoja.getText());
        t.setTxEcf(txEcf.getText());
        t.setTxClicod(txClicod.getText());
        t.setTxFuncod(txFuncod.getText());
        t.setTxSerieEcf(txSerieEcf.getText());
        t.setTxNfceNum(txNfceNum.getText());
        t.setTxNfceChave(txNfceChave.getText());
        t.setTxNfceProtocolo(txNfceProtocolo.getText());
        t.setTxNfceNumSerie(txNfceNumSerie.getText());
        t.setTxChaveSat(txChaveSat.getText());
        t.setTxValor(new BigDecimal(txValor.getText().replaceAll(",", ".")));
        t.setTxDesconto(new BigDecimal(txDesconto.getText().replaceAll(",", ".")));
        t.setTxAcrecimo(new BigDecimal(txAcrecimo.getText().replaceAll(",", ".")));
        t.setCbImpressora(cbImpressora.getValue().toString().substring(0,2));
        t.setCbTipoTransacao(cbTipoTransacao.getValue().toString().substring(0,1));      
        
        if(rdLogMilenio.visibleProperty().getValue()){
            t.setRdLogMilenio("A");
        }else{
            t.setRdLogMilenio("T");
        }
        if(rdLogVarejo.visibleProperty().getValue()){
            t.setRdLogVarejo("A");
        }else{
            t.setRdLogVarejo("T");
        }
        if(rdMovEstoque.visibleProperty().getValue()){
            t.setRdMovEstoque("S");
        }else{
            t.setRdMovEstoque("N");
        }
        t.setTxTrntrf("A");
        t.setTxVersao(dao.versao());    
        
        //Enviando objeto para cadastro no dao
        dao.cadastrar(t);
            mensagemConfirma("Cadastro de Transação", "Transação cadastrada com sucesso!");
            limparCamposTransacao();
        }catch(Exception e){
            mensagemAlerta("Ocorreu um erro ao inserir Transação", "Erro: "+e.getMessage());
        }
        }
        
    }

    @FXML
    void limparCampos(ActionEvent event) {
        limparCamposTransacao();
    }
    
    
    
    @FXML
    void teclaEnterSequencial(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seq = txSequencial.getText();
            txSequencial.setText(StringUtils.leftPad(seq, 6, "0"));
            txCaixa.requestFocus();
        }
    }
    
    @FXML
    void teclaEnterCaixa(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String cxa = txCaixa.getText();
            txCaixa.setText(StringUtils.leftPad(cxa, 3, "0"));
            txCoo.requestFocus();
        }
    }
    @FXML
    void teclaEnterCliente(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String cli = txClicod.getText();
            txClicod.setText(StringUtils.leftPad(cli, 15, "0"));
            if(txClicod.getText().equals("000000000000000") || txClicod.getText() == null){
                txNomeCliente.setText("Cliente não informado");
            }else{
            TransacaoDAO dao = new TransacaoDAO();
            txNomeCliente.setText(dao.clienteByCodigo(txClicod.getText()));
            }
            txFuncod.requestFocus();
        }
    }

    @FXML
    void teclaEnterCoo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {              
            txLoja.requestFocus();
        }
    }

    @FXML
    void teclaEnterEcf(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String ecf = txEcf.getText();
            txEcf.setText(StringUtils.leftPad(ecf, 3, "0"));
            txClicod.requestFocus();
        }
    }

    @FXML
    void teclaEnterFuncionario(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String func = txFuncod.getText();
            txFuncod.setText(StringUtils.leftPad(func, 6, "0"));
            if(txFuncod.getText().equals("000000") || txFuncod.getText() == null){
                txNomeFuncionario.setText("Funcionario não informado");
                txSerieEcf.requestFocus();
            }else{
                TransacaoDAO dao = new TransacaoDAO();
                txNomeFuncionario.setText(dao.funcionarioByCodigo(txFuncod.getText()));
                txSerieEcf.requestFocus();
            }
            
        }
    }

    @FXML
    void teclaEnterLoja(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String loja = txLoja.getText();
            txLoja.setText(StringUtils.leftPad(loja, 4, "0"));
            txEcf.requestFocus();
        }
    }

    @FXML
    void teclaEnterSerieEcf(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txValor.requestFocus();
        }
    }
    
    @FXML
    void teclaEnterAcrecimo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            if(txAcrecimo.getText().equals("") || txAcrecimo.getText().equals("0")){
                txAcrecimo.setText("0,00");
            }
        }
    }
    
    @FXML
    void teclaEnterDesconto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txDesconto.getText().equals("") || txDesconto.getText().equals("0")){
                txDesconto.setText("0,00");
            }
            txAcrecimo.requestFocus();
        }
    }
    
    @FXML
    void teclaEnterValor(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txValor.getText().equals("")){
                txValor.setText("0,00");
            }
            txDesconto.requestFocus();
        }
    }
    
     @FXML
    void teclaEnterChaveCfe(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txValor.requestFocus();
        }
    }

    @FXML
    void teclaEnterChaveNfce(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txNfceProtocolo.requestFocus();
        }
    }
    
    @FXML
    void teclaEnterNumNfce(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txNfceChave.requestFocus();
        }
    }

    @FXML
    void teclaEnterNumSerieNfce(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txValor.requestFocus();
        }
    }

    @FXML
    void teclaEnterProtocoloNfce(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {            
            txNfceNumSerie.requestFocus();
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MaskFieldUtil.monetaryField(txValor);
        MaskFieldUtil.monetaryField(txAcrecimo);
        MaskFieldUtil.monetaryField(txDesconto);
        MaskFieldUtil.numericField(txClicod);
        MaskFieldUtil.numericField(txCaixa);
        MaskFieldUtil.numericField(txSequencial);
        MaskFieldUtil.numericField(txCoo);
        MaskFieldUtil.numericField(txEcf);
        MaskFieldUtil.numericField(txFuncod);
        MaskFieldUtil.numericField(txLoja);
        MaskFieldUtil.numericField(txChaveSat);
        MaskFieldUtil.numericField(txNfceChave);
        MaskFieldUtil.numericField(txNfceNum);
        MaskFieldUtil.numericField(txNfceNumSerie);
        MaskFieldUtil.numericField(txNfceProtocolo);
        
        
        //Preenche as combobox
        preencheComboImpressora();
        preencheComboTipoTransacao();
        
        //controla a quantidade de caracteres num textfild
        txSequencial.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 6) txSequencial.setText(oldValue);
        }
        );
        txCaixa.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 3) txCaixa.setText(oldValue);
        }
        );
        txLoja.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 4) txLoja.setText(oldValue);
        }
        );
        txEcf.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 3) txEcf.setText(oldValue);
        }
        );
        txFuncod.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 6) txFuncod.setText(oldValue);
        }
        );
        txSerieEcf.textProperty().addListener(
        (observable,oldValue,newValue)-> {
            if(newValue.length() > 20) txSerieEcf.setText(oldValue);
        }
        );
    }    
    
    
    public void preencheComboImpressora(){
        ImpressoraDAO dao = new ImpressoraDAO();        
        List<Impressora> lista = dao.listaImpressoras();
        for (int i=0; i < lista.size(); i++){
            cbImpressora.getItems().add(lista.get(i).getEcfcod()+" - "+lista.get(i).getEcfdes());
        }
    }
    
    public void preencheComboTipoTransacao(){
        
        TipoTransacaoDAO dao = new TipoTransacaoDAO();        
        List<Tipotransacao> lista = dao.ListarTipoTransacao();        
        for (int i=0; i < lista.size(); i++){
            cbTipoTransacao.getItems().add(lista.get(i).getTptrcod()+" - "+lista.get(i).getTptrdes());           
            
        }    
        
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

    private boolean validarCampos() {
        
        if(txAcrecimo.getText().isEmpty() || txCaixa.getText().isEmpty() || txCoo.getText().isEmpty() || 
            txDesconto.getText().isEmpty() || txEcf.getText().isEmpty() ||  txLoja.getText().isEmpty() ||
            txSequencial.getText().isEmpty() || txSerieEcf.getText().isEmpty() | txValor.getText().isEmpty() | cbImpressora.getSelectionModel().isEmpty() |
            cbTipoTransacao.getSelectionModel().isEmpty()){
            mensagemAlerta("Erro ao tentar inserir transação", "Erro causado provavelmente porque alguns campos estão vazio.");
        return false;
        }
        return true;
    }
    
    private void limparCamposTransacao(){
        txAcrecimo.setText("");        
        txCaixa.setText("");
        txCoo.setText("");
        txData.getEditor().setText("");
        txDesconto.setText("");
        txEcf.setText("");
        txHora.getEditor().setText("");
        txLoja.setText("");
        txSequencial.setText("");
        txSerieEcf.setText("");
        txValor.setText("");
        cbImpressora.getSelectionModel().clearSelection();
        cbTipoTransacao.getSelectionModel().clearSelection();
        txChaveSat.setText("");
        txClicod.setText("");
        txFuncod.setText("");
        txNfceChave.setText("");
        txNfceNum.setText("");
        txNfceNumSerie.setText("");
        txNfceProtocolo.setText("");
        txNomeCliente.setText("");
        txNomeFuncionario.setText("");
        
              
    }
}
