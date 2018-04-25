package ast;


public class LogicalExpression
   extends Expression
{
   public final Operator operator;
   public final Expression left;
   public final Expression right;
   
   
   public LogicalExpression(int lineNum, String opStr,
      Expression left, Expression right)
   {
      super(lineNum);
      
      this.left = left;
      this.right = right;
      
      switch (opStr)
      {
         case "&&":
            this.operator = Operator.AND;
            break;
         
         case "||":
            this.operator = Operator.OR;
            break;
         
         default:
            throw new IllegalArgumentException();
      }
   }
   
   
   public enum Operator
   {
      AND,
      OR;
   }
}
