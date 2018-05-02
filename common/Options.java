package common;

public class Options
{
   public final String filename;
   public final boolean stack;
   public final boolean m32;
   
   
   Options(OptionsBuilder ob)
   {
      this.filename = ob.filename;
      this.stack = ob.stack;
      this.m32 = ob.m32;
   }
   
   
   public static Options parseOptions(String[] args)
   {
      OptionsBuilder ob = new OptionsBuilder();
      
      
      for (int i = 0; i < args.length; i++)
      {
         String arg = args[i];
         int len = arg.length();
         
         if (arg.equals("-stack"))
         {
            ob.stack = true;
         }
         else if (arg.equals("-m32"))
         {
            ob.m32 = true;
         }
         else if (arg.charAt(0) == '-')
         {
            System.err.println("unexpected option: " + arg);
            System.exit(6);
         }
         else if (ob.filename != null)
         {
            System.err.println("too many files specified");
            System.exit(7);
         }
         else if ((len <= 5) || !arg.endsWith(".mini"))
         {
            System.err.println("must be a .mini file");
            System.exit(8);
         }
         else
         {
            ob.filename = arg.substring(0, len - 5);
         }
      }
      
      
      return ob.create();
   }
   
   
   private static class OptionsBuilder
   {
      String filename = null;
      boolean stack = false;
      boolean m32 = false;
      
      Options create()
      {
         return new Options(this);
      }
   }
}
