/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class UsuarioMenusCtr implements Initializable {
    
        @FXML
    private Pane paneClienteNovo;

    @FXML
    private Pane paneClienteExcluir;

    @FXML
    private Pane paneClienteAlterar;
    
     @FXML
    private AnchorPane cardPane;
     
    private Usuario usuario;
    private Stage dialogStage;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/UsuarioMenusView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Informações Usuario");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
    
}
