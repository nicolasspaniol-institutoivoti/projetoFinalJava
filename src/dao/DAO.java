package dao;

import model.CampoSQL;

import java.lang.reflect.Field;
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
    public abstract void inserir(T obj) throws SQLException;
    public abstract void alterar(Object obj) throws SQLException;
    public abstract void deletar(Object obj) throws SQLException;

    public abstract Class<?> tipoRegistro();

    private void identificarColunas() {
        Field[] campos = tipoRegistro().getDeclaredFields();
        colunas = new LinkedHashMap<>();

        for (Field campo : campos) {
            CampoSQL an = campo.getAnnotation(CampoSQL.class);
            if (an != null) {
                String nome = an.nomeColuna();
                int largura = an.larguraColuna();

                if (Objects.equals(nome, "")) {
                    nome = campo.getName();
                    nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);
                }
                colunas.put(nome, largura);
            }
        }
    }

    public final String[] nomesColunas() {
        return colunas.keySet().toArray(new String[0]);
    }
    public final Integer[] largurasColunas() {
        return colunas.values().toArray(new Integer[0]);
    }
}