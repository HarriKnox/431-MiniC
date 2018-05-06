package common;


import java.io.File;


public class Options
{
   /**
    * The name of the file (minus the '.mini'). To be used for naming the
    * output files. If no source file is specified this defaults to 'a.mini'
    * (to produce an 'a.out').
    */
   public final String name;
   
   
   /**
    * The source file. If it's null, then the input file is standard-in. This
    * will be null if either no source file is presented or the source filename
    * given is literally '-'.
    *
    *    minic -
    */
   public final File miniFile;
   
   
   
   /**
    * Use the stack for storing variables.
    */
   public final boolean stack;
   
   
   /**
    * Prevents the compiler from cleaning up the control flow graphs.
    *
    * Orphans are nodes with no predecessors: nodes that would never be
    * executed unless a cosmic ray hit the processor at just the right moment
    * and the right angle to cause the program counter to jump into an orphan.
    */
   public final boolean orphans;
   
   
   /**
    * Use clang to compile. Will output the LLVM to a file in tmp.
    */
   public final boolean clang;
   
   
   /**
    * Output LLVM and don't continue compiling unless otherwise specified (if
    * the -arm option is given then continue compiling).
    */
   public final boolean llvm;
   
   
   /**
    * Output human-readable ARM and don't continue compiling unless otherwise
    * specified
    */
   public final boolean arm;
   
   
   
   Options(OptionsBuilder ob)
   {
      if ((ob.name == null) || (ob.name.equals("-")))
      {
         this.name = "a";
         this.miniFile = null;
      }
      else
      {
         this.name = ob.name;
         this.miniFile = new File(this.name + ".mini");
      }
      
      
      
      this.stack   = ob.stack;
      this.orphans = ob.orphans;
      this.clang   = ob.clang;
      this.llvm    = ob.llvm;
      this.arm     = ob.arm;
      
   }
   
   
   public static Options parseOptions(String[] args)
   {
      OptionsBuilder ob = new OptionsBuilder();
      
      
      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         int len = arg.length();
         
         if ((len > 1) && (arg.charAt(0) == '-'))
         {
            if (arg.equals("-stack"))
               ob.stack = true;
            
            else if (arg.equals("-orphans"))
               ob.orphans = true;
            
            else if (arg.equals("-clang"))
               ob.clang = true;
            
            else if (arg.equals("-llvm"))
               ob.llvm = true;
            
            else if (arg.equals("-arm"))
               ob.arm = true;
            
            else
               ErrorPrinter.printOut("unexpected option: " + arg);
         }
         else if (ob.name != null)
         {
            ErrorPrinter.printOut("too many files specified: " + arg);
         }
         else if (!arg.equals("-") && !arg.endsWith(".mini"))
         {
            ErrorPrinter.printOut("source must be a .mini file: " + arg);
         }
         else
         {
            ob.name = arg;
         }
      }
      
      
      if (ErrorPrinter.getErrorCount() > 0)
         System.exit(1);
      
      
      return ob.create();
   }
   
   
   private static class OptionsBuilder
   {
      String name;
      
      boolean stack;
      boolean orphans;
      
      boolean clang;
      boolean llvm;
      boolean arm;
      
      Options create()
      {
         return new Options(this);
      }
   }
}
