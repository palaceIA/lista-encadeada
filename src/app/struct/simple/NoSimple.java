package app.struct.simple;

public class NoSimple {
    public int valor;
    public NoSimple proximo;

    public NoSimple(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}