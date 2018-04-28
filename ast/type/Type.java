package ast.type;


import ast.declaration.Structs;


public abstract class Type
{
   public abstract boolean equivalent(Type type);
   
   
   public boolean isValid(Structs structs)
   {
      return true;
   }
}
