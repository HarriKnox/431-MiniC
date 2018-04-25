package ast;


public class TimesExpression
   extends ArithmeticExpression
{
   public TimesExpression(int lineNum, Expression left, Expression right)
   {
      super(lineNum, left, right);
   }
}
