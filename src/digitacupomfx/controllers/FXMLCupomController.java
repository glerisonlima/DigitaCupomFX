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
import digitacupomfx.entidades.Finalizacao;
import digitacupomfx.entidades.Impressora;
import digitacupomfx.entidades.Itenvda;
import digitacupomfx.entidades.Tipotransacao;
import digitacupomfx.utils.MaskFieldUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLCupomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Campos Transação
        MaskFieldUtil.monetaryField(txTrnAcrecimo);
        MaskFieldUtil.monetaryField(txTrnDesconto);
        MaskFieldUtil.monetaryField(txTrnValor);
        MaskFieldUtil.numericField(txTrnCaixa);
        MaskFieldUtil.numericField(txTrnClicod);
        MaskFieldUtil.numericField(txTrnCoo);
        MaskFieldUtil.numericField(txTrnEcf);
        MaskFieldUtil.numericField(txTrnFuncod);
        MaskFieldUtil.numericField(txTrnLoja);
        MaskFieldUtil.numericField(txTrnSequencial);
        txTrnAcrecimo.setText("0,00");
        txTrnDesconto.setText("0,00");
        txTrnValor.setText("0,00");
        
        // Campos Itens
        MaskFieldUtil.monetaryField(txItemAcrecimo);
        MaskFieldUtil.monetaryField(txItemDesconto);
        MaskFieldUtil.monetaryField(txItemValorTot);
        MaskFieldUtil.monetaryField(txItemVlrUnitario);
        MaskFieldUtil.numericField(txItemProcod);
        txItemAcrecimo.setText("0,00");
        txItemDesconto.setText("0,00");
        txItemValorTot.setText("0,00");
        txItemVlrUnitario.setText("0,00");
        
        // Campos Finalização
        MaskFieldUtil.monetaryField(txFinTroco);
        MaskFieldUtil.monetaryField(txFinVlrRecebido);
        MaskFieldUtil.numericField(txFinSeqFinalizadora);
        txFinTroco.setText("0,00");
        txFinVlrRecebido.setText("0,00");
        
        //Preenche as combobox
        preencheComboImpressora();
        preencheComboTipoTransacao();
    }
    
    @FXML
    private StackPane stackPane;

    @FXML
    private Tab tabTansacao;

    @FXML
    private JFXDatePicker txTrnData;

    @FXML
    private JFXDatePicker txTrnHora;

    @FXML
    private TextField txTrnClicod;

    @FXML
    private TextField txTrnFuncod;

    @FXML
    private TextField txTrnSequencial;

    @FXML
    private TextField txTrnCaixa;

    @FXML
    private TextField txTrnCoo;

    @FXML
    private TextField txTrnLoja;

    @FXML
    private TextField txTrnEcf;

    @FXML
    private TextField txTrnSerieEcf;

    @FXML
    private Label lbNomeCliente;

    @FXML
    private TextField txNomeCliente;

    @FXML
    private TextField txNomeFuncionario;

    @FXML
    private JFXComboBox cbTrnImpressora;

    @FXML
    private JFXComboBox cbTrnTipoTransacao;

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
    private JFXCheckBox rdTrnLogVarejo;

    @FXML
    private JFXCheckBox rdTrnLogMilenio;

    @FXML
    private JFXCheckBox rdTrnMovEstoque;

    @FXML
    private TextField txTrnValor;

    @FXML
    private TextField txTrnDesconto;

    @FXML
    private TextField txTrnAcrecimo;

    @FXML
    private JFXButton btnTrnLimparCampos;

    @FXML
    private Tab tabItens;

    @FXML
    private TextField txItemProcod;

    @FXML
    private TextField txItemProdes;

    @FXML
    private TextField txItemQuantidade;

    @FXML
    private TextField txItemVlrUnitario;

    @FXML
    private TextField txItemDesconto;

    @FXML
    private TextField txItemAcrecimo;

    @FXML
    private TextField txItemValorTot;

    @FXML
    private JFXComboBox cbItemTributacao;

    @FXML
    private JFXCheckBox ckItemCancelado;

    @FXML
    private JFXButton btnInserirItem;

    @FXML
    private JFXButton btnRemoverItem;

    @FXML
    private TableView<Itenvda> tbItens;

    @FXML
    private TableColumn<?, ?> procutoCol;

    @FXML
    private TableColumn<?, ?> descricaoCol;

    @FXML
    private TableColumn<?, ?> valorUniCol;

    @FXML
    private TableColumn<?, ?> quantidadeCol;

    @FXML
    private TableColumn<?, ?> totalCol;

    @FXML
    private Label lbTotalItemCupom;

    @FXML
    private Tab tabFinalizacao;

    @FXML
    private TextField txFinSeqFinalizadora;

    @FXML
    private JFXComboBox cbFinFinalizadora;

    @FXML
    private TextField txFinVlrRecebido;

    @FXML
    private JFXButton btnInserirFinalizadora;

    @FXML
    private JFXButton btnRemoverFinalizadora;

    @FXML
    private TextField txFinTroco;

    @FXML
    private TableView<Finalizacao> tbFinalizacao;

    @FXML
    private TableColumn<?, ?> seq;

    @FXML
    private TableColumn<?, ?> finalizadora;

    @FXML
    private TableColumn<?, ?> recebido;

    @FXML
    private TableColumn<?, ?> troco;

    @FXML
    private JFXButton btnGravarCupom;

    @FXML
    private Label lbTotalFinalizacao;

    @FXML
    private TabPane tabPane;
    
    @FXML
    void LimparCamposTransacao(ActionEvent event) {

    }

    @FXML
    void teclaEnterFinComboFinalizadora(KeyEvent event) {

    }

    @FXML
    void teclaEnterFinSeqFin(KeyEvent event) {

    }

    @FXML
    void teclaEnterFinTroco(KeyEvent event) {

    }

    @FXML
    void teclaEnterFinVlrRecebido(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemAcrecimo(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemDesconto(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemDescricao(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemProcod(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemQuantidade(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemVlrTotal(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemVlrUnit(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnAcrecimo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            if(camposVaziosTansacao()){
                mensagemAlerta("Campos vazios", "Verifique se a impressora e o tipo de transação estão preenchidos corretamente!");
                
            }else{
                tabPane.getSelectionModel().select(tabItens);                
            }
        }
    }

    @FXML
    void teclaEnterTrnCaixa(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seq = txTrnCaixa.getText();
            txTrnCaixa.setText(StringUtils.leftPad(seq, 3, "0"));
            txTrnCoo.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnClicod(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String aux = txTrnClicod.getText();
            txTrnClicod.setText(StringUtils.leftPad(aux, 15, "0"));
            if(txTrnClicod.getText().equals("000000000000000") || txTrnClicod.getText() == null){
                txNomeCliente.setText("Cliente não informado");
            }else{
            TransacaoDAO dao = new TransacaoDAO();
            txNomeCliente.setText(dao.clienteByCodigo(txTrnClicod.getText()));
            }
            txTrnFuncod.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnCoo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String aux = txTrnCoo.getText();
            txTrnCoo.setText(StringUtils.leftPad(aux, 6, "0"));
            txTrnLoja.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnDesconto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            txTrnAcrecimo.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnEcf(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String aux = txTrnEcf.getText();
            txTrnEcf.setText(StringUtils.leftPad(aux, 3, "0"));
            txTrnClicod.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnFuncod(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String aux = txTrnFuncod.getText();
            txTrnFuncod.setText(StringUtils.leftPad(aux, 6, "0"));
            if(txTrnFuncod.getText().equals("000000") || txTrnFuncod.getText() == null){
                txNomeFuncionario.setText("Funcionario não informado");
                txTrnFuncod.requestFocus();
            }else{
                TransacaoDAO dao = new TransacaoDAO();
                txNomeFuncionario.setText(dao.funcionarioByCodigo(txTrnFuncod.getText()));
                txTrnSerieEcf.requestFocus();
            }
        }
    }

    @FXML
    void teclaEnterTrnLoja(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String aux = txTrnLoja.getText();
            txTrnLoja.setText(StringUtils.leftPad(aux, 4, "0"));
            txTrnEcf.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnNfceChave(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnNfceNum(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnNfceProt(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnNfceSerie(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnSatChave(KeyEvent event) {

    }

    @FXML
    void teclaEnterTrnSequencial(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seq = txTrnSequencial.getText();
            txTrnSequencial.setText(StringUtils.leftPad(seq, 6, "0"));
            txTrnCaixa.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnSerieEcf(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            txTrnValor.requestFocus();
        }
    }

    @FXML
    void teclaEnterTrnValor(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            txTrnDesconto.requestFocus();
        }
    }
    
    public void preencheComboImpressora(){
        ImpressoraDAO dao = new ImpressoraDAO();        
        List<Impressora> lista = dao.listaImpressoras();
        for (int i=0; i < lista.size(); i++){
            cbTrnImpressora.getItems().add(lista.get(i).getEcfcod()+" - "+lista.get(i).getEcfdes());
        }
    }
    
    public void preencheComboTipoTransacao(){
        
        TipoTransacaoDAO dao = new TipoTransacaoDAO();        
        List<Tipotransacao> lista = dao.ListarTipoTransacao();        
        for (int i=0; i < lista.size(); i++){
            cbTrnTipoTransacao.getItems().add(lista.get(i).getTptrcod()+" - "+lista.get(i).getTptrdes());           
            
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
    
    public boolean camposVaziosTansacao(){
        if(txTrnAcrecimo.getText().isEmpty() | txTrnCaixa.getText().isEmpty() | txTrnClicod.getText().isEmpty()
           | txTrnCoo.getText().isEmpty() | txTrnDesconto.getText().isEmpty() | txTrnEcf.getText().isEmpty() 
           | txTrnFuncod.getText().isEmpty() | txTrnLoja.getText().isEmpty() | txTrnSequencial.getText().isEmpty()
           | txTrnSerieEcf.getText().isEmpty() | txTrnValor.getText().isEmpty() | cbTrnImpressora.getSelectionModel().isEmpty() | cbTrnTipoTransacao.getSelectionModel().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public boolean camposVaziosItens(){return true;}
    public boolean camposVaziosFinalizacao(){return true;}

}
