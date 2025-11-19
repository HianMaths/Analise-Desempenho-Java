package vetor;

public class OrdenacaoAvancada {
    
    // Quick Sort
    public static void quickSort(int[] vetor, int n) {
        quickSortRecursivo(vetor, 0, n - 1);
    }

    // Método auxiliar para Quick Sort
    private static void quickSortRecursivo(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(vetor, inicio, fim);
            quickSortRecursivo(vetor, inicio, indicePivo - 1);
            quickSortRecursivo(vetor, indicePivo + 1, fim);
        }
    }

    // Método de partição para Quick Sort
    private static int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }

        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temp;

        return i + 1;
    }
}