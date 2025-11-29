### Análise de Desempenho de Estruturas de Dados em Java
**Introdução**

Este trabalho tem como objetivo comparar o desempenho de diferentes estruturas de dados **Vetores, Árvores Binárias de Busca (ABB) e Árvores AVL**, em operações de **inserção e busca**, utilizando conjuntos de dados de tamanhos variados e diferentes ordens de inserção **(ordenada, inversa e aleatória)**.

Todos os algoritmos e estruturas foram implementados do **zero, sem uso de bibliotecas externas**, priorizando **códigos simples e/ou recursivos**, para reforçar o aprendizado sobre a complexidade e funcionamento interno dessas estruturas.

**Estrutura das pastas**

````
ANALISE-DESEMPENHO-JAVA/
│
├── src/
│ ├── vetor/
│ │ ├── Vetor.java → Implementação de vetor com inserção e busca
│ │ ├── OrdenacaoSimples.java → BubbleSort
│ │ └── OrdenacaoAvancada.java → QuickSort
│ │
│ ├── arvore/
│ │ ├── No.java → Classe representando um nó
│ │ ├── ArvoreABB.java → Árvore Binária de Busca (ABB)
│ │ └── ArvoreAVL.java → Árvore AVL auto-balanceada
│ │
│ ├── utils/
│ │ ├── GeradorDados.java → Gera conjuntos de dados
│ │ └── Cronometro.java → Mede tempo de execução
│ │
│ ├── estruturas/
│ │ └── EstruturasDados → Interface comum para todas as estruturas testadas
│ │
│ └── TestesDesempenhoEstruturas.java → Classe principal
│
├── relatorio/ → Relatório final em PDF
│ └── Relatorio_Analise.pd         
|
└── Readme
````
<br>

**Objetivo do Projeto**

**O projeto foi desenvolvido para:**
* Comparar **tempos de inserção e busca** entre:
  * Vetor
  * Árvore ABB
  * Árvore AVL
* Observar como o **formato dos dados de entrada** afeta o desempenho.
* Demonstrar efeitos como:
   * Desbalanceamento da ABB com entradas ordenadas
   * Estabilidade do desempenho da AVL devido ao balanceamento
   * Diferença entre buscas em vetor ordenado e desordenado

**Cenários Testados**
* Os experimentos são realizados com vetores de:
    * **100**
    * **1.000**
    * **10.000 elementos**
* E em três tipos de entrada:
    * **✔ Ordenada**
    * **✔ Reversa**
    * **✔ Aleatória**

**Métricas Coletadas**

O programa mede:

⏱ **Tempo médio de inserção** por estrutura

⏱ **Tempo médio de busca** por estrutura

A classe `Cronometro` é responsável pela medição precisa em nanosegundos.   

**Como Executar**

**1** - Certifique-se de ter o **Java 17+** instalado.

**2 - Compile:**
```
javac -d out src/**/*.java
```
**3 - Execute:**
```
java -cp out TestesDesempenhoEstruturas
```
<br>

**Funcionamento Geral**

O fluxo do programa é:

* `GeradorDados` cria os vetores de teste.
* Cada estrutura recebe N elementos enquanto o `Cronometro` mede o tempo.
* A busca é testada usando o mesmo conjunto de dados.
* Os resultados são exibidos de forma organizada no console.

**Exemplo (fictício):**
```
=== Tamanho: 1000 ===
--- Ordem: Aleatória ---

[Vetor]
Inserção: 14544 ns
Busca:    6500 ns

[ABB]
Inserção: 23999 ns
Busca:    7022 ns

[AVL]
Inserção: 35000 ns
Busca:    3000 ns
```
<br>

**Tecnologias Utilizadas**
* Java 17+
* Organização modular por pacotes
* Medição precisa com `System.nanoTime()`
