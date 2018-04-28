package ast.type;


public class NullType extends Type
{
   public boolean equivalent(Type t)
   {
      return (t instanceof NullType) || (t instanceof StructType);
   }
}
