package utils;

import java.util.Random;

public class GeradorDados {
    private static final Random RAND = new Random();
    
    public static int[] gerarOrdenado(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    public static int[] gerarInverso(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    public static int[] gerarAleatorio(int n) {
        return aleatorio(n, n * 10);
    }
    
    public static int[] aleatorio(int n, int max) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = RAND.nextInt(max) + 1;
        return arr;
    }
    
}