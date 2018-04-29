package ast.declaration;


import ast.type.Type;

import ast.statement.Statement;


public class Function extends Declaration
{
   public final Type retType;
   public final Variables params;
   public final Variables locals;
   public final Statement body;


   public Function(int lineNum, String name, Variables params,
         Type retType, Variables locals, Statement body)
   {
      super(lineNum, name);

      this.params = params;
      this.retType = retType;
      this.locals = locals;
      this.body = body;
   }
   
   /*
   public void validate(Structs structs, Variables globals, Functions functions)
   {
      if (!this.retType.isValid(structs))
         System.err.println("Invalid type");
      
      
   }*/
}
