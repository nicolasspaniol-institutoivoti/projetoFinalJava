package model;

public class Peca {
    int idPeca;
    String tamanho;
    String cor;
    String descricao;
    int preco; // Em centavos

    // FK
    int idReserva;
    int idCategoriaPeca;
    int idPontoColeta;

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCategoriaPeca() {
        return idCategoriaPeca;
    }

    public void setIdCategoriaPeca(int idCategoriaPeca) {
        this.idCategoriaPeca = idCategoriaPeca;
    }

    public int getIdPontoColeta() {
        return idPontoColeta;
    }

    public void setIdPontoColeta(int idPontoColeta) {
        this.idPontoColeta = idPontoColeta;
    }
}
