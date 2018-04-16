package ast;


public class IntType
   implements Type
{
   public boolean equals(Object o)
   {
      return o instanceof IntType;
   }
   
   public String toString()
   {
      return "int";
   }
}
