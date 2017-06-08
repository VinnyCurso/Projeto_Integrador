/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author vinicius caetano
 */
public class MainAppCtr implements Initializable {
    
    private Stage dialogStage;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        //            Imagem Botao 
//        Image imganuncio = new Image(getClass().getResourceAsStream("/imagem/anuncio.png"));
//        ImageView imgView10 = new ImageView(imganuncio);
//        imgView10.setFitHeight(65);
//        imgView10.setFitWidth(80);
//        btnAnuncio.setGraphic(imgView10);
        
    }    
    
    
    //Ação para chamar a tela de Anuncio
     @FXML
    public void btnOnActionCliente()throws IOException {
        
        AnuncioCtr anuncioCtr = new AnuncioCtr();
        anuncioCtr.gerarTela();
}
}
