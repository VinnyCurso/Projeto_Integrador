/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import dao.AcessoDao;
import dao.UsuarioDao;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Usuario;

/**
 *
 * @author vinicius caetano
 */
public class LoginViewController implements Initializable {

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogar;

    @FXML
    private JFXButton btnSair;

    @FXML
    private JFXButton btnCadasfrar;

    @FXML
    private AnchorPane cardPane;

    private Acesso acesso;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AcessoDao acessoDao = new AcessoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        JFXDepthManager.setDepth(cardPane, 2);
        acesso = new Acesso();
        acessoDao.setConnection(connection);

    }

    //Metodos
    public void btnActionSair() throws IOException {
        Platform.exit();
    }

    //Ação para chamar a tela Principal
    @FXML
    public void btnOnActionLogar() throws IOException {

        if (acesso != null) {
            Acesso acesso = new Acesso();

            acesso.setUsuario(txtUsuario.getText());
            acesso.setSenha(txtSenha.getText());
            acessoDao.inserir(acesso);

            String sql = "select * from login where usuario = ? and senha = ?"; //Comando do banco de dados

            try {

                PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, txtUsuario.getText()); //comando para buscar informações do usuario
                stmt.setString(2, txtSenha.getText()); //comando para buscar informações da senha

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    JOptionPane.showMessageDialog(null, "Loguin efetuado com sucesso ");
                    MainAppCtr mainAppCtr = new MainAppCtr();
                    mainAppCtr.gerarTela();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Favor , inserir dados corretos");
                    alert.show();
                }

            } catch (SQLException error) {
                JOptionPane.showMessageDialog(null, error);
            }

        }

    }
    
               //Ação para chamar a tela de Mensagem
     @FXML
    public void btnOnActionCadastrar()throws IOException {
        
        UsuarioCtr usuarioCtr = new UsuarioCtr();
        usuarioCtr.gerarTela();
    }
    
}
