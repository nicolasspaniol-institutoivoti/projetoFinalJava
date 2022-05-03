package model;

public record Peca(
        int id_peca,
        int tamanho,
        String cor,
        String descricao,
        int preco, // Em centavos

        // FK
        int id_reserva,
        int id_categoria_peca,
        int id_ponto_coleta
) {}
