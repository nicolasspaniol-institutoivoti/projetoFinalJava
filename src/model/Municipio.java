package model;

import util.Estado;

public record Municipio(
        int idMunicipio,
        String nome,
        Estado estado
) {}
