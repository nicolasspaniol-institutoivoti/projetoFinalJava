package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccessObject<T> {
    T fromResultSet(ResultSet rs) throws SQLException;
    ArrayList<T> lerTudo() throws SQLException;
    T ler(String termo) throws SQLException;
    void inserir(T obj);
    void alterar(T obj);
    void deletar(int id);

    String[] nomeCampos();
}