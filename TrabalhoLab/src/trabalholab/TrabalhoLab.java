package trabalholab;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Guilherme Willer
 */
public class TrabalhoLab {

    public static void main(String[] args) throws IOException {
//        RandomAccessFile file = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");
//        
//        LerArquivo testeas = new LerArquivo();
//        System.out.println(testeas.pesquisa("slaughter"));
//        
//        System.out.println(testeas.calcularHash("love"));
//        System.out.println(testeas.calcularHash("slaughter"));
//        file.seek(118004 * 52);
//        String teste = file.readLine();
////        System.out.println(teste.startsWith("38,pursuit"));
////        System.out.println(teste.trim() + "," + 12);
//        System.out.println(teste);
//
//        System.out.println(file.readLine());
//        file.close();

        LerArquivo teste = new LerArquivo();
//        System.out.println(teste.calcularHash("love"));
//        System.out.println(teste.calcularHash("slaughter"));
        System.out.println(teste.acharPalavra("love avatar"));
//        teste.criarTabelaHash();
    }

}
