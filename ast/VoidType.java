package ast;

public class VoidType
   implements Type
{
   public boolean equals(Object o)
   {
      return o instanceof VoidType;
   }
   
   public String toString()
   {
      return "void";
   }
}
