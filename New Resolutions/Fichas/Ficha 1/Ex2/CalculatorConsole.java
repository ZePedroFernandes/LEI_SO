
import java.text.*;

public class CalculatorConsole {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("You need to insert two arguments");
        } else {

            try {
                double num1 = Double.parseDouble(args[0]);
                double num2 = Double.parseDouble(args[1]);

                NumberFormat formatter = new DecimalFormat("#0.00");

                System.out.println("Sum: " + (num1 + num2));
                System.out.println("Sub: " + (num1 - num2));
                System.out.println("Div: " + (formatter.format(num1 / num2)));
                System.out.println("Mul: " + (num1 * num2));

            } catch (NumberFormatException ex) {
                System.out.println("Um dos inputs não é um número");
            }
        }
    }
}
