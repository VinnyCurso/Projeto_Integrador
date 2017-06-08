/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import model.Venda;

/**
 *
 * @author vinicius caetano
 */
public class VendaDao {
    
     private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Venda venda) {
        String sql = "INSERT INTO venda(datahora,valortotal) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setDate(1, venda.getDataHora());
            stmt.setFloat(2, venda.getValorTotal());


            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Venda venda) {
        String sql = "UPDATE venda SET datahora=?,valortotal=? WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setDate(1, venda.getDataHora());
            stmt.setFloat(2, venda.getValorTotal());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Venda venda) {
        String sql = "DELETE FROM venda WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, (int) venda.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Venda> listar() {
        String sql = "select * from venda";
        List<Venda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Venda venda = new Venda();
                venda.setCodigo(resultado.getInt("codigo"));
//                venda.setDataHora(resultado.getDate("datahora"));
                venda.setValorTotal(resultado.getFloat("valortotal"));
              
                retorno.add(venda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Venda buscar(Venda venda) {
        String sql = "SELECT * FROM venda WHERE codigo=?";
        Venda retorno = new Venda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, (int) venda.getCodigo());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

    venda.setCodigo(resultado.getInt("codigo"));
//                venda.setDataHora(resultado.getDate("datahora"));
                venda.setValorTotal(resultado.getFloat("valortotal"));

                retorno = venda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
