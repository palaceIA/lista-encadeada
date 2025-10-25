package app.struct.circular;

public class NoCircular {
    public int valor;
    public NoCircular proximo;

    public NoCircular(int valor) {
        this.valor = valor;
    }
}
