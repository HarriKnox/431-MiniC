package ast.expression;


public class NewExpression extends Expression
{
   public final String id;


   public NewExpression(int lineNum, String id)
   {
      super(lineNum, 0);
      this.id = id;
   }
   
   
   @Override
   public LLVMValue buildLLVM(
         ProgramAST program, Function current, LLVMCFGNode node)
   {
      Struct struct = program.structs.getStruct(this.id);
      
      if (struct == null)
      {
         System.err.println("line " + this.lineNul + " struct " + this.id + " not declared");
         return null;
      }
      
      LLVMMalloc malloc = new LLVMMalloc(struct.size);
      LLVMBitcast bitcast = new LLVMBitcast(
            malloc.target,
            new StructType(struct.name).getLLVMType());
      
      node.add(malloc).add(bitcast);
      
      return bitcast.target;
   }
}
