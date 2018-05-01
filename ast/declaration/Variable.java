package ast.declaration;


import ast.type.Type;


public class Variable
{
   public final String name;
   public final Type type;
   public final int index;


   public Variable(int lineNum, String name, Type type, int index)
   {
      super(lineNum);

      this.name = name;
      this.type = type;
      this.index = index;
   }
   
   
   public boolean hasValidType(Structs structs)
   {
      return this.type.isValid(structs);
   }
}
