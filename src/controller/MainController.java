/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class MainController implements Initializable {
    
     @FXML private JFXHamburger ItsMenu;
     @FXML private AnchorPane anchorPane;
     @FXML private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane();
        
//        HamburgerSlideCloseTransition transacao = new HamburgerSlideCloseTransition(ItsMenu);
//        transacao.setRate(-1);
//        ItsMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
//            transacao.setRate(transacao.getRate() * -1);
//            transacao.play();
//        });
        
         HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(ItsMenu);
         burgerTask2.setRate(-1);
         ItsMenu.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
             burgerTask2.setRate(burgerTask2.getRate()*-1);
             burgerTask2.play();
             
//             if(drawer.isShown()){  
//             drawer.hide();
//             }else{
//                drawer.draw(); 
//             }
//             
         });
    }    
    
}
