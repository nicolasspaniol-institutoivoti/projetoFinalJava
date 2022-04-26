package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataAccessObject<T> {
    public T fromResultSet(ResultSet rs) throws SQLException;
    public ArrayList<T> lerTudo() throws SQLException;
    public T ler(String termo) throws SQLException;
    public void inserir(T obj);
    public void alterar(T obj);
    public void deletar(int id);
}
