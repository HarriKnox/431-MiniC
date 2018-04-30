package ast.declaration;


import java.util.LinkedList;
import java.util.List;


public class Structs extends Declarations<Struct, LLVMStruct>
{
   public Structs(List<Struct> structs)
   {
      super(structs);
   }
}
