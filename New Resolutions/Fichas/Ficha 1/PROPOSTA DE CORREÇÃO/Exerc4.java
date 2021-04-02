
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Exerc4 {

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            if (args.length <= 0) {
                throw new Exception("Utilização: java Exec4 FICHEIRO");
            }
            if (!(new File(args[0]).exists())) {
                throw new Exception("ERRO: O ficheiro \"" + args[0] + "\" não existe!!!");
            }

            int i = 1;
            String linha;
            br = new BufferedReader(new FileReader(args[0]));

            while ((linha = br.readLine()) != null) {
                System.out.println("linha " + i++ + ": " + linha);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            //ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
    }
}
