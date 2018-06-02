package llvm;


import java.util.Arrays;

import llvm.declaration.LLVMFunctions;
import llvm.declaration.LLVMGlobals;
import llvm.declaration.LLVMStructs;

import llvm.value.operand.constant.LLVMStdio;

import common.Options;
import common.Printer;

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
   
   
   public void writeLLVM(Options opts, Printer printr)
   {
      printr.println("target triple = \"x86_64-unknown-linux-gnu\"").println();
      
      
      this.llvmStructs.writeLLVM(printr);
      this.llvmGlobals.writeLLVM(printr);
      this.llvmFunctions.writeLLVM(printr);
      
      
      printr.println("declare i8* @malloc(i32)")
            .println("declare void @free(i8*)")
            .println("declare i32 @printf(i8*, ...)")
            .println("declare i32 @scanf(i8*, ...)")
      
            .println();
      
      
      for (LLVMStdio stdio : Arrays.asList(new LLVMStdio[]{
            PRINTLN_FORMAT, PRINT_FORMAT, SCANF_FORMAT}))
         printr.print(stdio.name)
               .print(" = private unnamed_addr constant [4 x i8] ")
               .print(stdio.initialization)
               .println(", align 1");
   }
   
   
   public ProgramARM buildARM(Options opts)
   {
      ARMGlobals armGlobals = this.llvmGlobals.buildARM();
      ARMFunctions armFuncs = this.llvmFunctions.buildARM();
      
      return new ProgramARM(armGlobals, armFuncs);
   }
}
