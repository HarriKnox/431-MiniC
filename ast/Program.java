package ast;

import java.util.List;

public class Program
{
   public final List<TypeDeclaration> types;
   public final List<Declaration> decls;
   public final List<Function> funcs;

   public Program(List<TypeDeclaration> types, List<Declaration> decls,
      List<Function> funcs)
   {
      this.types = types;
      this.decls = decls;
      this.funcs = funcs;
   }
}
