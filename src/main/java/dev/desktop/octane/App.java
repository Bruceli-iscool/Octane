package dev.desktop.octane;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import dev.desktop.core.Octane;
public class App {
    /* Octane Frontend*/
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Octane v0.0.1 running on: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + "\nJava Ver: " + System.getProperty("java.version"));
        Scanner n = new Scanner(System.in);
        String filename = n.nextLine();
        n.close();
        Scanner read = new Scanner(new File(filename));
        String program = "";
        while(read.hasNextLine())
        {
            program += read.nextLine();
        }
        read.close();
        Octane oct = new Octane(program);
        oct.lex();
    }
}
