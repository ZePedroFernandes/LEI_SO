
import java.text.*;


public class Calculator {

    public static void main(String[] args) {
        System.out.println("Insira um número: ");
        String number1 = System.console().readLine();
        
        System.out.println("Insira outro número: ");
        String number2 = System.console().readLine();
        
        try{
            double num1 = Double.parseDouble(number1);
            double num2 = Double.parseDouble(number2);
            
            NumberFormat formatter = new DecimalFormat("#0.00");

            System.out.println("Sum: " + (num1 + num2));
            System.out.println("Sub: " + (num1 - num2));
            System.out.println("Div: " + (formatter.format(num1 / num2)));
            System.out.println("Mul: " + (num1 * num2));
            
        }catch(NumberFormatException ex){
            System.out.println("Um dos inputs não é um número");
        }
    }
}
