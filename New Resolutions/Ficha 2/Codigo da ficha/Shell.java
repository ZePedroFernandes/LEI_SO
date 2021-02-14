
import java.io.*;

public class Shell {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Modo de uso: java Shell <comando>");
            System.exit(0);
        }
        // args[0] comando a executar
        ProcessBuilder pb = new ProcessBuilder(args[0]);
        Process process = pb.start();
        System.out.println("Inicio do comando \"" + args[0] + "\".");
        try {
            process.waitFor();
            System.out.println("Fim do comando \"" + args[0] + "\".");
        } catch (InterruptedException e) {
        }
    }
}
