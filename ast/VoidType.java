package ast;


import org.antlr.v4.runtime.Token;


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
