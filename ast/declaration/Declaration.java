package ast.declaration;


import ast.LinedElement;


public abstract class Declaration extends LinedElement
{
   public final String name;
   
   
   public Declaration(int lineNum, String name)
   {
      super(lineNum);
      
      this.name = name;
   }
   
   
   public abstract boolean hasValidType(Structs structs);
   
   public abstract void removeInvalids(Structs structs);
}
