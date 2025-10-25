package app.struct.circular;

public class ListCircular {
    private NoCircular inicio;
    private NoCircular fim;

    public NoCircular getInicio() {
        return inicio;
    }

    public void inserir(int valor) {
        NoCircular novo = new NoCircular(valor);
        if (inicio == null) {
            inicio = fim = novo;
            novo.proximo = inicio;
        } else {
            fim.proximo = novo;
            novo.proximo = inicio;
            fim = novo;
        }
    }

    public boolean remover(int valor) {
        if (inicio == null) return false;

        NoCircular atual = inicio;
        NoCircular anterior = fim;

        do {
            if (atual.valor == valor) {
                if (atual == inicio) {
                    inicio = inicio.proximo;
                    fim.proximo = inicio;
                } else if (atual == fim) {
                    fim = anterior;
                    fim.proximo = inicio;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        } while (atual != inicio);

        return false;
    }

    public String listar() {
        if (inicio == null) return "Lista Circular vazia.";

        StringBuilder sb = new StringBuilder("Lista Encadeada Circular:\n");
        NoCircular atual = inicio;

        do {
            sb.append(atual.valor).append(" -> ");
            atual = atual.proximo;
        } while (atual != inicio);

        sb.append("(volta ao início)");
        return sb.toString();
    }

    public boolean buscar(int valor) {
        if (inicio == null) return false;
        NoCircular atual = inicio;
        do {
            if (atual.valor == valor) return true;
            atual = atual.proximo;
        } while (atual != inicio);
        return false;
    }
}
