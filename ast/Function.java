package ast;


import java.util.List;

import org.antlr.v4.runtime.Token;


public class Function
   extends LinedElement
{
   private final String name;
   private final Type retType;
   private final List<Declaration> params;
   private final List<Declaration> locals;
   private final Statement body;

   public Function(Token token, String name, List<Declaration> params,
      Type retType, List<Declaration> locals, Statement body)
   {
      super(token);
      this.name = name;
      this.params = params;
      this.retType = retType;
      this.locals = locals;
      this.body = body;
   }
}
