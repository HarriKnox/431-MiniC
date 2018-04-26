package parser.visitor;

import parser.MiniBaseVisitor;
import parser.MiniParser;

import ast.type.BoolType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.Type;
import ast.type.VoidType;

public class TypeVisitor
   extends MiniBaseVisitor<Type>
{
   @Override
   public Type visitIntType(MiniParser.IntTypeContext ctx)
   {
      return new IntType();
   }

   @Override
   public Type visitBoolType(MiniParser.BoolTypeContext ctx)
   {
      return new BoolType();
   }

   @Override
   public Type visitStructType(MiniParser.StructTypeContext ctx)
   {
      return new StructType(ctx.ID().getText());
   }

   @Override
   public Type visitReturnTypeReal(MiniParser.ReturnTypeRealContext ctx)
   {
      return visit(ctx.type());
   }

   @Override
   public Type visitReturnTypeVoid(MiniParser.ReturnTypeVoidContext ctx)
   {
      return new VoidType();
   }

   @Override
   protected Type defaultResult()
   {
      return new VoidType();
   }
}
