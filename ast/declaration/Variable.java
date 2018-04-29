package ast.declaration;


import ast.type.Type;


public class Variable extends Declaration
{
   public final Type type;


   public Variable(int lineNum, Type type, String name)
   {
      super(lineNum, name);

      this.type = type;
   }
   
   
   public boolean validate(Structs structs)
   {
      if (!this.type.isValid(structs))
      {
         System.err.println("Invalid type");
         return false;
      }
      
      
      return true;
   }
}
