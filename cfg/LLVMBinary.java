package cfg;


import ast.BinaryExpression;
import ast.BoolType;
import ast.IntType;
import ast.StructType;
import ast.Type;

class LLVMBinary
   implements LLVMInstruction
{
   LLVMRegister result;
   LLVMValue leftValue;
   LLVMValue rightValue;
   BinaryExpression.Operator operation;
   Type type;
   
   LLVMBinary(LLVMValue leftValue, LLVMValue rightValue, LLVMRegister result, BinaryExpression.Operator op, Type type)
   {
      this.result = result;
      this.leftValue = leftValue;
      this.rightValue = rightValue;
      this.operation = op;
      this.type = type;
   }
   
   
   private String operationString()
   {
      switch (this.operation)
      {
         case TIMES:
            return "mul";
         
         case DIVIDE:
            return "sdiv";
         
         case PLUS:
            return "add";
         
         case MINUS:
            return "sub";
         
         case LT:
            return "icmp slt";
         
         case GT:
            return "icmp sgt";
         
         case LE:
            return "icmp sle";
         
         case GE:
            return "icmp sge";
         
         case EQ:
            return "icmp eq";
         
         case NE:
            return "icmp ne";
         
         case AND:
            return "and";
         
         case OR:
            return "or";
      }
      
      return null;
   }
   
   
   public String toString()
   {
      return this.result.toString() + " = " + this.operationString() + " "
            + this.type.toLLVMTypeString() + " " + this.leftValue.toString()
            + ", " + this.rightValue.toString();
   }
}
