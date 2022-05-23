package dao;

import model.ImagemPontoColeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImagemPontoColeta extends DAO<ImagemPontoColeta> {
    public DAOImagemPontoColeta(DataSource ds) {
        super(ds);
    }

    public ImagemPontoColeta lerRegistro(ResultSet rs) throws SQLException {
        ImagemPontoColeta ipc = new ImagemPontoColeta();

        ipc.id_imagem_ponto_coleta = rs.getInt("id_imagem_ponto_coleta");
        ipc.caminho = rs.getString("caminho");
        ipc.id_ponto_coleta = rs.getInt("id_ponto_coleta");

        return ipc;
    }
    public ArrayList<ImagemPontoColeta> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from imagem_ponto_coleta"); ResultSet rs = ps.executeQuery()) {
            ArrayList<ImagemPontoColeta> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public ImagemPontoColeta inserir() throws SQLException {
        ImagemPontoColeta ipc = new ImagemPontoColeta();
        ipc.id_ponto_coleta = lerPrimeiroId("ponto_coleta");

        try (var ps = dataSource.preparar(
                "insert into imagem_ponto_coleta (caminho, id_ponto_coleta) values (?, ?)",
                ipc.caminho,
                String.valueOf(ipc.id_ponto_coleta)
        )) {
            ps.executeUpdate();
            return ipc;
        }
    }
    public void alterar(Object obj) throws SQLException {
        ImagemPontoColeta ipc = (ImagemPontoColeta) obj;
        try (var ps = dataSource.preparar(
                "update imagem_ponto_coleta set caminho=?, id_ponto_coleta=? where id_imagem_ponto_coleta=?",
                ipc.caminho,
                String.valueOf(ipc.id_ponto_coleta),
                String.valueOf(ipc.id_imagem_ponto_coleta)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from imagem_ponto_coleta where (id_imagem_ponto_coleta = ?)",
                String.valueOf(((ImagemPontoColeta) obj).id_imagem_ponto_coleta)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return ImagemPontoColeta.class;
    }
}