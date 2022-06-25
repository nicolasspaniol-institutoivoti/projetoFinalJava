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
        public String imagem = "";
}