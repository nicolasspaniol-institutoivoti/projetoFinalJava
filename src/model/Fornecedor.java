package model;

import util.TipoFornecedor;

public class Fornecedor {
        @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
        public int id_fornecedor;
        @CampoSQL
        public TipoFornecedor tipo = TipoFornecedor.Instituição;
        @CampoSQL
        public boolean ativo = false;

        // Dados
        @CampoSQL
        public String nome = "";
        @CampoSQL(larguraColuna = 80)
        public String telefone = "00000000000";
        @CampoSQL
        public String email = "";
        @CampoSQL(larguraColuna = 100)
        public String senha = "";
        @CampoSQL(larguraColuna = 100)
        public String cnp = "";
        @CampoSQL(nomeColuna = "Logo (caminho)")
        public String logo = "";

        // Local
        @CampoSQL(larguraColuna = 50)
        public int numero = 0;
        @CampoSQL
        public String rua = "";
        @CampoSQL
        public String bairro = "";
        @CampoSQL(larguraColuna = 80)
        public int cep = 0;
        @CampoSQL(larguraColuna = 50)
        public String complemento = "casa";
        @CampoSQL(larguraColuna = 150)
        public String coordenadas = "";

        //FK
        @CampoSQL(nomeColuna = "ID Município (FK)", larguraColuna = 100)
        public int id_municipio = 1;
}