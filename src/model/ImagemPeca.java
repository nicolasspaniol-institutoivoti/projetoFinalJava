package model;

public record ImagemPeca(
        int idImagemPeca,
        String caminho,

        // FK
        int idPeca
) {}
