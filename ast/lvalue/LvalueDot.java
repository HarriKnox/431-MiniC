package ast.lvalue;


import ast.type.StructType;

public class LvalueDot
   extends Lvalue
{
   public final Lvalue left;
   public final String id;
   public StructType type;

   public LvalueDot(int lineNum, Lvalue left, String id)
   {
      super(lineNum);
      this.left = left;
      this.id = id;
   }
}
