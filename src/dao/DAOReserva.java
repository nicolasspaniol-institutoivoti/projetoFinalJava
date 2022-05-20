package dao;

import model.Reserva;
import model.Reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOReserva extends DAO<Reserva> {
    public DAOReserva(DataSource ds) {
        super(ds);
    }

    public Reserva lerRegistro(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.id_reserva = rs.getInt("id_reserva");
        r.cpf = rs.getString("cpf");
        r.data = rs.getDate("data");
        r.nome = rs.getString("nome");
        return r;
    }
    public ArrayList<Reserva> lerTudo() throws SQLException {
        try (PreparedStatement ps = dataSource.preparar("select * from reserva"); ResultSet rs = ps.executeQuery()) {
            ArrayList<Reserva> lista = new ArrayList<>();
            while (rs.next()) lista.add(lerRegistro(rs));
            return lista;
        }
    }
    public void inserir(Object obj) throws SQLException {
        Reserva r = (Reserva) obj;
        LocalDate dataLocal = r.data.toLocalDate();
        try (PreparedStatement ps = dataSource.preparar(
                "insert into reserva (cpf, nome, data) values (?, ?, ?)",
                r.cpf,
                r.nome,
                String.format("%d-%d-%d", dataLocal.getYear(), dataLocal.getMonthValue(), dataLocal.getDayOfMonth())
        )) {
            ps.executeUpdate();
        }
    }
    public void alterar(Object obj) throws SQLException {
        Reserva r = (Reserva) obj;
        LocalDate dataLocal = r.data.toLocalDate();
        try (PreparedStatement ps = dataSource.preparar(
                "update reserva set cpf=?, nome=?, data=? where id_reserva=?",
                r.cpf,
                r.nome,
                String.format("%d-%d-%d", dataLocal.getYear(), dataLocal.getMonthValue(), dataLocal.getDayOfMonth()),
                String.valueOf(r.id_reserva)
        )) {
            ps.executeUpdate();
        }
    }
    public void deletar(Object obj) throws SQLException {
        try (PreparedStatement ps = dataSource.preparar(
                "delete from reserva where (id_reserva = ?)",
                String.valueOf(((Reserva) obj).id_reserva)
        )) {
            ps.executeUpdate();
        }
    }

    public Class<?> tipoRegistro() {
        return Reserva.class;
    }
}