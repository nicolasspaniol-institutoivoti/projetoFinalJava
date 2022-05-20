package dao;

import model.CategoriaPeca;
import model.CategoriaReporte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCategoriaPeca extends DAO<CategoriaPeca> {
    public DAOCategoriaPeca(DataSource ds) {
        super(ds);
    }

    public CategoriaPeca lerRegistro(ResultSet rs) throws SQLException {
        CategoriaPeca cp = new CategoriaPeca();
        cp.id_categoria_peca = rs.getInt("id_categoria_peca");
        cp.descricao = rs.getString("descricao");
        return cp;
    }
    public ArrayList<CategoriaPeca> lerTudo() throws SQLException {
        try (PreparedStatement ps = dataSource.preparar("select * from categoria_peca"); ResultSet rs = ps.executeQuery()) {
            ArrayList<CategoriaPeca> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public void inserir(Object obj) throws SQLException {
        CategoriaPeca cp = (CategoriaPeca) obj;
        try (PreparedStatement ps = dataSource.preparar(
                "insert into categoria_peca (descricao) values (?)",
                cp.descricao
        )) {
            ps.executeUpdate();
        }
    }
    public void alterar(Object obj) throws SQLException {
        CategoriaPeca cp = (CategoriaPeca) obj;
        try (PreparedStatement ps = dataSource.preparar(
                "update categoria_peca set descricao=? where id_categoria_peca=?",
                cp.descricao,
                String.valueOf(cp.id_categoria_peca)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "delete from categoria_peca where (id_categoria_peca = ?)",
                String.valueOf(((CategoriaPeca) obj).id_categoria_peca)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return CategoriaPeca.class;
    }
}