package model;

import util.Estado;

public class Municipio {
        @CampoSQL(nomeColuna = "ID")
        public int id_municipio;
        public String nome;
        public Estado estado;
}
