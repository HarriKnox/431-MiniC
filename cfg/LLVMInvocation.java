package cfg;


import java.util.LinkedList;
import java.util.List;


class LLVMInvocation
   implements LLVMInstruction
{
   List<LLVMValue> arguments;
   List<String> types;
   LLVMRegister result;
   String function;
   String retType;
   
   
   LLVMInvocation(List<LLVMValue> arguments, String name, LLVMRegister result)
   {
      this.arguments = arguments;
      this.function = name;
      this.result = result;
      this.types = new LinkedList<>();
      
      Function func = TypeChecker.functions.get(name);
      
      for (Declaration param : func.params)
      {
         if (param.type instanceof IntType)
            this.types.add("i32");
         
         else if (param.type instanceof BoolType)
            this.types.add("i1");
         
         else
            this.types.add("%struct." + ((StructType)param.type).name + "*");
      }
      
      
      if (func.retType instanceof IntType)
         this.retType = "i32";
      
      else if (func.retType instanceof BoolType)
         this.retType = "i1";
      
      else if (func.retType instanceof StructType)
         this.retType = "%struct." + ((StructType)func.retType).name + "*";
      
      else
         this.retType = "void";
   }
   
   
   public String toString()
   {
      StringBuilder args = new StringBuilder();
      
      Iterator<LLVMValue> argIter = arguments.iterator();
      Iterator<String> typeIter = types.iterator();
      
      if (argIter.hasNext())
      {
         String type0 = typeIter.next();
         LLVMValue arg0 = argIter.next();
         
         args.append(type0).append(" ").append(arg0.toString());
      }
      
      while (argIter.hasNext())
      {
         String typeN = typeIter.next();
         LLVMValue argN = argIter.next();
         
         args.append(", ").append(typeN).append(" ").append(argN.toString());
      }
      
      
      String resultString = (this.retType.equals("void")) ? "" : (this.result.toString() + " = ");
      
      
      return resultString + "call " + this.retType. + "@" +
            this.function + "(" + args.toString() + ")";
   }
}
