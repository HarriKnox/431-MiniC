package ast.declaration;


import java.util.ArrayList;
import java.util.List;

import ast.type.Type;

import ast.statement.Statement;


public class Function extends Declaration<LLVMFunction>
{
   public final Type type;
   public final Variables parameters;
   public final Variables locals;
   public final Statement body;
   
   public final List<Type> parameterTypes;


   public Function(int lineNum, String name, Type type,
         Variables params, Variables locals, Statement body)
   {
      super(lineNum, name);

      this.type = type;
      this.parameters = params;
      this.locals = locals;
      this.body = body;
      
      
      /* Get parameter types for function signature */
      this.parameterTypes = new ArrayList<>(params.declarations.size());
      
      for (Variable parameter : params.declarations)
         this.parameterTypes.add(parameter.type);
   }
   
   
   @Override
   public boolean hasValidType(Structs structs)
   {
      for (Type type : this.parameterTypes)
         if (!type.isValid(structs))
            return false;
      
      
      return this.type.isValid(structs);
   }
   
   
   @Override
   public void removeInvalids(Structs structs)
   {
      this.locals.removeInvalids(structs);
   }
}
