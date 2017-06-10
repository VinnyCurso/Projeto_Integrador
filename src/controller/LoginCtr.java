/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.AcessoDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Acesso;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class LoginCtr implements Initializable {

    @FXML
     JFXTextField txtUsuario;
    JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogar;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnCancelar;

    private Stage dialogStage;

    private Acesso acesso;
    
        ConectaBanco conecta = new ConectaBanco();
    @FXML
    private JFXPasswordField txSenha;
    
    
     //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AcessoDao acessoDao = new AcessoDao();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acesso = new Acesso();
     conecta.conexao();
     
      acessoDao.setConnection(connection);
    }

       
        //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Manter Login");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
        
        
        
              //Botoes
    @FXML
    public void btnOnActionSalvar() throws IOException, SQLException {

        JOptionPane.showMessageDialog(null, "Clicou ");
         if (acesso != null) {
            Acesso acesso = new Acesso();
            acessoDao.inserir(acesso);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso ");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor , dados corretos");
            alert.show();
        }

    }
    
     @FXML
    public void btnOnActionExcluir() throws IOException {

        JOptionPane.showMessageDialog(null, "Removido com sucesso ");

    }
     @FXML
    public void btnOnActionCadastroUsuario()throws IOException {
        
        UsuarioCtr usuarioCtr = new UsuarioCtr();
        usuarioCtr.gerarTela();
}
}
