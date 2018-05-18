package arm.declaration;


import java.util.List;


public class ARMFunction
{
   public final String name;
   public final List<ARMCFGNode> nodes;
   
   
   public ARMFunction(String name, List<ARMCFGNode> nodes)
   {
      this.name = name;
      this.nodes = nodes;
   }
}
