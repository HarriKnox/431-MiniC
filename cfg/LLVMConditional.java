package cfg;


class LLVMConditional
   implements LLVMBranchInstruction
{
   LLVMValue condition;
   CFGNode thenNode;
   CFGNode elseNode;
   
   LLVMConditional(LLVMValue condition, CFGNode thenDest, CFGNode elseDest)
   {
      this.condition = condition;
      this.thenNode = thenDest;
      this.elseNode = elseDest;
   }
   
   public String toString()
   {
      return "br i1 " + this.condition.toString() +
            ", label %" + this.thenNode.label +
            ", label %" + this.elseNode.label;
   }
}
