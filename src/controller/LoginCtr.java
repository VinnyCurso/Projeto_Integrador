/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.ConectaBanco;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Acesso;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class LoginCtr implements Initializable {

    @FXML
     JFXTextField txtUsuario;
    @FXML
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acesso = new Acesso();
     conecta.conexao();
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
}
