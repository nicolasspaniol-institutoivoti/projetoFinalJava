package model;

import util.Estado;

public record Municipio (
        @CampoSQL(nomeColuna = "ID")
        int idMunicipio,
        @CampoSQL
        String nome,
        @CampoSQL
        Estado estado
) {}
