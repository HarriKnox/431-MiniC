package ast;


import ast.declaration.Functions;
import ast.declaration.Structs;
import ast.declaration.Variables;


public class Program
{
   public final Structs structs;
   public final Variables globals;
   public final Functions functions;


   public Program(Structs structs, Variables globals, Functions functions)
   {
      this.structs = structs;
      this.globals = globals;
      this.functions = functions;
   }
   
   
   public CFG generateCFG()
   {
      
   }
}
