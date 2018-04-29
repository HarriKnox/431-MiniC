package ast.type;


public class IntType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return t instanceof IntType;
   }
}
