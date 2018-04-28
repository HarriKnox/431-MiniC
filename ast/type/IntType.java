package ast.type;


public class IntType extends Type
{
   public boolean equivalent(Type t)
   {
      return t instanceof IntType;
   }
}
