package app.struct.simple;

public class ListIncadeSimple {
    private NoSimple inicio;

    public NoSimple getInicio() {
        return inicio;
    }

    public void inserir(int valor) {
        NoSimple novo = new NoSimple(valor);
        if (inicio == null) {
            inicio = novo;
        } else {
            NoSimple atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public boolean remover(int valor) {
        if (inicio == null) return false;

        if (inicio.valor == valor) {
            inicio = inicio.proximo;
            return true;
        }

        NoSimple atual = inicio;
        while (atual.proximo != null) {
            if (atual.proximo.valor == valor) {
                atual.proximo = atual.proximo.proximo;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder("Lista Encadeada Simples:\n");
        NoSimple atual = inicio;
        while (atual != null) {
            sb.append(atual.valor).append(" -> ");
            atual = atual.proximo;
        }
        sb.append("null");
        return sb.toString();
    }

    public boolean buscar(int valor) {
        NoSimple atual = inicio;
        while (atual != null) {
            if (atual.valor == valor) return true;
            atual = atual.proximo;
        }
        return false;
    }
}
