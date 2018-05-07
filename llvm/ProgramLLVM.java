package llvm;


import java.io.PrintWriter;

import llvm.declaration.LLVMFunctions;
import llvm.declaration.LLVMGlobals;
import llvm.declaration.LLVMStructs;

import common.Options;


import static llvm.value.constant.LLVMStdio.PRINTLN_FORMAT;
import static llvm.value.constant.LLVMStdio.PRINT_FORMAT;
import static llvm.value.constant.LLVMStdio.SCANF_FORMAT;

import static llvm.value.variable.LLVMGlobal.SCANF_SCRATCH;


public class ProgramLLVM
{
   public final LLVMStructs llvmStructs;
   public final LLVMGlobals llvmGlobals;
   public final LLVMFunctions llvmFunctions;
   
   
   public ProgramLLVM(LLVMStructs structs,
         LLVMGlobals globals, LLVMFunctions functions)
   {
      this.llvmStructs = structs;
      this.llvmGlobals = globals;
      this.llvmFunctions = functions;
   }
   
   
   public void writeLLVM(Options opts, PrintWriter printer)
   {
      printer.print("target triple = \"");
      
      if (opts.clang)
      {
         if (opts.csl)
            printer.print("x86_64-redhat-linux-gnu");
         
         else
            printer.print("x86_64-pc-linux-gnu");
      }
      else
      {
         printer.print("i686");
      }
      
      printer.println('"');
      printer.println();
      
      
      this.llvmStructs.writeLLVM(printer);
      this.llvmGlobals.writeLLVM(printer);
      this.llvmFunctions.writeLLVM(printer);
      
      
      printer.println("declare i8* @malloc(i32)");
      printer.println("declare void @free(i8*)");
      printer.println("declare i32 @printf(i8*, ...)");
      printer.println("declare i32 @scanf(i8*, ...)");
      
      printer.println();
      
      
      printer.print(PRINTLN_FORMAT.name);
      printer.println(" = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\", align 1");
      
      printer.print(PRINT_FORMAT.name);
      printer.println(" = private unnamed_addr constant [4 x i8] c\"%d \\00\", align 1");
      
      printer.print(SCANF_FORMAT.name);
      printer.println(" = private unnamed_addr constant [4 x i8] c\"%d\\00\\00\", align 1");
      
      printer.print(SCANF_SCRATCH.llvmString());
      printer.println(" = common global i32 0, align 4");
   }
}
