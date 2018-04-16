package ast;


import org.antlr.v4.runtime.Token;


public class UnaryExpression
   extends AbstractExpression
{
   private final Operator operator;
   private final Expression operand;

   public UnaryExpression(Token token, String opStr, Expression operand)
   {
      super(token);
      
      this.operator = decode(opStr);
      this.operand = operand;
   }
   
   
   public Operator getOperator()
   {
      return this.operator;
   }
   
   public Expression getOperand()
   {
      return this.operand;
   }
   
   
   private static final String NOT_OPERATOR = "!";
   private static final String MINUS_OPERATOR = "-";

   public static enum Operator
   {
      NOT(NOT_OPERATOR), MINUS(MINUS_OPERATOR);
      
      private final String opStr;
      
      private Operator(String opStr)
      {
         this.opStr = opStr;
      }
      
      public String toString()
      {
         return this.opStr;
      }
   }
   
   private static Operator decode(String opStr)
   {
      switch (opStr)
      {
         case NOT_OPERATOR:
            return Operator.NOT;
      
         case MINUS_OPERATOR:
            return Operator.MINUS;
         
         default:
            throw new IllegalArgumentException();
      }
   }
}
