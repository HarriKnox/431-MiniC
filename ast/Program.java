package ast;


public class Program
{
   public final Types types;
   public final Declarations decls;
   public final Functions funcs;

   public Program(Types types, Declarations decls, Functions funcs)
   {
      this.types = types;
      this.decls = decls;
      this.funcs = funcs;
   }
}
