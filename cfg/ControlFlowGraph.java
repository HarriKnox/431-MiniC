package cfg;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ast.Declaration;
import ast.Function;
import ast.Program;
import ast.TypeDeclaration;


public class ControlFlowGraph
{
   public final List<CFGFunction> functions;
   public final List<LLVMTypeDeclaration> structs;
   public final List<LLVMGlobalDeclaration> globals;
   
   
   ControlFlowGraph(List<LLVMTypeDeclaration> structs, List<LLVMGlobalDeclaration> globals, List<CFGFunction> funcs)
   {
      this.structs = structs;
      this.globals = globals;
      this.functions = funcs;
   }
   
   
   public void printProgram()
   {
      System.out.println("target triple=\"i686\"");
      System.out.println();
      
      for (LLVMTypeDeclaration struct : this.structs)
         System.out.println(struct.toString());
      
      System.out.println();
      
      for (LLVMGlobalDeclaration global : this.globals)
         System.out.println(global.toString());
      
      System.out.println();
      
      for (CFGFunction functionCFG : this.functions)
         functionCFG.printGraph();
      
      System.out.println();
      
      System.out.println("declare i8* @malloc(i32)");
      System.out.println("declare void @free(i8*)");
      System.out.println("declare i32 @printf(i8*, ...)");
      System.out.println("declare i32 @scanf(i8*, ...)");
      System.out.println(LLVMGlobal.PRINTLN_FORMAT.toString() + " = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\", align 1");
      System.out.println(LLVMGlobal.PRINT_FORMAT.toString() + " = private unnamed_addr constant [4 x i8] c\"%d \\00\", align 1");
      System.out.println(LLVMGlobal.READ_FORMAT.toString() + " = private unnamed_addr constant [4 x i8] c\"%ld\\00\", align 1");
      System.out.println(LLVMGlobal.READ_SCRATCH + " = common global i32 0, align 8");
   }
   
   
   public static ControlFlowGraph buildProgramCFG(Program prog)
   {
      List<LLVMTypeDeclaration> typeDecls = new LinkedList<>();
      
      for (TypeDeclaration typeDecl : prog.types)
         typeDecls.add(new LLVMTypeDeclaration(typeDecl));
      
      
      List<LLVMGlobalDeclaration> globals = new LinkedList<>();
      
      for (Declaration decl : prog.decls)
         globals.add(new LLVMGlobalDeclaration(decl));
      
      
      List<CFGFunction> funcs = new LinkedList<>();
      
      for (Function func : prog.funcs)
         funcs.add(CFGFunction.buildFunctionCFG(func));
      
      
      return new ControlFlowGraph(typeDecls, globals, funcs);
   }
}
