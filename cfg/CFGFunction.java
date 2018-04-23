package cfg;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ast.*;


class CFGFunction
{
   public final CFGNode entry;
   public final CFGNode exit;
   public final String signature;
   public final List<CFGNode> toposort;
   
   
   CFGFunction(CFGNode entry, CFGNode exit, String sig, List<CFGNode> toposort)
   {
      this.entry = entry;
      this.exit = exit;
      this.signature = sig;
      this.toposort = toposort;
   }
   
   
   void printGraph()
   {
      System.out.println("define " + this.signature);
      System.out.println("{");
      
      for (CFGNode node : this.toposort)
         node.printNode();
      
      System.out.println("}");
   }
   
   
   static CFGFunction buildFunctionCFG(Function func)
   {
      StringBuilder sig = new StringBuilder()
            .append(func.retType.toLLVMTypeString())
            .append(" @")
            .append(func.name)
            .append("(");
      
      
      /* Get parameter list */
      Iterator<Declaration> decliter = func.params.iterator();
      
      if (decliter.hasNext())
      {
         Declaration decl = decliter.next();
         
         sig.append(decl.type.toLLVMTypeString()).append(" ")
               .append(new LLVMIdentifier(func.name, "param." + decl.name));
      }
      
      while (decliter.hasNext())
      {
         Declaration decl = decliter.next();
         
         sig.append(", ").append(decl.type.toLLVMTypeString()).append(" ")
               .append(new LLVMIdentifier(func.name, "param." + decl.name));
      }
      
      sig.append(")");
      
      
      /* Create entry and exit nodes */
      CFGNode entry = new CFGNode(true);
      
      CFGNode exit = new CFGNode();
      
      
      /* Add return value and instructions */
      LLVMRegister retValue = new LLVMRegister();
      
      if (!(func.retType instanceof VoidType))
      {
         entry.addInstruction(new LLVMAlloca(new LLVMReturnValue(func.name), func.retType));
         
         exit.addInstruction(
            new LLVMLoad(
               retValue,
               new LLVMReturnValue(func.name),
               func.retType.toLLVMTypeString()));
      }
         
      generateLocalDeclarations(entry, func.name, func.params, func.locals);
      
      exit.addInstruction(new LLVMReturn(func.retType, retValue));
      
      
      /* Build the CFG */
      CFGNode last = generateStatementCFG(func.body, entry, exit);
      last.link(exit);
      
      
      /* Sort the nodes into a topological sort */
      List<CFGNode> toposort = new LinkedList<>();
      exit.addNodeTopo(new HashSet<CFGNode>(), toposort);
      
      
      return new CFGFunction(entry, exit, sig.toString(), toposort);
   }
   
   
   private static void generateLocalDeclarations(CFGNode node, String funcName, List<Declaration> params, List<Declaration> locals)
   {
      for (Declaration param : params)
      {
         LLVMValue stackptr = new LLVMIdentifier(funcName, param.name);
         node.addInstruction(new LLVMAlloca(stackptr, param.type));
         node.addInstruction(
            new LLVMAssignment(
               stackptr,
               new LLVMIdentifier(funcName, "param." + param.name),
               param.type));
      }
      
      for (Declaration local : locals)
      {
         node.addInstruction(new LLVMAlloca(new LLVMIdentifier(funcName, local.name), local.type));
      }
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
      System.exit(1);
      return null;
   }
   
   
   private static LLVMValue writeLvalueInstructions(Lvalue target, CFGNode node)
   {
      if (target instanceof LvalueDot)
      {
         LvalueDot t = (LvalueDot)target;
         LLVMValue left = writeExpressionInstructions(t.left, node);
         
         LLVMRegister result = new LLVMRegister();
         
         node.addInstruction(new LLVMElementptr(left, result, t.type, t.id));
         return result;
      }
      
      return new LLVMIdentifier(((LvalueId)target).funcName, ((LvalueId)target).id);
   }
   
   
   private static CFGNode generateAssignmentCFG(AssignmentStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue target = writeLvalueInstructions(body.target, node);
      LLVMValue source = writeExpressionInstructions(body.source, node);
      
      node.addInstruction(new LLVMAssignment(target, source, body.type));
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
      LLVMValue object = writeExpressionInstructions(body.expression, node);
      LLVMRegister pointer = new LLVMRegister();
      
      node.addInstruction(new LLVMBitcast(object, pointer, body.type.toLLVMTypeString(), "i8*"));
      node.addInstruction(new LLVMFree(pointer));
      
      return node;
   }
   
   
   private static CFGNode generateInvocationCFG(InvocationStatement body, CFGNode node, CFGNode exit)
   {
      writeExpressionInstructions(body.expression, node);
      
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
      node.link(exit);
      
      return new CFGNode();
   }
   
   
   private static CFGNode generateReturnCFG(ReturnStatement body, CFGNode node, CFGNode exit)
   {
      LLVMValue source = writeExpressionInstructions(body.expression, node);
      
      node.addInstruction(
         new LLVMAssignment(
            new LLVMReturnValue(body.funcName),
            source,
            body.type));
      
      node.link(exit);
      
      return new CFGNode();
   }
   
   
   private static CFGNode generateWhileCFG(WhileStatement body, CFGNode node, CFGNode exit)
   {
      
      CFGNode guardNode = new CFGNode();
      
      node.link(guardNode);
      
      LLVMValue guard = writeExpressionInstructions(body.guard, guardNode);
      
      
      CFGNode bodyNode = new CFGNode();
      CFGNode rerouteNode = new CFGNode();
      guardNode.link(guard, bodyNode, rerouteNode);
      
      
      CFGNode bodyLast = generateStatementCFG(body.body, bodyNode, exit);
      bodyLast.link(guardNode, true);
      
      
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
      
      LLVMRegister pointer = new LLVMRegister();
      
      node.addInstruction(new LLVMElementptr(left, pointer, (StructType)exp.left.type, exp.id));
      
      
      LLVMRegister result = new LLVMRegister();
      
      node.addInstruction(new LLVMLoad(result, pointer, exp.type.toLLVMTypeString()));
      return result;
   }
   
   
   private static LLVMValue writeFalseExpressionInstructions(FalseExpression exp, CFGNode node)
   {
      return LLVMBoolean.FALSE;
   }
   
   
   private static LLVMValue writeIdentifierExpressionInstructions(IdentifierExpression exp, CFGNode node)
   {
      LLVMRegister loaded = new LLVMRegister();
      node.addInstruction(
         new LLVMLoad(
            loaded,
            new LLVMIdentifier(
               exp.funcName,
               exp.id),
            exp.type.toLLVMTypeString()));
      
      return loaded;
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
      node.addInstruction(new LLVMBitcast(mallocked, bitcasted, "i8*", exp.type.toLLVMTypeString()));
      
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
