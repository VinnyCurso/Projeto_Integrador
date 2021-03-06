/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class AnunciosMenuCtr implements Initializable {

    @FXML
    private JFXButton btnCriarAnuncio;

    @FXML
    private JFXButton btnListarAnuncios;

    @FXML
    private JFXButton btnAnuncio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Evento Chamar a tela 
    public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AnunciosMenuView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Anuncio Menu");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    public void btnOnActionCriarAnuncio() throws IOException {

        CriarAnuncioCtr criarAnuncioCtr = new CriarAnuncioCtr();
        criarAnuncioCtr.gerarTela();
       
    }

    @FXML
    public void btnOnActionListarAnuncio() throws IOException {

        ListaAnuncioViewCtr listaAnuncioViewCtr = new ListaAnuncioViewCtr();
        listaAnuncioViewCtr.gerarTela();
    }

    @FXML
    public void btnOnActionAnuncios() throws IOException {
        AnuncioCtr anuncioCtr = new AnuncioCtr();
        anuncioCtr.gerarTela();
    }
    
  
    
}
