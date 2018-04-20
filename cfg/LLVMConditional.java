package cfg;


class LLVMConditional
   implements LLVMBranchInstruction
{
   LLVMVariable condition;
   CFGNode thenDestination;
   CFGNode elseDestination;
   
   LLVMConditional(LLVMVariable condition, CFGNode thenDest, CFGNode elseDest)
   {
      this.condition = condition;
      this.thenDestination = thenDest;
      this.elseDestination = elseDest;
   }
   
   public toString()
   {
      return "br i1 " + this.condition.name +
            ", label " + this.thenDestination.label +
            ", label " + this.elseDestination.label;
   }
}
