package estruturas;

public interface EstruturasDados {
    void inserir(int valor);

    // Métodos de busca padronizados
    int buscarPrimeiroElemento();     // menor elemento
    int buscarUltimoElemento();       // maior elemento
    int buscarElementoMeio();         // elemento do meio com base nos dados inseridos
    int buscarElementoAleatorio();    // valor aleatório existente na estrutura
    int buscarElementoInexistente();  // valor que garantidamente não existe
}
