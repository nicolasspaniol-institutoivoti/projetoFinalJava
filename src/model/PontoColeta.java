package model;

public class PontoColeta {
    @CampoSQL(nomeColuna = "ID", larguraColuna = 50)
    public int id_ponto_coleta;
    @CampoSQL(nomeColuna = "Horário de atendimento")
    public String horario = "";
    @CampoSQL(larguraColuna = 50)
    public boolean sede = false;

    // Local
    @CampoSQL(larguraColuna = 50)
    public int numero;
    @CampoSQL
    public String rua = "";
    @CampoSQL(larguraColuna = 60)
    public int cep;
    @CampoSQL(larguraColuna = 80)
    public String complemento = "casa";
    @CampoSQL(larguraColuna = 100, nomeColuna = "Referência")
    public String referencia = "";

    //FK
    @CampoSQL(nomeColuna = "ID Município (FK)", larguraColuna = 100)
    public int id_municipio;
    @CampoSQL(nomeColuna = "ID Fornecedor (FK)", larguraColuna = 120)
    public int id_fornecedor;
}