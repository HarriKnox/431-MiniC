package ast.expression;


public class ReadExpression extends Expression
{
   public ReadExpression(int lineNum)
   {
      super(lineNum, 0);
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      node.addInstruction(
   }
}
