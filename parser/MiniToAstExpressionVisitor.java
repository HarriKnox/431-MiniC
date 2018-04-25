package parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

import ast.*;

public class MiniToAstExpressionVisitor
   extends MiniBaseVisitor<Expression>
{
   @Override
   public Expression visitIntegerExpr(MiniParser.IntegerExprContext ctx)
   {
      return new IntegerExpression(
         ctx.getStart().getLine(),
         ctx.INTEGER().getText());
   }

   @Override
   public Expression visitTrueExpr(MiniParser.TrueExprContext ctx)
   {
      return new TrueExpression(
         ctx.getStart().getLine());
   }

   @Override
   public Expression visitIdentifierExpr(MiniParser.IdentifierExprContext ctx)
   {
      return new IdentifierExpression(
         ctx.getStart().getLine(),
         ctx.ID().getText());
   }

   @Override
   public Expression visitArithmeticExpr(MiniParser.BinaryExprContext ctx)
   {
      switch (ctx.op.getText())
      {
         /* Arithmetic */
         case "*":
            return new TimesExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         
         case "/":
            return new DivideExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case "+":
            return new PlusExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case "-":
            return new MinusExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         /* Relational */
         case "<":
            return new LessThanExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case ">":
            return new GreaterThanExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case "<=":
            return new LessEqualsExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case ">=":
            return new GreaterEqualsExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         /* Equality */
         case "==":
            return new EqualsExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case "!=":
            return new NotEqualsExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         /* Logical */
         case "&&":
            return new AndExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
         
         case "||":
            return new OrExpression(
               ctx.op.getLine(),
               visit(ctx.lft),
               visit(ctx.rht));
      }
      
      return null;
   }

   @Override
   public Expression visitNewExpr(MiniParser.NewExprContext ctx)
   {
      return new NewExpression(
         ctx.getStart().getLine(),
         ctx.ID().getText());
   }

   @Override
   public Expression visitDotExpr(MiniParser.DotExprContext ctx)
   {
      return new DotExpression(
         ctx.getStart().getLine(),
         visit(ctx.expression()),
         ctx.ID().getText());
   }

   @Override
   public Expression visitUnaryExpr(MiniParser.UnaryExprContext ctx)
   {
      return new UnaryExpression(
         ctx.op.getLine(),
         ctx.op.getText(),
         visit(ctx.expression()));
   }

   @Override
   public Expression visitInvocationExpr(
      MiniParser.InvocationExprContext ctx)
   {
      return new InvocationExpression(
         ctx.getStart().getLine(),
         ctx.ID().getText(),
         gatherArguments(ctx.arguments()));
   }

   @Override
   public Expression visitFalseExpr(MiniParser.FalseExprContext ctx)
   {
      return new FalseExpression(
         ctx.getStart().getLine());
   }

   @Override
   public Expression visitNullExpr(MiniParser.NullExprContext ctx)
   {
      return new NullExpression(
         ctx.getStart().getLine());
   }

   private List<Expression> gatherArguments(
      MiniParser.ArgumentsContext ctx)
   {
      List<Expression> arguments = new ArrayList<>();

      for (MiniParser.ExpressionContext ectx : ctx.expression())
      {
         arguments.add(visit(ectx));
      }

      return arguments;
   }

   @Override
   public Expression visitNestedExpr(MiniParser.NestedExprContext ctx)
   {
      return visit(ctx.expression());
   }

   @Override
   public Expression defaultResult()
   {
      return new NullExpression(-1);
   }
}
