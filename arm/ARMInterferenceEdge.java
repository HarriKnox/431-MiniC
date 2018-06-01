package arm;


import arm.value.operand.ARMRegister;


public class ARMInterferenceEdge
{
   public final ARMRegister left, right;
   
   
   public ARMInterferenceEdge(ARMRegister left, ARMRegister right)
   {
      this.left = left;
      this.right = right;
   }
   
   
   @Override
   public int hashCode()
   {
      return this.left.hashCode() ^ this.right.hashCode();
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (o instanceof ARMInterferenceEdge)
      {
         ARMInterferenceEdge ie = (ARMInterferenceEdge)o;
         
         return ((ie.left == this.left) && (ie.right == this.right))
               || ((ie.left == this.right) && (ie.right == this.left));
      }
      
      return false;
   }
}
