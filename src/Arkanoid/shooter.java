
package Arkanoid;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class shooter extends Paddle
{ 
    Timeline time_shoots = new Timeline() ;
    
    Image paddle_gun =new Image("Resources/Images/Paddle/shooter.png",200,40,false,false);
    Image shoots =new Image("Resources/Images/Capsule/shoots.png",10,40,false,false);
    
    ImageView paddle_gun_iv= new ImageView(paddle_gun);
    ImageView shoots_iv1 = new ImageView(shoots);
    ImageView shoots_iv2 = new ImageView(shoots); 
    
    TranslateTransition translate_shoot1 = new TranslateTransition();
    TranslateTransition translate_shoot2= new TranslateTransition();
    
    double Height = 40 ; 
    double Width  = 10; 
   
  public void setx_iv1(double x)
  {
      shoots_iv1.setX(x);
 
  }
    public void setx_iv2(double x, double width)
  {
      shoots_iv2.setX(x+width*2-3);

  }
  public void sety(double y)
  {
      shoots_iv1.setY(y);
      shoots_iv2.setY(y);
      
  }
   
   public double getx_iv1()
   {
       return shoots_iv1.getX();
   }
   public  double getx_iv2()
   {
       return shoots_iv2.getX();
   }
   
  public  double gety_iv1()
   {
       return shoots_iv1.getTranslateY();
   }
   public double gety_iv2()
   {
       return shoots_iv2.getTranslateY();
   }
   
    public double getHeight() 
    {
        return Height;
    }

    public void setHeight(double height)
    {
        Height = height;
    }

    public double getWidth()
    {
        return Width;
    }

    public void setWidth(double width)
    {
        Width = width;
    }
    
        //=========================================================
    public void transition_shoots()
    {
    
        paddle_iv.setImage(paddle_gun);

        
        translate_shoot1.setToY(-800);
        translate_shoot1.setDuration(Duration.millis(2000));
        translate_shoot1.setCycleCount(5);
        translate_shoot1.setAutoReverse(false);
        translate_shoot1.setNode(shoots_iv1);
        translate_shoot1.play();


        translate_shoot2.setToY(-800);
        translate_shoot2.setDuration(Duration.millis(2000));
        translate_shoot2.setCycleCount(5);
        translate_shoot2.setAutoReverse(false);
        translate_shoot2.setNode(shoots_iv2);
        translate_shoot2.play();

    }
    
    
      public  void checkShootCapsuleTouchesPaddle(icons icons_obj , Paddle paddle_obj) 
    { 
        if(icons_obj.i_shoot.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent()) )
        {
             Sound.playsound_capsule();
             icons_obj.i_shoot.setImage(null);
            
            
            setx_iv2(paddle_obj.getX(), paddle_obj.getWidth());
            setx_iv1(paddle_obj.getX());
            sety(paddle_obj.getY());
            shoots_iv1.setVisible(true);
            shoots_iv2.setVisible(true);
            paddle_gun_iv.setX(paddle_obj.getX());
            paddle_gun_iv.setY(paddle_obj.getY());
           
    
            paddle_gun_iv.setVisible(true);      
           
           
            KeyFrame f_shoots = new KeyFrame(Duration.seconds(2));
            time_shoots = new Timeline(f_shoots); 
            
            transition_shoots();
            time_shoots.play();
            
           
        } 
    } 
    
}
