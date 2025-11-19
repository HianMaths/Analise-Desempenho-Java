package utils;

public class GeradorDados {
    // Semente para geração de números aleatórios
    private static long seed = 12345;
    
    // Gera um array ordenado de 1 a tamanho
    public static int[] gerarArrOrdenado(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = i + 1;
        }
        return dados;
    }

    // Gera um array em ordem inversa de tamanho a 1
    public static int[] gerarArrInverso(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = tamanho - i;
        }
        return dados;
    }

    // Gera um array com números aleatórios entre 1 e tamanho*10
    public static int[] gerarArrAleatorio(int tamanho) {
        int[] dados = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            dados[i] = gerarNumeroAleatorio(tamanho * 10) + 1;
        }
        return dados;
    }
    
    // Gerador de números aleatórios simples
    private static int gerarNumeroAleatorio(int max) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffff;
        return (int) (seed % max);
    }
    
    // Reseta a semente para o valor inicial
    public static void resetSeed() {
        seed = 12345;
    }
}