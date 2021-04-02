
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;

public class ReadFile {

    public static void main(String[] args) {

        try {
            if (args.length < 1) {
                System.out.println("Introduce a file to read from as argument");
            } else {
                Charset ENCODING = StandardCharsets.UTF_8;
                Path path = Paths.get(args[0]);
                Scanner file = new Scanner(path, ENCODING);

                while (file.hasNextLine()) {
                    System.out.println(file.nextLine());
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
