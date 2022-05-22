package model;

import util.Estado;

public class Municipio {
        @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
        public int id_municipio;
        @CampoSQL
        public String nome = "";
        @CampoSQL(larguraColuna = 50)
        public Estado estado = Estado.RS;
}