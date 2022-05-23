package dao;

import model.Reporte;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOReporte extends DAO<Reporte> {
    public DAOReporte(DataSource ds) {
        super(ds);
    }

    public Reporte lerRegistro(ResultSet rs) throws SQLException {
        Reporte r = new Reporte();

        r.id_reporte = rs.getInt("id_reporte");
        r.comentario = rs.getString("comentario");
        r.id_peca = rs.getInt("id_peca");
        r.id_categoria_reporte = rs.getInt("id_categoria_reporte");

        return r;
    }
    public ArrayList<Reporte> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from reporte"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Reporte> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public Reporte inserir() throws SQLException {
        Reporte r = new Reporte();
        r.id_peca = lerPrimeiroId("peca");
        r.id_categoria_reporte = lerPrimeiroId("categoria_reporte");

        try (var ps = dataSource.preparar(
                "insert into reporte (comentario, id_peca, id_categoria_reporte) values (?, ?, ?)",
                r.comentario,
                String.valueOf(r.id_peca),
                String.valueOf(r.id_categoria_reporte)
        )) {
            ps.executeUpdate();
            return r;
        }
    }
    public void alterar(Object obj) throws SQLException {
        Reporte r = (Reporte) obj;

        try (var ps = dataSource.preparar(
                "update reporte set comentario=?, id_peca=?, id_categoria_reporte=? where id_reporte=?",
                r.comentario,
                String.valueOf(r.id_peca),
                String.valueOf(r.id_categoria_reporte),
                String.valueOf(r.id_reporte)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from reporte where (id_reporte = ?)",
                String.valueOf(((Reporte) obj).id_reporte)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return Reporte.class;
    }
}