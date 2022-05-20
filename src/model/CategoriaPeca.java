package model;

public class CategoriaPeca {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_categoria_peca;
    @CampoSQL(nomeColuna = "Descrição")
    public String descricao = "";
}
