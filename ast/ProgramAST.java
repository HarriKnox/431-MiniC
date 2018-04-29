package ast;


import ast.declaration.Functions;
import ast.declaration.Structs;
import ast.declaration.Variables;


public class ProgramAST
{
   public final Structs structs;
   public final Variables globals;
   public final Functions functions;


   public ProgramAST(Structs structs, Variables globals, Functions functions)
   {
      this.structs = structs;
      this.globals = globals;
      this.functions = functions;
   }
}
