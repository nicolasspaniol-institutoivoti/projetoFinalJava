package util;

public class TipoFornecedor implements TypeMask<Boolean> {
    boolean instituicao;

    public TipoFornecedor(Boolean instituicao) {
        this.instituicao = instituicao;
    }

    public String nome() {
        return instituicao? "Instituição" : "Brechó";
    }

    public void definirValor(Boolean valor) {
        instituicao = valor;
    }

    public Boolean lerValor() {
        return instituicao;
    }
}
