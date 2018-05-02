package ast.lvalue;


public class LvalueId extends Lvalue
{
   public final String id;


   public LvalueId(int lineNum, String id)
   {
      super(lineNum, 0);

      this.id = id;
   }
   
   
   public LLVMVariable buildLLVM(
         ProgramAST program, Fuction current, LLVMCFGNode node)
   {
      LLVMVariable source = findVariable(program.globals, current);
      
      
      if (source == null)
      {
         System.err.println("line " + this.lineNum + " variable " + this.id + " not declared");
         return null;
      }
      
      
      return source;
   }
   
   
   private LLVMValue findVariable(Variables globals, Function current)
   {
      Variable variable;
      
      
      /* Find in locals */
      variable = current.getLocal(this.id);
      
      if (variable != null)
         return new LLVMLocal(
               current.name,
               this.id,
               variable.type.getLLVMType());
      
      
      /* Find in parameters */
      variable = current.getParameter(this.id);
      
      if (variable != null)
         return new LLVMParameter(
               current.name,
               this.id,
               variable.type.getLLVMType());
      
      
      /* Find in globals */
      variable = globals.getVariable(this.id);
      
      if (variable != null)
         return new LLVMGlobal(this.id, variable.type.getLLVMType());
      
      
      /* Not found */
      return null;
   }
}
