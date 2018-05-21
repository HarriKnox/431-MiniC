package common;


public class Options
{
   /**
    * The name of the file. If no source file is specified or the source "file"
    * is literally "-" (indicating to read from stdin), this is null;
    */
   public final String filename;
   
   
   
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
   public final boolean dirtyCFG;
   
   
   
   /*
    * The Four Output Options: -llvm -arm -clang -out
    *
    * During the compilation process there are a number of different files
    * Minic could produce, with default extensions in parentheses:
    *    -llvm   : human-readable LLVM code    (.ll   )
    *    -clang  : Clang-compiled executable   (.clang)
    *    -arm    : human-readable ARM code     (.s    )
    *    -out    : ARM-GCC-Compiled executable (.out  )
    *
    * When an output option is provided, Minic will write the data associated
    * with that option to the file with either the default name, stdout, or a
    * specified file. Multiple different options may be provided, but only one
    * of each type will be accepted.
    *
    * The default name for a particular option is the name of the input file
    * with the associated extension (see above) in place of the .mini extension
    * (for example, main.mini -> main.ll). If the input is stdin (either the
    * input file is explicitly "-" or not given, then the default name is "a"
    * (to produce a.ll, a.clang, a.s, and a.out).
    *
    * When no output options are specified, Minic will compile without writing
    * anything in-between. If any number of options are given, then Minic will
    * execute up through the last option and stop (so, outputting just LLVM
    * will cause Minic to not continue with the compile).
    *
    * Each option allows an optional equals sign with even more optional input
    * to follow. When no equals sign is given, the options cause Minic to write
    * to files with the default names. If just an equals sign is given, -llvm
    * and -arm will cause Minic to output their respective contents to stdout;
    * -clang and -out will still use the default filename.
    *
    * If both the equals and a name are provided for an option, then Minic will
    * write the data to the filename given.
    *
    *
    * Some examples
    *    minic main.mini                  : compile to main.out
    *
    *    minic main.mini -out             : compile to main.out (same as above)
    *
    *    minic main.mini -llvm            : Write LLVM to main.ll
    *
    *    minic main.mini -clang -out=     : Compile with clang to main.clang
    *                                     : and compile to main.out
    *
    *    minic main.mini -llvm= -arm=asd  : Write LLVM to stdout and ARM to asd
    */
   
   
   /**
    * Filename to output generated LLVM code to. If this is null, don't write.
    * If this is the empty string, write to stdout. If anything else, write to
    * that file.
    */
   public final String llvm;
   
   
   /**
    * Filename to write the Clang-compiled executable to. If this is null,
    * don't compile. If this is the empty string, panic (it should not be the
    * empty string). If anything else, write to that file.
    */
   public final String clang;
   
   
   /**
    * Filename to output generated ARM code to. If this is null, don't write.
    * If this is the empty string, write to stdout. If anything else, write to
    * that file.
    */
   public final String arm;
   
   
   /**
    * Filename to compile the final product to. If this is null, don't compile.
    * If this is the empty string, panic (it should not be the empty string).
    * If anything else, write to that file.
    */
   public final String out;
   
   
   
   Options(OptionsBuilder ob)
   {
      String name;
      
      if ((ob.name == null) || (ob.name.equals("-")))
      {
         name = "a";
         this.filename = null;
      }
      
      else
      {
         this.filename = ob.name;
         
         /* name is just the bit between the last slash and the .mini*/
         name = ob.name.substring(
               ob.name.lastIndexOf('/') + 1,
               ob.name.length() - 5);
      }
      
      
      this.llvm  = getFilename(ob.llvm,  name, true,  ".ll");
      this.clang = getFilename(ob.clang, name, false, ".clang");
      this.arm   = getFilename(ob.arm,   name, true,  ".s");
      
      if ((ob.llvm == null) && (ob.clang == null)
            && (ob.arm == null) && (ob.out == null))
         this.out = getFilename("", name, false, ".out");
      
      else
         this.out = getFilename(ob.out, name, false, ".out");
   }
   
   
   private static String getFilename(String filename,
         String source, boolean useStdio, String extension)
   {
      /* Option was never specified */
      if (filename == null)
         return null;
      
      
      /* No equals was used or empty equals but can't use stdio */
      if (filename.isEmpty() || (filename.equals("=") && !useStdio))
         return source + extension;
      
      
      /* Empty equals and can use stdio */
      if (filename.equals("="))
         return "";
      
      
      /* All other cases to just return what was given, without the equals */
      return filename.substring(1);
   }
   
   
   public static Options parseOptions(String[] args)
   {
      OptionsBuilder ob = new OptionsBuilder();
      
      
      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         int len = arg.length();
         
         
         if ((len > 1) && (arg.charAt(0) == '-'))
            handleOption(arg, ob);
         
         else if (ob.name != null)
            ErrorPrinter.printOut("too many files specified: " + arg);
         
         else if (arg.equals("-"))
            ob.name = arg;
         
         else if ((len < 5) || !arg.endsWith(".mini"))
            ErrorPrinter.printOut("source must be a .mini file: " + arg);
         
         else
            ob.name = arg;
      }
      
      
      if (ErrorPrinter.getErrorCount() > 0)
         System.exit(1);
      
      
      return ob.create();
   }
   
   
   /**
    * For a particular output option,
    *  - null  means it was never used
    *  - ""    means it was given without equals (use default name)
    *  - "=.*" means it was given with equals
    *          (perhaps with more, use default or stdout)
    */
   private static String getOutputName(String arg)
   {
      int index = arg.indexOf('=');
      
      return (index == -1) ? "" : arg.substring(index);
   }
   
   
   private static isOutputOption(String arg, String option)
   {
      return arg.equals(option) || arg.startsWith(option + '=');
   }
   
   
   private static handleOption(String option, OptionsBuilder ob)
   {
      if (isOutputOption(arg, "-llvm"))
         ob.llvm = getOutputName(arg);
      
      else if (isOutputOption(arg, "-clang"))
         ob.clang = getOutputName(arg);
      
      else if (isOutputOption(arg, "-arm"))
         ob.arm = getOutputName(arg);
      
      else if (isOutputOption(arg, "-out"))
         ob.out = getOutputName(arg);
      
      else if (arg.equals("-stack"))
         ob.stack = true;
      
      else if (arg.equals("-dirtycfg"))
         ob.dirtyCFG = true;
      
      else
         ErrorPrinter.printOut("unknown option: " + arg);
   }
   
   
   private static class OptionsBuilder
   {
      String name = null;
      
      boolean stack   = false;
      boolean dirtyCFG = false;
      
      String llvm = null;
      String clang = null;
      String arm = null;
      String out = null;
      
      Options create()
      {
         return new Options(this);
      }
   }
}
