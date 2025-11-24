package arvore;

public class Rotacoes {

    // Balanceia o nó e retorna o nó novo raiz
    public No balancear(No no) {
        int balanceamento = calcularBalanceamento(no);

        // Caso direita pesada
        if (balanceamento > 1) {
            if (calcularBalanceamento(no.getDireita()) >= 0) {
                no = rotacaoEsquerda(no);
            } else {
                no.setDireita(rotacaoDireita(no.getDireita()));
                no = rotacaoEsquerda(no);
            }
        }
        // Caso esquerda pesada
        else if (balanceamento < -1) {
            if (calcularBalanceamento(no.getEsquerda()) <= 0) {
                no = rotacaoDireita(no);
            } else {
                no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
                no = rotacaoDireita(no);
            }
        }

        return no;
    }

    // Rotação simples à direita
    public No rotacaoDireita(No x) {
        No y = x.getEsquerda();
        No z = y.getDireita();

        y.setDireita(x);
        x.setEsquerda(z);

        return y;
    }

    // Rotação simples à esquerda
    public No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No z = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(z);

        return y;
    }

    // Rotação dupla esquerda-direita
    public No rotacaoEsquerdaDireita(No no) {
        no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
        return rotacaoDireita(no);
    }

    // Rotação dupla direita-esquerda
    public No rotacaoDireitaEsquerda(No no) {
        no.setDireita(rotacaoDireita(no.getDireita()));
        return rotacaoEsquerda(no);
    }

    // MÉTODOS AUXILIARES
    // Calcula o balanceamento: altura(direita) - altura(esquerda)
    public int calcularBalanceamento(No no) {
        if (no == null) return 0;
        return altura(no.getDireita()) - altura(no.getEsquerda());
    }

    // Calcula altura de um nó
    public int altura(No no) {
        if (no == null) return 0;
        int esquerda = altura(no.getEsquerda());
        int direita = altura(no.getDireita());
        return 1 + Math.max(esquerda, direita);
    }
}