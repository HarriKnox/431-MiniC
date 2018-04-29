package ast.declaration;


import java.util.ArrayList;
import java.util.List;

import ast.type.Type;

import ast.statement.Statement;


public class Function extends Declaration
{
   public final Type type;
   public final Variables parameters; /* = only the params */
   public final Statement body;
   
   public final List<Type> parameterTypes;
   public final Variables locals; /* = params U vars */


   public Function(int lineNum, String name, Variables params,
         Type type, Variables vars, Statement body)
   {
      super(lineNum, name);

      this.parameters = params;
      this.type = type;
      this.body = body;
      
      
      /* Get parameter types for function signature */
      this.parameterTypes = new ArrayList<>(params.declarations.size());
      
      for (Variable parameter : params.declarations)
         this.parameterTypes.add(parameter.type);
      
      
      /* Get a union of all locally-scoped params and vars */
      List<Variable> localScope = new ArrayList<>(
            params.declarations.size() + vars.declarations.size());
      
      localScope.addAll(params.declarations);
      localScope.addAll(vars.declarations);
      
      this.locals = new Variables(localScope);
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
