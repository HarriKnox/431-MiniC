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
      this.leftValue = leftOp;
      this.rightValue = rightOp;
      this.operation = op;
      this.type = type;
   }
   
   
   private String operationString()
   {
      switch (this.operation)
      {
         case BinaryExpression.Operator.TIMES:
            return "mul";
         
         case BinaryExpression.Operator.DIVIDE:
            return "sdiv";
         
         case BinaryExpression.Operator.PLUS:
            return "add";
         
         case BinaryExpression.Operator.MINUS:
            return "sub";
         
         case BinaryExpression.Operator.LT:
            return "icmp lt";
         
         case BinaryExpression.Operator.GT:
            return "icmp gt";
         
         case BinaryExpression.Operator.LE:
            return "icmp le";
         
         case BinaryExpression.Operator.GE:
            return "icmp ge";
         
         case BinaryExpression.Operator.EQ:
            return "icmp eq";
         
         case BinaryExpression.Operator.NE:
            return "icmp ne";
         
         case BinaryExpression.Operator.AND:
            return "and";
         
         case BinaryExpression.Operator.OR:
            return "or";
      }
   }
   
   
   private String typeString()
   {
      if (this.type instanceof IntType)
         return "i32";
      
      if (this.type.instanceof BoolType)
         return "i1";
      
      return "%struct." + ((StructType)this.type).name + "*";
   }
   
   
   public toString()
   {
      return this.result.toString() " = " + this.operationString() + " " + this.typeString()
            + this.leftOperand.toString() + ", " + this.rightOperand.toString();
   }
}
