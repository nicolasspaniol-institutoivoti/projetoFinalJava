package dao;

public record Conexao(
        String endereco,
        int porta,
        String banco,
        String usuario,
        String senha
) {
    static String urlBase = "jdbc:mysql://%s:%d/%s?useTimezone=true&serverTimezone=UTC";

    String url() {
        return urlBase.formatted(endereco, porta, banco);
    }
}
