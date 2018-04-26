package ast.type;

public class StructType
   extends Type
{
   public final String name;

   public StructType(String name)
   {
      this.name = name;
   }
   
   
   public boolean equals(Object o)
   {
      return (o instanceof NullType) || ((o instanceof StructType) && this.name.equals(((StructType)o).name));
   }
   
   
   public String toString()
   {
      return "struct " + this.name;
   }
   
   
   public String toLLVMTypeString()
   {
      return "%struct." + this.name + "*";
   }
}
