package model;

public class CategoriaReporte {
        @CampoSQL(nomeColuna = "ID")
        public int id_categoria_reporte;
        @CampoSQL(nomeColuna = "Descrição")
        public String descricao;
}
