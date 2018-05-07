import ast.ProgramAST;

import common.Options;

import llvm.ProgramLLVM;

import parser.ProgramParser;


public class Main
{
   public static void main(String[] args)
   {
      /* Get options from command line arguments */
      Options opts = Options.parseOptions(args);
      
      
      /* Parse the source file and get the AST */
      ProgramAST ast = ProgramParser.parseProgram(opts);
      
      
      /* Build the LLVM representation using stack-based variable storage */
      ProgramLLVM llvm = ast.buildLLVM(opts);
      
      
      /* Compile with Clang (if enabled) */
      clangCompile(opts, llvm);
      
      
      /* Write all the LLVM code to stdout */
      llvm.writeLLVM(new PrintWriter(new OutputStreamWriter(System.out)));
      
      
      System.exit(0);
   }
}
