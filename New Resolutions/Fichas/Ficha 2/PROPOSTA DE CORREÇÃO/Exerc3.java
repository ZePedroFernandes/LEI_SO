
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Exerc3 {

    private static List<String> readFile(String filename) {
        List<String> lines = null;

        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get(filename);
            lines = Files.readAllLines(path, ENCODING);
        } catch (IOException ex) {
            System.out.println("Erro no acesso ao ficheiro...");
            System.exit(0);
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Modo de uso: java Exerc3 <ficheiro com comandos>");
            System.exit(0);
        }

        List<String> lines = readFile("fich.txt");

        for (int i = 0; i < lines.size(); i++) {
            // lines.get(i) contÃ©m o comando a executar
            ProcessBuilder pb = new ProcessBuilder(lines.get(i));
            Process process = pb.start();	// public Process start() throws IOException
            System.out.println("Início do comando \"" + lines.get(i) + "\".");

            // obter acesso ao output do processo
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // ler o output do processo
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

            try {
                process.waitFor();
                System.out.println("Fim do comando \"" + args[0] + "\".");
            } catch (InterruptedException ex) {
            }
        }
    }

}
