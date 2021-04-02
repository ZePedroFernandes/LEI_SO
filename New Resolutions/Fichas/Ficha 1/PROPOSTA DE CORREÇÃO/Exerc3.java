
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exerc3 {

    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            if (args.length <= 0) {
                throw new Exception("Utilização: java Exec3 FICHEIRO");
            }

            br = new BufferedReader(new FileReader(args[0]));

            // estou a assumir que os valores a ler estão na primeira e segunda linhas
            float oper1 = Float.parseFloat(br.readLine());
            float oper2 = Float.parseFloat(br.readLine());

            System.out.printf("%.1f + %.1f = %.1f\n", oper1, oper2, oper1 + oper2);
            System.out.printf("%.1f - %.1f = %.1f\n", oper1, oper2, oper1 - oper2);
            System.out.printf("%.1f / %.1f = %.1f\n", oper1, oper2, oper1 / oper2);
            System.out.printf("%.1f * %.1f = %.1f\n", oper1, oper2, oper1 * oper2);


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
