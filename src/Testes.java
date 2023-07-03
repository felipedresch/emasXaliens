import java.io.IOException;
import java.util.Scanner;

public class Testes {
    public static void main(String[] args) throws Exception {
        String[] info = Arquivo.getLinhas("meuArq.txt");//vetor onde cada posição é uma linha da matriz
        Scanner entrada = new Scanner(System.in);
        FuncoesTeste t = new FuncoesTeste();

        //iniciar o jogo
        System.out.println("Deseja começar um novo jogo ou continuar o anterior?");
        System.out.println("1: Novo Jogo       2: Continuar Jogo Salvo");
        System.out.println("Para encerrar e salvar o jogo, informe as seguintes posições: Linha: -1    Coluna: -1");
        int opcao = entrada.nextInt();

        if (opcao == 1){
            t.initVariables();
            t.matrizInicial();
        }else if (opcao == 2){
            t.recuperarJogo(info);
        }

        t.imprimirMatriz();
        t.contGame();

        System.out.println("Fim do jogo!");
        if (t.getContEmas()<3){
            System.out.println("Aliens venceram!!!");
        }else if (t.getContAliens() == 0){
            System.out.println("Emas venceram!!!");
        }else{
            System.out.println("Estado do tabuleiro salvo.");
        }


        Arquivo.gravar(t.salvarTabuleiro(), "meuArq.txt");//fechar

    }
}
