package ast;


public abstract class LinedElement
{
   public final int lineNum;


   public LinedElement(int lineNum)
   {
      this.lineNum = lineNum;
   }
}
