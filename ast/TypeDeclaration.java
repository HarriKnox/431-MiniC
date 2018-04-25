package ast;

import java.util.List;

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
