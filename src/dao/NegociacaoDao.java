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
import model.Negociacao;

/**
 *
 * @author vinicius caetano
 */
public class NegociacaoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Negociacao negociacao) {
        String sql = "INSERT INTO negociacao(tiponegociacao,formanegociacao,descricao,mensagem) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, negociacao.getTipoNegociacao());
            stmt.setString(2, negociacao.getFormaNegociacao());
            stmt.setString(3, negociacao.getDescricao());
            stmt.setObject(4, negociacao.getMensagem());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Negociacao negociacao) {
        String sql = "UPDATE negociacao SET tiponegociacao=?,formanegociacao=?,descricao=?,mensagem=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, negociacao.getTipoNegociacao());
            stmt.setString(2, negociacao.getFormaNegociacao());
            stmt.setString(3, negociacao.getDescricao());
            stmt.setObject(4, negociacao.getMensagem());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Negociacao negociacao) {
        String sql = "DELETE FROM negociacao WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) negociacao.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Negociacao> listar() {
        String sql = "select * from negociacao";
        List<Negociacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Negociacao negociacao = new Negociacao();
                negociacao.setCodigo(resultado.getInt("codigo"));
                negociacao.setTipoNegociacao(resultado.getString("tiponegociacao"));
                negociacao.setFormaNegociacao(resultado.getString("formanegociacao"));
                negociacao.setDescricao(resultado.getString("descricao"));
                negociacao.setMensagem((Mensagem) resultado.getObject("mensagem"));

                retorno.add(negociacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Negociacao buscar(Negociacao negociacao) {
        String sql = "SELECT * FROM negociacao WHERE codigo=?";
        Negociacao retorno = new Negociacao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) negociacao.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                negociacao.setTipoNegociacao(resultado.getString("tiponegociacao"));
                negociacao.setFormaNegociacao(resultado.getString("formanegociacao"));
                negociacao.setDescricao(resultado.getString("descricao"));
                negociacao.setMensagem((Mensagem) resultado.getObject("mensagem"));

                retorno = negociacao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegociacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
