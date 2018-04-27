package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import parser.MiniBaseVisitor;

import ast.expression.Expression;
import ast.expression.InvocationExpression;

import ast.lvalue.Lvalue;
import ast.lvalue.LvalueDot;
import ast.lvalue.LvalueId;

import ast.statement.AssignmentStatement;
import ast.statement.BlockStatement;
import ast.statement.ConditionalStatement;
import ast.statement.DeleteStatement;
import ast.statement.InvocationStatement;
import ast.statement.PrintLnStatement;
import ast.statement.PrintStatement;
import ast.statement.ReturnEmptyStatement;
import ast.statement.ReturnStatement;
import ast.statement.Statement;
import ast.statement.WhileStatement;


import static parser.MiniParser.AssignmentContext;
import static parser.MiniParser.BlockContext;
import static parser.MiniParser.ConditionalContext;
import static parser.MiniParser.DeleteContext;
import static parser.MiniParser.ExpressionContext;
import static parser.MiniParser.InvocationContext;
import static parser.MiniParser.LvalueContext;
import static parser.MiniParser.LvalueDotContext;
import static parser.MiniParser.LvalueIdContext;
import static parser.MiniParser.NestedBlockContext;
import static parser.MiniParser.PrintLnContext;
import static parser.MiniParser.PrintContext;
import static parser.MiniParser.ReturnContext;
import static parser.MiniParser.StatementContext;
import static parser.MiniParser.StatementListContext;
import static parser.MiniParser.WhileContext;


public class StatementVisitor extends MiniBaseVisitor<Statement>
{
   private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();


   @Override
   public Statement visitAssignment(AssignmentContext ctx)
   {
      return new AssignmentStatement(
            ctx.getStart().getLine(),
            visitLvalue(ctx.lvalue()),
            expressionVisitor.visit(ctx.expression()));
   }


   @Override
   public Statement visitBlock(BlockContext ctx)
   {
      return visit(ctx.statementList());
   }


   @Override
   public Statement visitConditional(ConditionalContext ctx)
   {
      return new ConditionalStatement(
            ctx.getStart().getLine(),
            expressionVisitor.visit(ctx.expression()),
            visit(ctx.thenBlock),
            ctx.elseBlock != null
                  ? visit(ctx.elseBlock)
                  : new BlockStatement(
                        -1,
                        new LinkedList<>());
   }


   @Override
   public Statement visitDelete(DeleteContext ctx)
   {
      return new DeleteStatement(
            ctx.getStart().getLine(),
            expressionVisitor.visit(ctx.expression()));
   }


   @Override
   public Statement visitInvocation(InvocationContext ctx)
   {
      List<Expression> arguments = new LinkedList<>();


      for (ExpressionContext ectx : ctx.arguments().expression())
         arguments.add(expressionVisitor.visit(ectx));


      return new InvocationStatement(
            ctx.getStart().getLine(),
            new InvocationExpression(
                  ctx.getStart().getLine(),
                  ctx.ID().getText(),
                  arguments));
   }


   @Override
   public Statement visitNestedBlock(NestedBlockContext ctx)
   {
      return visit(ctx.block());
   }


   @Override
   public Statement visitPrintLn(PrintLnContext ctx)
   {
      return new PrintLnStatement(
            ctx.getStart().getLine(),
            expressionVisitor.visit(ctx.expression()));
   }


   @Override
   public Statement visitPrint(PrintContext ctx)
   {
      return new PrintStatement(
            ctx.getStart().getLine(),
            expressionVisitor.visit(ctx.expression()));
   }


   @Override
   public Statement visitReturn(ReturnContext ctx)
   {
      if (ctx.expression() != null)
         return new ReturnStatement(ctx.getStart().getLine(),
               expressionVisitor.visit(ctx.expression()));

      else
         return new ReturnEmptyStatement(ctx.getStart().getLine());
   }


   @Override
   public Statement visitStatementList(StatementListContext ctx)
   {
      List<Statement> statements = new LinkedList<>();


      /* Gather all statements in the block */
      for (StatementContext sctx : ctx.statement())
      {
         Statement stmt = visit(sctx);


         /* If a statement is an empty block, ignore it */
         if (!(stmt instanceof BlockStatement)
               || !((BlockStatement)stmt).isEmpty())
         {
            statements.add(stmt);
         }
      }


      /* If there's only one thing in the block, return that thing */
      if (statements.size() == 1)
         return statements.get(0);


      return new BlockStatement(ctx.getStart().getLine(), statements);
   }


   @Override
   public Statement visitWhile(WhileContext ctx)
   {
      return new WhileStatement(
            ctx.getStart().getLine(),
            expressionVisitor.visit(ctx.expression()),
            visit(ctx.statement()));
   }


   private Lvalue visitLvalue(LvalueContext ctx)
   {
      if (ctx instanceof LvalueIdContext)
      {
         LvalueIdContext lctx = (LvalueIdContext)ctx;
         return new LvalueId(
               lctx.getStart().getLine(),
               lctx.ID().getText());
      }
      else
      {
         LvalueDotContext lctx = (LvalueDotContext)ctx;
         return new LvalueDot(
               lctx.getStart().getLine(),
               visitLvalue(lctx.lvalue()),
               lctx.ID().getText());
      }
   }
}
