package dao;

import model.CategoriaReporte;
import util.DataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCategoriaReporte implements DataAccessObject<CategoriaReporte> {
    private DataSource dataSource;

    public DAOCategoriaReporte(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CategoriaReporte fromResultSet(ResultSet rs) throws SQLException {
        CategoriaReporte cr = new CategoriaReporte();

        cr.setIdCategoriaReporte(rs.getInt("id_categoria_reporte"));
        cr.setDescricao(rs.getString("descricao"));

        return cr;
    }
    public ArrayList<CategoriaReporte> lerTudo() throws SQLException {
        ResultSet rs = dataSource.get("select * from categoria_reporte");
        ArrayList<CategoriaReporte> lista = new ArrayList<>();
        while (rs.next()) lista.add(fromResultSet(rs));
        dataSource.closeDataSource();
        return lista;
    }
    public CategoriaReporte ler(String termo) throws SQLException {
        ResultSet rs = dataSource.get("select * from categoria_reporte where (descricao like %?%) limit 1", termo);
        dataSource.closeDataSource();
        return fromResultSet(rs);
    }
    public void inserir(CategoriaReporte cr) {
        dataSource.set("insert into categoria_reporte (descricao) values (?)", cr.getDescricao());
        dataSource.closeDataSource();
    }
    public void alterar(CategoriaReporte cr) {
        dataSource.set("update categoria_reporte set descricao=? where id_categoria_reporte=?", cr.getDescricao(), String.valueOf(cr.getIdCategoriaReporte()));
        dataSource.closeDataSource();
    }
    public void deletar(int codigo) {
        dataSource.set("delete from categoria_reporte where (id_municipio = ?)", String.valueOf(codigo));
        dataSource.closeDataSource();
    }
}