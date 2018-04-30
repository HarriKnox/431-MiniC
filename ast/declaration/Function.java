package ast.declaration;


import java.util.ArrayList;
import java.util.List;

import ast.type.Type;

import ast.statement.Statement;


public class Function
{
   public final String name;
   public final Type type;
   public final Variables parameters;
   public final Variables locals;
   public final Statement body;
   
   public final List<Type> parameterTypes;


   public Function(int lineNum, String name, Type type,
         Variables params, Variables locals, Statement body)
   {
      super(lineNum);

      this.name = name;
      this.type = type;
      this.parameters = params;
      this.locals = locals;
      this.body = body;
      
      
      /* Get parameter types for function signature */
      this.parameterTypes = new ArrayList<>(params.declarations.size());
      
      for (Variable parameter : params.declarations)
         this.parameterTypes.add(parameter.type);
   }
   
   
   public boolean hasValidType(Structs structs)
   {
      for (Type type : this.parameterTypes)
         if (!type.isValid(structs))
            return false;
      
      
      return this.type.isValid(structs);
   }
   
   
   public LLVMFunction buildLLVm(Structs structs,
      Variables globals, Functions functions)
   {
      
   }
}
