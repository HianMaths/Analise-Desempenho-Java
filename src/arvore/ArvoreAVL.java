package arvore;

import estruturas.EstruturasDados;

public class ArvoreAVL implements EstruturasDados {

    private No raiz;
    private int[] valoresInseridos = new int[10];
    private int tamanho = 0;

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

        return balancear(no);
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
    public boolean buscar(int valor) {
        return buscarAVL(raiz, valor);
    }

    private boolean buscarAVL(No no, int valor) {
        if (no == null) return false;
        
        if (valor == no.getValor()) {
            return true;
        } else if (valor < no.getValor()) {
            return buscarAVL(no.getEsquerda(), valor);
        } else {
            return buscarAVL(no.getDireita(), valor);
        }
    }

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

    // MÉTODOS DE ROTAÇÕES
    // Balanceia o nó e retorna o nó novo raiz
    private No balancear(No no) {
        int balanceamento = calcularBalanceamento(no);

        // Caso direita pesada
        if (balanceamento > 1) {
            if (calcularBalanceamento(no.getDireita()) >= 0) {
                no = rotacaoEsquerda(no);
            } else {
                no = rotacaoDireitaEsquerda(no);
            }
        }
        // Caso esquerda pesada
        else if (balanceamento < -1) {
            if (calcularBalanceamento(no.getEsquerda()) <= 0) {
                no = rotacaoDireita(no);
            } else {
                no = rotacaoEsquerdaDireita(no);
            }
        }

        return no;
    }

    // Rotação simples à direita
    private No rotacaoDireita(No x) {
        No y = x.getEsquerda();
        No z = y.getDireita();

        y.setDireita(x);
        x.setEsquerda(z);

        return y;
    }

    // Rotação simples à esquerda
    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No z = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(z);

        return y;
    }

    // Rotação dupla esquerda-direita
    private No rotacaoEsquerdaDireita(No no) {
        no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
        return rotacaoDireita(no);
    }

    // Rotação dupla direita-esquerda
    private No rotacaoDireitaEsquerda(No no) {
        no.setDireita(rotacaoDireita(no.getDireita()));
        return rotacaoEsquerda(no);
    }

    // Calcula o balanceamento: altura(direita) - altura(esquerda)
    private int calcularBalanceamento(No no) {
        if (no == null) return 0;
        return altura(no.getDireita()) - altura(no.getEsquerda());
    }

    // Calcula altura de um nó
    private int altura(No no) {
        if (no == null) return 0;
        int esquerda = altura(no.getEsquerda());
        int direita = altura(no.getDireita());
        return 1 + Math.max(esquerda, direita);
    }
}