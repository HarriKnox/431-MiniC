package llvm.declaration;


public class LLVMFunction
{
   public final String name;
   public final LLVMType type;
   public final List<LLVMParameter> parameters;
   public final List<LLVMCFGNode> nodes;
   
   
   public LLVMFunction(String name, LLVMType type,
         List<LLVMParameter> parameters, List<LLVMCFGNode> nodes)
   {
      this.name = name;
      this.type = type;
      this.parameters = parameters;
      this.nodes = nodes;
   }
}
