package com.madaramebak.lox;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Lox {
    static boolean hadError = false;
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      System.exit(64); 
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

    //Executes given file when given path
  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
    if (hadError){
        System.exit(65);
    }
  }


    //Prompt user to enter code that will be executed one line at a time
  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) { 
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null){
        break;
      }
      run(line);
      hadError = false;
    }
  }

// For now, just print the tokens.
  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    
    for (Token token : tokens) {
      System.out.println(token);
    }
  }

    // Reports error at specified line
  static void error(int line, String message) {
    report(line, "", message);
  }

// Prints an error message
  private static void report(int line, String where,  String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }





}

