package dev.desktop.octane.compiler;

import java.util.ArrayList;

public class Compiler {
    final ArrayList<String> t;
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
                        while (stack > 0) {
                            String currentC  = "";
                            current = t.get(0);
                            t.remove(0);
                            if (current.matches("var")) {
                                current = t.get(0);
                                t.remove(0);
                                if (current.matches(":")) {
                                    // handle static typing.
                                    current = t.get(0);
                                    t.remove(0);
                                    if (current.matches("uns")) {
                                        // handle unsigned integers
                                        currentC += "unsigned ";
                                        current = t.get(0);
                                        t.remove (0);
                                    }
                                    if (current.matches("int")) {
                                        currentC += "int ";
                                    } else if (current.matches("boolean")) {
                                        currentC += "bool ";
                                    } else if (current.matches("long")) {
                                        currentC += "long ";
                                    } else if (current.matches("short")) {
                                        currentC += "short ";
                                    } else if (current.matches("double")) {
                                        currentC += "double ";
                                    } else if (current.matches("float")) {
                                        currentC += "float";
                                    }
                                    current = t.get(0);
                                } else {
                                    // infer type
                                }
                            }
                        }
                    } else {
                        System.out.println("Octane Compiler Error!: Expected " + f + " but revieved " + current + ".");
                    }
                } else {
                    System.out.println("Octane Compiler Error!: Expected an identifier but recieved " + current + ".");
                }
            } else if (current.matches("import")) {
                // todo
            }
        }
    }
}
