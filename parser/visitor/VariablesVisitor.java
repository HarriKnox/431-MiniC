package parser.visitor;


import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.MiniBaseVisitor;

import ast.declaration.Variable;
import ast.declaration.Variables;

import ast.type.Type;


import static parser.MiniParser.VariableContext;
import static parser.MiniParser.VariablesContext;


public class VariablesVisitor extends MiniBaseVisitor<Variables>
{
   private final TypeVisitor typeVisitor = new TypeVisitor();


   @Override
   public Variables visitVariables(VariablesContext ctx)
   {
      List<Variable> decls = new LinkedList<>();
      int index = 0;


      for (VariableContext vctx : ctx.variable())
      {
         Type type = typeVisitor.visit(vctx.type());

         for (TerminalNode node : vctx.ID())
         {
            decls.add(new Variable(
                  node.getSymbol().getLine(),
                  node.getText(),
                  type,
                  index++));
         }
      }


      return new Variables(decls);
   }
}
