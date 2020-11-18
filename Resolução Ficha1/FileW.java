import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;

public class FileW{
    public static void main(String[] args){
        try{
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get("linhas.txt");
            String linha = "Exemplo de linha";
            List<String> linhas = new ArrayList<>();
            for(int i = 0;i < 10;i++){
                linhas.add("" + i + ":" + linha);
                Files.write(path,linhas,ENCODING);
            }
        }catch(IOException ex){
            System.out.println("Erro no acesso ao ficheiro");
        }
    }
}