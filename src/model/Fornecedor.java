package model;

import util.TipoFornecedor;

public record Fornecedor(
        int idFornecedor,
        TipoFornecedor tipo,
        boolean ativo,

        // Dados
        String nome,
        String telefone,
        String email,
        String senha,
        String cnp,
        String logo,

        // Local
        short numero,
        String rua,
        String bairro,
        int cep,
        String complemento,
        String coordenadas
) {}
