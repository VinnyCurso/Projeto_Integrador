/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Anuncio;

/**
 *
 * @author vinicius caetano
 */
public class AnuncioDao {
    
     private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public ResultSet resultado;

    public boolean inserir(Anuncio anuncio) {
        String sql = "INSERT INTO anuncio(descricao,categoria,preco) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, anuncio.getDescricao());
            stmt.setString(2, anuncio.getCategoria());
            stmt.setFloat(3, anuncio.getValor());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Anuncio anuncio) {
        String sql = "UPDATE anuncio SET descricao=?,categoria=?,preco=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, anuncio.getDescricao());
            stmt.setString(2, anuncio.getCategoria());
            stmt.setFloat(3, anuncio.getValor());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Anuncio anuncio) {
        String sql = "DELETE FROM anuncio WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) anuncio.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Anuncio> listar() {
        String sql = "select * from anuncio";
        List<Anuncio> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Anuncio anuncio = new Anuncio();
                anuncio.setCodigo(resultado.getInt("codigo"));
                anuncio.setDescricao(resultado.getString("descricao"));
                anuncio.setCategoria(resultado.getString("categoria"));
                anuncio.setValor(resultado.getFloat("preco"));

                retorno.add(anuncio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Anuncio buscar(Anuncio anuncio) {
        String sql = "SELECT * FROM anuncio WHERE codigo=?";
        Anuncio retorno = new Anuncio();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) anuncio.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                anuncio.setCodigo(resultado.getInt("codigo"));
                anuncio.setDescricao(resultado.getString("descricao"));
                anuncio.setCategoria(resultado.getString("categoria"));
                anuncio.setValor(resultado.getFloat("valor"));
                

                retorno = anuncio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
