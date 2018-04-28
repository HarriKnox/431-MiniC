package ast.type;


public class VoidType extends Type
{
   public boolean equivalent(Type t)
   {
      return t instanceof VoidType;
   }
}
