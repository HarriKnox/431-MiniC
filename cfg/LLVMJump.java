package cfg;


class LLVMJump
   implements LLVMBranchInstruction
{
   final CFGNode destination;
   final boolean loopback;
   
   
   LLVMJump(CFGNode destination)
   {
      this.destination = destination;
      this.loopback = false;
   }
   
   
   LLVMJump(CFGNode destination, boolean loopback)
   {
      this.destination = destination;
      this.loopback = loopback;
   }
   
   
   public toString()
   {
      return "br label " + this.destination.label;
   }
}
