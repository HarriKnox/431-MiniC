package cfg;


/**
 * A marker iterface used by LLVMInstruction types to indicate that they are
 * branch (ie, control flow) instructions, and are thus the last instructions
 * run in any CFG Node.
 */
interface LLVMBranchInstruction
{
   public String toString();
}
