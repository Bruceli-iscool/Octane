package dev.desktop.Octane;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class App {
    /* Octane Frontend*/
    public static void main(String[] args) throws FileNotFoundException {
        Octane octTest = new Octane("import String;program f {print(\"Hello , World\")}", "f.oct");
        octTest.lex();
        octTest.compile();
        System.out.println("Octane v0.0.1 running on: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + "\nJava Ver: " + System.getProperty("java.version"));
        Scanner n = new Scanner(System.in);
        String filename = n.nextLine();
        n.close();
        Scanner read = new Scanner(new File(filename));
        StringBuilder program = new StringBuilder();
        while(read.hasNextLine())
        {
            program.append(read.nextLine());
        }
        read.close();
        Octane oct = new Octane(program.toString(), filename);
        oct.lex();
        oct.compile();
    }
}
