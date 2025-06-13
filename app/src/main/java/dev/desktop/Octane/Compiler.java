package dev.desktop.Octane;

import java.util.ArrayList;

public class Compiler {
    final ArrayList<String> t;
    private boolean stringImported = false;
    private String f;
    private String genJava = ""; 
    private boolean used = false;
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
                if (current.equals("import")) {
                    current = t.get(0);
                    t.remove(0);
                    if (current.equals("String") && !stringImported) {
                        current = t.get(0);
                        t.remove(0);
                        if (current.equals(";")) {
                            stringImported = true;
                        }
                    } else {
                        error(current + " is not a valid import!");
                    }
                } else if (current.equals("program")) {
                    current = t.get(0);
                    t.remove(0);
                    
                }
            }
        }
    }
    private void error(String message) {
        System.err.println("oce: " + message);
        System.exit(0);
    }
}
