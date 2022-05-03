package model;

public record Reporte(
        int id_reporte,
        String comentario,

        // FK
        int id_categoria_reporte,
        int id_peca
) {}
