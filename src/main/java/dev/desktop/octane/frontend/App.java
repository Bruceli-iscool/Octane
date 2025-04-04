package dev.desktop.octane.frontend;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import dev.desktop.octane.core.Octane;
public class App {
    /* Octane Frontend*/
    public static void main(String[] args) throws FileNotFoundException {
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
        Octane oct = new Octane(program.toString());
        oct.lex();
    }
}
