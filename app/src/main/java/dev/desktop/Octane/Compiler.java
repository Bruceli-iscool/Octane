package dev.desktop.Octane;

import java.util.ArrayList;

public class Compiler {
    final ArrayList<String> t;
    private boolean stringImported = false;
    private String f;
    private String genJava = ""; 
    private boolean used = false;
    private String programName;
    public Compiler(ArrayList<String> tokens, String filename) {
        t = tokens;
        used = false;
        f = filename;
    }
    public void generate() {
        Boolean program = false;
        while (!t.isEmpty()) {
            String current = t.get(0);
            t.remove(0);
            if (!program) {
                if (current.matches("import")) {
                    current = t.get(0);
                    t.remove(0);
                    if (current.matches("String") && !stringImported) {
                        current = t.get(0);
                        t.remove(0);
                        if (current.matches(";")) {
                            stringImported = true;
                        }
                    } else {
                        error(current + " is not a valid import!");
                    }
                } else if (current.matches("program")) {
                    current = t.get(0);
                    t.remove(0);
                    genJava += "public class out { public static void main(String args[]) {";
                    if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                        current = t.get(0);
                        t.remove(0);
                        if (current.matches("{")) {
                            
                        } else {
                            error("expected '{' but instead recieved: " + current + "!");
                        }
                    } else {
                        error(current + " is not a valid statement!");
                    }
                }
            }
        }
        genJava += "}}";
    }
    private void error(String message) {
        System.err.println("oce: " + message);
        System.exit(0);
    }
}
