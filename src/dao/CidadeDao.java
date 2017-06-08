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
import model.Cidade;
import model.Estado;

/**
 *
 * @author vinicius caetano
 */
public class CidadeDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cidade cidade) {
        String sql = "INSERT INTO cidade(cidade,estado) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cidade.getCidade());
            stmt.setObject(2, cidade.getEstado());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cidade cidade) {
        String sql = "UPDATE cidade SET cidade=?,estado=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cidade.getCidade());
            stmt.setObject(2, cidade.getEstado());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cidade cidade) {
        String sql = "DELETE FROM cidade WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) cidade.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cidade> listar() {
        String sql = "select * from cidade";
        List<Cidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setCidade(resultado.getString("cidade"));
                cidade.setEstado((Estado) resultado.getObject("estado"));

                retorno.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cidade buscar(Cidade cidade) {
        String sql = "SELECT * FROM cidade WHERE codigo=?";
        Cidade retorno = new Cidade();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) cidade.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                cidade.setCodigo(resultado.getInt("codigo"));
                cidade.setCidade(resultado.getString("cidade"));
                cidade.setEstado((Estado) resultado.getObject("estado"));

                retorno = cidade;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
