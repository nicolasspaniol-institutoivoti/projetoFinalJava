package model;

public class PontoColeta {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_ponto_coleta;
    @CampoSQL(nomeColuna = "Horário de atendimento")
    public String horario;

    // Local
    short numero;
    String rua;
    String bairro;
    int cep;
    String complemento;
    String coordenadas;

    //FK
    @CampoSQL(nomeColuna = "ID Município (FK)", larguraColuna = 100)
    public int id_municipio;
    @CampoSQL(nomeColuna = "ID Fornecedor (FK)", larguraColuna = 100)
    public int id_fornecedor;
}