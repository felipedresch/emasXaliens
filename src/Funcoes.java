import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Funcoes extends JPanel implements ActionListener {
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean emJogo = false;
    private final int linhas = 5;
    private final int colunas = 4;
    private String tabuleiro [][] = new String[linhas][colunas];
    //Campo[][] tab = new Campo[5][4];
    char simboloAtual;
    private int jogada;  //se jogada for impar, vez dos aliens
    private int scoreEmas;
    private int scoreAliens;
    private int contEmas;
    private int contAliens;
    private int contVazios;


    public void initGame(){
        emJogo = true;
        scoreEmas = 0;
        scoreAliens = 0;
        contEmas = 6;
        contAliens = 2;
        contVazios = 10;
        jogada = 1;
    }

    public void tabuleiroInicio(Campo[][] simb){
        //limpartela()
        contAliens = 0;
        contEmas = 0;
        contVazios = 0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                simb[i][j] = new Campo();
                while (contEmas < colunas*2) {
                    simb[i][j].setSimbolo('E');
                    contEmas++;
                }
                while (contVazios <= colunas*2) {
                    simb[i][j].setSimbolo('.');
                    contVazios++;
                }
                while (contAliens < 2) {
                    simb[i][j].setSimbolo('A');
                    contAliens++;
                    if (contAliens == 2){
                        simb[i][j].setSimbolo('.');
                        contVazios++;
                    }
                }
            }
        }
        System.out.println("Completo");

        /*
        contAliens = 0;
        contEmas = 0;
        contVazios = 0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                while (contEmas < colunas*2) {
                    System.out.print("E ");
                    contEmas++;
                    if (contEmas == colunas || contEmas == colunas*2){
                        System.out.println();
                    }
                }
                while (contVazios <= colunas*2) {
                    System.out.print(". ");
                    contVazios++;
                    if (contVazios == colunas || contVazios == colunas*2){
                        System.out.println();
                    }
                }
                while (contAliens < 2) {
                    System.out.print("A ");
                    contAliens++;
                    if (contAliens == 2){
                        System.out.print(". ");
                        contVazios++;
                    }
                }
            }
        }
         */
    }

    public void imprimirTab(Campo[][] tab) {
        System.out.println("Lá vai");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(tab[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }

    }

    public void paintComponent(Graphics g){
        //File f = new File();
        //BufferedImage alien = ImageIO.read(new File("C:\\Users\\Felipe\\IdeaProjects\\Trabalho2\\src\\alien.png"));
        //Image alien = Toolkit.getDefaultToolkit().getImage("alien.png");

        super.paintComponents(g);
        int posX = 0;
        int posY = 0;

        JFrame f = new JFrame("Ex");
        f.setSize(415, 538);

        //g.setColor(Color.BLUE);  //cor das linhas dos quadrados
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                g.drawRect(posX,posY,100,100);
                //g.drawImage(alien, 10, 10, null);
                posX += 100;
            }
            posX = 0;
            posY += 100;
        }

    }

    public void actionPerformed(ActionEvent e){

    }




}
