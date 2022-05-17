package dao;

import model.CategoriaReporte;
import model.Municipio;
import util.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMunicipio extends DAO<Municipio> {
    public DAOMunicipio(DataSource ds) {
        super(ds);
    }

    Municipio lerRegistro(ResultSet rs) throws SQLException {
        Municipio m = new Municipio();
        m.id_municipio = rs.getInt("id_municipio");
        m.nome = rs.getString("nome");
        m.estado = Estado.valueOf(rs.getString("estado"));
        return m;
    }
    public ArrayList<Municipio> lerTudo() throws SQLException {
        try (PreparedStatement ps = dataSource.preparar("select * from municipio"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Municipio> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public void inserir(Object obj) throws SQLException {
        Municipio m = (Municipio) obj;
        try (PreparedStatement ps = dataSource.preparar(
                "insert into municipio (nome, estado) values (?, ?)",
                m.nome,
                m.estado.name()
        )) {
            ps.executeUpdate();
        }
    }
    public void alterar(Object obj) throws SQLException {
        Municipio m = (Municipio) obj;
        try (PreparedStatement ps = dataSource.preparar(
                "update municipio set nome=?, estado=? where id_municipio=?",
                m.nome,
                m.estado.name(),
                String.valueOf(m.id_municipio)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "delete from municipio where (id_municipio = ?)",
                String.valueOf(((Municipio) obj).id_municipio)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return Municipio.class;
    }
}