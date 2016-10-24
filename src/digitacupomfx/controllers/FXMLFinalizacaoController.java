/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitacupomfx.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import digitacupomfx.dao.FinalizacaoDAO;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class FXMLFinalizacaoController implements Initializable {
    
    
    private List<Finalizacao> listFinalizacao = new ArrayList();
    
    private ObservableList<Finalizacao> ObservableListFinalizacao;    
    
    @FXML
    private StackPane stackPane;
    
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
        try{
        if(listFinalizacao != null){
        FinalizacaoDAO dao = new FinalizacaoDAO();
        for(int i=0; i < listFinalizacao.size(); i++){
            Finalizacao fin = listFinalizacao.get(i);
            dao.insereFinalizacao(fin);
        }
        
        limparCampos();
            mensagemConfirma("Inserido itens de venda", "Itens de venda inseridos com sucesso!");
        }
        }catch(Exception e){
            mensagemAlerta("Erro ao tentar inserir os itens de venda", "Erro: "+e.getMessage());
        }
    }
    
    public void calcularFinalizacaoTabela(){
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i<ObservableListFinalizacao.size();i++){
        BigDecimal coluna = new BigDecimal(tbFinalizacao.getVisibleLeafColumn(2).getCellObservableValue(i).getValue().toString());
        total = total.add(coluna);
        }
        System.out.println("Total: "+total);
        lbTotalFinalizacao.setText(String.valueOf(total).replace(".", ","));
    }

    @FXML
    void inserirFinalizacao(ActionEvent event) {
        if(camposVaziosFinalizacao()){
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
        calcularFinalizacaoTabela();
        }else{
            mensagemAlerta("Erro ao inserir finalização", "verifique se os campos estão preenchidos corretamente!");
        }
    }

    @FXML
    void removerFinalizacao(ActionEvent event) {
        Finalizacao finalizacaoSelecionado = tbFinalizacao.getSelectionModel().getSelectedItem();
        if(finalizacaoSelecionado != null){
        ObservableListFinalizacao.remove(finalizacaoSelecionado);
        listFinalizacao.remove(finalizacaoSelecionado);
        
        calcularFinalizacaoTabela();
        }else{
            mensagemAlerta("Problema ao tentar remover item da tabela", "Verifique se o item foi selecionado corretamente!");
        }
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

    private void limparCampos() {
        txSequencial.setText("");
        txCxanum.setText("");
        txSeqFinalizadora.setText("");
        txTroco.setText("0,00");
        txVlrRecebido.setText("0,00");
        cbFinalizadora.getSelectionModel().clearSelection();
        for (int i=0; i<=listFinalizacao.size();i++){
            listFinalizacao.remove(i);
            ObservableListFinalizacao.remove(i);
        }
    }

    private boolean camposVaziosFinalizacao() {
        if(txCxanum.getText().isEmpty() | txSequencial.getText().isEmpty() |txSeqFinalizadora.getText().isEmpty() | txVlrRecebido.getText().isEmpty() | cbFinalizadora.getSelectionModel().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
}
