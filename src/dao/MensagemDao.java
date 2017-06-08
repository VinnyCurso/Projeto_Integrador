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
import model.Mensagem;
import model.Usuario;

/**
 *
 * @author vinicius caetano
 */
public class MensagemDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Mensagem mensagem) {
        String sql = "INSERT INTO mensagem(descricao,usuario) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mensagem.getDescricao());
            stmt.setObject(2, mensagem.getUsuario());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Mensagem mensagem) {
        String sql = "UPDATE mensagem SET descricao=?,usuario=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mensagem.getDescricao());
            stmt.setObject(2, mensagem.getUsuario());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Mensagem mensagem) {
        String sql = "DELETE FROM mensagem WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) mensagem.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Mensagem> listar() {
        String sql = "select * from mensagem";
        List<Mensagem> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Mensagem mensagem = new Mensagem();
                mensagem.setCodigo(resultado.getInt("codigo"));
                mensagem.setDescricao(resultado.getString("descricao"));
                mensagem.setUsuario((Usuario) resultado.getObject("usuario"));

                retorno.add(mensagem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Mensagem buscar(Mensagem mensagem) {
        String sql = "SELECT * FROM mensagem WHERE codigo=?";
        Mensagem retorno = new Mensagem();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) mensagem.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                mensagem.setCodigo(resultado.getInt("codigo"));
                mensagem.setDescricao(resultado.getString("descricao"));
                mensagem.setUsuario((Usuario) resultado.getObject("usuario"));

                retorno = mensagem;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MensagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
