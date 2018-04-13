package ast;

public class NullType
   implements Type
{
   public boolean equals(Object o)
   {
      return (o instanceof NullType) || (o instanceof StructType);
   }
}
