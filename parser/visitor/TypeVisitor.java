package parser.visitor;

import parser.MiniBaseVisitor;

import ast.type.BoolType;
import ast.type.IntType;
import ast.type.StructType;
import ast.type.Type;
import ast.type.VoidType;


import static parser.MiniParser.BoolTypeContext;
import static parser.MiniParser.IntTypeContext;
import static parser.MiniParser.StructTypeContext;
import static parser.MiniParser.ReturnTypeRealContext;
import static parser.MiniParser.ReturnTypeVoidContext;


public class TypeVisitor extends MiniBaseVisitor<Type>
{
   @Override
   public Type visitBoolType(BoolTypeContext ctx)
   {
      return new BoolType();
   }
   
   
   @Override
   public Type visitIntType(IntTypeContext ctx)
   {
      return new IntType();
   }


   @Override
   public Type visitStructType(StructTypeContext ctx)
   {
      return new StructType(ctx.ID().getText());
   }


   @Override
   public Type visitReturnTypeReal(ReturnTypeRealContext ctx)
   {
      return visit(ctx.type());
   }


   @Override
   public Type visitReturnTypeVoid(ReturnTypeVoidContext ctx)
   {
      return new VoidType();
   }
}
