import ast.ProgramAST;

import common.Options;

import llvm.ProgramLLVM;

import parser.ProgramParser;


public class Main
{
   public static void main(String[] args)
   {
      Options opts = Options.parseOptions(args);
      
      ProgramParser parser = new ProgramParser();
      
      ProgramAST ast = parser.parseProgram(opts);
      
      ProgramLLVM llvm = ast.buildLLVM(opts);
      
      llvm.writeLLVM(opts);
      
      System.exit(0);
   }
}
