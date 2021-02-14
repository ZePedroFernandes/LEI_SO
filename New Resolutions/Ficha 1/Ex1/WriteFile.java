
import java.io.*;

public class WriteFile {

    public static void main(String[] args) {

        int lineNumber = 10;
        if (args.length > 0) {
            try {
                lineNumber = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
            }
        }

        try {
            try ( FileWriter fileWriter = new FileWriter("resultFile.txt")) {
                for (int i = 0; i < lineNumber; i++) {
                    fileWriter.write("Line: " + i + "\n");
                }
            }

        } catch (IOException e) {

        }
    }
}
