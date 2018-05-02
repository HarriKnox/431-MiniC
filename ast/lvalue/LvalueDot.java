package ast.lvalue;


public class LvalueDot extends Lvalue
{
   public final Lvalue left;
   public final String id;


   public LvalueDot(int lineNum, Lvalue left, String id)
   {
      super(lineNum, left.height + 1);

      this.left = left;
      this.id = id;
   }
   
   
   public LLVMVariable buildLLVM(
         ProgramAST program, Fuction current, LLVMCFGNode node)
   {
      LLVMVariable leftValue = this.left.buildLLVM(program, current, node);
      
      
      /* null indicates something went wrong deeper in the tree */
      if (leftValue == null)
         return null;
      
      
      if (!(leftValue.type instanceof LLVMStructType))
      {
         System.err.println("line " + exp.line + " attempt to index a(n) " + s);
         return null;
      }
      
      
      /* At this point leftValue refers to a valid struct */
      Variable field = program
            .getStruct(((LLVMStructType)leftValue.type).name)
            .getField(this.id);
      
      if (field == null)
      {
         System.err.println("line " + this.line + " struct "
               + ((LLVMStructType)leftValue.type).name
               + " does not contain field " + this.id);
         return null;
      }
      
      
      LLVMLoad load = new LLVMLoad(leftValue);
      
      LLVMGetelementptr getelementptr = new LLVMGetelementptr(
            load,
            field.type.getLLVMType()
            field.index);
      
      node.add(load).add(getelementptr);
      
      
      return getelementptr.target;
   }
}
