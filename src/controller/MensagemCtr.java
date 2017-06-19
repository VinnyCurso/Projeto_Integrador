/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.MensagemDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Mensagem;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class MensagemCtr implements Initializable {
    
    
       @FXML
    private JFXComboBox<String> comboFiltroMensagem;

    @FXML
    private JFXTextField txtProcuraMensagem;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private JFXButton btnEnviar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXComboBox<String> comboUsuario;

    @FXML
    private JFXTextArea AreaMensagem;

    @FXML
    private JFXComboBox<String> comboCategoria;

    @FXML
    private JFXComboBox<String> comboTipo;

    @FXML
    private TableView<Mensagem> tableMensagem;

    @FXML
    private TableColumn<Mensagem, String> colunaUsuario;

    @FXML
    private TableColumn<Mensagem, String> colunaCategoria;

    @FXML
    private TableColumn<Mensagem, String> colunaTipo;

    @FXML
    private TableColumn<Mensagem, String> colunaMensagem;
    
    @FXML private ObservableList<String> ListaobservavelFitro;
    
    @FXML private ObservableList<String> ListaobservavelUsuario;
    
    @FXML private ObservableList<String> ListaobservavelCategoria;
    
    @FXML private ObservableList<String> ListaobservavelTipo;
    
    
    //tabela
    
    private List<Mensagem> ListaMensagem;
    private ObservableList<Mensagem> ObservableListaMensagem;
    
    private Mensagem mensagem;
        private Stage stage;
        
         //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MensagemDao mensagemDao = new MensagemDao();

    ConectaBanco conecta = new ConectaBanco();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mensagemDao.setConnection(connection);
        mensagem = new Mensagem();
        conecta.conexao();

        CarregarComboFiltro();
        CarregarComboUsuario();
        CarregarComboCategoria();
        CarregarComboTipo();
        CarregarTabelaMensagem();
    } 
    
      //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MensagemView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Manter Mensagem");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
    //Combos
    public void CarregarComboFiltro() {
        List<String> listaFiltro = new ArrayList();
        String nome[] = {"Codigo", "Usuario", "Categoria", "Tipo",};
        String[] a = new String[nome.length];
        for (int i = 0; i < nome.length; i++) {
            a[i] = new String(nome[i]);
            listaFiltro.add(a[i]);
        }
        ListaobservavelFitro = FXCollections.observableArrayList(listaFiltro);
        comboFiltroMensagem.setItems(ListaobservavelFitro);
    }

    public void CarregarComboUsuario() {
        List<String> listaUsuario = new ArrayList();
        String nome[] = {"VInicius", "Guilherme", "Jeferson",};
        String[] a = new String[nome.length];
        for (int i = 0; i < nome.length; i++) {
            a[i] = new String(nome[i]);
            listaUsuario.add(a[i]);
        }
        ListaobservavelUsuario = FXCollections.observableArrayList(listaUsuario);
        comboUsuario.setItems(ListaobservavelUsuario);

    
        
        
    }

    public void CarregarComboCategoria() {
        List<String> listaCategoria = new ArrayList();
        String nome[] = {"Comprador", "Vendedor", "Curioso",};
        String[] a = new String[nome.length];
        for (int i = 0; i < nome.length; i++) {
            a[i] = new String(nome[i]);
            listaCategoria.add(a[i]);
        }
        ListaobservavelCategoria = FXCollections.observableArrayList(listaCategoria);
        comboCategoria.setItems(ListaobservavelCategoria);
    }

    public void CarregarComboTipo() {
        List<String> listaTipo = new ArrayList();
        String nome[] = {"Pergunta", "Resposta",};
        String[] a = new String[nome.length];
        for (int i = 0; i < nome.length; i++) {
            a[i] = new String(nome[i]);
            listaTipo.add(a[i]);
        }
        ListaobservavelTipo = FXCollections.observableArrayList(listaTipo);
        comboTipo.setItems(ListaobservavelTipo);
    }
    
    
    //Tabela
    
     public void CarregarTabelaMensagem(){
               
               colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
               colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
               colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
               colunaMensagem.setCellValueFactory(new PropertyValueFactory<>("mensagem"));
             
               
               ListaMensagem = mensagemDao.listar();
               
               ObservableListaMensagem = FXCollections.observableArrayList(ListaMensagem);
               tableMensagem.setItems(ObservableListaMensagem);
  
    }
        
         //Botoes
    @FXML
    public void btnOnActionEnviar() throws IOException, SQLException {

      if (mensagem != null) {
            Mensagem mensagem = new Mensagem();
            
             mensagem.setUsuario(comboUsuario.getValue());
             mensagem.setCategoria(comboCategoria.getValue());
             mensagem.setTipo(comboTipo.getValue());
             mensagem.setMensagem(AreaMensagem.getText());
  
      
            mensagemDao.inserir(mensagem);
            JOptionPane.showMessageDialog(null, "Mensagem Enviada com sucesso ");
            LimparTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor , inserir dados corretos");
            alert.show();
        }

    }

    
    
     @FXML
    public void btnOnActionPesquisar() throws IOException {

        JOptionPane.showMessageDialog(null, "Removido com sucesso ");

    }
    
     @FXML
    public void btnOnActionCancelar() throws IOException {

         Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }
    
      @FXML
    public void LimparTela() throws IOException {

          txtProcuraMensagem.setText("");
          comboFiltroMensagem.setValue("");
          comboUsuario.setValue("");
          comboCategoria.setValue("");
          comboTipo.setValue("");
          AreaMensagem.setText("");
 

    }

    
    
}
