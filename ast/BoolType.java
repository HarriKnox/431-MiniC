package ast;

public class BoolType
   implements Type
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
