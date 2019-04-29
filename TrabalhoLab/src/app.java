package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class app {

    static RandomAccessFile indices;

    public static void main(String[] args) throws IOException{

       geraLista();


    }

    static public void geraLista() throws IOException {
        Scanner scanner_arq = new Scanner(new File("movie_metadata.csv"));
        scanner_arq.useDelimiter("\n");
        Scanner scanner_linha;

        indices = new RandomAccessFile("lista-categorias", "rws");

        String aux;
        String categorias[] = null;
int k = 0;
//        while (scanner_arq.hasNext()) {
        while (k<2) {
            scanner_linha = new Scanner(scanner_arq.next());
            scanner_linha.useDelimiter(",");

            //AS CATEGORIAS ESTÃO NA 17ª COLUNA
            for (int i = 0; i < 16; i++)
                scanner_linha.next();

            categorias = scanner_linha.next().split("\\|");

            for(int i=0; i<categorias.length; i++ ){
                insertSort(categorias[i]);
            }
            k++;
        }
    }

    static public void insertSort(String palavra) throws IOException, NullPointerException{

        indices = new RandomAccessFile("lista-categorias", "rws");
        long posicao = indices.getFilePointer();

        if(indices.length() == 0){
            indices.writeBytes(palavra);
            indices.writeBytes("\n");
            return;
        }


        for (String palavraArquivo = indices.readLine();
             palavraArquivo != null && comparaPalavras(palavraArquivo, palavra, 0);
             palavraArquivo = indices.readLine())
        {
            posicao = indices.getFilePointer();
        }


        indices.seek(posicao);
        indices.writeBytes(palavra);
        indices.writeBytes("\n");
    }

    /**
     * Compara 2 palavras, letra por letra, pela orde alfabética
     * @param primeira - primeira palavra
     * @param segunda - segunda palavra
     * @param index - 0
     * @return true se a primeira vier antes q a segunda
     * @throws IndexOutOfBoundsException
     */
    static public boolean comparaPalavras(String primeira, String segunda, int index) throws IndexOutOfBoundsException {

        System.out.println(primeira);
        //AMBAS PALAVRAS ACABARAM
        if(primeira.length() == index && segunda.length() == index){
            return false;
        }//A primeira ACABOU
        else if(primeira.length() == index && segunda.length() > index){
            return true;
        }//A segunda ACABOU
        else if(segunda.length() == index && primeira.length() > index){
            return false;
        }

        if(primeira.charAt(index) < segunda.charAt(index))
            return true;

        if(primeira.charAt(index) > segunda.charAt(index))
            return false;

//SE NÃO É MAIOR NEM MENOR É IGUAL, PORTANTO DEVE SER USADA A RECURSIVIDADE
            return comparaPalavras(primeira, segunda, index++);
    }
}
