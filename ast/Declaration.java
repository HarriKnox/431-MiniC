package ast;

public class Declaration
   extends LinedElement
{
   private final Type type;
   private final String name;

   public Declaration(int lineNum, Type type, String name)
   {
      super(lineNum);
      this.type = type;
      this.name = name;
   }
}
