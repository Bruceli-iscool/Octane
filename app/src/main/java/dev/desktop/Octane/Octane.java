package dev.desktop.Octane;

import java.util.ArrayList;

/*
Core components for Octane
*/ 
public class Octane {
    final String p;
    private final String f; 
    protected ArrayList<String> tokens;
    public Octane(String program, String filename) {
        p = program;
        f = filename;
    }
    public void lex() {
        ArrayList<String> result = new ArrayList<String>();
        String z = "";
        boolean ifString = false;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            switch (c) {
                case '(': case ')': case ';': case '=': case '+': case '-': case '*': case '/':
                    if (!z.isEmpty()) {
                        result.add(z);
                        z = "";
                    }
                    result.add(String.valueOf(c));
                    break;
                case '"':
                    if (!z.isEmpty() && ifString) {
                        ifString = false;
                        result.add(z);
                        z = "";
                    } else {
                        ifString = true;
                    }
                    result.add("\"");
                    break;
                case ' ':
                    if (!z.isEmpty() && !ifString) {
                        result.add(z);
                        z = "";
                    } else if (ifString) {
                        z += c;
                    }
                    break;
                default:
                    z += c;
                    break;
            }
        }
        if (!z.isEmpty()) {
            result.add(z);
        }

        tokens = result;
    }
    public void compile() {
        Compiler c = new Compiler(tokens, f);
        c.generate();
    }
}
