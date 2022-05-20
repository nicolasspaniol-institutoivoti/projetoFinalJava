package model;

public class Peca {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_peca;
    @CampoSQL(larguraColuna = 50)
    public int tamanho;
    @CampoSQL(larguraColuna = 50)
    public String cor;
    @CampoSQL(nomeColuna = "Descrição")
    public String descricao;
    @CampoSQL(nomeColuna = "Preço", larguraColuna = 100)
    public int preco; // Em centavos

    // FK
    @CampoSQL(nomeColuna = "ID Reserva (FK)", larguraColuna = 100)
    public int id_reserva;
    @CampoSQL(nomeColuna = "ID Categoria peça (FK)", larguraColuna = 100)
    public int id_categoria_peca;
    @CampoSQL(nomeColuna = "ID Ponto coleta (FK)", larguraColuna = 100)
    public int id_ponto_coleta;
}
