
package Arkanoid;



public class Fast 
{
    
    Fast() {} 
    
      public static void checkFast(icons icons_obj ,Paddle paddle_obj , Ball  ball_obj ) 
     { 
         if(icons_obj.i_fast.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent()) )
         { 
             Sound.playsound_capsule(); 
             icons_obj.i_fast.setImage(null);
             ball_obj.setSpeed(7); 

         } 
     } 
} 

