package parser;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.tree.ParseTree;

import common.Options;

import parser.MiniLexer;
import parser.MiniParser;
import parser.visitor.ProgramVisitor;


public class ProgramParser
{
   public ProgramAST parseProgram(Options opts)
   {
      MiniParser parser = new MiniParser(
            new CommonTokenStream(
                  new MiniLexer(
                        this.getCharStream(opts))));
      
      ParseTree tree = parser.program();
      
      
      int syntaxErrors = parser.getNumberOfSyntaxErrors();
      
      if (syntaxErrors != 0)
      {
         System.err.println("Found " + syntaxErrors
               + " syntax errors. Fix them and try compiling again.");
         
         System.exit(1);
      }
      
      
      return new ProgramVisitor().visit(tree);
   }
   
   
   private CharStream getCharStream(Options opts)
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
         System.err.println(e);
         System.exit(2);
      }
      
      
      if (input == null)
      {
         System.err.println("Some error occurred and I don't know why.");
         System.exit(3);
      }
      
      
      return input;
   }
}
