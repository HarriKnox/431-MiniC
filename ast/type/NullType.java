package ast.type;


public class NullType extends Type
{
   @Override
   public boolean equivalent(Type t)
   {
      return (t instanceof NullType) || (t instanceof StructType);
   }
}
