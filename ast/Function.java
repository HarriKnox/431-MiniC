package ast;

import java.util.List;

public class Function
   extends LinedElement
{
   public final String name;
   public final Type retType;
   public final List<Declaration> params;
   public final List<Declaration> locals;
   public final Statement body;

   public Function(int lineNum, String name, List<Declaration> params,
      Type retType, List<Declaration> locals, Statement body)
   {
      super(lineNum);
      this.name = name;
      this.params = params;
      this.retType = retType;
      this.locals = locals;
      this.body = body;
   }
}
