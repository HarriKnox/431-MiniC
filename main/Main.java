package main;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.LinkedList;
import java.util.List;

import analyzer.TypeChecker;

import ast.Program;
import ast.TypeDeclaration;
import ast.Declaration;
import ast.Function;

import cfg.ControlFlowGraph;

import parser.MiniLexer;
import parser.MiniParser;
import parser.MiniToAstProgramVisitor;


public class Main
{
   private static Program parseProgram(Options opts)
   {
      MiniParser parser = new MiniParser(new CommonTokenStream(new MiniLexer(getCharStream(opts))));
      ParseTree tree = parser.program();
      
      
      int syntaxErrors = parser.getNumberOfSyntaxErrors();

      if (syntaxErrors != 0)
      {
         System.err.println("Found " + syntaxErrors + " syntax errors. Fix " + 
            "them and try compiling again.");
         
         System.exit(1);
      }
      
      
      return new MiniToAstProgramVisitor().visit(tree);
   }
   
   
   private static CharStream getCharStream(Options opts)
   {
      CharStream input = null;
      
      try
      {
         if (opts.filename == null)
            input = CharStreams.fromStream(System.in);
         
         else
            input = CharStreams.fromFileName(opts.filename + ".mini");
      }
      catch (IOException e)
      {
         System.err.println(e.getMessage());
         System.exit(2);
      }
      
      
      if (input == null)
      {
         System.err.println("Some error occurred and I don't know why.");
         System.exit(3);
      }
      
      
      return input;
   }
   
   
   private static void writeToFile(String fname, ControlFlowGraph program)
   {
      PrintStream stdout = System.out;
      try (PrintStream output = new PrintStream(new BufferedOutputStream(new FileOutputStream(fname + ".ll"))))
      {
         System.setOut(output);
         program.printProgram();
         output.close();
      }
      catch (Exception e)
      {
         System.err.println(e);
      }
      
      System.setOut(stdout);
   }
   
   
   private static void runClang(String fname, boolean m32)
   {
      List<String> command = new LinkedList<>();
      
      command.add("clang");
      command.add(fname + ".ll");
      command.add("-o");
      command.add(fname);
      
      if (m32)
         command.add("-m32");
      
      try
      {
         Process clang = new ProcessBuilder(command).inheritIO().start();
         
         int status = clang.waitFor();
         
         if (status != 0)
         {
            System.exit(status);
         }
      }
      catch (Exception e)
      {
         System.err.println(e);
         System.exit(4);
      }
   }
   
   
   public static void main(String[] args)
   {
      Options opts = Options.parseOptions(args);
      
      Program program = parseProgram(opts);
      
      if (!TypeChecker.staticTypeCheck(program, opts))
      {
         System.err.println("Detected errors. Fix them and recompile.");
         System.exit(5);
      }
      
      ControlFlowGraph programCFG = ControlFlowGraph.buildProgramCFG(program);
      
      writeToFile(opts.filename, programCFG);
      
      runClang(opts.filename, opts.m32);
      
      System.exit(0);
   }
}
