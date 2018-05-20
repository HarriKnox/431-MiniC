package llvm;


import java.io.PrintWriter;

import java.util.Arrays;

import llvm.declaration.LLVMFunctions;
import llvm.declaration.LLVMGlobals;
import llvm.declaration.LLVMStructs;

import llvm.value.operand.constant.LLVMStdio;

import common.Options;

import arm.ProgramARM;

import arm.declaration.ARMFunctions;
import arm.declaration.ARMGlobals;


import static llvm.value.operand.constant.LLVMStdio.PRINTLN_FORMAT;
import static llvm.value.operand.constant.LLVMStdio.PRINT_FORMAT;
import static llvm.value.operand.constant.LLVMStdio.SCANF_FORMAT;

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
      
      
      for (LLVMStdio stdio : Arrays.asList(new LLVMStdio[]{
               PRINTLN_FORMAT, PRINT_FORMAT, SCANF_FORMAT}))
      {
         printer.print(stdio.name);
         printer.print(" = private unnamed_addr constant [4 x i8] ");
         printer.print(stdio.initialization);
         printer.println(", align 1");
      }
   }
   
   
   public ProgramARM buildARM(Options opts)
   {
      ARMGlobals armGlobals = this.llvmGlobals.buildARM();
      ARMFunctions armFuncs = this.llvmFunctions.buildARM();
      
      return new ProgramARM(armGlobals, armFuncs);
   }
}
