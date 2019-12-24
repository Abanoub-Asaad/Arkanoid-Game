
package Arkanoid;

import javafx.scene.Scene;


public class Slow extends Paddle
{
    
       public static void checkSlow(icons icons_obj , Paddle paddle_obj , Ball ball_obj) 
     { 
         if(icons_obj.i_slow.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent()) )
         {
             Sound.playsound_capsule();
             icons_obj.i_slow.setImage(null);
             ball_obj.setSpeed(4);

         } 
     }
    
}
