package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.expression.BoolExpression;
import ast.expression.DotExpression;
import ast.expression.Expression;
import ast.expression.IdentifierExpression;
import ast.expression.IntExpression;
import ast.expression.InvocationExpression;
import ast.expression.NewExpression;
import ast.expression.NullExpression;
import ast.expression.ReadExpression;

import ast.expression.binary.BinaryExpression;

import ast.expression.binary.arithmetic.ArithmeticExpression;
import ast.expression.binary.arithmetic.DivideExpression;
import ast.expression.binary.arithmetic.MinusExpression;
import ast.expression.binary.arithmetic.PlusExpression;
import ast.expression.binary.arithmetic.TimesExpression;

import ast.expression.binary.equality.EqualityExpression;
import ast.expression.binary.equality.EqualsExpression;
import ast.expression.binary.equality.NotEqualsExpression;

import ast.expression.binary.logical.AndExpression;
import ast.expression.binary.logical.LogicalExpression;
import ast.expression.binary.logical.OrExpression;

import ast.expression.binary.relational.GreaterEqualsExpression;
import ast.expression.binary.relational.GreaterThanExpression;
import ast.expression.binary.relational.LessEqualsExpression;
import ast.expression.binary.relational.LessThanExpression;
import ast.expression.binary.relational.RelationalExpression;

import ast.expression.unary.NegateExpression;
import ast.expression.unary.NotExpression;
import ast.expression.unary.UnaryExpression;


import static parser.MiniParser.BinaryExprContext;
import static parser.MiniParser.BoolExprContext;
import static parser.MiniParser.DotExprContext;
import static parser.MiniParser.ExpressionContext;
import static parser.MiniParser.IdentifierExprContext;
import static parser.MiniParser.IntExprContext;
import static parser.MiniParser.InvocationExprContext;
import static parser.MiniParser.NestedExprContext;
import static parser.MiniParser.NewExprContext;
import static parser.MiniParser.NullExprContext;
import static parser.MiniParser.ReadExprContext;
import static parser.MiniParser.UnaryExprContext;


public class ExpressionVisitor extends MiniBaseVisitor<Expression>
{
   @Override
   public Expression visitBinaryExpr(BinaryExprContext ctx)
   {
      switch (ctx.op.getText())
      {
         /* Arithmetic */
         case "*":
            return new TimesExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "/":
            return new DivideExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "+":
            return new PlusExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "-":
            return new MinusExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         /* Relational */
         case "<":
            return new LessThanExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case ">":
            return new GreaterThanExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "<=":
            return new LessEqualsExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case ">=":
            return new GreaterEqualsExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         /* Equality */
         case "==":
            return new EqualsExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "!=":
            return new NotEqualsExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         /* Logical */
         case "&&":
            return new AndExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));


         case "||":
            return new OrExpression(
                  ctx.op,
                  visit(ctx.lft),
                  visit(ctx.rht));
      }


      return null;
   }


   @Override
   public Expression visitBoolExpr(BoolExprContext ctx)
   {
      return new BoolExpression(
            ctx.getStart(),
            ctx.getStart().getText().equals("true"));
   }


   @Override
   public Expression visitDotExpr(DotExprContext ctx)
   {
      return new DotExpression(
            ctx.getStart(),
            visit(ctx.expression()),
            ctx.ID().getText());
   }


   @Override
   public Expression visitIdentifierExpr(IdentifierExprContext ctx)
   {
      return new IdentifierExpression(
            ctx.getStart(),
            ctx.ID().getText());
   }


   @Override
   public Expression visitIntExpr(IntExprContext ctx)
   {
      return new IntExpression(
            ctx.getStart(),
            ctx.INTEGER().getText());
   }


   @Override
   public Expression visitInvocationExpr(InvocationExprContext ctx)
   {
      List<Expression> arguments = new LinkedList<>();


      for (ExpressionContext ectx : ctx.arguments().expression())
         arguments.add(visit(ectx));


      return new InvocationExpression(
            ctx.getStart(),
            ctx.ID().getText(),
            arguments);
   }


   @Override
   public Expression visitNewExpr(NewExprContext ctx)
   {
      return new NewExpression(
            ctx.getStart(),
            ctx.ID().getText());
   }


   @Override
   public Expression visitNestedExpr(NestedExprContext ctx)
   {
      return visit(ctx.expression());
   }


   @Override
   public Expression visitNullExpr(NullExprContext ctx)
   {
      return new NullExpression(ctx.getStart());
   }


   @Override
   public Expression visitReadExpr(ReadExprContext ctx)
   {
      return new ReadExpression(ctx.getStart());
   }


   @Override
   public Expression visitUnaryExpr(UnaryExprContext ctx)
   {
      switch (ctx.op.getText())
      {
         case "!":
            return new NotExpression(
                  ctx.op,
                  visit(ctx.expression()));


         case "-":
            Expression exp = visit(ctx.expression());
            
            if (exp instanceof IntExpression)
               return ((IntExpression)exp).negate();
            
            return new NegateExpression(
                  ctx.op,
                  exp);
      }


      return null;
   }
}
