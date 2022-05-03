package model;

public record ImagemPeca(
        int id_imagem_peca,
        String caminho,

        // FK
        int id_peca
) {}
