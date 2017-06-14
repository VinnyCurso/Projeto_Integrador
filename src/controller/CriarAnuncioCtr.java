/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import database.ManipularImagem;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.StatImage;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class CriarAnuncioCtr implements Initializable {
    
    @FXML
    private JFXTextArea txtareaDescricao;

    @FXML
    private JFXComboBox<?> comboCategoria;

    @FXML
    private Label inserirImagem;

    @FXML
    private Hyperlink carregarImagem;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    private ImageView imagens;
    
    BufferedImage imagem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CriarAnuncioView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Cadastro Usuario");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
    
    
    public void CarregarImagen(){
     StatImage start = new StatImage();
     start.NStart();
    }
    
    public void CarregarImagens(){
     JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);

                imagens.setImage(new Image("/image/"));

            } catch (Exception ex) {
               // System.out.println(ex.printStackTrace().toString());
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }
    }
    
      public void EnvairImagen()throws Exception{
    
        try {
             // TODO add your handling code here:
             String caminho = getClass().getResource("/image/").toString().substring(5);
             File outputfile = new File(caminho+"image.jpg");
             ImageIO.write(imagem, "jpg", outputfile);
             JOptionPane.showMessageDialog(null, "Imagem enviada com sucesso");
         } catch (IOException ex) {
             Logger.getLogger(CriarAnuncioCtr.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
