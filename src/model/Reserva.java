package model;

import java.sql.Date;

public record Reserva(
        int id_reserva,
        String cpf,
        String nome,
        Date data
) {}
