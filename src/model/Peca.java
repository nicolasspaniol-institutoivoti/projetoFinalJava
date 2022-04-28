package model;

public record Peca(
        int idPeca,
        int tamanho,
        String cor,
        String descricao,
        int preco, // Em centavos

        // FK
        int idReserva,
        int idCategoriaPeca,
        int idPontoColeta
) {}
