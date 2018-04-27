package ast;


import ast.declaration.Declarations;
import ast.declaration.Functions;
import ast.declaration.Structs;


public class Program
{
   public final Structs structs;
   public final Declarations globals;
   public final Functions functions;


   public Program(Structs structs, Declarations globals, Functions functions)
   {
      this.structs = structs;
      this.globals = globals;
      this.functions = functions;
   }
}
