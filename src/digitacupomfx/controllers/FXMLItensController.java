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
import digitacupomfx.DigitaCupomFx;
import digitacupomfx.dao.ItenvdaDao;
import digitacupomfx.entidades.Itenvda;
import digitacupomfx.entidades.Produto;
import digitacupomfx.entidades.Tributacao;
import digitacupomfx.utils.MaskFieldUtil;
import digitacupomfx.utils.MaskTextField;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 * FXML Controller class
 *
 * @author glerison
 */
public class FXMLItensController implements Initializable {
    
    private List<Itenvda> listItens = new ArrayList();
    
    private ObservableList<Itenvda> ObservableListItens;

    @FXML
    private JFXDatePicker txData;

    @FXML
    private TextField txSequencial;

    @FXML
    private TextField txCaixa;

    @FXML
    private TextField txProcod;

    @FXML
    private TextField txProdes;

    @FXML
    private TextField txQuantidade;

    @FXML
    private TextField txVlrUnitario;

    @FXML
    private TextField txDesconto;

    @FXML
    private TextField txAcrecimo;

    @FXML
    private TextField txVlrItem;

    @FXML
    private JFXComboBox cbTributacao;

    @FXML
    private JFXCheckBox ckItemCancelado;

    @FXML
    private JFXButton btnInserirItem;

    @FXML
    private JFXButton btnRemoverItem;

    @FXML
    private TableView<Itenvda> tbItens;

    @FXML
    private TableColumn<Itenvda, String> procutoCol;

    @FXML
    private TableColumn<Itenvda, String> descricaoCol;

    @FXML
    private TableColumn<Itenvda, Double> valorUniCol;

    @FXML
    private TableColumn<Itenvda, Double> quantidadeCol;

    @FXML
    private TableColumn<Itenvda, Double> totalCol;

    @FXML
    private JFXButton btnGravarItens;
    
    @FXML
    private Label lbTotalCupom;
    
    @FXML
    private StackPane stackPane;

    @FXML
    public void teclaEnterAcrecimo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txAcrecimo.getText().equals("")){
                txAcrecimo.setText("0,00");
            }
            BigDecimal vlrUnitario = new BigDecimal(txVlrItem.getText().replace(",", "."));
            BigDecimal vlrAcrecimo = new BigDecimal(txAcrecimo.getText().replace(",", "."));
            BigDecimal vlrItem = casasDecimais(2, vlrUnitario.add(vlrAcrecimo));
                txVlrItem.setText(vlrItem.toString().replace(".", ","));
            cbTributacao.requestFocus();
        }
    }

    @FXML
    public void teclaEnterBtnInserir(KeyEvent event) {
        
        
    }

    @FXML
    public void teclaEnterBtnRemover(KeyEvent event) {

    }

    @FXML
    public void teclaEnterCaixa(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String cxa = txCaixa.getText();
            txCaixa.setText(StringUtils.leftPad(cxa, 3, "0"));
            txProcod.requestFocus();
        }
    }

    @FXML
    public void teclaEnterDesconto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txDesconto.getText().equals("")){
                txDesconto.setText("0,00");
            }
                BigDecimal vlrUnitario = new BigDecimal(txVlrItem.getText().replace(",", "."));
                BigDecimal vlrDesconto = new BigDecimal(txDesconto.getText().replace(",", "."));
                BigDecimal vlrItem = casasDecimais(2, vlrUnitario.subtract(vlrDesconto));
                txVlrItem.setText(vlrItem.toString().replace(".", ","));
            
                txVlrItem.requestFocus();
        }
    }

    @FXML
    public void teclaEnterDescricao(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
        txQuantidade.requestFocus();
        }
    }

    @FXML
    public void teclaEnterProduto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String pro = txProcod.getText();
            txProcod.setText(StringUtils.leftPad(pro, 14, "0"));
            try{
            ItenvdaDao dao = new ItenvdaDao();
            Produto p = dao.buscarProdutoCodigo(txProcod.getText());
            if(p.getPreco() != null){
                txProdes.setText(p.getDescricao());
                txProcod.setText(p.getCodigo());
                txVlrUnitario.setText(p.getPreco().toString());
            }else{
                p = dao.buscarProdutoCodBarras(txProcod.getText());
                txProdes.setText(p.getDescricao());
                txProcod.setText(p.getCodigo());
                txVlrUnitario.setText(p.getPreco().toString());            
            }
            txQuantidade.setText("1");
            txQuantidade.requestFocus();
            }catch(Exception e){
                txProcod.requestFocus();
                mensagemAlerta("Produto não localizado!", "Tente novamente com um codigo de barras ou codigo principal valido. Erro: "+e.getMessage());
                
            }
            
        }
    }

    @FXML
    public void teclaEnterQuantidade(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(!txQuantidade.equals("")){          
            BigDecimal quantidade = new BigDecimal(txQuantidade.getText());
            BigDecimal vlrUnitario = new BigDecimal(txVlrUnitario.getText().replace(",", "."));
            BigDecimal novoVlr = casasDecimais(2, vlrUnitario.multiply(quantidade));
            txVlrUnitario.setText(novoVlr.toString().replace(".", ","));
            txVlrItem.setText(novoVlr.toString().replace(".", ","));
            }
        txVlrItem.requestFocus();    
        }
        
    }
    
    private BigDecimal casasDecimais(int casas, BigDecimal valor){
        

        String quantCasas = "%."+casas+"f", textoValor = "0";
        try
        {
            textoValor = String.format(Locale.getDefault(), quantCasas, valor);
        }catch(java.lang.IllegalArgumentException e)
        {
            // Quando os digitos com 2 casas decimais forem Zeros, exemplo: 0.000001233888.
            // Nao existe valor 0,00 , logo entra na java.lang.IllegalArgumentException.
            if(e.getMessage().equals("Digits < 0"))
                textoValor = "0";
            System.out.println(e.getMessage());
        }
        return new BigDecimal(textoValor.replace(",", "."));
    }
    
    
    public void teclaEnterSequencial(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String seq = txSequencial.getText();
            txSequencial.setText(StringUtils.leftPad(seq, 6, "0"));
            txCaixa.requestFocus();
        }
    }

    @FXML
    public void teclaEnterValorItem(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txVlrItem.getText().equals("")){
                txVlrItem.setText("0,00");
            }
            cbTributacao.requestFocus();
        }
    }

    @FXML
    public void teclaEnterValorUnitario(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER) { 
            if(txVlrUnitario.getText().equals("")){
                txVlrUnitario.setText("0,00");
            }
            txDesconto.requestFocus();
        }     
    }
    
    @FXML
    public void gravarItens(ActionEvent event) {
        try{
        if(listItens != null){
        ItenvdaDao dao = new ItenvdaDao();
        for(int i=0; i < listItens.size(); i++){
            Itenvda item = listItens.get(i);
            item.setItvseq(String.valueOf(i+1));
            dao.insereItenvda(item);
        }
        
        limparCampos();
            mensagemConfirma("Inserido itens de venda", "Itens de venda inseridos com sucesso!");
        }
        }catch(Exception e){
            mensagemAlerta("Erro ao tentar inserir os itens de venda", "Erro: "+e.getMessage());
        }
    }
    
    
    @FXML
    public void removerItem(ActionEvent event) {
        Itenvda itemSelecionado = tbItens.getSelectionModel().getSelectedItem();
        if(itemSelecionado != null){
        ObservableListItens.remove(itemSelecionado);
        listItens.remove(itemSelecionado);
        
        calcularCupomTabela();
        }else{
            mensagemAlerta("Problema ao tentar remover item da tabela", "Verifique se o item foi selecionado corretamente!");
        }
    }
    
    @FXML
    public void inserirItem(ActionEvent event) {     
        if(validarCampos()){    
        
        
        Itenvda item = new Itenvda();
        item.setTxtrndat(txData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        item.setTxtrnseq(txSequencial.getText());
        item.setTxcxanum(txCaixa.getText());
        item.setTxprocod(txProcod.getText());
        item.setTxprodes(txProdes.getText());
        item.setTxitenquant(new BigDecimal(txQuantidade.getText()));
        item.setTxvlrunit(new BigDecimal(txVlrUnitario.getText().replace(",", ".")));
        item.setTxdesconto(new BigDecimal(txDesconto.getText().replace(",", ".")));
        item.setTxacrecimo(new BigDecimal(txAcrecimo.getText().replace(",", ".")));
        item.setTxtotal(new BigDecimal(txVlrItem.getText().replaceAll(",", ".")));
        item.setTxtributacao(cbTributacao.getValue().toString());
        if (ckItemCancelado.visibleProperty().getValue()){
            item.setItvtip("1");
        }else{
            item.setItvtip("2");
        }
        
        preencherTabela(item);
        calcularCupomTabela();
        txDesconto.setText("0,00");
        txAcrecimo.setText("0,00");
        txProdes.setText("");
        txQuantidade.setText("");
        txVlrItem.setText("0,00");
        txVlrUnitario.setText("0,00");
        
        txProcod.requestFocus();
                
        }else{
            mensagemAlerta("Erro ao inserir item", "Verifique se os campos estão vazios!");
        }
        
        
        
    }
    
    public void calcularCupomTabela(){
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i<ObservableListItens.size();i++){
        BigDecimal coluna = new BigDecimal(tbItens.getVisibleLeafColumn(4).getCellObservableValue(i).getValue().toString());
        total = total.add(coluna);
        }
        System.out.println("Total: "+total);
        lbTotalCupom.setText(String.valueOf(total).replace(".", ","));
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Mascaras de campos
        MaskFieldUtil.monetaryField(txAcrecimo);
        MaskFieldUtil.monetaryField(txDesconto);
        MaskFieldUtil.monetaryField(txVlrItem);
        MaskFieldUtil.monetaryField(txVlrUnitario);
        MaskFieldUtil.numericField(txCaixa);
        MaskFieldUtil.numericField(txProcod);
        MaskFieldUtil.numericField(txSequencial);
        
        txDesconto.setText("0,00");
        txAcrecimo.setText("0,00");
        
        
        //preencher Combobox
        preencherTributacoes();
        cbTributacao.getSelectionModel().select(0);
        tbItens.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTabela(newValue));
        
        
        
    }   
    
    public void preencherTributacoes(){
        ItenvdaDao dao = new ItenvdaDao();
        List<Tributacao> lista = dao.buscarTributacao(); 
        for (int i=0; i < lista.size(); i++){
            cbTributacao.getItems().add(lista.get(i).getTrbid());          
            
        }
    }
    
    public void preencherTabela(Itenvda item){
        
        procutoCol.setCellValueFactory(new PropertyValueFactory<>("Txprocod"));
        descricaoCol.setCellValueFactory(new PropertyValueFactory<>("Txprodes"));
        valorUniCol.setCellValueFactory(new PropertyValueFactory<>("Txvlrunit"));
        quantidadeCol.setCellValueFactory(new PropertyValueFactory<>("Txitenquant"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("Txtotal".replace(".", ",")));
        
        //Itenvda itv1 = new Itenvda("00000000000001", "001", "teste 1", "000001", 1.0, "0001", "1", "11.10.2016", "111", "F00", 1, 1, 1, 1);
        
        listItens.add(item);
        
        ObservableListItens = FXCollections.observableArrayList(listItens);
        tbItens.setItems(ObservableListItens);
    }

    private void selecionarItemTabela(Itenvda itemSelecionado) {
        System.out.println("Item Selecionado: "+itemSelecionado.getTxprocod());
    }
    
    
    private void limparCampos(){
        for (int i=0; i<=listItens.size();i++){
            listItens.remove(i);
            ObservableListItens.remove(i);
        }
        txAcrecimo.setText("0,00");
        txCaixa.setText("");
        txDesconto.setText("0,00");
        txProcod.setText("");
        txProdes.setText("");
        txQuantidade.setText("");
        txSequencial.setText("");
        txVlrItem.setText("");
        txVlrUnitario.setText("");
        cbTributacao.getSelectionModel().clearSelection();
        
        
        
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
        if(txAcrecimo.getText().isEmpty() | txCaixa.getText().isEmpty() | txDesconto.getText().isEmpty() |
                txProcod.getText().isEmpty() | txQuantidade.getText().isEmpty() | txSequencial.getText().isEmpty() |
                txVlrItem.getText().isEmpty() | txVlrUnitario.getText().isEmpty()){            
            return false;            
        }else{
        return true;
        }
    }
    
    
    
    
}
