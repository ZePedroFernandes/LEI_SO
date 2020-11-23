import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class numberOfLines{
    public static void main (String[] args){
        if(args[0] == null){
            System.out.println("Deve de introduzir um nome de um ficheiro como argumento!");
            return;
        }
        
        try{
            Scanner file = new Scanner(new File(args[0]));
            int count = 0;
            
            while(file.hasNextLine()){
                count++;
                file.nextLine();
            }
            System.out.println(count);
            
        }catch(IOException ex){
            System.out.println("ERRO: O ficheiro " + args[0] + "não existe!");
        }
        
        
    }
}