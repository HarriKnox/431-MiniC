package ast;

public class NullType
   implements Type
{
   public boolean equals(Object o)
   {
      return (o instanceof NullType) || (o instanceof StructType);
   }
   
   public String toLLVMTypeString()
   {
      return "null";
   }
}
