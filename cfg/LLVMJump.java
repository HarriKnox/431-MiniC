package cfg;


class LLVMJump
   implements LLVMBranchInstruction
{
   CFGNode destination;
   
   LLVMJump(CFGNode destination)
   {
      this.destination = destination;
   }
   
   public toString()
   {
      return "br label " + this.destination.label;
   }
}
