package ast.type;


public class VoidType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof VoidType;
   }
}
