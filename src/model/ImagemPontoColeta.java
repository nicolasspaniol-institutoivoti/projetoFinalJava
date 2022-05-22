package model;

public class ImagemPontoColeta {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_imagem_ponto_coleta;
    @CampoSQL
    public String caminho = "";

    // FK
    @CampoSQL(nomeColuna = "ID Ponto coleta (FK)", larguraColuna = 150)
    public int id_ponto_coleta;
}