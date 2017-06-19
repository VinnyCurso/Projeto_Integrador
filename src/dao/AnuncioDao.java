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
        String sql = "INSERT INTO anuncio(descricao,categoria,preco,titulo) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, anuncio.getDescricao());
            stmt.setString(2, anuncio.getCategoria());
            stmt.setDouble(3, anuncio.getValor());
            stmt.setString(4, anuncio.getTitulo());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Anuncio anuncio) {
        String sql = "UPDATE anuncio SET descricao=?,categoria=?,preco=?,titulo=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, anuncio.getDescricao());
            stmt.setString(2, anuncio.getCategoria());
            stmt.setDouble(3, anuncio.getValor());
            stmt.setString(4, anuncio.getTitulo());
            stmt.setLong(5,anuncio.getCodigo());
            

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

    public List<Anuncio> listar(String nome) throws SQLException {
        List<Anuncio> retorno = new ArrayList<>();
        
        String sql = "select * from anuncio"
                   + " WHERE 1=1";
        
        if (nome != null) {
            if (!nome.isEmpty()) {
                sql += " and titulo like ? ";
            }
        }

        sql += "ORDER BY titulo";
        
        PreparedStatement prd = connection.prepareStatement(sql);
        
        if (nome != null) {
            if (!nome.isEmpty()) {
                prd.setString(1, "%" + nome + "%");
            }
        }

        ResultSet rs = prd.executeQuery();


        try {
            ResultSet resultado = prd.executeQuery();
            while (resultado.next()) {
                Anuncio anuncio = new Anuncio();
                anuncio.setCodigo(resultado.getInt("codigo"));
                anuncio.setDescricao(resultado.getString("descricao"));
                anuncio.setCategoria(resultado.getString("categoria"));
                anuncio.setValor(resultado.getFloat("preco"));
                anuncio.setTitulo(resultado.getString("titulo"));

                retorno.add(anuncio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnuncioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Anuncio buscar(int parametro) throws SQLException {
        String sql = "SELECT * FROM anuncio WHERE codigo=?";
        
            PreparedStatement prd = connection.prepareStatement(sql);

            prd.setInt(1, parametro);
            ResultSet rs = prd.executeQuery();
            
            Anuncio retorno = new Anuncio();
            if (rs.next()) {
                retorno.setCodigo(rs.getInt("codigo"));
                retorno.setDescricao(rs.getString("descricao"));
                retorno.setCategoria(rs.getString("categoria"));
                retorno.setValor(rs.getFloat("preco"));
                retorno.setTitulo(rs.getString("titulo"));
            }      
        return retorno;
    }
    
}
