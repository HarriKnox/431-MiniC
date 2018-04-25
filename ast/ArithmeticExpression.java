package ast;


public class ArithmeticExpression
   extends Expression
{
   public final Operator operator;
   public final Expression left;
   public final Expression right;
   
   
   public ArithmeticExpression(int lineNum, String opStr,
      Expression left, Expression right)
   {
      super(lineNum);
      
      this.left = left;
      this.right = right;
      
      switch (opStr)
      {
         case "*":
            this.operator = Operator.TIMES;
            break;
         
         case "/":
            this.operator = Operator.DIVIDE;
            break;
         
         case "+":
            this.operator = Operator.PLUS;
            break;
         
         case "-":
            this.operator = Operator.MINUS;
            break;
         
         default:
            throw new IllegalArgumentException();
      }
   }
   
   
   public enum Operator
   {
      TIMES,
      DIVIDE,
      PLUS,
      MINUS;
   }
}
