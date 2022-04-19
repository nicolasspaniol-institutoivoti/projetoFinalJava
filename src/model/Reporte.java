package model;

public class Reporte {
    int idReporte;
    String comentario;

    // FK
    int idCategoriaReporte;
    int idPeca;

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdCategoriaReporte() {
        return idCategoriaReporte;
    }

    public void setIdCategoriaReporte(int idCategoriaReporte) {
        this.idCategoriaReporte = idCategoriaReporte;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }
}
