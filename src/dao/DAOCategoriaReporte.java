package dao;

import model.CategoriaReporte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCategoriaReporte extends DAO<CategoriaReporte> {
    public DAOCategoriaReporte(DataSource ds) {
        super(ds);
    }

    public CategoriaReporte lerRegistro(ResultSet rs) throws SQLException {
        return new CategoriaReporte(
                rs.getInt("id_categoria_reporte"),
                rs.getString("descricao")
        );
    }
    public ArrayList<CategoriaReporte> lerTudo() throws SQLException {
        try (PreparedStatement ps = dataSource.preparar("select * from categoria_reporte"); ResultSet rs = ps.executeQuery()) {
            ArrayList<CategoriaReporte> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public void inserir(CategoriaReporte cr) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "insert into categoria_reporte (descricao) values (?)",
                cr.descricao()
        )) {}
    }
    public void alterar(Object obj) throws SQLException {
        CategoriaReporte cr = (CategoriaReporte) obj;
        try (PreparedStatement ps = dataSource.preparar(
                "update categoria_reporte set descricao=? where id_categoria_reporte=?",
                cr.descricao(),
                String.valueOf(cr.id_categoria_reporte())
        )) {}
    }
    public void deletar(int codigo) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "delete from categoria_reporte where (id_municipio = ?)",
                String.valueOf(codigo)
        )) {}
    }

    public Class<?> tipoRegistro() {
        return CategoriaReporte.class;
    }
}