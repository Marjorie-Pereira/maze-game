package org.ulbra.labirinto;

public class ListaDuplamenteLigada<T> {
    protected  No<T> inicio;
    protected  No<T> fim;

    public ListaDuplamenteLigada() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionar(T dado) {
        No<T> novoNo = new No<>(dado);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
    }

    public void adicionarPrimeiro(T dado) {
        No<T> novoNo = new No<>(dado);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
        inicio = novoNo;
        }
    }

    public void remover(T dado) {
        if (inicio == null) return;
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.dado.equals(dado)) {
                if (atual == inicio) {
                    inicio = atual.proximo;
                    if (inicio != null) inicio.anterior = null;
                }
                else if (atual == fim) {
                    fim = atual.anterior;
                    if (fim != null) fim.proximo = null;
                } else {
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
                return;
            }
            atual = atual.proximo;
        }
    }

    public boolean contem(T dado) {
        No<T> atual = inicio;
        while (atual != null) {
            if (atual.dado.equals(dado)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    public void imprimirLista() {
        No<T> atual = inicio;
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public int tamanhoLista() {
        No<T> atual = inicio;
        int tamanho = 0;
        while (atual != null) {
            atual = atual.proximo;
            tamanho += 1;
        }

        return tamanho;
    }

}
