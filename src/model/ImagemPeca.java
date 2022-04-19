package model;

public class ImagemPeca {
    int idImagemPeca;
    String caminho;

    // FK
    int idPeca;

    public int getIdImagemPeca() {
        return idImagemPeca;
    }

    public void setIdImagemPeca(int idImagemPeca) {
        this.idImagemPeca = idImagemPeca;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }
}
