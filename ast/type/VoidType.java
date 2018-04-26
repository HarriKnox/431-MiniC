package ast.type;

public class VoidType
   extends Type
{
   public boolean equals(Object o)
   {
      return o instanceof VoidType;
   }
   
   public String toString()
   {
      return "void";
   }
   
   
   public String toLLVMTypeString()
   {
      return this.toString();
   }
}
