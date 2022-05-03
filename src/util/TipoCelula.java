package util;

public interface TipoCelula<T> {
    String nome();
    void definirValor(T valor);
    T lerValor();
}
