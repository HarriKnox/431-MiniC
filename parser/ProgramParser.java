package parser;


import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.tree.ParseTree;

import ast.ProgramAST;

import common.Options;

import parser.MiniLexer;
import parser.MiniParser;
import parser.visitor.ProgramVisitor;


public class ProgramParser
{
   public static ProgramAST parseProgram(Options opts)
   {
      CharStream charStream = null;
      
      try
      {
         if (opts.miniFile == null)
            charStream = CharStreams.fromStream(System.in);
         
         else
            charStream = CharStreams.fromFileName(
                  opts.miniFile.getAbsolutePath());
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.exit(2);
      }
      
      
      if (charStream == null)
      {
         System.err.println("Some error occurred and I don't know why.");
         System.exit(3);
      }
      
      
      MiniParser parser = new MiniParser(
            new CommonTokenStream(
                  new MiniLexer(charStream)));
      
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
}
