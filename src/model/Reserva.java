package model;

import java.sql.Date;

public class Reserva {
    @CampoSQL (nomeColuna = "ID", larguraColuna = 50)
    public int id_reserva;
    @CampoSQL (nomeColuna = "CPF", larguraColuna = 100)
    public String cpf = "00000000000";
    @CampoSQL
    public String nome = "";
    @CampoSQL (larguraColuna = 150)
    public Date data = new Date(System.currentTimeMillis());
}
