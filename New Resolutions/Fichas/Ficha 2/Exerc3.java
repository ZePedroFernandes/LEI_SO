
import java.io.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;

public class Exerc3 {

    private static List<String> readFile(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get(fileName);
            result = Files.readAllLines(path, ENCODING);

        } catch (IOException e) {
            System.out.println("Erro no acesso ao ficheiro");
            System.exit(1);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            System.err.println("Modo de uso: java FileShell");
            return;
        }

        List<String> comands = readFile("comands.txt");

        for (int i = 0; i < comands.size(); i++) {
            try {
                ProcessBuilder pb = new ProcessBuilder(comands.get(i));
                pb.inheritIO();
                Process process = pb.start();
                System.out.println("Inicio do comando " + comands.get(i));

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

                process.waitFor();
                System.out.println("Fim do comando " + comands.get(i));
            } catch (InterruptedException e) {
            }
        }

    }
}
