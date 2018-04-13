package main;

public class Options
{
   public final String filename;
   
   
   Options(String fname)
   {
      this.filename = fname;
   }
   
   
   static Options parseOptions(String[] args)
   {
      OptionsBuilder optsBuilder = new OptionsBuilder();
      
      
      for (int i = 0; i < args.length; i++)
      {
         if (args[i].charAt(0) == '-')
         {
            System.err.println("unexpected option: " + args[i]);
            System.exit(1);
         }
         else if (optsBuilder.filename != null)
         {
            System.err.println("too many files specified");
            System.exit(1);
         }
         else
         {
            optsBuilder.filename = args[i];
         }
      }
      
      
      return optsBuilder.create();
   }
   
   
   private static class OptionsBuilder
   {
      String filename = null;
      
      Options create()
      {
         return new Options(this.filename);
      }
   }
}
