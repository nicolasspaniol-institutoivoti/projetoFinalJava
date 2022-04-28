package model;

public record PontoColeta(
        int idPontoColeta,
        String horario,

        // Local
        short numero,
        String rua,
        String bairro,
        int cep,
        String complemento,
        String coordenadas,

        //FK
        int idMunicipio,
        int idFornecedor
) {}
