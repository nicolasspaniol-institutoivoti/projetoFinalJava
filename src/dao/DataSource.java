package dao;

import java.sql.*;

public class DataSource {
    private Connection conexao;
    static boolean registrado = false;

    private static final Conexao conexaoLocal = new Conexao("localhost", 3306, "projeto_ms", "root", "projeto_MS_2022");
    private static final Conexao conexaoEscola = new Conexao("192.168.20.3", 3306, "projeto_ms", "root", "12345");

    public DataSource() {
        Conexao fonteConexao = conexaoEscola;

        try{
            if (!registrado) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                registrado = true;
            }
            conexao = DriverManager.getConnection(fonteConexao.url(), fonteConexao.usuario(), fonteConexao.senha());
            System.out.println("Conectado");
        }
        catch (SQLException ex) {
            System.err.println("Erro na conexão: " + ex.getMessage());
        }
    }

    public void fecharConexao() {
        try {
            System.out.println("Desconectado");
            conexao.close();
        } catch (SQLException ignored) {}
    }

    public PreparedStatement preparar(String SQL, String... valores) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(SQL);

        for (int i = 0; i < valores.length; i++) {
            ps.setString(i + 1, valores[i]);
        }

        return ps;
    }
}
