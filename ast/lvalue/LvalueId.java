package ast.lvalue;


import ast.type.Type;

public class LvalueId
   extends Lvalue
{
   public final String id;
   public String funcName;
   public Type type;
   public boolean global = false;;

   public LvalueId(int lineNum, String id)
   {
      super(lineNum);
      this.id = id;
   }
}
