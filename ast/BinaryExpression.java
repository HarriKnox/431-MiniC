package ast;

public class BinaryExpression
   extends AbstractExpression
{
   public final Operator operator;
   public final Expression left;
   public final Expression right;

   public BinaryExpression(int lineNum, String opStr,
      Expression left, Expression right)
   {
      super(lineNum);
      
      this.operator = decode(opStr);
      this.left = left;
      this.right = right;
   }
   

   private static final String TIMES_OPERATOR = "*";
   private static final String DIVIDE_OPERATOR = "/";
   private static final String PLUS_OPERATOR = "+";
   private static final String MINUS_OPERATOR = "-";
   private static final String LT_OPERATOR = "<";
   private static final String LE_OPERATOR = "<=";
   private static final String GT_OPERATOR = ">";
   private static final String GE_OPERATOR = ">=";
   private static final String EQ_OPERATOR = "==";
   private static final String NE_OPERATOR = "!=";
   private static final String AND_OPERATOR = "&&";
   private static final String OR_OPERATOR = "||";

   public static enum Operator
   {
      /* Int -> Int */
      TIMES(TIMES_OPERATOR),
      DIVIDE(DIVIDE_OPERATOR),
      PLUS(PLUS_OPERATOR),
      MINUS(MINUS_OPERATOR),
      
      /* Int -> Bool */
      LT(LT_OPERATOR),
      GT(GT_OPERATOR),
      LE(LE_OPERATOR),
      GE(GE_OPERATOR),
      
      /* 'a -> Bool */
      EQ(EQ_OPERATOR),
      NE(NE_OPERATOR),
      
      /* Bool -> Bool */
      AND(AND_OPERATOR),
      OR(OR_OPERATOR);
      
      private final String opStr;
      
      Operator(String opStr)
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
         case TIMES_OPERATOR:
            return Operator.TIMES;
         
         case DIVIDE_OPERATOR:
            return Operator.DIVIDE;
         
         case PLUS_OPERATOR:
            return Operator.PLUS;
         
         case MINUS_OPERATOR:
            return Operator.MINUS;
         
         case LT_OPERATOR:
            return Operator.LT;
         
         case LE_OPERATOR:
            return Operator.LE;
         
         case GT_OPERATOR:
            return Operator.GT;
         
         case GE_OPERATOR:
            return Operator.GE;
         
         case EQ_OPERATOR:
            return Operator.EQ;
         
         case NE_OPERATOR:
            return Operator.NE;
         
         case AND_OPERATOR:
            return Operator.AND;
         
         case OR_OPERATOR:
            return Operator.OR;
         
         default:
            throw new IllegalArgumentException();
      }
   }
}
