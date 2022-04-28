package model;

public record ImagemPontoColeta(
        int idImagemPontoColeta,
        String caminho,

        // FK
        int idPontoColeta
) {}
