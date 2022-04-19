package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private Connection connection;

    public DataSource() {
        try{
            String hostname = "192.168.20.3";
            int porta = 3306;
            String banco = "ecommerce_nicolasspaniol";
            String usuario = "root";
            String senha = "12345";

            String url = "jdbc:mysql://" + hostname + ":" + porta + "/" + banco + "?useTimezone=true&serverTimezone=UTC";

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Conectado");
        }
        catch (SQLException ex) {
            System.err.println("Erro na conexão: " + ex.getMessage());
        }
        catch (Exception ex) {
            System.err.println("Erro geral: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeDataSource() {
        try{
            connection.close();
        }
        catch (Exception ex) {
            System.err.println("Erro ao desconectar: " + ex.getMessage());
        }
    }
}
