
public class operacoesCmdInput {

    public static void main(String[] args) {
        
        float number1 = Float.parseFloat(args[0]);
        float number2 = Float.parseFloat(args[1]);
        
        System.out.println("addition: " + (number1 + number2));
        System.out.println("subtraction: " + (number1 - number2));
        System.out.println("multiplication: " + (number1 * number2));
        System.out.println("division: " + (number1 / number2));
    }
}
