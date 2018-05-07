import ast.ProgramAST;

import common.Options;

import llvm.ProgramLLVM;

import parser.ProgramParser;


public class Main
{
   public static void main(String[] args)
   {
      Options opts = Options.parseOptions(args);
      
      ProgramAST ast = ProgramParser.parseProgram(opts);
      
      ProgramLLVM llvm = ast.buildLLVM(opts);
      
      llvm.writeLLVM();
      
      System.exit(0);
   }
}
