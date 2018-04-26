package ast.type;

public class IntType
   extends Type
{
   public boolean equals(Object o)
   {
      return o instanceof IntType;
   }
   
   public String toString()
   {
      return "int";
   }
   
   
   public String toLLVMTypeString()
   {
      return "i32";
   }
}
