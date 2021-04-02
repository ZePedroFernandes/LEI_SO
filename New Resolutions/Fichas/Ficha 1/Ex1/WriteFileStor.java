
import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class WriteFileStor {

    public static void main(String[] args) {
        try {
            int nLines = 10;
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get("resultFile.txt");
            List<String> file = new ArrayList<>(nLines);

            for (int i = 0; i < nLines; i++) {
                file.add("Line " + i);
            }

            Files.write(path, file, ENCODING);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
