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
   
   
   @Override
   public boolean hasValidType(Structs structs)
   {
      return this.type.isValid(structs);
   }
   
   
   @Override
   public void removeInvalids(Structs structs)
   {
      /* do nothing: this does not contain a declaration collection */
   }
}
