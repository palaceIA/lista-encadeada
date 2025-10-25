package app.struct.duple;

public class ListDoubleIncluded {
    private NoDouble inicio;
    private NoDouble fim;

    public NoDouble getInicio() {
        return inicio;
    }

    public void inserir(int valor) {
        NoDouble novo = new NoDouble(valor);
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        }
    }

    public boolean remover(int valor) {
        if (inicio == null) return false;

        NoDouble atual = inicio;
        while (atual != null) {
            if (atual.valor == valor) {
                if (atual == inicio) inicio = atual.proximo;
                if (atual == fim) fim = atual.anterior;
                if (atual.anterior != null) atual.anterior.proximo = atual.proximo;
                if (atual.proximo != null) atual.proximo.anterior = atual.anterior;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder("Lista Duplamente Encadeada:\n");
        NoDouble atual = inicio;
        while (atual != null) {
            sb.append(atual.valor).append(" <-> ");
            atual = atual.proximo;
        }
        sb.append("null");
        return sb.toString();
    }

    public boolean buscar(int valor) {
        NoDouble atual = inicio;
        while (atual != null) {
            if (atual.valor == valor) return true;
            atual = atual.proximo;
        }
        return false;
    }
}
