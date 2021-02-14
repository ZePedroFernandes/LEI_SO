
import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Printer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("The program needs one argument, this argument must be a file to be read");
            return;
        }

        try {
            //Resolução 1
            
            // Ler o ficheiro
//            Charset ENCODING = StandardCharsets.UTF_8;
//            Path path = Paths.get(args[0]);
//            List<String> list = Files.readAllLines(path, ENCODING);
//            Iterator<String> itr = Files.readAllLines(path, ENCODING).iterator();
//            
//            int lineNumber = 0;
//            while (itr.hasNext()) {
//                System.out.println("Line " + lineNumber + ": " + itr.next());
//                lineNumber++;
//            }
            
            
            // Resolução 2
            Path path = Paths.get(args[0]);
            Scanner file = new Scanner(path);
            int i = 0;
            
            while(file.hasNextLine()){
                System.out.println("Line " + i + ": " + file.nextLine());
                i++;
            }
            

        } catch (IOException exception) {
            System.out.println("ERRO: O ficheiro " + args[0] + " não existe!!!");
        }
    }
}
