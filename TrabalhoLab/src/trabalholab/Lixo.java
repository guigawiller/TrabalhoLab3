/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalholab;

/**
 *
 * @author guilh
 */
public class Lixo {
    
    //    public void imprimir(String palavra, FileWriter arq, int num, RandomAccessFile file) throws IOException {
//
//        PrintWriter gravarArq = new PrintWriter(arq);
//        String resposta = calcularHash(palavra) + "," + palavra + "," + num;
//        while (resposta.length() < 30) {
//            resposta += " ";
//        }
//        resposta += "%n";
//        gravarArq.printf(resposta);
//
//    }
//    public void imprimir(String palavra, int num) throws IOException {
//        RandomAccessFile file = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");
//        RandomAccessFile file2 = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");
//
//        file.seek(calcularHash(palavra) * 52);
//
//        String resposta = calcularHash(palavra) + "," + palavra + "," + num;
//        String respostaComparativa = calcularHash(palavra) + "," + palavra;
//        String linhaLida = file.readLine();
//
//        file2.seek((calcularHash(palavra) + 1) * 52);
//
//        String linha2 = file2.readLine();
//
//        if (linha2.startsWith(respostaComparativa)) {
//            if (linha2.trim().endsWith(num + "")) {
//                resposta = linha2;
//            } else {
//                resposta = linha2.trim();
//                resposta += "," + num;
//            }
//        } else if (!linha2.trim().equals("")) {
//            if (acharBuraco(palavra, num, 2) != 0) {
//                file2.seek((calcularHash(palavra) + acharBuraco(palavra, num, 2)) * 52);
//                linha2 = file2.readLine();
//                if (linha2.startsWith(respostaComparativa)) {
//                    if (linha2.trim().endsWith(num + "")) {
//                        resposta = linha2;
//                    } else {
//                        resposta = linha2.trim();
//                        resposta += "," + num;
//                    }
//                }
//            }
//
//        }
//
//        while (resposta.length() < 50) {
//            resposta += " ";
//        }
//
//        file2.close();
//        file.write(resposta.getBytes());
//        file.close();
//
//    }
//
//    public int acharBuraco(String palavra, int num, int count) throws FileNotFoundException, IOException {
//        RandomAccessFile file = new RandomAccessFile("C:\\Users\\guilh\\Documents\\NetBeansProjects\\TrabalhoLab\\src\\trabalholab\\indice.txt", "rw");
//
//        file.seek((calcularHash(palavra) + count) * 52);
//        String linha = file.readLine();
//        if (!linha.trim().equals("")) {
//            if (linha.startsWith(calcularHash(palavra) + "," + palavra)) {
//                file.close();
//                return count;
//            } else {
//                file.close();
//                count++;
//                acharBuraco(palavra, num, count);
//            }
//        } else {
//            file.close();
//            return count;
//        }
//        file.close();
//        return 0;
//    }
    
}
