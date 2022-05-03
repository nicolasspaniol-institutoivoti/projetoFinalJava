/*
package dao;

import model.CategoriaReporte;
import model.Fornecedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOFornecedor extends DAO<Fornecedor> {
    public DAOFornecedor(DataSource ds) {
        super(ds);
    }

    public Fornecedor lerRegistro(ResultSet rs) throws SQLException {
        return new Fornecedor(
                rs.getInt("id_fornecedor"),
                rs.getBoolean("tipo"),
                rs.getBoolean("ativo"),
                rs.getString("nome"),
                rs.getString()
        );
    }
    public ArrayList<Fornecedor> lerTudo() throws SQLException {
        try (PreparedStatement ps = dataSource.preparar("select * from categoria_reporte"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Fornecedor> lista = new ArrayList<>();
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
    public void alterar(CategoriaReporte cr) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "update categoria_reporte set descricao=? where id_categoria_reporte=?",
                cr.descricao(),
                String.valueOf(cr.idCategoriaReporte())
        )) {}
    }
    public void deletar(int codigo) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "delete from categoria_reporte where (id_municipio = ?)",
                String.valueOf(codigo)
        )) {}
    }

    public Class<?> tipoRegistro() {
        return Fornecedor.class;
    }
}*/
