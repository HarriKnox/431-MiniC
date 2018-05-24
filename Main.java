import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

import arm.ProgramARM;

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
      ProgramAST ast = ProgramParser.parseProgram(opts.filename);
      
      
      /* Build the LLVM representation using stack-based variable storage */
      ProgramLLVM llvm = ast.buildLLVM(opts);
      
      
      /* Write all the LLVM code to stdout (if enabled) */
      writeLLVM(opts, llvm);
      
      
      /* Compile with Clang (if enabled) */
      clangCompile(opts, llvm);
      
      
      if (opts.arm != null)
      {
         ProgramARM arm = llvm.buildARM(opts);
         
         
         /* Write output ARM code to file */
         writeARM(opts, arm);
      }
   }
   
   
   private static void writeLLVM(Options opts, ProgramLLVM llvm)
   {
      if (opts.llvm == null)
         return;
      
      PrintWriter printer;
      
      if (opts.llvm.isEmpty())
      {
         printer = new PrintWriter(System.out);
      }
      
      else
      {
         try
         {
            printer = new PrintWriter(opts.llvm);
         }
         catch (Exception e)
         {
            System.err.println("Exception opening file:");
            e.printStackTrace();
            return;
         }
      }
      
      llvm.writeLLVM(opts, printer);
      
      printer.close();
   }
   
   
   private static void clangCompile(Options opts, ProgramLLVM llvm)
   {
      if ((opts.clang == null) || opts.clang.isEmpty())
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
         e.printStackTrace();
         return;
      }
      
      
      llvm.writeLLVM(opts, printer);
      printer.close();
      
      
      if (printer.checkError())
      {
         System.err.println("Error in printing.");
         System.err.println("Too bad we won't know what the error was");
         return;
      }
      
      
      List<String> command = new LinkedList<>();
      
      command.add("clang");
      command.add(file.getName());
      command.add("-o");
      command.add(opts.clang);
      
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
         e.printStackTrace();
      }
   }
   
   
   private static void writeARM(Options opts, ProgramARM arm)
   {
      if (opts.arm == null)
         return;
      
      PrintWriter printer;
      
      if (opts.arm.isEmpty())
      {
         printer = new PrintWriter(System.out);
      }
      
      else
      {
         try
         {
            printer = new PrintWriter(opts.arm);
         }
         catch (Exception e)
         {
            System.err.println("Exception opening file:");
            e.printStackTrace();
            return;
         }
      }
      
      arm.writeARM(opts, printer);
      
      printer.close();
   }
}
