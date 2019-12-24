
package Arkanoid;





public class Shrink 
{ 
   public static void checkShrink(icons icons_obj , Paddle paddle_obj)
    {
        
        if(icons_obj.i_shrink.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent()) )
         {
             
             Sound.playsound_capsule();
             icons_obj.i_shrink.setImage(null);             
             paddle_obj.paddle_iv .setFitWidth(150);
            
         
         }
       
    }
}
