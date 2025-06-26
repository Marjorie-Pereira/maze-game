
package org.ulbra.labirinto;

public class Labirinto extends ListaDuplamenteLigada<Sala> {
    @Override
    public void imprimirLista() {
        No<Sala> atual = inicio;
        while (atual != null) {
            System.out.print(atual.dado.getId() + " | " + atual.dado.getDescricao() + " | " + atual.dado.getTipo());
            System.out.println();
            atual = atual.proximo;
        }
        
    }
}
