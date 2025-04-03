package dev.desktop.octane;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Octane: Error! Usage: octane [filepath] [options]");
        } else {
            Scanner read = new Scanner(new File(args[0]));
        }
    }
}
