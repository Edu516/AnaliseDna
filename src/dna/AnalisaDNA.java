package dna;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class AnalisaDNA implements Runnable {
    private int totalFitas;
    private int totalValidas;
    private int totalInvalidas;
    private List<Integer> fitasInvalidas;
    private String nomeArquivo;

    public AnalisaDNA(String nomeArquivo) {
        this.totalFitas = 0;
        this.totalValidas = 0;
        this.totalInvalidas = 0;
        this.fitasInvalidas = new ArrayList<>();
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void run() {
        try {
            Txt txt = new Txt();
            String[] linhas = txt.lerArquivo(nomeArquivo);
            List<String> novoArquivo = new ArrayList<>();
            for (String linha : linhas) {
                totalFitas++;
                if (sequenciaValida(linha)) {
                    totalValidas++;
                    novoArquivo.add(linha);
                } else {
                    totalInvalidas++;
                    fitasInvalidas.add(totalFitas);
                    novoArquivo.add("*** FITA INVALIDA - "+linha);
                }
            }
            gravarArquivoAnalisado(novoArquivo);
            System.out.println(toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void gravarArquivoAnalisado(List<String> novoArquivo) throws IOException{
        Txt txt = new Txt();
        String nome = nomeArquivo+"VERIFICADO";
        txt.gravar(nome, novoArquivo);
    }

//    private boolean sequenciaValida(String sequencia) {
//        int countA = 0, countT = 0, countC = 0, countG = 0;
//        for (char base : sequencia.toCharArray()) {
//            switch (base) {
//                case 'A':
//                    countA++;
//                    break;
//                case 'T':
//                    countT++;
//                    break;
//                case 'C':
//                    countC++;
//                    break;
//                case 'G':
//                    countG++;
//                    break;
//            }
//        }
//        return countA == countT && countC == countG;
//    }
    
    private boolean sequenciaValida(String sequencia) {
        String nucleotideosValidos = "ACGT";

        for (int i = 0; i < sequencia.length(); i++) {
            char nucleotideo = sequencia.charAt(i);
            if (nucleotideosValidos.indexOf(nucleotideo) == -1) {
                return false;
            }
        }
        
        return true;
    }

    public int getTotalFitas() {
        return totalFitas;
    }

    public int getTotalValidas() {
        return totalValidas;
    }

    public int getTotalInvalidas() {
        return totalInvalidas;
    }

    public List<Integer> getFitasInvalidas() {
        return fitasInvalidas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Analise do DNA : ").append(nomeArquivo);
        sb.append("\nTotalFitas = ").append(totalFitas);
        sb.append("\nTotalValidas = ").append(totalValidas);
        sb.append("\nTotalInvalidas = ").append(totalInvalidas);
        sb.append("\nFitasInvalidas = ").append(fitasInvalidas);
        sb.append("\n-------------------------------------------------");
        return sb.toString();
    }
    
    
}
