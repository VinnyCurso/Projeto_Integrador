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
import model.AvaliacaoVendedor;

/**
 *
 * @author vinicius caetano
 */
public class AvaliacaoVendedorDao {
    
      private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(AvaliacaoVendedor avaliacaoVendedor) {
        String sql = "INSERT INTO avaliacao(anuncio,usuario,nota,sugestao) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, avaliacaoVendedor.getAnuncio());
            stmt.setString(2, avaliacaoVendedor.getUsuario());
            stmt.setInt(3, avaliacaoVendedor.getNota());
            stmt.setString(4, avaliacaoVendedor.getSugestao());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoVendedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(AvaliacaoVendedor avaliacaoVendedor) {
        String sql = "UPDATE avaliacao SET anuncio=?,usuario=?,nota=?,sugestao=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, avaliacaoVendedor.getAnuncio());
                stmt.setString(2, avaliacaoVendedor.getUsuario());
                stmt.setInt(3, avaliacaoVendedor.getNota());
                stmt.setString(4, avaliacaoVendedor.getSugestao());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoVendedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(AvaliacaoVendedor avaliacaoVendedor) {
        String sql = "DELETE FROM avaliacao WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) avaliacaoVendedor.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoVendedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<AvaliacaoVendedor> listar() {
        String sql = "select * from avaliacao";
        List<AvaliacaoVendedor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                AvaliacaoVendedor avaliacaoVendedor = new AvaliacaoVendedor();
                avaliacaoVendedor.setCodigo(resultado.getInt("codigo"));
                avaliacaoVendedor.setAnuncio(resultado.getString("anuncio"));
                avaliacaoVendedor.setUsuario(resultado.getString("usuario"));
                avaliacaoVendedor.setNota(resultado.getInt("nota"));
                avaliacaoVendedor.setSugestao(resultado.getString("sugestao"));
      
                retorno.add(avaliacaoVendedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoVendedorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public AvaliacaoVendedor buscar(AvaliacaoVendedor avaliacaoVendedor) {
        String sql = "SELECT * FROM avaliacao WHERE codigo=?";
        AvaliacaoVendedor retorno = new AvaliacaoVendedor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) avaliacaoVendedor.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                avaliacaoVendedor.setCodigo(resultado.getInt("codigo"));
                avaliacaoVendedor.setAnuncio(resultado.getString("anuncio"));
                avaliacaoVendedor.setUsuario(resultado.getString("usuario"));
                avaliacaoVendedor.setNota(resultado.getInt("nota"));
                avaliacaoVendedor.setSugestao(resultado.getString("sugestao"));

                retorno = avaliacaoVendedor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
