package vetor;

import estruturas.EstruturasDados;

public class Vetor implements EstruturasDados {

    private int[] elementos;
    private int tamanho;
    private int capacidade;
    private boolean ordenado = true; // controla se o vetor está ordenado

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

        if (tamanho > 0 && elemento < elementos[tamanho - 1]) {
            ordenado = false;
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
        
        if (!ordenado) {
            System.out.println("ERRO: Busca binária não pode ser usada em vetor não ordenado!");
            return false;
        }
        
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
        buscaSequencial(valor); 
        return valor;
    }

    @Override
    public int buscarUltimoElemento() {
        if (tamanho == 0) return -1;
        int valor = elementos[tamanho - 1];
        buscaSequencial(valor);
        return valor;
    }

    @Override
    public int buscarElementoMeio() {
        if (tamanho == 0) return -1;
        int valor = elementos[tamanho / 2];
        buscaSequencial(valor);
        return valor;
    }

    @Override
    public int buscarElementoAleatorio() {
        if (tamanho == 0) return -1;
        int indiceAleatorio = (int)(Math.random() * tamanho);
        int valor = elementos[indiceAleatorio];
        buscaSequencial(valor);
        return valor;
    }

    @Override
    public int buscarElementoInexistente() {
        buscaSequencial(-1); 
        return -1;
    }

    public boolean isOrdenado() {
        return ordenado;
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