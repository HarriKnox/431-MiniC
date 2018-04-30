package ast.declaration;


import ast.type.Type;


public class Variable
{
   public final String name;
   public final Type type;


   public Variable(int lineNum, String name, Type type)
   {
      super(lineNum);

      this.name = name;
      this.type = type;
   }
   
   
   public boolean hasValidType(Structs structs)
   {
      return this.type.isValid(structs);
   }
}
