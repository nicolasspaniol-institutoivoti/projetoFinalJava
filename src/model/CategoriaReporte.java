package model;

public record CategoriaReporte(
        @CampoSQL(nomeColuna = "ID")
        int idCategoriaReporte,
        @CampoSQL(nomeColuna = "Descrição")
        String descricao
) {}
