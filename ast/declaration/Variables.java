package ast.declaration;


import java.util.List;

import ast.type.Type;


public class Variables extends Declarations<Variable, LLVMGlobal>
{
   public Variables(List<Variable> variables)
   {
      super(variables);
   }
}
