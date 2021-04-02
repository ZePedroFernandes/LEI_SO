
import java.io.*;

public class Shell2 {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Modo de uso: java Shell <comando>");
            System.exit(0);
        }
        // args[0] comando a executar
        ProcessBuilder pb = new ProcessBuilder(args[0]);
        Process process = pb.start();
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
    }
}
