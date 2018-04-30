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
      
      LLVMRegister mallocked = new LLVMRegister(new LLVMPointerType());
      node.addInstruction(new LLVMMalloc(mallocked, struct.size));
      
      LLVMRegister bitcasted = new LLVMRegister(new StructType(struct.name).getLLVMType());
      node.addInstruction(new LLVMBitcast(bitcasted, mallocked));
      
      return bitcasted;
   }
}
