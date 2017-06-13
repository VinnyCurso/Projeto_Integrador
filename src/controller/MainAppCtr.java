/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author vinicius caetano
 */
public class MainAppCtr implements Initializable {
    
    @FXML  JFXButton btnAnuncio;
    @FXML JFXButton btnUsuario;
    @FXML JFXButton btnCategoria;
    @FXML JFXButton btnMensagem;
    
    
    @FXML private AnchorPane anchorpane;
    @FXML private JFXDrawer drawer;
    @FXML private JFXHamburger hamburguer;
    @FXML private VBox box;
   
   
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/view/DrawerView.fxml"));
            
            drawer.setSidePane(box);
            HamburgerBackArrowBasicTransition transacao = new HamburgerBackArrowBasicTransition();
            transacao.setRate(-1);
            hamburguer.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)->{
                transacao.setRate(transacao.getRate()*-1);
                transacao.play();
                
                if(drawer.isShown()){
                    drawer.close();
                }else{
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MainAppCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainApp.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Sistema Comercial Iguanys");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
        
                   //Ação para chamar a tela de Mensagem
     @FXML
    public void btnOnActionCliente()throws IOException {
        
        UsuarioMenusCtr usuarioMenusCtr = new UsuarioMenusCtr();
        usuarioMenusCtr.gerarTela();
    }
  
}
