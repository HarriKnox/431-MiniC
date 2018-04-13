package analyzer;

import ast.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.common.lang3.tuple.Pair;


public class FunctionChecker
{
   private static final Map<String, Pair<Type, List<Type>>> signatures = new HashMap<>();
   
   
   static void check(List<Function> funcs)
   {
      Map<String, Function> validFuncs = new LinkedHashMap<>();
      
      
      for (Function func : funcs)
      {
         if (validFuncs.containsKey(func.name))
            ErrorPrinter.duplicate(func.line, "function " + func.name);
         
         else
            validFuncs.put(func.name, func);
      }
      
      
      for (Function validFunc : validFuncs.values())
      {
         Map<String, Type> localScope = new HashMap<>();
         
         for (Declaration param : validFunc.params)
         {
            if (!StructChecker.validType(param.type))
               ErrorPrinter.unknownStruct(param.line, ((StructType)param.type).name);
            
            else if (localScope.containsKey(param.name))
               ErrorPrinter.duplicate(param.line, "parameter " + param.name);
            
            else
               localScope.put(param.name, param.type);
         }
         
         for (Declaration local : validFunc.locals)
         {
            if (!StructChecker.validType(local.type))
               ErrorPrinter.unknownStruct(local.line, ((StructType)local.type).name);
            
            else if (localScope.containsKey(local.name))
               ErrorPrinter.duplicate(local.line, "parameter " + local.name);
            
            else
               localScope.put(local.name, local.type);
         }
         
         
         boolean returns = validStatement(func.body, func.retType);
         
         if (!(validFunc.retType instanceof VoidType) && !returns)
            ErrorPrinter.nonReturn(func.line, func.name);
      }
   }
   
   
   private static boolean validStatement(Statement body, Type retType)
   {
      if (body instanceof AssignmentStatement)
         return validAssignmentStatement((AssignmentStatement)body, retType);
      
      if (body instanceof BlockStatement)
         return validBlockStatement((BlockStatement)body, retType);
      
      if (body instanceof ConditionalStatement)
         return validConditionalStatement((ConditionalStatement)body, retType);
      
      if (body instanceof DeleteStatement)
         return validDeleteStatement((DeleteStatement)body, retType);
      
      if (body instanceof InvocationStatement)
         return validInvocationStatement((InvocationStatement)body, retType);
      
      if (body instanceof PrintLnStatement)
         return validPrintLnStatement((PrintLnStatement)body, retType);
      
      if (body instanceof PrintStatement)
         return validPrintStatement((PrintStatement)body, retType);
      
      if (body instanceof ReturnEmptyStatement)
         return validReturnEmptyStatement((ReturnEmptyStatement)body, retType);
      
      if (body instanceof ReturnStatement)
         return validReturnStatement((ReturnStatement)body, retType);
      
      if (body instanceof WhileStatement)
         return validWhileStatement((WhileStatement)body, retType);
      
      ErrorPrinter.IDK("validFunction", body.getClass().getName());
      return false;
   }
   
   
   private static boolean validAssignmentStatement(AssignmentStatement assignment, Type retType)
   {
      /* Left is same type as right */
      Type l = getLvalueType(assignment.target);
      Type r = getExpressionType(assignment.source);
      
      
      if (l != null && r != null && !l.equals(r))
         ErrorPrinter.unexpectedType(assignment.line, l.toString(), "assignment", r.toString());
      
      return false;
   }
   
   
   private static boolean validBlockStatement(BlockStatement block, Type retType)
   {
      boolean returns = false;
      
      
      for (Statement statement : block.statements)
      {
         if (returns)
         {
            ErrorPrinter.printLine(((LinedElement)statement).line, "cannot have code after a return", true);
            break;
         }
         
         returns = validStatement(statement, retType);
      }
      
      return returns;
   }
   
   
   private static boolean validConditionalStatement(ConditionalStatement conditional, Type retType)
   {
      Type g = getExpressionType(conditional.guard);
      
      
      if (g != null && !(g instanceof BoolType))
         ErrorPrinter.unexpectedType(conditional.line, "bool", "if-guard", g.toString());
      
      
      boolean thenReturns = validStatement(conditional.thenBlock, retType);
      boolean elseReturns = validStatement(conditional.elseBlock, retType);
      
      return thenReturns && elseReturns;
   }
   
   
   private static boolean validDeleteStatement(DeleteStatement delete, Type retType)
   {
      Type s = getExpressionType(delete.expression);
      
      if (s != null && !(s instanceof StructType))
         ErrorPrinter.unexpectedType(delete.line, "struct", "delete", s.toString());
      
      return false;
   }
   
   
   private static boolean validInvocationStatement(InvocationStatement invocation, Type retType)
   {
      getExpressionType(invocation.expression);
      
      return false;
   }
   
   
   private static boolean validPrintLnStatement(PrintLnStatement println, Type retType)
   {
      Type q = getExpressionType(println.expression);
      
      
      if (q != null && !(q instanceof IntType))
         ErrorPrinter.unexpectedType(println.line, "int", "print", q.toString());
      
      return false;
   }
   
   
   private static boolean validPrintStatement(PrintStatement print, Type retType)
   {
      Type q = getExpressionType(print.expression);
      
      
      if (q != null && !(q instanceof IntType))
         ErrorPrinter.unexpectedType(println.line, "int", "print", q.toString());
      
      return false;
   }
   
   
   private static boolean validReturnEmptyStatement(ReturnEmptyStatement returnEmpty, Type retType)
   {
      if (!(retType instanceof VoidType))
         ErrorPrinter.unexpectedType(returnEmpty.line, retType.toString(), "return", "void");
      
      return true;
   }
   
   
   private static boolean validReturnStatement(ReturnStatement returnStatement, Type retType)
   {
      Type r = getExpressionType(returnStatement.expression);
      
      if (r != null && !retType.equals(r))
         ErrorPrinter.unexpectedType(returnStatement.line, retType.toString(), "return", r.toString());
      
      return true;
   }
   
   
   private static boolean validWhileStatement(WhileStatement whileStatement, Type retType)
   {
      Type g = getExpressionType(whileStatement.guard);
      
      
      if (g != null && !(g instanceof BoolType))
         ErrorPrinter.unexpectedType(whileStatement.line, "bool", "while-guard", g.toString());
      
      
      validStatement(whileStatement.body, retType);
      
      return false;
   }
   
   
   
   private static Type getLvalueType(Lvalue lvalue)
   {
      if (lvalue instanceof LvalueDot)
         return getLvalueDotType((LvalueDot)lvalue);
      
      if (lvalue instanceof LvalueId)
         return getLvalueIdType((LvalueId)lvalue);
      
      
      ErrorPrinter.IDK("getLvalueType", lvalue.getClass().getName());
      return null;
   }
   
   
   private static Type getLvalueDotType(LvalueDot lvalue)
   {
      Type s = getExpressionType(lvalue.left);
      
      
      if (s == null)
         return null;
      
      
      if (!(s instanceof StructType))
      {
         ErrorPrinter.printLine(lvalue.line, "attempt to index a(n) " + s);
         return null;
      }
      
      
      StructType ss = (StructType)s;
      Map<String, Type> structDecl = types.get(ss.name);
      
      if (structDecl == null)
      {
         ErrorPrinter.IDK("getLvalueDotType", "attempted to access struct " + ss.name);
         return null;
      }
      
      
      if (!structDecl.containsKey(lvalue.id))
      {
         ErrorPrinter.noField(lvalue.line, ss.name, lvalue.id);
         return null;
      }
      
      return structDecl.get(lvalue.id);
   }
   
   
   private static Type getLvalueIdType(LvalueId lvalue)
   {
      if (locals.containsKey(lvalue.id))
         return locals.get(lvalue.id);
      
      if (globals.containsKey(lvalue.id))
         return globals.get(lvalue.id);
      
      ErrorPrinter(lvalue.line, "variable " + lvalue.id);
      return null;
   }
   
   
   
   private static Type getExpressionType(Expression exp)
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
      
      ErrorPrinter.IDK("getExpressionType", exp.getClass().getName());
      return null;
   }
   
   
   private static Type getBinaryExpressionType(BinaryExpression exp)
   {
      Type l = getExpressionType(exp.left);
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
         ErrorPrinter.printLine(exp.line, "attempt to perform " + op + " on " + l + " and " + r);
      
      return b;
   }
   
   
   private static Type getDotExpressionType(DotExpression exp)
   {
      Type s = getExpressionType(exp.left);
      
      
      if (s == null)
         return null;
      
      
      if (!(s instanceof StructType))
      {
         ErrorPrinter.printLine(lvalue.line, "attempt to index a(n) " + s);
         return null;
      }
      
      
      StructType ss = (StructType)s;
      Map<String, Type> structDecl = types.get(ss.name);
      
      if (structDecl == null)
      {
         ErrorPrinter.IDK("getDotExpressionType", "attempted to access struct " + ss.name);
         return null;
      }
      
      
      if (!structDecl.containsKey(exp.id))
      {
         ErrorPrinter.noField(exp.line, ss.name, exp.id);
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
      if (locals.containsKey(exp.id))
         return locals.get(exp.id);
      
      if (globals.containsKey(exp.id))
         return globals.get(exp.id);
      
      ErrorPrinter.undeclared(exp.line, "variable " + exp.id);
      return null;
   }
   
   
   private static Type getIntegerExpressionType(IntegerExpression exp)
   {
      return new IntType();
   }
   
   
   private static Type getInvocationExpressionType(InvocationExpression exp)
   {
      if (!functions.containsKey(exp.name))
      {
         ErrorPrinter.undeclared(exp.line, "function " + exp.name);
         return null;
      }
      
      
      Function func = functions.get(exp.name);
      int f = func.params.size();
      int e = exp.arguments.size();
      
      if (f != e)
      {
         ErrorPrinter.printLine(exp.line, "wrong arity: function " + exp.name + " expects " + f + ", received " + e);
         return null;
      }
      
      boolean ok = true;
      
      for (int i = 0; i < e; i++)
      {
         Type fp = func.params.get(i).type;
         Type ea = getExpressionType(exp.arguments.get(i));
         
         
         if (!fp.equals(ea))
         {
            ErrorPrinter.unexpectedType(exp.line, fp.toString(), "argument " + i, ea.toString());
            ok = false;
         }
      }
      
      return ok ? func.retType : null;
   }
   
   
   private static Type getNewExpressionType(NewExpression exp)
   {
      if (types.containsKey(exp.id))
         return new StructType(exp.id);
      
      ErrorPrinter.unknownStruct(exp.line, exp.id);
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
      
      
      ErrorPrinter.printLine(exp.line, "attempt to perform " + op + " on " + o);
      return null;
   }
}
