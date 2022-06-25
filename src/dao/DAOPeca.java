package dao;

import model.Peca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPeca extends DAO<Peca> {
    public DAOPeca(DataSource ds) {
        super(ds);
    }

    public Peca lerRegistro(ResultSet rs) throws SQLException {
        Peca p = new Peca();

        p.id_peca = rs.getInt("id_peca");
        p.tamanho = rs.getString("tamanho");
        p.cor = rs.getString("cor");
        p.descricao = rs.getString("descricao");
        p.preco = rs.getInt("preco");
        p.id_categoria_peca = rs.getInt("id_categoria_peca");
        p.id_ponto_coleta = rs.getInt("id_ponto_coleta");
        p.id_reserva = rs.getInt("id_reserva");
        p.titulo = rs.getString("titulo");

        return p;
    }
    public ArrayList<Peca> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from peca"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Peca> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public Peca inserir() throws SQLException {
        Peca p = new Peca();
        p.id_categoria_peca = lerPrimeiroId("categoria_peca");
        p.id_ponto_coleta = lerPrimeiroId("ponto_coleta");
        p.id_reserva = lerPrimeiroId("reserva");

        try (var ps = dataSource.preparar(
                "insert into peca (tamanho, cor, descricao, titulo, preco, id_categoria_peca, id_ponto_coleta, id_reserva) values (?, ?, ?, ?, ?, ?, ?, ?)",
                String.valueOf(p.tamanho),
                p.cor,
                p.descricao,
                p.titulo,
                String.valueOf(p.preco),
                String.valueOf(p.id_categoria_peca),
                String.valueOf(p.id_ponto_coleta),
                String.valueOf(p.id_reserva)
        )) {
            ps.executeUpdate();
            return p;
        }
    }
    public void alterar(Object obj) throws SQLException {
        Peca p = (Peca) obj;

        try (var ps = dataSource.preparar(
                "update peca set tamanho=?, cor=?, descricao=?, titulo=?, preco=?, id_categoria_peca=?, id_ponto_coleta=?, id_reserva=? where id_peca=?",
                String.valueOf(p.tamanho),
                p.cor,
                p.descricao,
                p.titulo,
                String.valueOf(p.preco),
                String.valueOf(p.id_categoria_peca),
                String.valueOf(p.id_ponto_coleta),
                String.valueOf(p.id_reserva),
                String.valueOf(p.id_peca)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from peca where (id_peca = ?)",
                String.valueOf(((Peca) obj).id_peca)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return Peca.class;
    }
}