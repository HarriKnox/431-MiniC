package ast;


import ast.declaration.Variables;
import ast.declaration.Functions;
import ast.declaration.Structs;


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
}
