package ast.declaration;


import java.util.List;

import ast.LinedElement;


public class Struct extends LinedElement
{
   public final String name;
   public final Declarations fields;

   public Struct(int lineNum, String name, Declarations fields)
   {
      super(lineNum);

      this.name = name;
      this.fields = fields;
   }
}
