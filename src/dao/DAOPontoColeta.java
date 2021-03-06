package dao;

import model.PontoColeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPontoColeta extends DAO<PontoColeta> {
    public DAOPontoColeta(DataSource ds) {
        super(ds);
    }

    public PontoColeta lerRegistro(ResultSet rs) throws SQLException {
        PontoColeta pc = new PontoColeta();
        
        pc.id_ponto_coleta = rs.getInt("id_ponto_coleta");
        pc.horario = rs.getString("horario");
        pc.complemento = rs.getString("complemento");
        pc.referencia = rs.getString("referencia");
        pc.numero = rs.getShort("numero");
        pc.rua = rs.getString("rua");
        pc.cep = rs.getInt("cep");
        pc.id_municipio = rs.getInt("id_municipio");
        pc.id_fornecedor = rs.getInt("id_fornecedor");
        pc.sede = rs.getBoolean("sede");
        
        return pc;
    }
    public ArrayList<PontoColeta> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from ponto_coleta"); ResultSet rs = ps.executeQuery()) {
            ArrayList<PontoColeta> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public PontoColeta inserir() throws SQLException {
        PontoColeta cp = new PontoColeta();
        cp.id_municipio = lerPrimeiroId("municipio");
        cp.id_fornecedor = lerPrimeiroId("fornecedor");

        try (var ps = dataSource.preparar(
                "insert into ponto_coleta (cep, referencia, complemento, numero, rua, id_municipio, id_fornecedor, horario, sede) values (?, ?, ?, ?, ?, ?, ?)",
                String.valueOf(cp.cep),
                cp.referencia,
                cp.complemento,
                String.valueOf(cp.numero),
                cp.rua,
                String.valueOf(cp.id_municipio),
                String.valueOf(cp.id_fornecedor),
                cp.horario,
                cp.sede? "1" : "0"
        )) {
            ps.executeUpdate();
            return cp;
        }
    }
    public void alterar(Object obj) throws SQLException {
        PontoColeta cp = (PontoColeta) obj;
        
        try (var ps = dataSource.preparar(
                "update ponto_coleta set cep=?, referencia=?, complemento=?, numero=?, rua=?, id_municipio=?, id_fornecedor=?, horario=?, sede=? where id_ponto_coleta=?",
                String.valueOf(cp.cep),
                cp.referencia,
                cp.complemento,
                String.valueOf(cp.numero),
                cp.rua,
                String.valueOf(cp.id_municipio),
                String.valueOf(cp.id_fornecedor),
                cp.horario,
                cp.sede? "1" : "0",
                String.valueOf(cp.id_ponto_coleta)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from ponto_coleta where (id_ponto_coleta = ?)",
                String.valueOf(((PontoColeta) obj).id_ponto_coleta)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return PontoColeta.class;
    }
}