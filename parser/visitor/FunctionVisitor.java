package parser.visitor;


import parser.MiniBaseVisitor;

import ast.declaration.Function;


import static parser.MiniParser.FunctionContext;


public class FunctionVisitor extends MiniBaseVisitor<Function>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();
   private final DeclVisitor declVisitor = new DeclVisitor();
   private final VariablesVisitor variablesVisitor = new VariablesVisitor();
   private final StatementVisitor statementVisitor = new StatementVisitor();


   @Override
   public Function visitFunction(FunctionContext ctx)
   {
      return new Function(
            ctx.getStart().getLine(),
            ctx.ID().getText(),
            typeVisitor.visit(ctx.returnType()),
            declVisitor.visit(ctx.parameters()),
            variablesVisitor.visit(ctx.variables()),
            statementVisitor.visit(ctx.statementList()));
   }
}
