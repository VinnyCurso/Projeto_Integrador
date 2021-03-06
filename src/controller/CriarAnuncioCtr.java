/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.AnuncioDao;
import dao.ImagemDao;
import database.Database;
import database.DatabaseFactory;
import database.ManipulaImagem;
import database.ManipularImagem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Anuncio;
import model.Imagem;
//import model.StatImage;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class CriarAnuncioCtr implements Initializable {
    
    @FXML private JFXTextField txtTitulo;
    @FXML private JFXTextArea txtareaDescricao;
    @FXML private JFXComboBox<String> comboCategoria;
    @FXML private Label inserirImagem;
    @FXML private Hyperlink carregarImagem;
    @FXML private JFXButton btnSalvar;
    @FXML private JFXButton btnCancelar;
    @FXML private JFXTextField txtPreco;
    @FXML private ObservableList<String> ListaobservavelCategoria;
    @FXML private ImageView imagens;
    
    private Anuncio anuncio;
    private Imagem imag;
    BufferedImage imagem;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AnuncioDao anuncioDao = new AnuncioDao();
    private final ImagemDao imagemDao = new ImagemDao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        anuncioDao.setConnection(connection);
        anuncio = new Anuncio();
        CarregarComboCategoria();
    }

    //Evento Chamar a tela 
    public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CriarAnuncioView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Cadastro Anuncio");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public void CarregarImagen() {
//        StatImage start = new StatImage();
//        start.NStart();
    }

    public File CarregarImagens() {
        
//        JFileChooser fc = new JFileChooser();
//        int res = fc.showOpenDialog(null);
//
//        if (res == JFileChooser.APPROVE_OPTION) {
//            File arquivo = fc.getSelectedFile();
//            
//
//            try {
//                imagem = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);
//
//                imagens.setImage(new Image("/image/"));
//
//            } catch (Exception ex) {
//                // System.out.println(ex.printStackTrace().toString());
//            }
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
//        }
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extesionJPEG = new FileChooser.ExtensionFilter("JPEG arquivos (*.JPEG)", "*.JPEG");
        FileChooser.ExtensionFilter extesionJpeg = new FileChooser.ExtensionFilter("jpeg arquivos (*.jpeg)", "*.jpeg");
        FileChooser.ExtensionFilter extesionJPG = new FileChooser.ExtensionFilter("JPG arquivos (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extesionJpg = new FileChooser.ExtensionFilter("jpg arquivos (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extesionPNG = new FileChooser.ExtensionFilter("PNG arquivos (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extesionPng = new FileChooser.ExtensionFilter("png arquivos (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(extesionJPG, extesionJpg, extesionJPEG, extesionJpeg, extesionPNG, extesionPng);

        return fileChooser.showOpenDialog(null);
    }

    public void EnvairImagen() throws Exception {
//
//        try {
//            // TODO add your handling code here:
//            String caminho = getClass().getResource("/image/").toString().substring(5);
//            File outputfile = new File(caminho + "image.jpg");
//            ImageIO.write(imagem, "jpg", outputfile);
//            JOptionPane.showMessageDialog(null, "Imagem enviada com sucesso");
//        } catch (IOException ex) {
//            Logger.getLogger(CriarAnuncioCtr.class.getName()).log(Level.SEVERE, null, ex);
//        }
                try {
                Imagem obj = new Imagem();
             obj.setImagem(ManipularImagem.getImgBytes(imagem));
             ImagemDao dao = new ImagemDao();
             Boolean foi = dao.inserir(obj);
             if(foi)
             {
                 JOptionPane.showMessageDialog(null, "Imagem enviada com sucesso");
         
             }
             else
             {
                JOptionPane.showMessageDialog(null, "Imagem não enviada");
         
             }
             
             } catch (Exception ex) {
             Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    public void CarregarComboCategoria() {

        List<String> listaCategoria = new ArrayList();
        String nome[] = {"Computadores", "Celulares", "Produtos e outros",};
        String[] a = new String[nome.length];
        for (int i = 0; i < nome.length; i++) {
            a[i] = new String(nome[i]);
            listaCategoria.add(a[i]);
        }
        ListaobservavelCategoria = FXCollections.observableArrayList(listaCategoria);
        comboCategoria.setItems(ListaobservavelCategoria);
    }

    private void LimparTela() {
        txtTitulo.setText("");
        txtareaDescricao.setText("");
        comboCategoria.setValue("");
        txtPreco.setText("");

    }

    @FXML
    public void btnOnActionSalvar() throws IOException, SQLException {

        if (anuncio != null) {
            Anuncio anuncios = new Anuncio();
            
            anuncios.setTitulo(txtTitulo.getText());
            anuncios.setDescricao(txtareaDescricao.getText());
            anuncios.setCategoria(comboCategoria.getValue());
            anuncios.setValor(Float.parseFloat(txtPreco.getText()));

            anuncioDao.inserir(anuncios);
            JOptionPane.showMessageDialog(null, "Anuncio Salvo com sucesso ");
            LimparTela();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Favor , inserir dados corretos");
            alert.show();
        }

    }

    @FXML
    public void btnOnActionCancelar(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }
    
//     public File selecionaImagem() {
////        FileChooser fileChooser = new FileChooser();
////
////        FileChooser.ExtensionFilter extesionJPEG = new FileChooser.ExtensionFilter("JPEG arquivos (*.JPEG)", "*.JPEG");
////        FileChooser.ExtensionFilter extesionJpeg = new FileChooser.ExtensionFilter("jpeg arquivos (*.jpeg)", "*.jpeg");
////        FileChooser.ExtensionFilter extesionJPG = new FileChooser.ExtensionFilter("JPG arquivos (*.JPG)", "*.JPG");
////        FileChooser.ExtensionFilter extesionJpg = new FileChooser.ExtensionFilter("jpg arquivos (*.jpg)", "*.jpg");
////        FileChooser.ExtensionFilter extesionPNG = new FileChooser.ExtensionFilter("PNG arquivos (*.PNG)", "*.PNG");
////        FileChooser.ExtensionFilter extesionPng = new FileChooser.ExtensionFilter("png arquivos (*.png)", "*.png");
////
////        fileChooser.getExtensionFilters().addAll(extesionJPG, extesionJpg,extesionJPEG, extesionJpeg, extesionPNG, extesionPng);
////
////        return fileChooser.showOpenDialog(null);
//    }
     
     @FXML
    public void abrirImagem(MouseEvent event) throws FileNotFoundException {
        File arquivoSelecionado = CarregarImagens();

        if (arquivoSelecionado != null) {
            imagens.setImage(new Image(new FileInputStream(arquivoSelecionado)));
        }
    }

    
    
    

}
