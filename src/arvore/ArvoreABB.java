package arvore;

import estruturas.EstruturasDados;

public class ArvoreABB implements EstruturasDados {

    private No raiz;
    private int[] valoresInseridos = new int[10];
    private int tamanho = 0;

    // INSERÇÃO ABB
    @Override
    public void inserir(int valor) {
        armazenarValor(valor);
        if (raiz == null) {
            raiz = new No(valor);
        } else {
            inserirRec(raiz, valor);
        }
    }

    private void inserirRec(No no, int valor) {
        if (valor < no.getValor()) {
            if (no.getEsquerda() == null) {
                no.setEsquerda(new No(valor));
            } else {
                inserirRec(no.getEsquerda(), valor);
            }
        } else if (valor > no.getValor()) {
            if (no.getDireita() == null) {
                no.setDireita(new No(valor));
            } else {
                inserirRec(no.getDireita(), valor);
            }
        }
    }

    private void armazenarValor(int valor) {
        if (tamanho == valoresInseridos.length) {
            int[] novo = new int[valoresInseridos.length * 2];
            for (int i = 0; i < tamanho; i++) novo[i] = valoresInseridos[i];
            valoresInseridos = novo;
        }
        valoresInseridos[tamanho++] = valor;
    }

    // MÉTODOS DE BUSCA
    @Override
    public int buscarPrimeiroElemento() {
        if (raiz == null) return -1;
        No atual = raiz;
        while (atual.getEsquerda() != null) atual = atual.getEsquerda();
        return atual.getValor();
    }

    @Override
    public int buscarUltimoElemento() {
        if (raiz == null) return -1;
        No atual = raiz;
        while (atual.getDireita() != null) atual = atual.getDireita();
        return atual.getValor();
    }

    @Override
    public int buscarElementoMeio() {
        if (tamanho == 0) return -1;
        return valoresInseridos[tamanho / 2];
    }

    @Override
    public int buscarElementoAleatorio() {
        if (tamanho == 0) return -1;
        int indice = (int) (Math.random() * tamanho);
        return valoresInseridos[indice];
    }

    @Override
    public int buscarElementoInexistente() {
        return -1;
    }
}