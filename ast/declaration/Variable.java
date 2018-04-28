package ast.declaration;


import ast.LinedElement;

import ast.type.Type;


public class Variable extends LinedElement
{
   public final Type type;
   public final String name;


   public Variable(int lineNum, Type type, String name)
   {
      super(lineNum);

      this.type = type;
      this.name = name;
   }
   
   
   public void validate(Structs structs)
   {
      if (!this.type.isValid(structs))
         System.err.println("Invalid type");
   }
}
