package ast.type;


import ast.declaration.Structs;


public class StructType extends Type
{
   public final String name;


   public StructType(String name)
   {
      this.name = name;
   }


   public boolean equivalent(Type t)
   {
      return (t instanceof NullType)
            || ((t instanceof StructType)
                  && this.name.equals(((StructType)t).name));
   }
   
   
   public boolean isValid(Structs structs)
   {
      return structs.isValid(this.name);
   }
   
   
   public boolean isField(Structs structs, String fieldName)
   {
      return structs.isField(this.name, fieldName);
   }
   
   
   public Type getFieldType(Structs structs, String fieldName)
   {
      return structs.getFieldType(this.name, fieldName);
   }
}
