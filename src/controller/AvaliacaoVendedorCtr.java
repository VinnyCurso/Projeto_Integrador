/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.AvaliacaoVendedorDao;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.AvaliacaoVendedor;
import model.Mensagem;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class AvaliacaoVendedorCtr implements Initializable {
    
      @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtAnuncio;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXTextField txtNota;

    @FXML
    private JFXTextArea txtElogio;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnCancelar;
    
     private AvaliacaoVendedor avaliacaoVendedor;
      private Stage dialogStage;

    ConectaBanco conecta = new ConectaBanco();

      
         //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AvaliacaoVendedorDao avaliacaoVendedorDao = new AvaliacaoVendedorDao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          avaliacaoVendedor = new AvaliacaoVendedor();
        conecta.conexao();
        avaliacaoVendedorDao.setConnection(connection);
    }   
    
    //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AvaliacaoVendedorView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Avaliacao Vendedor");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
        @FXML
    public void btnOnActionEnviar() throws IOException, SQLException {

      if (avaliacaoVendedor != null) {
            AvaliacaoVendedor AvaliacaoV = new AvaliacaoVendedor();
           
             AvaliacaoV.setAnuncio(txtAnuncio.getText());
             AvaliacaoV.setUsuario(txtUsuario.getText());
             AvaliacaoV.setNota(Integer.parseInt(txtNota.getText()));
             AvaliacaoV.setSugestao(txtElogio.getText());
  
      
            avaliacaoVendedorDao.inserir(AvaliacaoV);
            JOptionPane.showMessageDialog(null, "Mensagem Enviada com sucesso ");
            LimparTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor , inserir dados corretos");
            alert.show();
        }
      
     
    }
      private void LimparTela() {
        txtAnuncio.setText("");
        txtUsuario.setText("");
        txtNota.setText("");
        txtElogio.setText("");
      }
      
         @FXML
    public void btnOnActionCancelar(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }

    
}
