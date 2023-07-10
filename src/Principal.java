import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        String[] info = Arquivo.getLinhas("meuArq.txt");//vetor onde cada posição é uma linha da matriz
        Scanner entrada = new Scanner(System.in);
        Funcoes t = new Funcoes();

        System.out.println("Deseja começar um novo jogo ou continuar o anterior?");
        System.out.println("1: Novo Jogo       2: Continuar Jogo Salvo");
        System.out.println("Para encerrar e salvar o jogo, insira a linha e/ou coluna na posição -1");

        int opcao = entrada.nextInt();
        while (opcao != 1 && opcao !=2){
            System.out.println("Opção inválida. Tente novamente: ");
            opcao = entrada.nextInt();
        }
        if (opcao == 1){
            t.initVariables();
            t.matrizInicial();
        }else {
            t.recuperarJogo(info);
        }
        t.imprimirMatriz();
        t.contGame();

        System.out.println("Fim do jogo!");
        if (t.getContEmas()<3){
            System.out.println("Aliens venceram\nParabèns!!!!");
        }else if (t.getContAliens() == 0){
            System.out.println("Emas venceram\nParabèns!!!!");
        }else{
            System.out.println("Estado do tabuleiro salvo.");
            Arquivo.gravar(t.salvarTabuleiro(), "meuArq.txt");//fechar
        }

    }
}
