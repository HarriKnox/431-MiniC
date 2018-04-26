package ast.type;

public class BoolType
   extends Type
{
   public boolean equals(Object o)
   {
      return o instanceof BoolType;
   }
   
   public String toString()
   {
      return "bool";
   }
   
   
   public String toLLVMTypeString()
   {
      return "i1";
   }
}
