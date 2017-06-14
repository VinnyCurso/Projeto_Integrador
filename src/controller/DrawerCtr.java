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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class DrawerCtr implements Initializable {
    
    
     @FXML
    private JFXButton btnUsuario;

    @FXML
    private JFXButton btnAnuncio;

    @FXML
    private JFXButton btnListaAnuncio;

    @FXML
    private JFXButton btnAvaliacao;

    @FXML
    private JFXButton btnSair;
    
    @FXML private VBox box;
    @FXML private Stage dialogStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
       box = FXMLLoader.load(getClass().getResource("/view/DrawerView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(box);

        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        @FXML
         public void btnActionSair()throws IOException{
        Platform.exit();
    }
        
                         //Ação para chamar a tela de Mensagem
     @FXML
    public void btnOnActionCliente()throws IOException {
        
        UsuarioMenusCtr usuarioMenusCtr = new UsuarioMenusCtr();
        usuarioMenusCtr.gerarTela();
    }
    
                         //Ação para chamar a tela de Mensagem
     @FXML
    public void btnOnActionAnuncio()throws IOException {
        
        AnunciosMenuCtr anunciosMenuCtr = new AnunciosMenuCtr();
        anunciosMenuCtr.gerarTela();
    }
    
                         //Ação para chamar a tela de Mensagem
     @FXML
    public void btnOnActionAvaliacao()throws IOException {
        
        AvaliacaoVendedorCtr avaliacaoVendedorCtr = new AvaliacaoVendedorCtr();
        avaliacaoVendedorCtr.gerarTela();
    }
    
    @FXML
    public void btnOnActionListaAnuncio()throws IOException {
        
        ListaAnuncioViewCtr listaAnuncioViewCtr = new ListaAnuncioViewCtr();
        listaAnuncioViewCtr.gerarTela();
    }
    
}
