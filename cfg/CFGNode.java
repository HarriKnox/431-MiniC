package cfg;


import java.util.LinkedList;
import java.util.List;


class CFGNode
{
   List<CFGNode> predecesors;
   List<CFGNode> successors;
   List<LLVMInstruction> instructions;
}
