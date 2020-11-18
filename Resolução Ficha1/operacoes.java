import java.util.Scanner;

public class operacoes {

    public static void main(String[] args) {
            Scanner file = new Scanner(System.in);
            System.out.println("Digite 2 valores:");
            
            float number1 = file.nextFloat();
            float number2 = file.nextFloat();
            
            System.out.println("addition: " + (number1 + number2));
            System.out.println("subtraction: " + (number1 - number2));
            System.out.println("multiplication: " + (number1 * number2));
            System.out.println("division: " + (number1 / number2));

    }
}
