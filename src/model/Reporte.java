package model;

public class Reporte {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_reporte;
    @CampoSQL(nomeColuna = "Comentário")
    public String comentario = "";

    // FK
    @CampoSQL(nomeColuna = "ID Categoria reporte (FK)", larguraColuna = 150)
    public int id_categoria_reporte;
    @CampoSQL(nomeColuna = "ID Peça (FK)", larguraColuna = 100)
    public int id_peca;
}