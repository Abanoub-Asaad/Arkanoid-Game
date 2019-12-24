

package Arkanoid;



public class Expand 
{
    public static void checkExpand(icons icons_obj , Paddle paddle_obj , Ball ball_obj)
    {
        if(icons_obj.i_expand.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent()) )
         {
            Sound.playsound_capsule();
            icons_obj.i_expand.setImage(null);   
            paddle_obj.paddle_iv .setFitWidth(230);

         }

    }
   
}
