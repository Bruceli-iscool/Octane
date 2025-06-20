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
                    genJava += "public class " +f.replace(".oct", "")+ " { public static void main(String args[]) {";
                    if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                        if (!current.matches(f.replace(".oct", ""))) {
                            error("Expected " + f.replace(".oct", "")+ " but recieved " + current+ "!");
                        } 
                        current = t.get(0);
                        t.remove(0);
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
                if (current.matches("const")){
                    current = t.get(0);
                    t.remove(0);
                    if (current.matches(":")) {
                        current = t.get(0);
                        t.remove(0);
                        String type = returnType(current);
                        if (current.matches("^[a-zA-Z_][a-zA-Z0-9_]*$")) {
                            String varName= current;
                            current = t.get(0);
                            t.remove(0);
                            if (current.matches("=")) {
                                current = t.get(0);
                                t.remove(0);
                                List<String> statementTokens = new ArrayList<>();
                                while (current!= ";") {
                                    statementTokens.add(current);
                                    current = t.get(0);
                                    t.remove(0);
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
        //test
        System.out.println(genJava);
    }
    private void error(String message) {
        System.err.println("oce: " + message);
        System.exit(0);
    }
    private String processStatement(List<String> in) {
        // todo
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

