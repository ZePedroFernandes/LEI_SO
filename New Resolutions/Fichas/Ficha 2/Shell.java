
import java.io.*;
import java.util.*;

public class Shell {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Modo de uso: java Exerc1 <comando> [argumento 1] [argumento 2] ... [argumento N]");
            return;
        }

        List<String> comand = Arrays.asList(args);

        ProcessBuilder pb = new ProcessBuilder(comand);
        Process process = pb.start();
        System.out.println("Inicio do comando " + String.join(" ", args) + "\".");

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
            System.out.println("Fim do comando \"" + String.join(" ", args) + "\"."); // Arrays.toString( args ) + "\"." );
        } catch (InterruptedException iex) {
        }

    }
}
