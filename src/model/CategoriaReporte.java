package model;

public record CategoriaReporte(
        @CampoSQL(nomeColuna = "ID")
        int id_categoria_reporte,
        @CampoSQL(nomeColuna = "Descrição")
        String descricao
) {}
