package dao;

import java.sql.*;

public class DataSource {
    private Connection conexao;
    static boolean registrado = false;

    private final String urlBase = "jdbc:mysql://%s:3306/projeto_ms?useTimezone=true&serverTimezone=UTC";
    private final String[] conexaoLocal = new String[] {
            "localhost", // endereco
            "root", // usuario
            "projeto_MS_2022" // senha
    };
    private final String[] conexaoEscola = new String[] {
            "192.168.20.3", // endereco
            "root", // usuario
            "12345" // senha
    };

    public DataSource() {
        try{
            if (!registrado) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                registrado = true;
            }
            conexao = DriverManager.getConnection(urlBase.formatted(conexaoLocal[0]), conexaoLocal[1], conexaoLocal[2]);
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
