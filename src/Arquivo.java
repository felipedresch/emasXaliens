import java.io.*;
import java.nio.charset.StandardCharsets;

public class Arquivo{
    public static void gravar(String conteudo, String nomeArquivo) throws IOException {
        File f = new File(nomeArquivo);
        FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8);
        BufferedWriter buffer = new BufferedWriter(fw);

        buffer.write(conteudo);
        buffer.close();
        fw.close();
    }

    public static String[] getLinhas(String nomeArquivo) throws Exception {
        // declaração das variáveis
        String[] linhas;
        String linha;
        String conteudo = "";
        int i = 0;

        //abre o arquivo para leitura
        File f = new File(nomeArquivo);
        FileReader reader = new FileReader(f, StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(reader);

        //lê a primeira linha do arquivo
        linha = buffer.readLine();
        linhas = new String[6];

        //lê as demais linhas do arquivo
        do{
            linha = buffer.readLine();
            if(linha != null)
                linhas[i++] = linha;
        }while(linha != null);

        //fecha o arquivo
        buffer.close();
        reader.close();
        return linhas;
    }

    public char recuperarJogada(String nomeArquivo) throws IOException {
        File f = new File(nomeArquivo);
        FileReader reader = new FileReader(f, StandardCharsets.UTF_8);
        char[] jogada = new char[1];
        char j = ' ';
        reader.read(jogada);
        for (char c : jogada){
            j = c;
        }
        return j;
    }
}
