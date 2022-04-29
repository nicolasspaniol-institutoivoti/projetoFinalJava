package dao;

import java.sql.*;

public class DataSource {
    private Connection connection;

    public DataSource() {
        try{
            String endereco = "192.168.20.3";
            int porta = 3306;
            String banco = "ecommerce_nicolasspaniol";
            String usuario = "root";
            String senha = "12345";

            String url = String.format(
                    "jdbc:mysql://%s:%d/%s?useTimezone=true&serverTimezone=UTC",
                    endereco,
                    porta,
                    banco
            );

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

    public void closeDataSource() {
        try{
            connection.close();
        }
        catch (Exception ex) {
            System.err.println("Erro ao desconectar: " + ex.getMessage());
        }
    }

    public ResultSet get(String SQL, String... valores) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);

            for (int i = 0; i < valores.length; i++) {
                ps.setString(i + 1, valores[i]);
            }

            ResultSet rs = ps.executeQuery();
            ps.close();
            return rs;
        }
        catch (SQLException ex){
            System.err.println("Erro ao recuperar dados: " + ex.getMessage());
            return null;
        }
    }
    public void set(String SQL, String... valores) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);

            for (int i = 0; i < valores.length; i++) {
                ps.setString(i + 1, valores[i]);
            }

            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex){
            System.err.println("Erro ao recuperar dados: " + ex.getMessage());
        }
    }
}
