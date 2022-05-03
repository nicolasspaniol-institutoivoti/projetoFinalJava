package model;

import util.Estado;

public record Municipio (
        @CampoSQL(nomeColuna = "ID")
        int idMunicipio,
        String nome,
        Estado estado
) {}
