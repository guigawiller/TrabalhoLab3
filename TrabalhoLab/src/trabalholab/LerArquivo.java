package trabalholab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;

/**
 *
 * @author Guilherme Willer
 */
public class LerArquivo {

    public void criarTabelaHash() throws FileNotFoundException, IOException {

        FileReader arquivo = new FileReader("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\movie_metadata.csv");
        FileWriter arq = new FileWriter("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt");

        BufferedReader lerarquivo = new BufferedReader(arquivo);
        String linha, palavra;
        ArrayList plotKeywords, plotKeywordsEspaco;

        criarTabelaNula(arq);
        arq.close();

        linha = lerarquivo.readLine();
        linha = lerarquivo.readLine();

        int cont = 0;
        while (linha != null) {
//        for (int y = 0; y < 300; y++) {

            plotKeywords = new ArrayList(Arrays.asList(linha.split("\\s*;\\s*")));
            palavra = (String) plotKeywords.get(16);
            plotKeywordsEspaco = new ArrayList(Arrays.asList(palavra.split("\\|")));

            for (int i = 0; i < plotKeywordsEspaco.size(); i++) {

                if (plotKeywordsEspaco.get(i).toString().indexOf(" ") == -1) {
                    if (isNotStopWord(plotKeywordsEspaco.get(i).toString())) {
                        imprimir(plotKeywordsEspaco.get(i).toString(), cont);
                    }

                } else {

                    ArrayList palavras = new ArrayList(Arrays.asList(plotKeywordsEspaco.get(i).toString().split("\\ ")));
                    for (int j = 0; j < palavras.size(); j++) {
                        if (isNotStopWord(palavras.get(j).toString())) {
                            imprimir(palavras.get(j).toString(), cont);
                        }
                    }
                }
            }
            cont++;
            linha = lerarquivo.readLine();

        }

    }

    public void imprimir(String palavra, int num) throws IOException {
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");
        RandomAccessFile file2 = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");

        file.seek(calcularHash(palavra) * 2502);

        String resposta = calcularHash(palavra) + "," + palavra + "," + num;
        String respostaComparativa = calcularHash(palavra) + "," + palavra;
        String linhaLida = file.readLine();

        file2.seek((calcularHash(palavra) + 1) * 2502);

        String linha2 = file2.readLine();

        if (linha2.startsWith(respostaComparativa)) {
            if (linha2.trim().endsWith(num + "")) {
                resposta = linha2;
            } else {

                resposta = linha2.trim();
                resposta += "," + num;
            }
        } else if (!linha2.trim().equals("")) {
            while (!linha2.trim().equals("")) {
                if (linha2.startsWith(respostaComparativa)) {
                    if (linha2.trim().endsWith(num + "")) {
                        resposta = linha2;
                    } else {

                        resposta = linha2.trim();
                        resposta += "," + num;
                    }
                    System.out.println(palavra);
                    break;
                }
                linha2 = file2.readLine();
            }
        }

        while (resposta.length() < 2500) {
            resposta += " ";
        }

        file2.close();
        file.write(resposta.getBytes());
        file.close();

    }

    public int calcularHash(String palavra) {
        int i = palavra.hashCode();
        if (i < 0) {
            i *= -1;
        }
        i %= 12345;
        return i;
    }

    public void criarTabelaNula(FileWriter arq) {
        PrintWriter gravarArq = new PrintWriter(arq);
        for (int i = 0; i < 12345; i++) {
            for (int j = 0; j < 2500; j++) {
                gravarArq.printf(" ");
            }
            gravarArq.printf("%n");
        }

    }

    public Boolean isNotStopWord(String word) {
        String[] stopWords = {"title", "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};
        for (int i = 0; i < stopWords.length; i++) {
            if (word.equals(stopWords[i])) {
                return false;
            }
        }

        return true;

    }

    public ArrayList acharPalavra(String texto) throws FileNotFoundException, IOException {

        RandomAccessFile file = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");

        ArrayList<ArrayList<List>> arrays = new ArrayList<ArrayList<List>>();
        ArrayList resultado = new ArrayList();
        ArrayList plotKeywordsEspaco = new ArrayList(Arrays.asList(texto.split("\\s* \\s*")));

        for (int i = 0; i < plotKeywordsEspaco.size(); i++) {
            ArrayList<List> numeros = new ArrayList();
            if (isNotStopWord(plotKeywordsEspaco.get(i).toString())) {
                file.seek(calcularHash(plotKeywordsEspaco.get(i).toString()) * 2502);
                String linha = file.readLine();
                linha = file.readLine();
                linha = linha.trim();
                numeros.add(Arrays.asList(linha.split("\\s*,\\s*")));
                arrays.add(numeros);
            }

        }

        if (arrays.size() == 1) {
            for (int i = 2; i < arrays.get(0).get(0).size(); i++) {
                resultado.add(arrays.get(0).get(0).get(i));
            }
        } else {

            ArrayList comparacao = new ArrayList();
            for (int i = 0; i < arrays.size() - 1; i++) {

                for (int j = 2; j < arrays.get(i).get(0).size(); j++) {
                    int count = 0;
                    for (int k = 0; k < arrays.size(); k++) {
                        if (arrays.get(k).get(0).contains(arrays.get(i).get(0).get(j))) {
//                        System.out.println(arrays.get(k).get(0));
                            count++;
                        }

                    }

                    if (count == arrays.size()) {
                        if (!resultado.contains(arrays.get(i).get(0).get(j))) {
                            resultado.add(arrays.get(i).get(0).get(j));
                        }
                    }
                }
            }
        }
        return resultado;
    }

}
