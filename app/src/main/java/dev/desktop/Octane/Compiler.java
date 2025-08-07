package dev.desktop.Octane;

import java.util.*;

public class Compiler {
    int stack;
    final ArrayList<String> t;
    private boolean stringImported = false;
    private String f;
    private String genJava = ""; 
    private List<String> constVars = new ArrayList<>();
    public Compiler(ArrayList<String> tokens, String filename) {
        t = tokens;
        f = filename;
    }
    private String remove() {
        if (t.isEmpty()) {
            error("Expected a value but recieved none!");
        }
        return t.remove(0);
    }
    public void generate() {
        Boolean program = false;
        while (!t.isEmpty()) {
            String current = remove();
            if (!program) {
                if (current.matches("import")) {
                    current = remove();
                    if (current.matches("String") && !stringImported) {
                        current = remove();
                        if (current.matches(";")) {
                            stringImported = true;
                        } else {
                            error("expected ';' but instead recieved: " + current + "!");
                        }
                    } else {
                        error(current + " is not a valid import!");
                    }
                } else if (current.matches("program")) {
                    current = remove();
                    genJava += "public class " + f.replace(".oct", "") + " { public static void main(String args[]) {";
                    if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                        if (!current.matches(f.replace(".oct", ""))) {
                            error("Expected " + f.replace(".oct", "") + " but recieved " + current + "!");
                        }
                        current = remove();
                        if (current.matches("\\{")) {
                            program = true;
                            stack += 1;
                        } else {
                            error("expected '{' but instead recieved: " + current + "!");
                        }
                    } else {
                        error(current + " is not a valid statement!");
                    }
                }
            } else {
                if (current.matches("const")) {
                    current = remove();
                    if (current.matches(":")) {
                        current = remove();
                        String type = returnType(current);
                        if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                            String varName= current;
                            current = remove();
                            if (current.matches("=")) {
                                current = remove();
                                List<String> statementTokens = new ArrayList<>();
                                while (!current.equals(";")) {
                                    statementTokens.add(current);
                                    current = remove();
                                }
                                String genCode = processStatement(statementTokens);
                            } else if (current.matches(";")) {
                                genJava += "final " + type + " " + varName + ";";
                                constVars.add(varName);
                            }
                        }
                    } else {
                        error("Expected : but recieved " + current + "!");
                    }
                }
            }
        }
        genJava += "}";
        System.out.println(genJava);
    }
    private void error(String message) {
        System.err.println("oce: " + message);
        System.exit(0);
    }
    private String processStatement(List<String> in) {
        // tdod
        return "";
    }
    private String returnType(String in) {
        if (in.matches("String")&&stringImported) {
            return "String";
        } else if (in.matches("int")) {
            return "int";
        } else {
            error(in + " is not a valid type!");
            return "";
        }
    }
} 

