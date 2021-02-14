
import java.io.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;

public class FileShell {

    private static List<String> readFile(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get(fileName);
            result = Files.readAllLines(path, ENCODING);

        } catch (IOException e) {
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            System.err.println("Modo de uso: java FileShell");
            return;
        }

        List<String> comands = readFile("comands.txt");

        Process[] processes = new Process[comands.size()];

        for (int i = 0; i < processes.length; i++) {
            ProcessBuilder pb = new ProcessBuilder(comands.get(i));
            pb.inheritIO();
            processes[i] = pb.start();
            System.out.println("Inicio do comando " + comands.get(i));
        }

        for (int i = 0; i < processes.length; i++) {
            try {
                processes[i].waitFor();
                System.out.println("Fim do comando " + comands.get(i));
            } catch (InterruptedException e) {
            }
        }

    }
}
