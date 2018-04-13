package main;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import analyzer.TypeChecker;

import ast.Program;
import ast.TypeDeclaration;
import ast.Declaration;
import ast.Function;

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
            input = CharStreams.fromFileName(opts.filename);
      }
      catch (IOException e)
      {
         System.err.println(e.getMessage());
         System.exit(1);
      }
      
      
      if (input == null)
      {
         System.err.println("Some error occurred and I don't know why.");
         System.exit(1);
      }
      
      
      return input;
   }
   
   
   public static void main(String[] args)
   {
      Options opts = Options.parseOptions(args);
      
      Program program = parseProgram(opts);
      
      if (!TypeChecker.staticTypeCheck(program, opts))
      {
         System.err.println("Detected errors. Fix them and recompile.");
         System.exit(1);
      }
      
      System.exit(0);
   }
}
