package model;

public class ImagemPeca {
    @CampoSQL (nomeColuna = "ID", larguraColuna = 50)
    public int id_imagem_peca;
    @CampoSQL
    public String imagem = "";

    // FK
    @CampoSQL (nomeColuna = "ID Peça (FK)", larguraColuna = 100)
    public int id_peca;
}
