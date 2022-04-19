package model;

public class ImagemPontoColeta {
    int idImagemPontoColeta;
    String caminho;

    // FK
    int idPontoColeta;

    public int getIdImagemPontoColeta() {
        return idImagemPontoColeta;
    }

    public void setIdImagemPontoColeta(int idImagemPontoColeta) {
        this.idImagemPontoColeta = idImagemPontoColeta;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getIdPontoColeta() {
        return idPontoColeta;
    }

    public void setIdPontoColeta(int idPontoColeta) {
        this.idPontoColeta = idPontoColeta;
    }
}
