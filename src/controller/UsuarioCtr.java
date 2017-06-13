/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.AcessoDao;
import dao.UsuarioDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class UsuarioCtr implements Initializable {
    
    @FXML JFXTextField txtNome;
    @FXML JFXTextField txtSobrenome;
    @FXML JFXTextField txtTelefone;
    @FXML JFXTextField txtEmail;
    @FXML JFXTextField txtSenha;
    
    
    @FXML private JFXButton btnSalvar;
    @FXML private JFXButton btnAlterar;
    @FXML private JFXButton btnExcluir;
    @FXML private JFXButton btnCancelar;
    
    private Usuario usuario;
    private Stage dialogStage;

    ConectaBanco conecta = new ConectaBanco();

      
         //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usuario = new Usuario();
        conecta.conexao();
        usuarioDao.setConnection(connection);
    } 
    
      //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/UsuarioView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Cadastro Usuario");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
        
         //Botoes
    @FXML
    public void btnOnActionSalvar() throws IOException, SQLException {

        
        if (usuario != null) {
            Usuario user = new Usuario();
            
             user.setNome(txtNome.getText());
             user.setSobrenome(txtSobrenome.getText());
             user.setTelefone(txtTelefone.getText());
             user.setEmail(txtEmail.getText());
             user.setSenha(txtSenha.getText());
      
            usuarioDao.inserir(user);
            JOptionPane.showMessageDialog(null, "Usuario Salvo com sucesso ");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor , inserir dados corretos");
            alert.show();
        }

    }
    
     @FXML
    public void btnOnActionExcluir() throws IOException {

        JOptionPane.showMessageDialog(null, "Removido com sucesso ");

    }
    
     @FXML
    public void btnOnActionAlterar() throws IOException {

        JOptionPane.showMessageDialog(null, "Dados alterados com sucesso ");

    }

    @FXML
    public void btnOnActionNovo() throws IOException {

        JOptionPane.showMessageDialog(null, "Preparado para novos dados ");

    }
    
     @FXML
    public void btnOnActionConsultar() throws IOException {

        JOptionPane.showMessageDialog(null, "Qual dado voce deseja consultar ? ");

    }
    
      @FXML
    public void btnOnActionlistar() throws IOException {

        JOptionPane.showMessageDialog(null, "Segue a lista de dados : ");

    }
    
       @FXML
    public void btnOnActionCancelar() throws IOException {

        JOptionPane.showMessageDialog(null, "Segue a lista de dados : ");
        

    }
    
}
