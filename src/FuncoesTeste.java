import java.io.IOException;
import java.util.Scanner;

public class FuncoesTeste {
    private final int LINHAS = 5;
    private final int COLUNAS = 4;
    private final String[][] tabuleiro = new String[LINHAS][COLUNAS];
    private boolean alienJoga;
    private int contEmas;
    private int contAliens;
    private int contVazios;
    Scanner entrada = new Scanner(System.in);
    public int getContEmas() {
        return contEmas;
    }
    public int getContAliens() {
        return contAliens;
    }

    public String salvarTabuleiro(){
        String conteudo;
        if (alienJoga){
            conteudo = "1;\n"; //vez dos aliens
        }else{
            conteudo = "2;\n"; //vez das emas
        }
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                conteudo += tabuleiro[i][j] + ";";
            }
            conteudo+="\n";
        }
        return conteudo;
    }

    public void recuperarJogo(String[] info) throws IOException {
        //Recuperar a matriz do .txt
        Arquivo arq = new Arquivo();
        String[] linha;
        for (int l = 0; l < LINHAS; l++) {
            linha = info[l].split(";");
            for (int c = 0; c < COLUNAS; c++) {
                tabuleiro[l][c] = linha[c];
            }
        }

        //Lembrar de quem é a jogada
        if (arq.recuperarJogada("meuArq.txt") == '1'){
            alienJoga = true;
        }else{
            alienJoga = false;
        }

        //Recuperar pontuação
        contAliens = 0;
        contEmas = 0;
        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                if (tabuleiro[l][c].equals("A")){
                    contAliens++;
                }else if (tabuleiro[l][c].equals("E")){
                    contEmas++;
                }
            }
        }
    }

    public boolean vitoria(){
        return contEmas < 3 || aliensImoveis();
        //return false;
    }

    public boolean aliensImoveis(){ //verifica se os aliens estão cercados
        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                if (tabuleiro[l][c].equals("A")) {//ate aqui ta certo
                    if (l == 0 || tabuleiro[l-1][c].equals("A") || tabuleiro[l-1][c].equals("E")){
                        if (l == 4 || tabuleiro[l+1][c].equals("A") || tabuleiro[l+1][c].equals("E")){
                            if (c == 0 ||tabuleiro[l][c-1].equals("A") || tabuleiro[l][c-1].equals("E")){
                                if (c == 3 ||tabuleiro[l][c+1].equals("A") || tabuleiro[l][c+1].equals("E")){
                                    contAliens--;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (contAliens !=0 ){
            contAliens = 2;
        }
        return contAliens == 0; //SE contAliens=0, retorna true (alien perdeu). Se não, retorna false.
    }

    public void jogadaAlien(int lAtual, int cAtual, int lNova, int cNova){


    }

    public void contGame() {
        int lAtual;
        int cAtual;
        int lNova;
        int cNova;
        while (!vitoria()){
            if (alienJoga) {
                System.out.println("Aliens jogam. Qual peça deseja mover? informe a linha e a coluna: ");
                lAtual = entrada.nextInt();
                cAtual = entrada.nextInt();
                if (lAtual != -1 && cAtual != -1){
                    if (tabuleiro[lAtual][cAtual].equals("A")) { //Selecionou sua própria peça
                        System.out.println("Agora, informe a posição para a qual deseja ir: ");
                        lNova = entrada.nextInt();
                        cNova = entrada.nextInt();
                        //jogadaAlien(lAtual, cAtual, lNova, cNova);
                        if (lNova != lAtual && cNova != cAtual) { //só pode mover a linha ou a coluna, nao os dois
                            System.out.println("Jogada inválida. Selecione outra posição!");
                        } else {
                            if (lNova != lAtual) { //tentou mudar de linha
                                if (lNova == lAtual + 1 || lNova == lAtual - 1) {
                                    if (tabuleiro[lNova][cAtual].equals(".")){//Moveu para um vazio
                                        tabuleiro[lNova][cNova] = "A";
                                        tabuleiro[lAtual][cAtual] = ".";
                                        alienJoga = false;
                                        imprimirMatriz();
                                    }else{
                                        System.out.println("Jogada inválida. Selecione outra posição!");
                                    }
                                }else if(lNova == lAtual + 2 && tabuleiro[lAtual+1][cAtual].equals(".") && tabuleiro[lNova][cAtual].equals("E")){//tentou comer
                                    tabuleiro[lNova][cNova] = "A";
                                    tabuleiro[lAtual][cAtual] = ".";
                                    alienJoga = false;
                                    contEmas--;
                                    imprimirMatriz();
                                }else if(lNova == lAtual - 2 && tabuleiro[lAtual-1][cAtual].equals(".") && tabuleiro[lNova][cAtual].equals("E")){//tentou comer
                                    tabuleiro[lNova][cNova] = "A";
                                    tabuleiro[lAtual][cAtual] = ".";
                                    alienJoga = false;
                                    contEmas--;
                                    imprimirMatriz();
                                }else{
                                    imprimirMatriz();
                                    System.out.println("Jogada inválida. Selecione outra posição!");
                                }
                            } else if (cNova != cAtual) { //tentou se mover para os lados
                                if (cNova == cAtual + 1 || cNova == cAtual - 1) {
                                    if (tabuleiro[lAtual][cNova].equals(".")) {
                                        tabuleiro[lNova][cNova] = "A";
                                        tabuleiro[lAtual][cAtual] = ".";
                                        alienJoga = false;
                                        imprimirMatriz();
                                    }else{
                                        System.out.println("Jogada inválida. Selecione outra posição!");
                                    }
                                }else if(cNova == cAtual + 2 && tabuleiro[lAtual][cAtual+1].equals(".") && tabuleiro[lAtual][cNova].equals("E")){
                                    tabuleiro[lNova][cNova] = "A";
                                    tabuleiro[lAtual][cAtual] = ".";
                                    alienJoga = false;
                                    contEmas--;
                                    imprimirMatriz();
                                }else if(cNova == cAtual - 2 && tabuleiro[lAtual][cAtual-1].equals(".") && tabuleiro[lAtual][cNova].equals("E")){
                                    tabuleiro[lNova][cNova] = "A";
                                    tabuleiro[lAtual][cAtual] = ".";
                                    alienJoga = false;
                                    contEmas--;
                                    imprimirMatriz();
                                }else{
                                    imprimirMatriz();
                                    System.out.println("Jogada inválida. Selecione outra posição!");
                                }
                            }
                        }
                    } else {
                        System.out.println("Essa posição não contém um alienígina! Tente Novamente");
                    }
                }else{
                    salvarTabuleiro();
                    break;
                }

            } else { //Jogada das Emas
                System.out.println("Emas jogam. Qual peça deseja mover? informe a linha e a coluna: ");
                lAtual = entrada.nextInt();
                cAtual = entrada.nextInt();
                if (lAtual != -1 && cAtual != -1){
                    if (tabuleiro[lAtual][cAtual].equals("E")) { //Selecionou uma posição válida
                        System.out.println("Agora, informe a posição para a qual deseja ir: ");
                        lNova = entrada.nextInt();
                        cNova = entrada.nextInt();
                        if (lNova != lAtual && cNova != cAtual) { //só pode mover a linha ou a coluna, nao os dois
                            System.out.println("Jogada inválida. Selecione outra posição!");
                        } else {
                            if (lNova != lAtual) { //tentou mudar de linha
                                if (lNova == lAtual + 1 || lNova == lAtual - 1) {
                                    if (tabuleiro[lNova][cAtual].equals(".")){
                                        tabuleiro[lNova][cNova] = "E";
                                        tabuleiro[lAtual][cAtual] = ".";
                                        alienJoga = true;
                                        imprimirMatriz();
                                    }else{
                                        System.out.println("Jogada inválida. Selecione outra posição!");
                                    }
                                }else{
                                    imprimirMatriz();
                                    System.out.println("Jogada inválida. Selecione outra posição!");
                                }
                            } else if (cNova != cAtual) {
                                if (cNova == cAtual + 1 || cNova == cAtual - 1) {
                                    if (tabuleiro[lAtual][cNova].equals(".")){
                                        tabuleiro[lNova][cNova] = "E";
                                        tabuleiro[lAtual][cAtual] = ".";
                                        alienJoga = true;
                                        imprimirMatriz();
                                    }else{
                                        System.out.println("Jogada inválida. Selecione outra posição!");
                                    }
                                }else{
                                    imprimirMatriz();
                                    System.out.println("Jogada inválida. Selecione outra posição!");
                                }
                            }
                        }
                    } else {
                        System.out.println("Essa posição não contém uma ema! Tente Novamente");
                    }
                }else{
                    salvarTabuleiro();
                    break;
                }

            }
        }

    }

    public void initVariables() {
        contEmas = 6;
        contAliens = 2;
        contVazios = 10;
        alienJoga = true;
    }

    public void matrizInicial() {
        contVazios = 0;
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                if (i < 2) {
                    tabuleiro[i][j] = "E";
                } else if (i < 4) {
                    tabuleiro[i][j] = ".";
                } else {
                    if (contVazios == 0) {
                        tabuleiro[i][j] = ".";
                        contVazios++;
                    } else {
                        if (j == 3) {
                            tabuleiro[i][j] = ".";
                        } else {
                            tabuleiro[i][j] = "A";
                        }
                    }
                }
            }
        }
    }

    public void imprimirMatriz() {
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

}
