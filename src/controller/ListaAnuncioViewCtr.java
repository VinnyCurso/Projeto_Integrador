/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.AnuncioDao;
import dao.UsuarioDao;
import database.ConectaBanco;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Anuncio;

/**
 * FXML Controller class
 *
 * @author vinicius caetano
 */
public class ListaAnuncioViewCtr implements Initializable {
    
    
    @FXML
    private JFXComboBox<String> comboFiltroAnuncio;

    @FXML
    private JFXTextField txtProcuraAnuncio;

    @FXML
    private JFXButton btnPesquisar;

    @FXML
    private TableView<Anuncio> tableView;

    @FXML
    private TableColumn<Anuncio, String> colunacodigo;

    @FXML
    private TableColumn<Anuncio, String> colunaDescricao;

    @FXML
    private TableColumn<Anuncio, String> colunacategoria;

    @FXML
    private TableColumn<Anuncio, String> colunapreco;
    
    @FXML
    private ObservableList<String> ListaobservavelFiltro;
    
    private Anuncio anuncio;
    
    private Stage stage;
    
     //tabela
    
    private List<Anuncio> ListaAnuncio;
    private ObservableList<Anuncio> ObservableListaAnuncio;

    ConectaBanco conecta = new ConectaBanco();
    
     //Atritutos para manipulação banco de dados
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AnuncioDao anuncioDao = new AnuncioDao();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        conecta.conexao();
        anuncioDao.setConnection(connection);
        anuncio = new Anuncio();
        CarregarComboFiltro();
        CarregarTabelaAnuncio();
    } 
    
    //Evento Chamar a tela 
    
        public void gerarTela() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ListaAnuncioView.fxml"));

        Stage dialogStage = new Stage();
        Scene scene = new Scene(root);

        dialogStage.setTitle("Listas de Anuncios");
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }
        
          public void CarregarComboFiltro(){
        List<String> listaFiltro = new ArrayList();
        String nome[] = {"codigo","descricao","categoria","preco",};
        String[] a = new String[nome.length];       
        for(int i =0; i< nome.length;i++){
           a[i] = new String(nome[i]); 
           listaFiltro.add(a[i]);
        }    
        ListaobservavelFiltro = FXCollections.observableArrayList(listaFiltro);     
        comboFiltroAnuncio.setItems(ListaobservavelFiltro);
    }
          
          public void CarregarTabelaAnuncio(){
               
               colunacodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
               colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
               colunacategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
               colunapreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
               
               ListaAnuncio = anuncioDao.listar();
               
               ObservableListaAnuncio = FXCollections.observableArrayList(ListaAnuncio);
               tableView.setItems(ObservableListaAnuncio);
  
    }
    
    
    
     //Botoes
          

    
     @FXML
    public void btnOnActionPesquisar() throws IOException {

        JOptionPane.showMessageDialog(null, "Qual dado voce deseja consultar ? ");

    }
    
    
    
    
    
}
