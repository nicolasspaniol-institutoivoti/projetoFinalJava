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
        f.ativo = rs.getBoolean("ativo");
        f.cnp = rs.getString("cnp");
        f.email = rs.getString("email");
        f.senha = rs.getString("senha");
        f.tipo = TipoFornecedor.values()[rs.getInt("tipo")];
        f.telefone = rs.getString("telefone");
        f.nome = rs.getString("nome");
        f.imagem = rs.getString("imagem");

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

        try (var ps = dataSource.preparar(
                "insert into fornecedor (nome, telefone, email, senha, cnp, tipo, ativo) values (?, ?, ?, ?, ?, ?, ?)",
                f.nome,
                f.telefone,
                f.email,
                f.senha,
                f.cnp,
                f.tipo == TipoFornecedor.Brechó ? "1" : "0",
                f.ativo ? "1" : "0"
        )) {
            ps.executeUpdate();
            return f;
        }
    }
    public void alterar(Object obj) throws SQLException {
        Fornecedor f = (Fornecedor) obj;

        try (var ps = dataSource.preparar(
                "update fornecedor set nome=?, telefone=?, email=?, senha=?, cnp=?, tipo=?, ativo=? where id_fornecedor=?",
                f.nome,
                f.telefone,
                f.email,
                f.senha,
                f.cnp,
                f.tipo == TipoFornecedor.Brechó ? "1" : "0",
                f.ativo ? "1" : "0",
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