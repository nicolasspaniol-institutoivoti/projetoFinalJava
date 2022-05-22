package dao;

import model.CampoSQL;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public abstract class DAO<T> {
    public DataSource dataSource;
    LinkedHashMap<String, Integer> colunas;

     public DAO(DataSource ds) {
        this.dataSource = ds;
        identificarColunas();
    }

    public abstract ArrayList<T> lerTudo() throws SQLException;
    public abstract Object inserir() throws SQLException;
    public abstract void alterar(Object obj) throws SQLException;
    public abstract void deletar(Object obj) throws SQLException;

    public abstract Class<?> tipoRegistro();

    private void identificarColunas() {
        // Identifica os campos dos registros dessa tabela
        Field[] campos = tipoRegistro().getDeclaredFields();
        // Cria uma mapa para armazenar os nomes e tamanhos das colunas
        colunas = new LinkedHashMap<>();

        // Analiza cada campo da classe
        for (Field campo : campos) {
            // Verifica se o campo possui a anotação CampoSQL
            CampoSQL an = campo.getAnnotation(CampoSQL.class);
            if (an != null) {
                // Armazena os valores de largura e nome da coluna na anotação
                String nome = an.nomeColuna();
                int largura = an.larguraColuna();

                // Se o nome da coluna não está anotado, usa o nome do próprio campo (com a primeira letra maiúscula)
                if (Objects.equals(nome, "")) {
                    nome = campo.getName();
                    nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);
                }

                // Armazena esses valores no mapa
                colunas.put(nome, largura);
            }
        }
    }

    protected int lerPrimeiroId(String tabela) throws SQLException {
        String campoId = "id_" + tabela;

        try (
                var ps = dataSource.preparar(String.format("select %s from %s limit 1", campoId, tabela));
                ResultSet rs = ps.executeQuery()
        ) {
            rs.next();
            return rs.getInt(campoId);
        }
    }

    public final String[] nomesColunas() {
        return colunas.keySet().toArray(new String[0]);
    }
    public final Integer[] largurasColunas() {
        return colunas.values().toArray(new Integer[0]);
    }
}