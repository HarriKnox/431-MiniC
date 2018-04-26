package ast.type;

public class NullType
   extends Type
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
