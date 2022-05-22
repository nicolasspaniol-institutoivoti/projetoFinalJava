package dao;

import model.Fornecedor;
import util.TipoFornecedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOFornecedor extends DAO<Fornecedor> {
    public DAOFornecedor(DataSource ds) {
        super(ds);
    }

    public Fornecedor lerRegistro(ResultSet rs) throws SQLException {
        Fornecedor f = new Fornecedor();

        f.id_fornecedor = rs.getInt("id_fornecedor");
        f.cep = rs.getInt("cep");
        f.ativo = rs.getBoolean("ativo");
        f.bairro = rs.getString("bairro");
        f.complemento = rs.getString("complemento");
        f.coordenadas = rs.getString("coordenadas");
        f.logo = rs.getString("logo");
        f.cnp = rs.getString("cnp");
        f.email = rs.getString("email");
        f.numero = rs.getShort("numero");
        f.rua = rs.getString("rua");
        f.senha = rs.getString("senha");
        f.id_municipio = rs.getInt("id_municipio");
        f.tipo = TipoFornecedor.values()[rs.getInt("tipo")];
        f.telefone = rs.getString("telefone");
        f.nome = rs.getString("nome");

        return f;
    }
    public ArrayList<Fornecedor> lerTudo() throws SQLException {
        try (var ps = dataSource.preparar("select * from fornecedor"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Fornecedor> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public Fornecedor inserir() throws SQLException {
        Fornecedor f = new Fornecedor();
        f.id_municipio = lerPrimeiroId("municipio");

        try (var ps = dataSource.preparar(
                "insert into fornecedor (cep, ativo, bairro, complemento, coordenadas, logo, cnp, email, numero, rua, senha, id_municipio, tipo, telefone, nome) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                String.valueOf(f.cep),
                f.ativo ? "1" : "0",
                f.bairro,
                f.complemento,
                f.coordenadas,
                f.logo,
                f.cnp,
                f.email,
                String.valueOf(f.numero),
                f.rua,
                f.senha,
                String.valueOf(f.id_municipio),
                f.tipo == TipoFornecedor.Brechó ? "1" : "0",
                f.telefone,
                f.nome
        )) {
            ps.executeUpdate();
            return f;
        }
    }
    public void alterar(Object obj) throws SQLException {
        Fornecedor f = (Fornecedor) obj;

        try (var ps = dataSource.preparar(
                "update fornecedor set cep=?, ativo=?, bairro=?, complemento=?, coordenadas=?, logo=?, cnp=?, email=?, numero=?, rua=?, senha=?, id_municipio=?, tipo=?, telefone=?, nome=? where id_fornecedor=?",
                String.valueOf(f.cep),
                f.ativo ? "1" : "0",
                f.bairro,
                f.complemento,
                f.coordenadas,
                f.logo,
                f.cnp,
                f.email,
                String.valueOf(f.numero),
                f.rua,
                f.senha,
                String.valueOf(f.id_municipio),
                f.tipo == TipoFornecedor.Brechó ? "1" : "0",
                f.telefone,
                f.nome,
                String.valueOf(f.id_fornecedor)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (var ps = dataSource.preparar(
                "delete from fornecedor where (id_fornecedor = ?)",
                String.valueOf(((Fornecedor) obj).id_fornecedor)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return Fornecedor.class;
    }
}