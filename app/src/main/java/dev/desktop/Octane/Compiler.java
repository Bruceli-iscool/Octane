package dev.desktop.Octane;

import java.util.ArrayList;

public class Compiler {
    final ArrayList<String> t;
    private boolean stringImported = false;
    private String f;
    private String genC = ""; 
    private boolean used = false;
    public Compiler(ArrayList<String> tokens, String filename) {
        t = tokens;
        used = false;
        f = filename;
    }
    public void generate() {
        while (!t.isEmpty()) {
            String current = t.get(0);
            t.remove(0);
            if (current.matches("program") && !used) {
                current = t.get(0);
                t.remove(0);
                if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                    int stack = 0;
                    stack += 1;
                    if (f.matches(current)) {
                        // program generation.
                        genC += "int main(void){";
                        used = true;
                        String currentC  = "";
                        while (stack > 0) {
                            current = t.get(0);
                            t.remove(0);
                            if (current.matches("var")) {
                                current = t.get(0);
                                t.remove(0);
                                if (current.matches(":")) {
                                    // handle static typing.
                                    current = t.get(0);
                                    t.remove(0);
                                    if (current.matches("int")) {
                                        currentC += "int ";
                                    } else if (current.matches("boolean")) {
                                        currentC += "bool ";
                                    } else if (current.matches("String")&&stringImported) {
                                        // todo
                                    }
                                    current = t.get(0);
                                } else {
                                    // infer type

                                }
                            }
                        }
                        genC = genC + currentC + "}";
                        // write to file
                    } else {
                        System.out.println("Octane Compiler Error!: Expected " + f + " but revieved " + current + ".");
                    }
                } else {
                    System.out.println("Octane Compiler Error!: Expected an identifier but recieved " + current + ".");
                }
            } else if (current.matches("import")) {
                current = t.get(0);
                t.remove(0);
                if (current.matches("String")) {
                    current = t.get(0);
                    t.remove(0);
                    if (current.matches(";")) {
                        stringImported = true;
                    } else {
                        System.err.println("Octane Compiler Error!: Expected ';' but recieved "+current+".");
                    }
                }
            }
        }
    }
    public String returnGenCode(){
        // return genC with the standard library imported.
        return "<stdio.h>" + genC;
    }
}
