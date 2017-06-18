/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Imagem;

/**
 *
 * @author vinicius caetano
 */
public class ImagemDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Imagem imagem) throws SQLException {
       Boolean retorno = false;
        String sql = "INSERT INTO imagem (imagem) values (?)";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        try
        {
            stmt.setBytes(1, imagem.getImagem());
            stmt.executeUpdate();
            retorno = true;
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return retorno;
    }

    public boolean alterar(Imagem imagem) {
        String sql = "UPDATE imagem SET imagem=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setBlob(1, (Blob) imagem.getImagem());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Imagem imagem) {
        String sql = "DELETE FROM imagem WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) imagem.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Imagem> listar() {
        String sql = "select * from imagem";
        List<Imagem> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Imagem imagem = new Imagem();
                imagem.setCodigo(resultado.getInt("codigo"));
//                imagem.setImagem((FileReader) resultado.getBlob("imagem"));

                retorno.add(imagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Imagem buscar(Imagem imagem) {
        String sql = "SELECT * FROM imagem WHERE codigo=?";
        Imagem retorno = new Imagem();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) imagem.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                imagem.setCodigo(resultado.getInt("codigo"));
//               imagem.setImagem((FileReader) resultado.getBlob("imagem"));

                retorno = imagem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
