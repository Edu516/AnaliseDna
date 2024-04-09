package dna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<AnalisaDNA> analisadores = new ArrayList<>();

        ExecutorService executor = Executors.newCachedThreadPool();

        String[] arquivos = {
            "arquivosDNA/dna-0",
            "arquivosDNA/dna-1",
            "arquivosDNA/dna-2",
            "arquivosDNA/dna-3",
            "arquivosDNA/dna-4",
            "arquivosDNA/dna-5",
            "arquivosDNA/dna-6",
            "arquivosDNA/dna-7",
            "arquivosDNA/dna-8",
            "arquivosDNA/dna-9"
        };

        for (String arquivo : arquivos) {
            AnalisaDNA dna = new AnalisaDNA(arquivo);
            analisadores.add(dna);
            executor.execute(dna);
        }

        executor.shutdown();

    }
}
