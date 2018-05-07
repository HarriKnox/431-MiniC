import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

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
      writeLLVM(opts, llvm);
      
      
      System.exit(0);
   }
   
   
   private static void clangCompile(Options opts, ProgramLLVM llvm)
   {
      if (!opts.clang)
         return;
      
      
      PrintWriter printer;
      File file;
      
      try
      {
         file = File.createTempFile("tmp", ".ll", new File("."));
         file.deleteOnExit();
         
         printer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      }
      catch (Exception e)
      {
         System.err.println("Can't use clang: can't create a temp file");
         System.err.println(e);
         return;
      }
      
      
      llvm.writeLLVM(printer);
      printer.close();
      
      
      if (printer.checkError())
      {
         System.err.println("Error in printing.");
         System.err.println("Too bad we won't know what it is");
         return;
      }
      
      
      List<String> command = new LinkedList<>();
      
      command.add("clang");
      command.add(file.getName());
      command.add("-o");
      command.add(opts.name);
      
      try
      {
         Process clang = new ProcessBuilder(command).inheritIO().start();
         
         int status = clang.waitFor();
         
         if (status != 0)
            System.err.println("Non-zero exit status: " + status);
      }
      catch (Exception e)
      {
         System.err.println("Exception running clang:");
         System.err.println(e);
      }
   }
   
   
   private static void writeLLVM(Options opts, ProgramLLVM llvm)
   {
      if (!opts.llvm)
         return;
      
      
      PrintWriter printer = new PrintWriter(System.out);
      
      llvm.writeLLVM(printer);
      
      printer.close();
   }
}
