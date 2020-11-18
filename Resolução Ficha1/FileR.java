import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;

public class FileR{
    public static void main (String[] args){
        try{
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get("linhas.txt");
            List<String> linhas = Files.readAllLines(path, ENCODING);
            for(int i = 0;i < linhas.size();i++){
                System.err.println("Linha_n" + i + ":" + linhas.get(i));
            }
        }catch(IOException ex){
            System.out.println("Erro no acesso ao ficheiro!");
        }
    }
}