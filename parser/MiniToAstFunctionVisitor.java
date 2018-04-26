package parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

import ast.declaration.Function;
import ast.statement.BlockStatement;
import ast.type.VoidType;

public class MiniToAstFunctionVisitor
   extends MiniBaseVisitor<Function>
{
   private final MiniToAstTypeVisitor typeVisitor = new MiniToAstTypeVisitor();
   private final MiniToAstDeclVisitor declVisitor = new MiniToAstDeclVisitor();
   private final MiniToAstDeclarationsVisitor declarationsVisitor =
      new MiniToAstDeclarationsVisitor();
   private final MiniToAstStatementVisitor statementVisitor =
      new MiniToAstStatementVisitor();

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
