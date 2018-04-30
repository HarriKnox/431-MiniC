package ast.declaration;


import java.util.List;


public class Functions extends Declarations<Function, LLVMFunction>
{
   public Functions(List<Function> functions)
   {
      super(functions);
   }
}
