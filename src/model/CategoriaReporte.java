package model;

public class CategoriaReporte {
        @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
        public int id_categoria_reporte;
        @CampoSQL(nomeColuna = "Descrição")
        public String descricao = "";
}
