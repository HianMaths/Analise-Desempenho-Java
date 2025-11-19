package utils;

public class Cronometro {
    private long inicio;
    private long fim;

    // Inicia o cronômetro
    public void iniciar() {
        inicio = System.nanoTime();
    }

    // Para o cronômetro
    public void parar() {
        fim = System.nanoTime();
    }

    // Retorna o tempo decorrido em milissegundos
    public double getTempoMilissegundos() {
        return (fim - inicio) / 1000000.0;
    }

    // Retorna o tempo decorrido em segundos
    public double getTempoSegundos() {
        return (fim - inicio) / 1000000000.0;
    }
}