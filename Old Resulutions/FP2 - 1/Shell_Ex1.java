import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Shell_Ex1 {

    public static void main(String args[]) throws IOException {
        if (args.length < 1) {
            System.err.println("Modo de uso: java Shell [Argumento1] [Argumento2] [Argumento3]...");
            System.exit(0);
        }
        
        List<String> comands = Arrays.asList(args);
        
        //Executar o comando
        ProcessBuilder pb = new ProcessBuilder(comands);
        Process process = pb.start();
        System.out.println("Inicio do comando \"" + Arrays.toString(args) + "\".");
        
        //Obter Output
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
        

        try {
            process.waitFor();
            System.out.println("Fim do comando \"" + args[0] + "\".");
        } catch (InterruptedException e) {
        }

    }
}
