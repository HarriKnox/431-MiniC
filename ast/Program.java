package ast;

import ast.declaration.Types;
import ast.declaration.Declarations;
import ast.declaration.Functions;

public class Program
{
   public final Types types;
   public final Declarations decls;
   public final Functions funcs;

   public Program(Types types, Declarations decls, Functions funcs)
   {
      this.types = types;
      this.decls = decls;
      this.funcs = funcs;
   }
}
