import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Jogo extends JFrame{
    public Jogo(){
        add(new Funcoes());
    }
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Jogo jogo = new Jogo();
        Campo[][] tab = new Campo[5][4];


        Funcoes f = new Funcoes();
        f.tabuleiroInicio(tab);
        f.imprimirTab(tab);

        //jogo.setVisible(true);
        //f.setBackground(Color.blue);
        //jogo.setBackground(Color.CYAN);
        //jogo.setTitle("Jogo Emas X Aliens");
        //jogo.setSize(415, 538);
        //jogo.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //jogo.setLocationRelativeTo(null);






        /*
        Scanner entrada = new Scanner(System.in);
        Panel p = new Panel();
        p.setFocusable(true);
        p.requestFocus();

        JFrame f = new JFrame();
        f.add(p);
        f.setSize(415, 538);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


         */


    }
}
