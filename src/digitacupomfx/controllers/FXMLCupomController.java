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
import digitacupomfx.dao.EstoqueMovimentacaoDAO;
import digitacupomfx.dao.FinalizacaoDAO;
import digitacupomfx.dao.FinalizadoraDAO;
import digitacupomfx.dao.ImpressoraDAO;
import digitacupomfx.dao.ItenvdaDao;
import digitacupomfx.dao.TipoTransacaoDAO;
import digitacupomfx.dao.TransacaoDAO;
import digitacupomfx.entidades.Estoque;
import digitacupomfx.entidades.Finalizacao;
import digitacupomfx.entidades.Finalizadora;
import digitacupomfx.entidades.Impressora;
import digitacupomfx.entidades.Itenvda;
import digitacupomfx.entidades.Produto;
import digitacupomfx.entidades.Tipotransacao;
import digitacupomfx.entidades.Transacao;
import digitacupomfx.entidades.Tributacao;
import digitacupomfx.utils.MaskFieldUtil;
import java.math.BigDecimal;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
        preencherTributacoes();
        preencherFinalizadoras();
        
        //Inicializa o valor da combobox tributação
        cbItemTributacao.getSelectionModel().select(0);
    }
    
    private List<Itenvda> listItens = new ArrayList();
    
    private ObservableList<Itenvda> ObservableListItens;
    
    private List<Finalizacao> listFinalizacao = new ArrayList();
    
    private ObservableList<Finalizacao> ObservableListFinalizacao; 
    
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
    private TableColumn<Finalizacao, String> seq;

    @FXML
    private TableColumn<Finalizacao, String> finalizadora;

    @FXML
    private TableColumn<Finalizacao, BigDecimal> recebido;

    @FXML
    private TableColumn<Finalizacao, BigDecimal> troco;

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
        if (event.getCode() == KeyCode.ENTER) {  
            String seqFin = txFinSeqFinalizadora.getText();
            txFinSeqFinalizadora.setText(StringUtils.leftPad(seqFin, 3, "0"));
            cbFinFinalizadora.requestFocus();
        }
    }

    @FXML
    void teclaEnterFinTroco(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txFinTroco.getText().equals("")){
                txFinTroco.setText("0,00");
            }
        }
    }

    @FXML
    void teclaEnterFinVlrRecebido(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txFinVlrRecebido.getText().equals("")){
                txFinVlrRecebido.setText("0,00");
            }
            txFinTroco.requestFocus();
        }
    }
    
      @FXML
    void inserirItem(ActionEvent event) {
        if(camposVaziosItens()){      
        Itenvda item = new Itenvda();
        item.setTxtrndat(txTrnData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        item.setTxtrnseq(txTrnSequencial.getText());
        item.setTxcxanum(txTrnCaixa.getText());
        item.setTxprocod(txItemProcod.getText());
        item.setTxprodes(txItemProdes.getText());
        item.setTxitenquant(new BigDecimal(txItemQuantidade.getText()));
        item.setTxvlrunit(new BigDecimal(txItemVlrUnitario.getText().replace(",", ".")));
        item.setTxdesconto(new BigDecimal(txItemDesconto.getText().replace(",", ".")));
        item.setTxacrecimo(new BigDecimal(txItemAcrecimo.getText().replace(",", ".")));
        item.setTxtotal(new BigDecimal(txItemValorTot.getText().replaceAll(",", ".")));
        item.setTxtributacao(cbItemTributacao.getValue().toString());
        if (ckItemCancelado.visibleProperty().getValue()){
            item.setItvtip("1");
        }else{
            item.setItvtip("2");
        }
        
        preencherTabela(item);
        calcularCupomTabela();
        txItemDesconto.setText("0,00");
        txItemAcrecimo.setText("0,00");
        txItemProdes.setText("");
        txItemQuantidade.setText("");
        txItemValorTot.setText("0,00");
        txItemVlrUnitario.setText("0,00");
        
        txItemProcod.requestFocus();
                
        }else{
            mensagemAlerta("Erro ao inserir item", "Verifique se os campos estão vazios!");
        }
    }

    @FXML
    void removerItem(ActionEvent event) {
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
    void teclaEnterItemAcrecimo(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txItemAcrecimo.getText().equals("")){
                txItemAcrecimo.setText("0,00");
            }
            BigDecimal vlrUnitario = new BigDecimal(txItemValorTot.getText().replace(",", "."));
            BigDecimal vlrAcrecimo = new BigDecimal(txItemAcrecimo.getText().replace(",", "."));
            BigDecimal vlrItem = casasDecimais(2, vlrUnitario.add(vlrAcrecimo));
                txItemValorTot.setText(vlrItem.toString().replace(".", ","));
            txItemValorTot.requestFocus();
        }
    }

    @FXML
    void teclaEnterItemDesconto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txItemDesconto.getText().equals("")){
                txItemDesconto.setText("0,00");
            }
                BigDecimal vlrUnitario = new BigDecimal(txItemValorTot.getText().replace(",", "."));
                BigDecimal vlrDesconto = new BigDecimal(txItemDesconto.getText().replace(",", "."));
                BigDecimal vlrItem = casasDecimais(2, vlrUnitario.subtract(vlrDesconto));
                txItemValorTot.setText(vlrItem.toString().replace(".", ","));
            
                txItemValorTot.requestFocus();
        }
    }

    @FXML
    void teclaEnterItemDescricao(KeyEvent event) {

    }
    
    private boolean buscaTransacao(String seq, String cxa, String data) {
        TransacaoDAO dao = new TransacaoDAO();
        if (dao.buscaTransacao(seq, cxa,data)==true){
        return true;
        }else{
        return false;
        }
    }
    
    @FXML
    void gravarCupom(ActionEvent event) {
        
        try{
        TransacaoDAO dao = new TransacaoDAO();
        Transacao t = new Transacao();
        t.setTxData(txTrnData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        t.setTxHora(txTrnHora.getTime().format(DateTimeFormatter.ofPattern("HHmm")));
        t.setTxSequencial(txTrnSequencial.getText());
        t.setTxCaixa(txTrnCaixa.getText());
        t.setTxCoo(txTrnCoo.getText());
        t.setTxLoja(txTrnLoja.getText());
        t.setTxEcf(txTrnEcf.getText());
        t.setTxClicod(txTrnClicod.getText());
        t.setTxFuncod(txTrnFuncod.getText());
        t.setTxSerieEcf(txTrnSerieEcf.getText());
        t.setTxNfceNum(txNfceNum.getText());
        t.setTxNfceChave(txNfceChave.getText());
        t.setTxNfceProtocolo(txNfceProtocolo.getText());
        t.setTxNfceNumSerie(txNfceNumSerie.getText());
        t.setTxChaveSat(txChaveSat.getText());
        t.setTxValor(new BigDecimal(txTrnValor.getText().replaceAll(",", ".")));
        t.setTxDesconto(new BigDecimal(txTrnDesconto.getText().replaceAll(",", ".")));
        t.setTxAcrecimo(new BigDecimal(txTrnAcrecimo.getText().replaceAll(",", ".")));
        t.setCbImpressora(cbTrnImpressora.getValue().toString().substring(0,2));
        t.setCbTipoTransacao(cbTrnTipoTransacao.getValue().toString().substring(0,1));      
        
        if(rdTrnLogMilenio.visibleProperty().getValue()){
            t.setRdLogMilenio("A");
        }else{
            t.setRdLogMilenio("T");
        }
        if(rdTrnLogVarejo.visibleProperty().getValue()){
            t.setRdLogVarejo("A");
        }else{
            t.setRdLogVarejo("T");
        }
        if(rdTrnMovEstoque.visibleProperty().getValue()){
            t.setRdMovEstoque("S");
        }else{
            t.setRdMovEstoque("N");
        }
        t.setTxTrntrf("A");
        t.setTxVersao(dao.versao());    
        
        //Enviando objeto para cadastro no dao
        if(buscaTransacao(txTrnSequencial.getText(),txTrnCaixa.getText(),txTrnData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")))==true){

            if(dao.cadastrar(t)){
                ItenvdaDao daoItem = new ItenvdaDao();
                for(int i=0; i < listItens.size(); i++){
                    Itenvda item = listItens.get(i);
                    item.setItvseq(String.valueOf(i+1));
                    daoItem.insereItenvda(item);
                    if(t.getRdMovEstoque() == "S"){
                    Estoque est = new Estoque();
                    est.setLoccod("01");
                    est.setMovdat(item.getTxtrndat());
                    est.setMovdoc("CupomDigitado");
                    est.setMovprc("N");
                    if(ckItemCancelado.isSelected()){
                        est.setMovqtd(item.getTxitenquant().toString());
                    }else{
                        est.setMovqtd("-"+item.getTxitenquant());
                    }
                    est.setMovtip("V");
                    est.setProcod(item.getTxprocod());
                    EstoqueMovimentacaoDAO daoEstoque = new EstoqueMovimentacaoDAO();
                    daoEstoque.movEstoque(est);
                    }
                }


                FinalizacaoDAO daoFin = new FinalizacaoDAO();
                for(int i=0; i < listFinalizacao.size(); i++){
                    Finalizacao fin = listFinalizacao.get(i);
                    daoFin.insereFinalizacao(fin);
                }
                mensagemConfirma("Cadastro de Cupom", "Cupom cadastrado com sucesso!");
                limparTodososCampo();
            }else{
                mensagemAlerta("Ocorreu um erro ao inserir Transação", "Contact o administrador do sistema");
            }  
        }else{
        mensagemAlerta("Cadastro de Transação", "Opa parece que esta transação ja esta cadastrada :(");
        }
                    
        
        
        }catch(Exception e){
            mensagemAlerta("Ocorreu um erro ao inserir Transação", "Erro: "+e.getMessage());
        }
    }

    @FXML
    void inserirFinalizacao(ActionEvent event) {
        if(camposVaziosFinalizacao()){
        Finalizacao finalizacao = new Finalizacao();
        finalizacao.setTRNDAT(txTrnData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00.000")));
        finalizacao.setTRNSEQ(txTrnSequencial.getText());
        finalizacao.setCXANUM(txTrnCaixa.getText());
        finalizacao.setFZDSEQ(txFinSeqFinalizadora.getText());
        finalizacao.setFZDCOD(cbFinFinalizadora.getValue().toString().substring(0,3));
        finalizacao.setFZDDES(cbFinFinalizadora.getValue().toString().substring(4));
        finalizacao.setFZDVLR(new BigDecimal(txFinVlrRecebido.getText().replace(",", ".")));
        finalizacao.setFZDTRO(new BigDecimal(txFinTroco.getText().replace(",", ".")));
        
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
    void teclaEnterItemProcod(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {  
            String pro = txItemProcod.getText();
            txItemProcod.setText(StringUtils.leftPad(pro, 14, "0"));
            try{
            ItenvdaDao dao = new ItenvdaDao();
            Produto p = dao.buscarProdutoCodigo(txItemProcod.getText());
            if(p.getPreco() != null){
                txItemProdes.setText(p.getDescricao());
                txItemProcod.setText(p.getCodigo());
                txItemVlrUnitario.setText(p.getPreco().toString());
            }else{
                p = dao.buscarProdutoCodBarras(txItemProcod.getText());
                txItemProdes.setText(p.getDescricao());
                txItemProcod.setText(p.getCodigo());
                txItemVlrUnitario.setText(p.getPreco().toString());            
            }
            txItemQuantidade.setText("1");
            txItemQuantidade.requestFocus();
            }catch(Exception e){
                txItemProcod.requestFocus();
                mensagemAlerta("Produto não localizado!", "Tente novamente com um codigo de barras ou codigo principal valido. Erro: "+e.getMessage());
                
            }
            
        }
    }
    
    @FXML
    void mouseCliquedQuantidade(MouseEvent event) {
        if(!txItemProcod.getText().equals("")){
            String pro = txItemProcod.getText();
            txItemProcod.setText(StringUtils.leftPad(pro, 14, "0"));
            try{
            ItenvdaDao dao = new ItenvdaDao();
            Produto p = dao.buscarProdutoCodigo(txItemProcod.getText());
            if(p.getPreco() != null){
                txItemProdes.setText(p.getDescricao());
                txItemProcod.setText(p.getCodigo());
                txItemVlrUnitario.setText(p.getPreco().toString());
            }else{
                p = dao.buscarProdutoCodBarras(txItemProcod.getText());
                txItemProdes.setText(p.getDescricao());
                txItemProcod.setText(p.getCodigo());
                txItemVlrUnitario.setText(p.getPreco().toString());            
            }
            txItemQuantidade.setText("1");
            txItemQuantidade.requestFocus();
            }catch(Exception e){
                txItemProdes.requestFocus();
                mensagemAlerta("Produto não localizado!", "Tente novamente com um codigo de barras ou codigo principal valido. Erro: "+e.getMessage());
                
            }
        }
    }

    @FXML
    void teclaEnterItemQuantidade(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(!txItemQuantidade.equals("")){          
            BigDecimal quantidade = new BigDecimal(txItemQuantidade.getText());
            BigDecimal vlrUnitario = new BigDecimal(txItemVlrUnitario.getText().replace(",", "."));
            BigDecimal novoVlr = casasDecimais(2, vlrUnitario.multiply(quantidade));
            txItemVlrUnitario.setText(novoVlr.toString().replace(".", ","));
            txItemValorTot.setText(novoVlr.toString().replace(".", ","));
            }
        txItemValorTot.requestFocus();    
        }
    }

    @FXML
    void teclaEnterItemVlrTotal(KeyEvent event) {

    }

    @FXML
    void teclaEnterItemVlrUnit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { 
            if(txItemVlrUnitario.getText().equals("")){
                txItemVlrUnitario.setText("0,00");
            }
            if(!txItemQuantidade.equals("")){          
            BigDecimal quantidade = new BigDecimal(txItemQuantidade.getText());
            BigDecimal vlrUnitario = new BigDecimal(txItemVlrUnitario.getText().replace(",", "."));
            BigDecimal novoVlr = casasDecimais(2, vlrUnitario.multiply(quantidade));
            txItemVlrUnitario.setText(novoVlr.toString().replace(".", ","));
            txItemValorTot.setText(novoVlr.toString().replace(".", ","));
            }
            txItemDesconto.requestFocus();
        }
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
    public boolean camposVaziosItens(){
        if(txItemProcod.getText().isEmpty() | txItemAcrecimo.getText().isEmpty() | txItemDesconto.getText().isEmpty()|
                txItemQuantidade.getText().isEmpty() | txItemValorTot.getText().isEmpty() | cbItemTributacao.getSelectionModel().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean camposVaziosFinalizacao(){
        if(txTrnCaixa.getText().isEmpty() | txTrnSequencial.getText().isEmpty() |txFinSeqFinalizadora.getText().isEmpty() | txFinVlrRecebido.getText().isEmpty() | cbFinFinalizadora.getSelectionModel().isEmpty()){
            return false;
        }else{
            return true;
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
    
    public void preencherTributacoes(){
        ItenvdaDao dao = new ItenvdaDao();
        List<Tributacao> lista = dao.buscarTributacao(); 
        for (int i=0; i < lista.size(); i++){
            cbItemTributacao.getItems().add(lista.get(i).getTrbid());          
            
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
    
    public void calcularCupomTabela(){
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i<ObservableListItens.size();i++){
        BigDecimal coluna = new BigDecimal(tbItens.getVisibleLeafColumn(4).getCellObservableValue(i).getValue().toString());
        total = total.add(coluna);
        }
        System.out.println("Total: "+total);
        lbTotalItemCupom.setText(String.valueOf(total).replace(".", ","));
    } 
    
    private void preencherFinalizadoras() {
        FinalizadoraDAO dao = new FinalizadoraDAO();
        List<Finalizadora> listFinalizadora = dao.listarFinalizadora();
        for (Finalizadora finalizadora : listFinalizadora) {
            cbFinFinalizadora.getItems().add(finalizadora.getFzdcod()+"-"+finalizadora.getFzddes());
        }
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
    
    public void calcularFinalizacaoTabela(){
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i<ObservableListFinalizacao.size();i++){
        BigDecimal coluna = new BigDecimal(tbFinalizacao.getVisibleLeafColumn(2).getCellObservableValue(i).getValue().toString());
        total = total.add(coluna);
        }
        System.out.println("Total: "+total);
        lbTotalFinalizacao.setText(String.valueOf(total).replace(".", ","));
    }

    private void limparTodososCampo() {
        txTrnAcrecimo.setText("");        
        txTrnCaixa.setText("");
        txTrnCoo.setText("");
        txTrnData.getEditor().setText("");
        txTrnDesconto.setText("");
        txTrnEcf.setText("");
        txTrnHora.getEditor().setText("");
        txTrnLoja.setText("");
        txTrnSequencial.setText("");
        txTrnSerieEcf.setText("");
        txTrnValor.setText("");
        cbTrnImpressora.getSelectionModel().clearSelection();
        cbTrnTipoTransacao.getSelectionModel().clearSelection();
        txChaveSat.setText("");
        txTrnClicod.setText("");
        txTrnFuncod.setText("");
        txNfceChave.setText("");
        txNfceNum.setText("");
        txNfceNumSerie.setText("");
        txNfceProtocolo.setText("");
        txNomeCliente.setText("");
        txNomeFuncionario.setText("");
        
        for (int i=0; i<=listItens.size();i++){
            listItens.remove(i);
            ObservableListItens.remove(i);
        }
        txItemAcrecimo.setText("0,00");
        txItemDesconto.setText("0,00");
        txItemProcod.setText("");
        txItemProdes.setText("");
        txItemQuantidade.setText("");
        txItemValorTot.setText("");
        txItemVlrUnitario.setText("");
        cbItemTributacao.getSelectionModel().clearSelection();
        
        
        txFinSeqFinalizadora.setText("");
        txFinTroco.setText("0,00");
        txFinVlrRecebido.setText("0,00");
        cbFinFinalizadora.getSelectionModel().clearSelection();
        for (int i=0; i<=listFinalizacao.size();i++){
            listFinalizacao.remove(i);
            ObservableListFinalizacao.remove(i);
        }
        
        tabPane.getSelectionModel().select(tabTansacao);
    }
    
    

}
