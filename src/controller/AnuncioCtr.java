/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import dao.AnuncioDao;
import dao.ImagemDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import database.ManipularImagem;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Anuncio;
import model.Imagem;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class AnuncioCtr implements Initializable {

    @FXML private JFXButton btnPesquisar;
    @FXML private ImageView imagemview;
    @FXML private Label labelDescricao;
    @FXML private Label labelPreco;
    @FXML private Label labelCodigo;
    @FXML private JFXButton btnAnterior;
    @FXML private JFXButton btnProximo;
    @FXML private JFXButton btnListaAnuncio;
    @FXML private JFXButton btnDetalhesProduto;
    @FXML private JFXButton btnComprar;
    @FXML private JFXButton btnCancelar;

    private Anuncio anuncio;
    private Stage stage;

    
   //Banco
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AnuncioDao anuncioDao = new AnuncioDao();
    ConectaBanco conecta ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        carregarBotoesProximo();
        anuncio = new Anuncio();
        anuncioDao.setConnection(connection);
    
    }

    //Evento Chamar a tela 
    public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AnuncioView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Manter Anuncio");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    //Botoes
    @FXML
    public void btnOnActionListarAnuncio() throws IOException {

        ListaAnuncioViewCtr listaAnuncioViewCtr = new ListaAnuncioViewCtr();
        listaAnuncioViewCtr.gerarTela();
    }

    @FXML
    public void btnOnActionDetalhesProduto() throws IOException, SQLException {

        JOptionPane.showMessageDialog(null, "Segue os detalhes do Produto ");

    }

    @FXML
    public void btnOnActionCompra() throws IOException, SQLException {

         MensagemCtr mensagemCtr = new MensagemCtr();
        mensagemCtr.gerarTela();

    }

    @FXML
    public void btnOnActionCancelar(ActionEvent event) throws IOException {

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void btnOnActionAnterior(ActionEvent event) throws IOException {

        JOptionPane.showMessageDialog(null, "Segue a informação do Anuncio Anterior ");

        try {
            conecta.executaSQL("select * from anuncio where codigo=?");
            conecta.resul.previous();

            labelCodigo.setText(String.valueOf(conecta.resul.getInt("codigo")));
            labelDescricao.setText(String.valueOf(conecta.resul.getString("descricao")));
            labelPreco.setText(String.valueOf(conecta.resul.getFloat("preco")));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Erro ao mostrar dados!  " + e);
        }
    }

    @FXML
    public void btnOnActionProximo(ActionEvent event) throws IOException {

        JOptionPane.showMessageDialog(null, "Segue a informação do Anuncio Posterior ");

        try {
             conecta.executaSQL("select * from anuncio");   
            conecta.resul.next();

            labelCodigo.setText(String.valueOf(conecta.resul.getInt("codigo")));
            labelDescricao.setText(String.valueOf(conecta.resul.getString("descricao")));
            labelPreco.setText(String.valueOf(conecta.resul.getFloat("preco")));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Erro ao mostrar dados!  " + e);
        }
    }

    @FXML
    public void btnOnActionPesquisar() throws IOException {

        JOptionPane.showMessageDialog(null, "Qual dado voce deseja consultar ? ");

    }

//    private void carregarBotoesProximo() {
//        btnProximo.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                JOptionPane.showMessageDialog(null, "Segue a informação do Anuncio Posterior ");
//
//                try {
//                    conecta.executaSQL("select * from anuncio");
//                    conecta.resul.next();
//
//                    labelCodigo.setText(String.valueOf(conecta.resul.getInt("codigo")));
//                    labelDescricao.setText(String.valueOf(conecta.resul.getString("descricao")));
//                    labelPreco.setText(String.valueOf(conecta.resul.getFloat("preco")));
//                     
//                } catch (SQLException e) {
//                    JOptionPane.showMessageDialog(null, " Erro ao mostrar dados!  " + e);
//                }
//            }
//
//        });
//        btnAnterior.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                JOptionPane.showMessageDialog(null, "Segue a informação do Anuncio Anterior ");
//
//        try {
//            conecta.executaSQL("select * from anuncio");
//            conecta.resul.previous();
//
//            labelCodigo.setText(String.valueOf(conecta.resul.getInt("codigo")));
//            labelDescricao.setText(String.valueOf(conecta.resul.getString("descricao")));
//            labelPreco.setText(String.valueOf(conecta.resul.getFloat("preco")));
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, " Erro ao mostrar dados!  " + e);
//        }
//
//            }
//        });
//    }
    
}
