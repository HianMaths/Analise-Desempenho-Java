
import estruturas.EstruturasDados;
import vetor.Vetor;
import arvore.ArvoreABB;
import arvore.ArvoreAVL;
import utils.GeradorDados;
import utils.Cronometro;

public class TestesDesempenhoEstruturas {

    private static final int REPETICOES = 5;
    private static final int[] TAMANHOS = {100, 1000, 10000};

    public static void main(String[] args) {
        System.out.println("=== ANÁLISE DE DESEMPENHO - ESTRUTURAS ===");
        testarTudo();
    }

    private static void testarTudo() {
        for (int tam : TAMANHOS) {
            System.out.println("\n=== Tamanho: " + tam + " elementos ===");

            testarPorTipo(GeradorDados.gerarOrdenado(tam), "Ordenada", tam);
            testarPorTipo(GeradorDados.gerarInverso(tam), "Inversa", tam);
            testarPorTipo(GeradorDados.gerarAleatorio(tam), "Aleatória", tam);
        }
    }

    private static void testarPorTipo(int[] dados, String ordem, int tam) {
        System.out.println("\n--- Ordem: " + ordem + " ---");

        // vetor
        Vetor vet = new Vetor(tam);
        for (int v : dados) vet.inserir(v);
        if (ordem.equals("Ordenada")) vet.setOrdenado(true);
        testar(vet, dados, "Vetor");

        // árvore normal
        ArvoreABB abb = new ArvoreABB();
        testar(abb, dados, "Árvore Binária");

        // avl
        ArvoreAVL avl = new ArvoreAVL();
        testar(avl, dados, "Árvore AVL");
    }

    private static void testar(EstruturasDados estrutura, int[] dados, String nome) {

        double tInsercao = 0;
        double tBubble = 0;;
        double tQuick = 0;
        double tPrimeiro = 0;
        double tUltimo = 0;
        double tMeio = 0;
        double tInexistente = 0;
        double[] tAleatorios = new double[3];

        for (int r = 0; r < REPETICOES; r++) {
            Cronometro c = new Cronometro();

            // inserção
            c.iniciar();
            for (int v : dados) estrutura.inserir(v);
            c.parar();
            tInsercao += c.getTempoNanoSegundos();

            // buscas
            c.iniciar();
            estrutura.buscarPrimeiroElemento();
            c.parar();
            tPrimeiro += c.getTempoNanoSegundos();

            c.iniciar();
            estrutura.buscarUltimoElemento();
            c.parar();
            tUltimo += c.getTempoNanoSegundos();

            c.iniciar();
            estrutura.buscarElementoMeio();
            c.parar();
            tMeio += c.getTempoNanoSegundos();

            for (int j = 0; j < 3; j++) {
                c.iniciar();
                estrutura.buscarElementoAleatorio();
                c.parar();
                tAleatorios[j] += c.getTempoNanoSegundos();
            }

            c.iniciar();
            estrutura.buscarElementoInexistente();
            c.parar();
            tInexistente += c.getTempoNanoSegundos();

            // medições separadas para os algoritmos de ordenação (apenas para Vetor)
            if (estrutura instanceof Vetor) {
                // bubble
                Vetor vBubble = new Vetor(dados.length);
                for (int x : dados) vBubble.inserir(x);
                Cronometro cb = new Cronometro();
                cb.iniciar();
                vBubble.ordenarBubble();
                cb.parar();
                tBubble += cb.getTempoNanoSegundos();

                // quick
                Vetor vQuick = new Vetor(dados.length);
                for (int x : dados) vQuick.inserir(x);
                Cronometro cq = new Cronometro();
                cq.iniciar();
                vQuick.ordenarQuick();
                cq.parar();
                tQuick += cq.getTempoNanoSegundos();
            }
        }

        tInsercao /= REPETICOES;
        tPrimeiro /= REPETICOES;
        tUltimo /= REPETICOES;
        tMeio /= REPETICOES;
        tInexistente /= REPETICOES;
        for (int i = 0; i < 3; i++) tAleatorios[i] /= REPETICOES;
        tBubble /= REPETICOES;
        tQuick /= REPETICOES;

        // resultados
        System.out.printf("\n--- Resultados (%s) ---\n", nome);
        System.out.printf("Inserção: %.3f ns\n", tInsercao);
        System.out.printf("Busca primeiro: %.3f ns\n", tPrimeiro);
        System.out.printf("Busca último: %.3f ns\n", tUltimo);
        System.out.printf("Busca meio: %.3f ns\n", tMeio);
        System.out.printf("Buscas aleatórias: %.3f / %.3f / %.3f ns\n",
                tAleatorios[0], tAleatorios[1], tAleatorios[2]);
        System.out.printf("Busca inexistente: %.3f ns\n", tInexistente);

        // resultados das ordenações 
        if (estrutura instanceof Vetor) {
            System.out.printf("Tempo ordenar Bubble: %.3f ns\n", tBubble);
            System.out.printf("Tempo ordenar Quick: %.3f ns\n", tQuick);
        }

        // binária só se for vetor ordenado
        if (estrutura instanceof Vetor v && v.isOrdenado()) {
            System.out.println("\nBusca binária (vetor ordenado):");
            System.out.println("Primeiro: " + v.buscaBinaria(v.getElemento(0)));
            System.out.println("Último: " + v.buscaBinaria(v.getElemento(dados.length - 1)));
            System.out.println("Meio: " + v.buscaBinaria(v.getElemento(dados.length / 2)));
            System.out.println("Inexistente: " + v.buscaBinaria(-1));
        }

        // Busca padrão para árvores
        if (estrutura instanceof ArvoreABB abb) {
            System.out.println("\nBusca padrão (Árvore ABB):");
            System.out.println("Primeiro: " + abb.buscar(dados[0]));
            System.out.println("Último: " + abb.buscar(dados[dados.length - 1]));
            System.out.println("Meio: " + abb.buscar(dados[dados.length / 2]));
            System.out.println("Inexistente: " + abb.buscar(-1));
        }

        if (estrutura instanceof ArvoreAVL avl) {
            System.out.println("\nBusca padrão (Árvore AVL):");
            System.out.println("Primeiro: " + avl.buscar(dados[0]));
            System.out.println("Último: " + avl.buscar(dados[dados.length - 1]));
            System.out.println("Meio: " + avl.buscar(dados[dados.length / 2]));
            System.out.println("Inexistente: " + avl.buscar(-1));
        }
    }
}