package analyzer;

import ast.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class FunctionChecker
{
   private static Map<String, Type> locals;
   private static String functionName;
   private static Type returnType;
   private static boolean ok;
   
   
   static boolean validFunction(Function func)
   {
      ok = true;
      
      
      /* check parameters */
      Map<String, Type> localScope = new HashMap<>();
      
      for (Declaration param : func.params)
      {
         if (!TypeChecker.validType(param.type, param.line) || TypeChecker.contains(localScope, param.name, param.line, "parameter"))
            ok = false;
         
         else
            localScope.put(param.name, param.type);
      }
      
      /* check locals */
      for (Declaration local : func.locals)
      {
         if (!TypeChecker.validType(local.type, local.line) || TypeChecker.contains(localScope, local.name, local.line, "local"))
            ok = false;
         
         else
            localScope.put(local.name, local.type);
      }
      
      locals = localScope;
      returnType = func.retType;
      functionName = func.name;
      
      boolean returns = validStatement(func.body);
      
      if (!(func.retType instanceof VoidType) && !returns)
      {
         System.err.println("line " + func.line + " function " + func.name + " does not return on all paths");
         ok = false;
      }
      
      
      return ok;
   }
   
   private static boolean validStatement(Statement body)
   {
      if (body instanceof AssignmentStatement)
         return validAssignmentStatement((AssignmentStatement)body);
      
      if (body instanceof BlockStatement)
         return validBlockStatement((BlockStatement)body);
      
      if (body instanceof ConditionalStatement)
         return validConditionalStatement((ConditionalStatement)body);
      
      if (body instanceof DeleteStatement)
         return validDeleteStatement((DeleteStatement)body);
      
      if (body instanceof InvocationStatement)
         return validInvocationStatement((InvocationStatement)body);
      
      if (body instanceof PrintLnStatement)
         return validPrintLnStatement((PrintLnStatement)body);
      
      if (body instanceof PrintStatement)
         return validPrintStatement((PrintStatement)body);
      
      if (body instanceof ReturnEmptyStatement)
         return validReturnEmptyStatement((ReturnEmptyStatement)body);
      
      if (body instanceof ReturnStatement)
         return validReturnStatement((ReturnStatement)body);
      
      if (body instanceof WhileStatement)
         return validWhileStatement((WhileStatement)body);
      
      System.err.println("I have no idea what went wrong in validFunction: " + body.getClass().getName());
      ok = false;
      return false;
   }
   
   
   private static boolean validAssignmentStatement(AssignmentStatement assignment)
   {
      /* Left is same type as right */
      Type l = getLvalueType(assignment.target);
      Type r = getExpressionType(assignment.source);
      
      
      if (l == null || r == null)
      {
         ok = false;
      }
      else if (!l.equals(r))
      {
         System.err.println("line " + assignment.line + " cannot assign " + r + " to " + l);
         ok = false;
      }
      
      assignment.type = l;
      
      /* assignments don't return */
      return false;
   }
   
   
   private static boolean validBlockStatement(BlockStatement block)
   {
      boolean returns = false;
      
      
      for (Statement statement : block.statements)
      {
         if (returns)
         {
            System.err.println("line " + ((LinedElement)statement).line + " WARNING: code after a return will not be examined nor executed");
            break;
         }
         
         returns = validStatement(statement);
      }
      
      return returns;
   }
   
   
   private static boolean validConditionalStatement(ConditionalStatement conditional)
   {
      Type g = getExpressionType(conditional.guard);
      
      
      if (g == null)
      {
         ok = false;
      }
      else if (!(g instanceof BoolType))
      {
         System.err.println("line " + conditional.line + " guard is of type " + g);
         ok = false;
      }
      
      
      boolean thenReturns = validStatement(conditional.thenBlock);
      boolean elseReturns = validStatement(conditional.elseBlock);
      
      return thenReturns && elseReturns;
   }
   
   
   private static boolean validDeleteStatement(DeleteStatement delete)
   {
      Type s = getExpressionType(delete.expression);
      
      if (s == null)
      {
         ok = false;
      }
      else if (!(s instanceof StructType))
      {
         System.err.println("line " + delete.line + " cannot delete values of type " + s);
         ok = false;
      }
      
      delete.type = (StructType)s;
      
      return false;
   }
   
   
   private static boolean validInvocationStatement(InvocationStatement invocation)
   {
      if (getExpressionType(invocation.expression) == null)
         ok = false;
      
      return false;
   }
   
   
   private static boolean validPrintLnStatement(PrintLnStatement println)
   {
      Type q = getExpressionType(println.expression);
      
      
      if (q == null)
      {
         ok = false;
      }
      else if (!(q instanceof IntType))
      {
         System.err.println("line " + println.line + " cannot print value of type " + q);
         ok = false;
      }
      
      return false;
   }
   
   
   private static boolean validPrintStatement(PrintStatement print)
   {
      Type q = getExpressionType(print.expression);
      
      
      if (q == null)
      {
         ok = false;
      }
      else if (!(q instanceof IntType))
      {
         System.err.println("line " + print.line + " cannot print value of type " + q);
         ok = false;
      }
      
      return false;
   }
   
   
   private static boolean validReturnEmptyStatement(ReturnEmptyStatement returnEmpty)
   {
      if (!(returnType instanceof VoidType))
      {
         System.err.println("line " + returnEmpty.line + " cannot return void, expected " + returnType);
         ok = false;
      }
      
      
      return true;
   }
   
   
   private static boolean validReturnStatement(ReturnStatement returnStatement)
   {
      Type r = getExpressionType(returnStatement.expression);
      
      
      if (r == null)
      {
         ok = false;
      }
      else if (!returnType.equals(r))
      {
         System.err.println("line " + returnStatement.line + " cannot return " + r + ", expected " + returnType);
         ok = false;
      }
      
      
      returnStatement.type = r;
      returnStatement.funcName = functionName;
      
      return true;
   }
   
   
   private static boolean validWhileStatement(WhileStatement whileStatement)
   {
      Type g = getExpressionType(whileStatement.guard);
      
      
      if (g == null)
      {
         ok = false;
      }
      else if (!(g instanceof BoolType))
      {
         System.err.println("line " + whileStatement.line + " guard is of type " + g);
         ok = false;
      }
      
      
      validStatement(whileStatement.body);
      
      return false;
   }
   
   
   
   private static Type getLvalueType(Lvalue lvalue)
   {
      if (lvalue instanceof LvalueDot)
         return getLvalueDotType((LvalueDot)lvalue);
      
      if (lvalue instanceof LvalueId)
         return getLvalueIdType((LvalueId)lvalue);
      
      
      System.err.println("I have no idea what went wrong in getLvalueType: " + lvalue.getClass().getName());
      return null;
   }
   
   
   private static Type getLvalueDotType(LvalueDot lvalue)
   {
      Type s = getExpressionType(lvalue.left);
      
      
      if (s == null)
         return null;
      
      
      if (!(s instanceof StructType))
      {
         System.err.println("line " + lvalue.line + " attempt to index a(n) " + s);
         return null;
      }
      
      
      StructType ss = (StructType)s;
      Map<String, Type> structDecl = TypeChecker.types.get(ss.name);
      lvalue.type = ss;
      
      
      if (structDecl == null)
      {
         System.err.println("I have no idea what went wrong in getLvalueDotType: attempted to access struct " + ss.name);
         return null;
      }
      
      
      if (!structDecl.containsKey(lvalue.id))
      {
         System.err.println("line " + lvalue.line + " struct " + ss.name + " does not contain field " + lvalue.id);
         return null;
      }
      
      return structDecl.get(lvalue.id);
   }
   
   
   private static Type getLvalueIdType(LvalueId lvalue)
   {
      lvalue.funcName = functionName;
      
      if (locals.containsKey(lvalue.id))
         return lvalue.type = locals.get(lvalue.id);
      
      if (TypeChecker.globals.containsKey(lvalue.id))
      {
         lvalue.global = true;
         return lvalue.type = TypeChecker.globals.get(lvalue.id);
      }
      
      System.err.println("line " + lvalue.line + " variable " + lvalue.id + " not declared");
      return null;
   }
   
   
   
   private static Type getExpressionType(Expression exp)
   {
      return exp.type = getExpressionTypeSelect(exp);
   }
   
   private static Type getExpressionTypeSelect(Expression exp)
   {
      if (exp instanceof BinaryExpression)
         return getBinaryExpressionType((BinaryExpression)exp);
      
      if (exp instanceof DotExpression)
         return getDotExpressionType((DotExpression)exp);
      
      if (exp instanceof FalseExpression)
         return getFalseExpressionType((FalseExpression)exp);
      
      if (exp instanceof IdentifierExpression)
         return getIdentifierExpressionType((IdentifierExpression)exp);
      
      if (exp instanceof IntegerExpression)
         return getIntegerExpressionType((IntegerExpression)exp);
      
      if (exp instanceof InvocationExpression)
         return getInvocationExpressionType((InvocationExpression)exp);
      
      if (exp instanceof NewExpression)
         return getNewExpressionType((NewExpression)exp);
      
      if (exp instanceof NullExpression)
         return getNullExpressionType((NullExpression)exp);
      
      if (exp instanceof ReadExpression)
         return getReadExpressionType((ReadExpression)exp);
      
      if (exp instanceof TrueExpression)
         return getTrueExpressionType((TrueExpression)exp);
      
      if (exp instanceof UnaryExpression)
         return getUnaryExpressionType((UnaryExpression)exp);
      
      System.err.println("I have no idea what went wrong in getExpressionType: " + exp.getClass().getName());
      return null;
   }
   
   
   private static Type getBinaryExpressionType(BinaryExpression exp)
   {
      Type l = exp.operandType = getExpressionType(exp.left);
      Type r = getExpressionType(exp.right);
      
      BinaryExpression.Operator op = exp.operator;
      
      Type b = null;
      
      
      if (l == null || r == null)
         return null;
      
      
      switch (op)
      {
         case TIMES:
         case DIVIDE:
         case PLUS:
         case MINUS:
         
            if ((l instanceof IntType) && (r instanceof IntType))
               b = new IntType();
            break;
         
         case LT:
         case LE:
         case GT:
         case GE:
            if ((l instanceof IntType) && (r instanceof IntType))
               b = new BoolType();
            break;
         
         case EQ:
         case NE:
            if (l.equals(r))
               b = new BoolType();
            break;
         
         case AND:
         case OR:
            if ((l instanceof BoolType) && (r instanceof BoolType))
               b = new BoolType();
            break;
      }
      
      
      if (b == null)
         System.err.println("line " + exp.line + " attempt to perform " + op + " on " + l + " and " + r);
      
      return b;
   }
   
   
   private static Type getDotExpressionType(DotExpression exp)
   {
      Type s = getExpressionType(exp.left);
      
      
      if (s == null)
         return null;
      
      
      if (!(s instanceof StructType))
      {
         System.err.println("line " + exp.line + " attempt to index a(n) " + s);
         return null;
      }
      
      
      StructType ss = (StructType)s;
      Map<String, Type> structDecl = TypeChecker.types.get(ss.name);
      
      if (structDecl == null)
      {
         System.err.println("I have no idea what went wrong in getDotExpressionType: attempted to access struct " + ss.name);
         return null;
      }
      
      
      if (!structDecl.containsKey(exp.id))
      {
         System.err.println("line " + exp.line + " struct " + ss.name + " does not contain field " + exp.id);
         return null;
      }
      
      return structDecl.get(exp.id);
   }
   
   
   private static Type getFalseExpressionType(FalseExpression exp)
   {
      return new BoolType();
   }
   
   
   private static Type getIdentifierExpressionType(IdentifierExpression exp)
   {
      exp.funcName = functionName;
      
      if (locals.containsKey(exp.id))
         return locals.get(exp.id);
      
      if (TypeChecker.globals.containsKey(exp.id))
      {
         exp.global = true;
         return TypeChecker.globals.get(exp.id);
      }
      
      System.err.println("line " + exp.line + " variable " + exp.id + " not declared");
      return null;
   }
   
   
   private static Type getIntegerExpressionType(IntegerExpression exp)
   {
      try
      {
         Integer.parseInt(exp.value);
      }
      catch (NumberFormatException ne)
      {
         System.err.println("line " + exp.line + " integer out of range");
         return null;
      }
      return new IntType();
   }
   
   
   private static Type getInvocationExpressionType(InvocationExpression exp)
   {
      if (!TypeChecker.functions.containsKey(exp.name))
      {
         System.err.println("line " + exp.line + " function " + exp.name + " not declared");
         return null;
      }
      
      
      Function func = TypeChecker.functions.get(exp.name);
      int f = func.params.size();
      int e = exp.arguments.size();
      
      if (f != e)
      {
         System.err.println("line " + exp.line + " wrong arity: function " + exp.name + " expects " + f + ", received " + e);
         return null;
      }
      
      boolean ok = true;
      
      for (int i = 0; i < e; i++)
      {
         Type fp = func.params.get(i).type;
         Type ea = getExpressionType(exp.arguments.get(i));
         
         
         if (!fp.equals(ea))
         {
            System.err.println("line " + exp.line + " wrong type for argument " + i + ", should be " + fp + " but is " + ea);
            ok = false;
         }
      }
      
      if (!ok)
         return null;
      
      
      return func.retType;
   }
   
   
   private static Type getNewExpressionType(NewExpression exp)
   {
      if (TypeChecker.types.containsKey(exp.id))
         return new StructType(exp.id);
      
      System.err.println("line " + exp.line + " struct " + exp.id + " not declared");
      return null;
   }
   
   
   private static Type getNullExpressionType(NullExpression exp)
   {
      return new NullType();
   }
   
   
   private static Type getReadExpressionType(ReadExpression exp)
   {
      return new IntType();
   }
   
   
   private static Type getTrueExpressionType(TrueExpression exp)
   {
      return new BoolType();
   }
   
   
   private static Type getUnaryExpressionType(UnaryExpression exp)
   {
      Type o = getExpressionType(exp.operand);
      UnaryExpression.Operator op = exp.operator;
      
      if ((op == UnaryExpression.Operator.NOT && (o instanceof BoolType))
            || (op == UnaryExpression.Operator.MINUS && (o instanceof IntType)))
         return o;
      
      
      System.err.println("line " + exp.line + " attempt to perform " + op + " on " + o);
      return null;
   }
}
