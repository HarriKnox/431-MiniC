package ast.type;


public class BoolType extends Type
{
   public boolean equivalent(Type t)
   {
      return t instanceof BoolType;
   }
}
