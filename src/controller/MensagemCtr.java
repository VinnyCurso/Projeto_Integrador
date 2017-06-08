/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ConectaBanco;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Mensagem;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class MensagemCtr implements Initializable {
    
    private Mensagem mensagem;
        private Stage stage;

    ConectaBanco conecta = new ConectaBanco();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           mensagem = new Mensagem();
        conecta.conexao();
    } 
    
      //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/br/view/ClienteView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Manter Anuncio");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
         //Botoes
    @FXML
    public void btnOnActionSalvar() throws IOException, SQLException {

        JOptionPane.showMessageDialog(null, "Salvo com sucesso ");

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
    
}
