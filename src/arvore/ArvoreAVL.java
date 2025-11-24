package arvore;

import estruturas.EstruturasDados;

public class ArvoreAVL implements EstruturasDados {

    private No raiz;
    private int[] valoresInseridos = new int[10];
    private int tamanho = 0;
    private Rotacoes rotacoes = new Rotacoes();

    // INSERÇÃO AVL
    @Override
    public void inserir(int valor) {
        armazenarValor(valor);
        raiz = inserirAVL(raiz, valor);
    }

    private No inserirAVL(No no, int valor) {
        if (no == null) return new No(valor);

        if (valor < no.getValor()) {
            no.setEsquerda(inserirAVL(no.getEsquerda(), valor));
        } else if (valor > no.getValor()) {
            no.setDireita(inserirAVL(no.getDireita(), valor));
        } else {
            return no; 
        }

        return rotacoes.balancear(no);
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