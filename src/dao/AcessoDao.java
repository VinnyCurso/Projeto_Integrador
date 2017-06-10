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
import model.Acesso;

/**
 *
 * @author vinicius caetano
 */
public class AcessoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Acesso acesso) {
        String sql = "INSERT INTO login (usuario,senha) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, acesso.getUsuario());
            stmt.setString(2, acesso.getSenha());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Acesso acesso) {
        String sql = "UPDATE acesso SET usuario=?,senha=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1, acesso.getUsuario());
           stmt.setString(2, acesso.getSenha());
 

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Acesso acesso) {
        String sql = "DELETE FROM login WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) acesso.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Acesso> listar() {
        String sql = "select * from acesso";
        List<Acesso> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Acesso acesso = new Acesso();
                acesso.setCodigo(resultado.getInt("codigo"));
//                acesso.setUsuario(resultado.getObject("usuario"));
                acesso.setSenha(resultado.getString("senha"));

                retorno.add(acesso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Acesso buscar(Acesso acesso) {
        String sql = "SELECT * FROM acesso WHERE codigo=?";
        Acesso retorno = new Acesso();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) acesso.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                acesso.setCodigo(resultado.getInt("codigo"));
//                acesso.setUsuario(resultado.getObject("usuario"));
                acesso.setSenha(resultado.getString("senha"));


                retorno = acesso;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcessoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
