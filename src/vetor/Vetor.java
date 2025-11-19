package vetor;

import estruturas.EstruturasDados;

public class Vetor implements EstruturasDados {

    private int[] elementos;
    private int tamanho;
    private int capacidade;

    public Vetor(int capacidade) {
        this.capacidade = capacidade;
        this.elementos = new int[capacidade];
        this.tamanho = 0;
    }

    @Override
    public void inserir(int elemento) {
        if (tamanho == capacidade) {
            redimensionar();
        }
        elementos[tamanho] = elemento;
        tamanho++;
    }

    private void redimensionar() {
        capacidade *= 2;
        int[] novoArray = new int[capacidade];
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = elementos[i];
        }
        elementos = novoArray;
    }

    public boolean buscaSequencial(int elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public boolean buscaBinaria(int elemento) {
        int esquerda = 0;
        int direita = tamanho - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if (elementos[meio] == elemento) {
                return true;
            }
            if (elementos[meio] < elemento) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return false;
    }

    @Override
    public int buscarPrimeiroElemento() {
        if (tamanho == 0) return -1;
        int valor = elementos[0];
        buscaBinaria(valor); 
        return valor;
    }

    @Override
    public int buscarUltimoElemento() {
        if (tamanho == 0) return -1;
        int valor = elementos[tamanho - 1];
        buscaBinaria(valor);
        return valor;
    }

    @Override
    public int buscarElementoMeio() {
        if (tamanho == 0) return -1;
        int valor = elementos[tamanho / 2];
        buscaBinaria(valor);
        return valor;
    }

    @Override
    public int buscarElementoAleatorio() {
        if (tamanho == 0) return -1;
        int indiceAleatorio = (int)(Math.random() * tamanho);
        int valor = elementos[indiceAleatorio];
        buscaBinaria(valor);
        return valor;
    }

    @Override
    public int buscarElementoInexistente() {
        buscaBinaria(-1); 
        return -1;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getElemento(int indice) {
        if (indice >= 0 && indice < tamanho) {
            return elementos[indice];
        }
        return -1;
    }

    public int[] getArray() {
        return elementos;
    }
}