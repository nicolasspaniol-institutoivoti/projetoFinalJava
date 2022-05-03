package util;

public class TipoFornecedor implements TipoCelula<Boolean> {
    boolean instituicao;

    public TipoFornecedor(Boolean val) {

    }

    public String nome() {
        return instituicao? "Instituição" : "Brechó";
    }

    public void definirValor(Boolean valor) {
        instituicao = valor;
    }

    public boolean lerValor() {
        return instituicao;
    }
}
