
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class printFile {

    public static void main(String[] args) {
        boolean validExtension = args[0].contains(".txt");
        if (!validExtension){
            System.out.println("ERRO: O ficheiro " + args[0] +" nao existe!!!");
        }
        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get(args[0]);
            List<String> linhas = Files.readAllLines(path, ENCODING);
            for (int i = 0; i < linhas.size(); i++) {
                System.out.println("Linha " + i + ": " + linhas.get(i));
            }

        } catch (IOException ex) {
            System.out.println("Coloque um argumento válido\n"
                    + "É considerado válido todo o argumento que represente o path para um ficheiro txt\n"
                    + "Ex: \"numbers.txt\" (este ficheiro tem que existir)");
        }

    }
}
