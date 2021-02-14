import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Shell_Ex3 {
    public static void main(String[] args) {
        try {
            if (args.length != 1) { 
                System.err.println("Insert one and only one argument");
                System.exit(0);
            }

            Scanner file = new Scanner(new File(args[0]));
            List<String> fileLines = new ArrayList<>();

            while (file.hasNextLine()) {
                fileLines.add(file.nextLine());
            }

            Process[] processesList = new Process[fileLines.size()];

            for (int i = 0; i < fileLines.size(); i++) {
                try {
                    ProcessBuilder pb = new ProcessBuilder(fileLines.get(i));
                    pb.inheritIO();
                    processesList[i] = pb.start();
                    processesList[i].waitFor();
                    System.out.println("Fim do comando \"" + processesList[i].toString() + "\".");
                } catch (IOException | InterruptedException e ) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
