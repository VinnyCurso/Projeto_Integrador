package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class DatabasePostgreSQL implements Database {
    private Connection connection;

    @Override
    public Connection conectar() {
   
       try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/sysiguanys", "postgres", "root");
        } catch (ClassNotFoundException e) {
            System.out.println("A aplicação não contém o driver para o banco.");
            return null;
        } catch (SQLException e) {
            System.out.println("Erro na conexao com o banco. Verifique a url, o usuário e a senha");
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
