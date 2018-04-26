package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Function;
import ast.statement.BlockStatement;
import ast.type.VoidType;

public class FunctionVisitor
   extends MiniBaseVisitor<Function>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();
   private final DeclVisitor declVisitor = new DeclVisitor();
   private final DeclarationsVisitor declarationsVisitor =
      new DeclarationsVisitor();
   private final StatementVisitor statementVisitor =
      new StatementVisitor();

   @Override
   public Function visitFunction(MiniParser.FunctionContext ctx)
   {
      return new Function(
         ctx.getStart().getLine(),
         ctx.ID().getText(),
         declVisitor.visit(ctx.parameters()),
         typeVisitor.visit(ctx.returnType()),
         declarationsVisitor.visit(ctx.declarations()),
         statementVisitor.visit(ctx.statementList()));
   }

   @Override
   protected Function defaultResult()
   {
      return new Function(-1, "invalid", declarationsVisitor.defaultResult(),
         new VoidType(), declarationsVisitor.defaultResult(),
         BlockStatement.emptyBlock());
   }
}
