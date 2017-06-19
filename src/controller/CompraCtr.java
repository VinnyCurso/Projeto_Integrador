/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.CompraDao;
import dao.UsuarioDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Compra;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class CompraCtr implements Initializable {
    
      @FXML
    private Label labelProduto;

    @FXML
    private Label labelPreco;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelFormaNegociacao;

    @FXML
    private JFXButton btnComprar;

    @FXML
    private JFXButton btnCancelar;
    
       private Compra compra;
       private Stage dialogStage;

    ConectaBanco conecta = new ConectaBanco();

      
         //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CompraDao compraDao = new CompraDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compra = new Compra();
        conecta.conexao();
        compraDao.setConnection(connection);
    } 
    
      //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CompraView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Manter Compra");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
         //Botoes
    @FXML
    public void btnOnActionComprar() throws IOException {

        JOptionPane.showMessageDialog(null, "Compra realizada com sucesso ");

    }
    
     @FXML
    public void btnOnActionCancelar(ActionEvent event) throws IOException {

         Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }
    
   
}
