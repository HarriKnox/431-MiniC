package cfg;


class LLVMScanf
   implements LLVMInstruction
{
   static final LLVMScanf SCANF = new LLVMScanf();
   
   private LLVMScanf()
   {
      ;
   }
   
   
   public toString()
   {
      return "call i32 (i8*, ...)* @scanf(i8* getelementptr inbounds ([4 x i8]* "
            + LLVMGlobal.READ_FORMAT.toString() + ", i32 0, i32 0), i32* "
            + LLVMGlobal.READ_SCRATCH.toString() + ")"
   }
}
