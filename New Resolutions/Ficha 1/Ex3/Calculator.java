
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Calculator {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Insira um argumento");
            return;
        }

        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader(args[0]));

            double num1 = Double.parseDouble(bf.readLine());
            double num2 = Double.parseDouble(bf.readLine());

            NumberFormat formatter = new DecimalFormat("#0.00");

            System.out.println("Sum: " + formatter.format(num1 + num2));
            System.out.println("Sub: " + formatter.format(num1 - num2));
            System.out.println("Div: " + formatter.format(num1 / num2));
            System.out.println("Mul: " + formatter.format(num1 * num2));

        } catch (InputMismatchException | NumberFormatException exception) {
            System.out.println("File with inapropriate representation of numbers");
        } catch (IOException ex) {
            System.out.println("Can't open file");
        } catch (NoSuchElementException ex) {
            System.out.println("The file does not have two numbers in it");
        }

    }
}
