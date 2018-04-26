package ast.declaration;


import java.util.List;

import ast.LinedElement;


public class TypeDeclaration
   extends LinedElement
{
   public final String name;
   public final Declarations fields;

   public TypeDeclaration(int lineNum, String name, Declarations fields)
   {
      super(lineNum);
      this.name = name;
      this.fields = fields;
   }
}
