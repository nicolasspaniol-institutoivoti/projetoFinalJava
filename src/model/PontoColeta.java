package model;

public record PontoColeta(
        int id_ponto_coleta,
        String horario,

        // Local
        short numero,
        String rua,
        String bairro,
        int cep,
        String complemento,
        String coordenadas,

        //FK
        int id_municipio,
        int id_fornecedor
) {}
