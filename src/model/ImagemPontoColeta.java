package model;

public record ImagemPontoColeta(
        int id_imagem_ponto_coleta,
        String caminho,

        // FK
        int id_ponto_coleta
) {}
