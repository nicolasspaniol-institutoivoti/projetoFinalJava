package model;

public class Peca {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_peca;
    @CampoSQL(larguraColuna = 75)
    public String tamanho;
    @CampoSQL(larguraColuna = 75)
    public String cor = "";
    @CampoSQL(nomeColuna = "Descrição")
    public String descricao = "";
    @CampoSQL(nomeColuna = "Título")
    public String titulo = "";
    @CampoSQL(nomeColuna = "Preço (centavos)", larguraColuna = 100)
    public int preco;

    // FK
    @CampoSQL(nomeColuna = "ID Reserva (FK)", larguraColuna = 100)
    public int id_reserva;
    @CampoSQL(nomeColuna = "ID Categoria peça (FK)", larguraColuna = 150)
    public int id_categoria_peca;
    @CampoSQL(nomeColuna = "ID Ponto coleta (FK)", larguraColuna = 130)
    public int id_ponto_coleta;
}
