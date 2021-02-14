import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class operacoesReadFile{
    public static void main (String[] args){
        try{
            Scanner file = new Scanner(new File(args[0]));
            float number1 = file.nextFloat();
            float number2 = file.nextFloat();
            
            System.out.println("addition: " + (number1 + number2));
            System.out.println("subtraction: " + (number1 - number2));
            System.out.println("multiplication: " + (number1 * number2));
            System.out.println("division: " + (number1 / number2));
            
        }catch(IOException ex){
            System.out.println("Erro no acesso ao ficheiro!");
        }
        
        
    }
}