package util;

public interface TypeMask<T> {
    String nome();
    void definirValor(T valor);
    T lerValor();
}
