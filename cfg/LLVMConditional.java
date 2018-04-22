package cfg;


class LLVMConditional
   implements LLVMBranchInstruction
{
   LLVMValue condition;
   CFGNode thenDestination;
   CFGNode elseDestination;
   
   LLVMConditional(LLVMValue condition, CFGNode thenDest, CFGNode elseDest)
   {
      this.condition = condition;
      this.thenDestination = thenDest;
      this.elseDestination = elseDest;
   }
   
   public String toString()
   {
      return "br i1 " + this.condition.toString() +
            ", label %" + this.thenDestination.label +
            ", label %" + this.elseDestination.label;
   }
}
