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
import model.Usuario;
import controller.UsuarioCtr;

/**
 *
 * @author vinicius caetano
 */
public class UsuarioDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome,sobrenome,telefone,email,senha) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?,sobrenome=?,telefone=?,email=?,senha=?  WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setLong(6, usuario.getCodigo());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) usuario.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Usuario> listar(String nome) throws SQLException {
        List<Usuario> retorno = new ArrayList<>();

        String sql = "select * from usuario where 1=1";

        if (nome != null) {
            if (!nome.isEmpty()) {
                sql += " and nome like ? ";
            }
        }

        sql += "ORDER BY nome";

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
                Usuario usuario = new Usuario();
                usuario.setCodigo(resultado.getInt("codigo"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSobrenome(resultado.getString("sobrenome"));
                usuario.setTelefone(resultado.getString("telefone"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setSenha(resultado.getString("senha"));

                retorno.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Usuario buscar(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE codigo=?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) usuario.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                usuario.setCodigo(resultado.getInt("codigo"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSobrenome(resultado.getString("sobrenome"));
                usuario.setTelefone(resultado.getString("telefone"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setSenha(resultado.getString("senha"));

                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
