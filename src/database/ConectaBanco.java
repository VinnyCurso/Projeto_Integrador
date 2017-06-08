/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vinicius caetano
 */
public class ConectaBanco {

    public Statement stat; // responsavel por preparar e realizar pesquisas no banco de dados 
    public ResultSet resul; //responsavel por armazenar o resultado de uma pesquisa pasada pelo o Statement
    public Connection conection; //responsavel por realizar a conexão com o banco de dados

    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/alugacar";
    private String usuario = "postgres";
    private String senha = "root";

    public void conexao() {

        try {

            System.setProperty("jdbc.Drivers", driver);
            conection = DriverManager.getConnection(caminho, usuario, senha);

            JOptionPane.showMessageDialog(null, "Conectado com sucesso! ");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro de conexão \n" + ex.getMessage());
        }
    }
    
    public void executaSQL(String sql){
     
        try {
            
            stat = conection.createStatement(resul.TYPE_SCROLL_INSENSITIVE,resul.CONCUR_READ_ONLY);
            resul = stat.executeQuery(sql);
            
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }
        
    }
    public void dseconecta(){
        
        try {
            conection.close();
            
            JOptionPane.showMessageDialog(null, "Desconectado  com sucesso! ");
            
        } catch (SQLException ex) {
            
          JOptionPane.showMessageDialog(null, "Erro ao desconectar a conexão atual  \n" + ex.getMessage());
        }
    }
}
