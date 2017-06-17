/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.UsuarioDao;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class UsuarioAlterarExcluirCtr implements Initializable {
    
    @FXML
    private JFXComboBox<String> comboFiltroCliente;

    @FXML
    private JFXTextField txtProcuraCliente;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnExcluir;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private TableView<Usuario> tableClientes;

    @FXML
    private TableColumn<Usuario, String> colunaCodigo;

    @FXML
    private TableColumn<Usuario, String> colunaUsuario;

    @FXML
    private TableColumn<Usuario, String> colunaEmail;

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtSobrenome;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtSenha;
    
    @FXML private ObservableList<String> ListaobservavelFitro;
    
    //tabela
    
    private List<Usuario> ListaUsuario;
    private ObservableList<Usuario> ObservableListaUsuario;
    
    private Usuario usuario;
    
    
     //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
        usuario = new Usuario();
        CarregarComboFiltro();
        CarregarTabelaUsuario();
        
        tableClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                selecionarItemTabela(newValue);
            } catch (IOException ex) {
                Logger.getLogger(UsuarioAlterarExcluirCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
               
    } 
    
        //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/UsuarioAlterarExcluirView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Alterar e Exlcuir Usuario");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
         private void preencherTela(Usuario usuario) throws Exception  {

        if (usuario.getCodigo() == 0) {
            throw new Exception("Cliente não encontrado!");
        }
            txtNome.setText(""+usuario.getNome());
            txtSobrenome.setText(""+usuario.getSobrenome());
            txtTelefone.setText(""+usuario.getTelefone());
            txtEmail.setText(""+usuario.getEmail());            
            txtSenha.setText(""+usuario.getSenha());       
            
    }
         
          public void CarregarComboFiltro(){
        List<String> listaFiltro = new ArrayList();
        String nome[] = {"Nome","Sobrenome","Codigo","email",};
        String[] a = new String[nome.length];       
        for(int i =0; i< nome.length;i++){
           a[i] = new String(nome[i]); 
           listaFiltro.add(a[i]);
        }    
        ListaobservavelFitro = FXCollections.observableArrayList(listaFiltro);     
        comboFiltroCliente.setItems(ListaobservavelFitro);
    }
          
           public void CarregarTabelaUsuario(){
               
               colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
               colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
               colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
               
               ListaUsuario = usuarioDao.listar();
               
               ObservableListaUsuario = FXCollections.observableArrayList(ListaUsuario);
               tableClientes.setItems(ObservableListaUsuario);
  
    }
           
     public void selecionarItemTabela(Usuario usuario) throws IOException {

        if (this.usuario != null) {
            txtCodigo.setText(String.valueOf(usuario.getCodigo()));
            txtNome.setText(usuario.getNome());
            txtSobrenome.setText(usuario.getSobrenome());
            txtTelefone.setText(usuario.getTelefone());
            txtEmail.setText(usuario.getEmail());
            txtSenha.setText(usuario.getSenha());
        } else {

      LimparTela();
        }

    }
        
     @FXML
        public void btnOnActionRemover() throws IOException {

     Usuario user = tableClientes.getSelectionModel().getSelectedItem();
     usuarioDao.remover(user);
     JOptionPane.showMessageDialog(null, "Cadastro excluido com sucesso .");
     LimparTela();
     }

    
     
    @FXML
    public void btnOnActionCancelar(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }
    
      @FXML
    public void LimparTela() throws IOException {

          txtCodigo.setText("");
          txtNome.setText("");
          txtSobrenome.setText("");
          txtTelefone.setText("");
          txtEmail.setText("");
          txtSenha.setText("");

    }
    
}
