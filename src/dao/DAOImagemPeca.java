package dao;

import model.ImagemPeca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImagemPeca extends DAO<ImagemPeca> {
    public DAOImagemPeca(DataSource ds) {
        super(ds);
    }

    public ImagemPeca lerRegistro(ResultSet rs) throws SQLException {
        ImagemPeca ip = new ImagemPeca();

        ip.id_imagem_peca = rs.getInt("id_categoria_peca");
        ip.caminho = rs.getString("caminho");
        ip.id_peca = rs.getInt("id_peca");

        return ip;
    }
    public ArrayList<ImagemPeca> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from imagem_peca"); ResultSet rs = ps.executeQuery()) {
            ArrayList<ImagemPeca> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public ImagemPeca inserir() throws SQLException {
        ImagemPeca ip = new ImagemPeca();
        ip.id_peca = lerPrimeiroId("peca");

        try (var ps = dataSource.preparar(
                "insert into imagem_peca (caminho, id_peca) values (?, ?)",
                ip.caminho,
                String.valueOf(ip.id_peca)
        )) {
            ps.executeUpdate();
            return ip;
        }
    }
    public void alterar(Object obj) throws SQLException {
        ImagemPeca ip = (ImagemPeca) obj;
        try (var ps = dataSource.preparar(
                "update imagem_peca set caminho=?, id_peca=? where id_categoria_peca=?",
                ip.caminho,
                String.valueOf(ip.id_peca)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from imagem_peca where (id_imagem_peca = ?)",
                String.valueOf(((ImagemPeca) obj).id_imagem_peca)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return ImagemPeca.class;
    }
}