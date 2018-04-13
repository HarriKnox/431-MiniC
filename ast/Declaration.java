package ast;

public class Declaration
   extends LinedElement
{
   public final Type type;
   public final String name;

   public Declaration(int lineNum, Type type, String name)
   {
      super(lineNum);
      this.type = type;
      this.name = name;
   }
}
