package dna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Txt {

    public String[] lerArquivo(String caminhoArquivo) throws IOException {
        ArrayList<String> linhasEncontradas = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo + ".txt"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhasEncontradas.add(linha);
            }
        }
        return linhasEncontradas.toArray(new String[0]);
    }

    public void gravar(String caminhoArquivo, List<String> conteudo) throws IOException {
        File arquivo = new File(caminhoArquivo + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (String string : conteudo) {
                bw.write(string);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
