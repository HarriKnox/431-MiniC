package ast;


public class RelationalExpression
   extends Expression
{
   public final Operator operator;
   public final Expression left;
   public final Expression right;
   
   
   public RelationalExpression(int lineNum, String opStr,
      Expression left, Expression right)
   {
      super(lineNum);
      
      this.left = left;
      this.right = right;
      
      switch (opStr)
      {
         case "<":
            this.operator = Operator.LT;
            break;
         
         case "<=":
            this.operator = Operator.LE;
            break;
         
         case ">":
            this.operator = Operator.GT;
            break;
         
         case ">=":
            this.operator = Operator.GE;
            break;
         
         default:
            throw new IllegalArgumentException();
      }
   }
   
   
   public enum Operator
   {
      LT,
      LE,
      GT,
      GE;
   }
}
