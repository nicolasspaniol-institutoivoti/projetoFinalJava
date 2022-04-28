package model;

public record Reporte(
        int idReporte,
        String comentario,

        // FK
        int idCategoriaReporte,
        int idPeca
) {}
