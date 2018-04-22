package cfg;


import java.util.LinkedList;
import java.util.List;
import ast.*;


public class ControlFlowGraphGenerator
{
   public static CFGNode generateFunctionCFG(Function func)
   {
      CFGNode entry = new CFGNode();
      CFGNode exit = new CFGNode();
      
      CFGNode last = generateStatementCFG(func.body, entry, exit);
      last.link(exit);
      
      return entry;
   }
   
   
   private static CFGNode generateStatementCFG(Statement body, CFGNode node, CFGNode exit)
   {
      if (body instanceof AssignmentStatement)
         return generateAssignmentCFG((AssignmentStatement)body, node, exit);
      
      if (body instanceof BlockStatement)
         return generateBlockCFG((BlockStatement)body, node, exit);
      
      if (body instanceof ConditionalStatement)
         return generateConditionalCFG((ConditionalStatement)body, node, exit);
      
      if (body instanceof DeleteStatement)
         return generateDeleteCFG((DeleteStatement)body, node, exit);
      
      if (body instanceof InvocationStatement)
         return generateInvocationCFG((InvocationStatement)body, node, exit);
      
      if (body instanceof PrintLnStatement)
         return generatePrintLnCFG((PrintLnStatement)body, node, exit);
      
      if (body instanceof PrintStatement)
         return generatePrintCFG((PrintStatement)body, node, exit);
      
      if (body instanceof ReturnEmptyStatement)
         return generateReturnEmptyCFG((ReturnEmptyStatement)body, node, exit);
      
      if (body instanceof ReturnStatement)
         return generateReturnCFG((ReturnStatement)body, node, exit);
      
      if (body instanceof WhileStatement)
         return generateWhileCFG((WhileStatement)body, node, exit);
      
      System.err.println("I have no idea what went wrong in validFunction: " + body.getClass().getName());
      ok = false;
      return false;
   }
   
   
   private static LLVMValue writeLvalueInstructions(Lvalue target, CFGNode node)
   {
      if (target instanceof LvalueDot)
      {
         LLVMValue left = writeExpressionInstructions(target.left, node);
         
         LLVMRegister result = new LLVMRegister();
         
         node.addInstruction(new LLVMElementptr(left, result, target.type, target.id));
         return result;
      }
      
      return new LLVMIdentifier(target.funcName, target.type);
   }
   
   
   private static CFGNode generateAssignmentCFG(AssignmentStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue left = writeLvalueInstructions(body.left, node);
      LLVMValue right = writeExpressionInstructions(body.source, node);
      
      node.addInstruction(new LLVMAssignment(left, right, body.type));
      return node;
   }
   
   
   private static CFGNode generateBlockCFG(BlockStatement body, CFGNode node, CFGNode exit)
   {
      for (Statement statement : body.statements)
         node = generateStatementCFG(statement, node, exit);
      
      return node;
   }
   
   
   private static CFGNode generateConditionalCFG(ConditionalStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue guard = writeExpressionInstructions(body.guard, node);
      
      
      CFGNode thenNode = new CFGNode();
      CFGNode elseNode = new CFGNode();
      node.link(guard, thenNode, elseNode);
      
      
      CFGNode thenLast = generateStatementCFG(body.thenBlock, thenNode, exit);
      CFGNode elseLast = generateStatementCFG(body.elseBlock, elseNode, exit);
      
      
      CFGNode joinNode = new CFGNode();
      thenLast.link(joinNode);
      elseLast.link(joinNode);
      
      return joinNode;
   }
   
   
   private static CFGNode generateDeleteCFG(DeleteStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue object = writeExpressionInstructions(body.expression);
      LLVMRegister pointer = new LLVMRegister();
      
      node.addInstruction(new LLVMBitcast(object, pointer, body.type.name, "i8*"));
      node.addInstruction(new LLVMFree(pointer));
      
      return node;
   }
   
   
   private static CFGNode generateInvocationCFG(InvocationStatement body, CFGNode node, CFGNode exit)
   {
      List<LLVMValue> arguments = new LinkedList<>();
      
      for (Expression e : exp.arguments)
      {
         arguments.add(writeExpressionInstructions(e, node));
      }
      
      LLVMRegister result = new LLVMRegister();
      node.addInstruction(new LLVMInvocation(arguments, exp.name, result));
      return node;
   }
   
   
   private static CFGNode generatePrintLnCFG(PrintLnStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue exp = writeExpressionInstructions(body.expression, node);
      
      node.addInstruction(new LLVMPrint(LLVMGlobal.PRINTLN_FORMAT, exp));
      
      return node;
   }
   
   
   private static CFGNode generatePrintCFG(PrintStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue exp = writeExpressionInstructions(body.expression, node);
      
      node.addInstruction(new LLVMPrint(LLVMGlobal.PRINT_FORMAT, exp));
      
      return node;
   }
   
   
   private static CFGNode generateReturnEmptyCFG(ReturnEmptyStatement body, CFGNode node, CFGNode exit)
   {
      node.addInstruction(new LLVMReturn());
      
      return new CFGNode();
   }
   
   
   private static CFGNode generateReturnCFG(ReturnStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue value = writeExpressionInstructions(body.expression, node);
      
      node.addInstruction(new LLVMReturn(value, body.type));
   }
   
   
   private static CFGNode generateWhileCFG(WhileStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue guard = writeExpressionInstructions(body.guard, node);
      
      
      CFGNode bodyNode = new CFGNode();
      CFGNode rerouteNode = new CFGNode();
      node.link(guard, bodyNode, rerouteNode);
      
      
      CFGNode bodyLast = generateStatementCFG(body.statements, bodyNode, exit);
      bodyLast.link(node, true);
      
      
      return rerouteNode;
   }
   
   
   
   private static LLVMValue writeExpressionInstructions(Expression exp, CFGNode node)
   {
      if (exp instanceof BinaryExpression)
         return writeBinaryExpressionInstructions((BinaryExpression)exp, node);
      
      if (exp instanceof DotExpression)
         return writeDotExpressionInstructions((DotExpression)exp, node);
      
      if (exp instanceof FalseExpression)
         return writeFalseExpressionInstructions((FalseExpression)exp, node);
      
      if (exp instanceof IdentifierExpression)
         return writeIdentifierExpressionInstructions((IdentifierExpression)exp, node);
      
      if (exp instanceof IntegerExpression)
         return writeIntegerExpressionInstructions((IntegerExpression)exp, node);
      
      if (exp instanceof InvocationExpression)
         return writeInvocationExpressionInstructions((InvocationExpression)exp, node);
      
      if (exp instanceof NewExpression)
         return writeNewExpressionInstructions((NewExpression)exp, node);
      
      if (exp instanceof NullExpression)
         return writeNullExpressionInstructions((NullExpression)exp, node);
      
      if (exp instanceof ReadExpression)
         return writeReadExpressionInstructions((ReadExpression)exp, node);
      
      if (exp instanceof TrueExpression)
         return writeTrueExpressionInstructions((TrueExpression)exp, node);
      
      if (exp instanceof UnaryExpression)
         return writeUnaryExpressionInstructions((UnaryExpression)exp, node);
      
      System.err.println("I have no idea what went wrong in writeExpressionInstructions: " + exp.getClass().getName());
      return null;
   }
   
   
   private static LLVMValue writeBinaryExpressionInstructions(BinaryExpression exp, CFGNode node)
   {
      LLVMValue leftValue = writeExpressionInstructions(exp.left, node);
      LLVMValue rightValue = writeExpressionInstructions(exp.right, node);
      
      LLVMRegister result = new LLVMRegister();
      
      node.addInstruction(new LLVMBinary(leftValue, rightValue, result, exp.operator, exp.type));
      return result;
   }
   
   
   private static LLVMValue writeDotExpressionInstructions(DotExpression exp, CFGNode node)
   {
      LLVMValue left = writeExpressionInstructions(exp.left, node);
      
      LLVMRegister result = new LLVMRegister();
      
      node.addInstruction(new LLVMElementptr(left, result, exp.type, exp.id));
      return result;
   }
   
   
   private static LLVMValue writeFalseExpressionInstructions(FalseExpression exp, CFGNode node)
   {
      return LLVMBoolean.FALSE;
   }
   
   
   private static LLVMValue writeIdentifierExpressionInstructions(IdentifierExpression exp, CFGNode node)
   {
      return new LLVMIdentifier(exp.funcName, exp.type);
   }
   
   
   private static LLVMValue writeIntegerExpressionInstructions(IntegerExpression exp, CFGNode node)
   {
      return new LLVMInteger(exp.value);
   }
   
   
   private static LLVMValue writeInvocationExpressionInstructions(InvocationExpression exp, CFGNode node)
   {
      List<LLVMValue> arguments = new LinkedList<>();
      
      for (Expression e : exp.arguments)
      {
         arguments.add(writeExpressionInstructions(e, node));
      }
      
      LLVMRegister result = new LLVMRegister();
      node.addInstruction(new LLVMInvocation(arguments, exp.name, result));
      return result;
   }
   
   
   private static LLVMValue writeNewExpressionInstructions(NewExpression exp, CFGNode node)
   {
      
      LLVMRegister mallocked = new LLVMRegister();
      node.addInstruction(new LLVMMalloc(exp.id, mallocked));
      
      LLVMRegister bitcasted = new LLVMRegister();
      node.addInstruction(new LLVMBitcast(mallocked, bitcasted, "i8*", exp.id));
      
      return bitcasted;
   }
   
   
   private static LLVMValue writeNullExpressionInstructions(NullExpression exp, CFGNode node)
   {
      return LLVMNull.NULL;
   }
   
   
   private static LLVMValue writeReadExpressionInstructions(ReadExpression exp, CFGNode node)
   {
      node.addInstruction(LLVMScanf.SCANF);
      return LLVMGlobal.READ_SCRATCH;
   }
   
   
   private static LLVMValue writeTrueExpressionInstructions(TrueExpression exp, CFGNode node)
   {
      return LLVMBoolean.TRUE;
   }
   
   
   private static LLVMValue writeUnaryExpressionInstructions(UnaryExpression exp, CFGNode node)
   {
      LLVMValue value = writeExpressionInstructions(exp.operand, node);
      
      LLVMRegister result = new LLVMRegister();
      
      node.addInstruction(new LLVMUnary(value, result, exp.operator));
      return result;
   }
}
