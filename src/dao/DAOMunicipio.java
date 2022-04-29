package dao;

import model.Municipio;
import util.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMunicipio implements DataAccessObject<Municipio> {
    private final DataSource dataSource;

    public DAOMunicipio(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Municipio fromResultSet(ResultSet rs) throws SQLException {
        return new Municipio(
                rs.getInt("id_municipio"),
                rs.getString("nome"),
                Estado.valueOf(rs.getString("estado"))
        );
    }
    public ArrayList<Municipio> lerTudo() throws SQLException {
        ResultSet rs = dataSource.get("select * from municipio");
        ArrayList<Municipio> lista = new ArrayList<>();
        while (rs.next()) lista.add(fromResultSet(rs));
        dataSource.closeDataSource();
        return lista;
    }
    public Municipio ler(String termo) throws SQLException {
        ResultSet rs = dataSource.get("select * from municipio where (nome like %?%) limit 1", termo);
        dataSource.closeDataSource();
        return fromResultSet(rs);
    }
    public void inserir(Municipio m){
        dataSource.set("insert into municipio (nome, estado) values (?, ?)", m.nome(), m.estado().name());
        dataSource.closeDataSource();
    }
    public void alterar(Municipio m) {
        dataSource.set(
                "update municipio set nome=?, estado=? where id_municipio=?",
                m.nome(),
                m.estado().name(),
                String.valueOf(m.idMunicipio())
        );
        dataSource.closeDataSource();
    }
    public void deletar(int codigo) {
        dataSource.set("delete from municipio where (id_municipio = ?)", String.valueOf(codigo));
        dataSource.closeDataSource();
    }

    public String[] nomeCampos() {
        return new String[] {"ID", "nome", "estado"};
    }
}