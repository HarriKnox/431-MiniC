package llvm.declaration;


import java.util.List;


public abstract class LLVMDeclarations<T extends LLVMDeclaration
{
   public final List<T> declarations;
   
   
   public LLVMDeclarations(List<T> declarations)
   {
      this.declarations = declarations;
   }
}
