package model;

import java.sql.Date;

public record Reserva(
        int idReserva,
        String cpf,
        String nome,
        Date data
) {}
