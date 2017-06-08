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
import model.Compra;

/**
 *
 * @author vinicius caetano
 */
public class CompraDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Compra compra) {
        String sql = "INSERT INTO compra(datahora,valortotal) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setDate(1, compra.getDataHora());
            stmt.setFloat(2, compra.getValorTotal());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Compra compra) {
        String sql = "UPDATE compra SET datahora=?,valortotal=? WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setDate(1, compra.getDataHora());
            stmt.setFloat(2, compra.getValorTotal());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Compra compra) {
        String sql = "DELETE FROM compra WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) compra.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Compra> listar() {
        String sql = "select * from compra";
        List<Compra> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Compra compra = new Compra();
                compra.setCodigo(resultado.getInt("codigo"));
//                compra.setDataHora(resultado.getDate("datahora"));
                compra.setValorTotal(resultado.getFloat("valortotal"));

                retorno.add(compra);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Compra buscar(Compra compra) {
        String sql = "SELECT * FROM compra WHERE codigo=?";
        Compra retorno = new Compra();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) compra.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                compra.setCodigo(resultado.getInt("codigo"));
//                compra.setDataHora(resultado.getDate("datahora"));
                compra.setValorTotal(resultado.getFloat("valortotal"));

                retorno = compra;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
