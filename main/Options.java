package main;

public class Options
{
   public final String filename;
   public final boolean stack;
   
   
   Options(String fname, boolean stack)
   {
      this.filename = fname;
      this.stack = stack;
   }
   
   
   static Options parseOptions(String[] args)
   {
      OptionsBuilder optsBuilder = new OptionsBuilder();
      
      
      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         int len = arg.length();
         
         if (arg.equals("-stack"))
         {
            optsBuilder.stack = true;
         }
         else if (arg.charAt(0) == '-')
         {
            System.err.println("unexpected option: " + arg);
            System.exit(1);
         }
         else if (optsBuilder.filename != null)
         {
            System.err.println("too many files specified");
            System.exit(1);
         }
         else if ((len <= 5) || !arg.endsWith(".mini"))
         {
            System.err.println("must be a .mini file");
            System.exit(1);
         }
         else
         {
            optsBuilder.filename = arg.substring(0, len - 5);
         }
      }
      
      
      return optsBuilder.create();
   }
   
   
   private static class OptionsBuilder
   {
      String filename = null;
      boolean stack = false;
      
      Options create()
      {
         return new Options(this.filename, this.stack);
      }
   }
}
