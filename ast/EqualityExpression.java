package ast;


public class EqualityExpression
   extends Expression
{
   public final Operator operator;
   public final Expression left;
   public final Expression right;
   
   
   public EqualityExpression(int lineNum, String opStr,
      Expression left, Expression right)
   {
      super(lineNum);
      
      this.left = left;
      this.right = right;
      
      switch (opStr)
      {
         case "==":
            this.operator = Operator.EQ;
            break;
         
         case "!=":
            this.operator = Operator.NE;
            break;
         
         default:
            throw new IllegalArgumentException();
      }
   }
   
   
   public enum Operator
   {
      EQ,
      NE;
   }
}
