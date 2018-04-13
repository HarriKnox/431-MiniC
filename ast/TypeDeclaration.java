package ast;

import java.util.List;

public class TypeDeclaration
   extends LinedElement
{
   public final String name;
   public final List<Declaration> fields;

   public TypeDeclaration(int lineNum, String name, List<Declaration> fields)
   {
      super(lineNum);
      this.name = name;
      this.fields = fields;
   }
}
