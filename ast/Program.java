package ast;

import ast.declaration.Structs;
import ast.declaration.Declarations;
import ast.declaration.Functions;

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
