package model;

import util.Estado;

public record Municipio (
        @CampoSQL(nomeColuna = "ID")
        int id_municipio,
        String nome,
        Estado estado
) {}
