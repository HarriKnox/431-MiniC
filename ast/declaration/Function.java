package ast.declaration;


import java.util.List;

import ast.LinedElement;

import ast.type.Type;
import ast.statement.Statement;


public class Function
   extends LinedElement
{
   public final String name;
   public final Type retType;
   public final Declarations params;
   public final Declarations locals;
   public final Statement body;

   public Function(int lineNum, String name, Declarations params,
      Type retType, Declarations locals, Statement body)
   {
      super(lineNum);
      this.name = name;
      this.params = params;
      this.retType = retType;
      this.locals = locals;
      this.body = body;
   }
}
