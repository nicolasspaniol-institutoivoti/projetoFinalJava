package model;

public class PontoColeta {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_ponto_coleta;
    @CampoSQL(nomeColuna = "Horário de atendimento")
    public String horario = "";

    // Local
    @CampoSQL(larguraColuna = 50)
    public int numero;
    @CampoSQL
    public String rua = "";
    @CampoSQL
    public String bairro = "";
    @CampoSQL(larguraColuna = 80)
    public int cep;
    @CampoSQL(larguraColuna = 50)
    public String complemento = "casa";
    @CampoSQL(larguraColuna = 150)
    public String coordenadas = "";

    //FK
    @CampoSQL(nomeColuna = "ID Município (FK)", larguraColuna = 100)
    public int id_municipio;
    @CampoSQL(nomeColuna = "ID Fornecedor (FK)", larguraColuna = 120)
    public int id_fornecedor;
}