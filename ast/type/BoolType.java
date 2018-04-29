package ast.type;


public class BoolType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof BoolType;
   }
}
