package vetor;

import estruturas.EstruturasDados;

public class Vetor implements EstruturasDados {

    private int[] dados;
    private int qtd;
    private int capacidade;
    private boolean ordenado = true; 

    public Vetor(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new int[capacidade];
    }

    @Override
    public void inserir(int elemento) {
        if (qtd == capacidade) {
            redimensionar();
        }

        if (qtd > 0 && elemento < dados[qtd - 1]) {
            ordenado = false;
        }

        dados[qtd++] = elemento;
    }

    private void redimensionar() {
        capacidade *= 2;
        int[] novoArray = new int[capacidade];
        for (int i = 0; i < qtd; i++) {
            novoArray[i] = dados[i];
        }
        dados = novoArray;
    }

    public boolean buscaSequencial(int elemento) {
        for (int i = 0; i < qtd; i++) {
            if (dados[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public boolean buscaBinaria(int elemento) {
        
        if (!ordenado) {
            System.out.println("ERRO: Busca binária não pode ser usada em vetor não ordenado!");
            return false;
        }
        
        int inicio = 0;
        int fim = qtd - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int v = dados[meio];

            if (v == elemento) {
                return true;
            } else if (v < elemento) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return false;
    }

    @Override
    public int buscarPrimeiroElemento() {
        if (qtd == 0) return -1;
        return dados[0]; 
    }

    @Override
    public int buscarUltimoElemento() {
        if (qtd == 0) return -1;
        return dados[qtd - 1];
    }

    @Override
    public int buscarElementoMeio() {
        if (qtd == 0) return -1;
        return dados[qtd / 2];
    }

    @Override
    public int buscarElementoAleatorio() {
        if (qtd == 0) return -1;
        int idx = (int) (Math.random() * qtd);
        return dados[idx];
    }

    @Override
    public int buscarElementoInexistente() {
        buscaSequencial(-1);
        return -1; 
    }

   public void setOrdenado(boolean ordenado) {
        this.ordenado = ordenado;
    }

    public boolean isOrdenado() {
        return this.ordenado;
    }

    public int getElemento(int indice) {
        if (indice < 0 || indice >= qtd) return -1;
        return dados[indice];
    }

     // Métodos que delegam para as classes de ordenação existentes
    public void ordenarBubble() {
        OrdenacaoSimples.bubbleSort(this.dados, this.qtd);
        this.ordenado = true;
    }

    public void ordenarQuick() {
        OrdenacaoAvancada.quickSort(this.dados, this.qtd);
        this.ordenado = true;
    }
}