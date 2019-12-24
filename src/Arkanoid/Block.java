
package Arkanoid;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Block 
{
    
    //ArrayList of type (Class "Block") to access all Attributes and methods of that class 
     public static ArrayList<Block>blockList = new ArrayList<Block>(100)  ;
     
    
    public static int brick_pos_X = 100 ;
    public static int brick_pos_Y = 100 ;
    public static int space = 5 ; //Space between Bricks 
    
     //=====================================================================================   

        
    Image block1 = new Image("Resources/Images/Brick/normal brick1.png",100,35,false,false); 
    Image block2 = new Image("Resources/Images/Brick/normal brick2.png",100,35,false,false); 
    Image block3 = new Image("Resources/Images/Brick/normal brick5.png",100,35,false,false); 
    Image block4 = new Image("Resources/Images/Brick/normal brick13.png",100,35,false,false); 
    Image block5 = new Image("Resources/Images/Brick/normal brick14.png",100,35,false,false); 
    
    Image block6 = new Image("Resources/Images/Brick/small brick7.png",80,50,false,false); 
    
    ImageView block_iv = new ImageView(block1) ;
    
   

        
      
       
    
    static double Height = 50 ; 
    static double Width  = 150 ; 
    int type ; 
    private boolean deleted = false ; 
    
    Block(int t , double x , double y)
    {
        type = t ;
        block_iv.setX(x);
        block_iv.setY(y);
        
        switch(t)
        {
            case 1 : 
                block_iv=new ImageView(block1);
                break ;
            case 2 : 
                block_iv=new ImageView(block2);
                break ; 
            case 3 : 
                block_iv=new ImageView(block3);
                break ; 
            case 4 : 
                block_iv=new ImageView(block4);
                break ; 
            case 5 : 
                block_iv=new ImageView(block5);
                break ; 
        }
        
        
    }

    Block() 
    {
        
    }
    
    public Pane create_backgroundForBricks(Pane p)
    {
           Image backGround = new Image("Resources/Images/Background/ww.jpg",1400,800,false,false) ;
           ImageView backGround_iv = new ImageView(backGround);  
        
           p.getChildren().add(backGround_iv) ; 
           
           return p ;
    }
    

    
    public static double getHeight()
    {
        return Height ;
    }
    
    public static void setHeight(double height)
    {
        Height = height ;
    }
    
    public static double getWidth()
    {
        return Width ;
    }
    
    public static void setWidth(double width)
    {
        Width = width ;
    }

    
    
    
    public double getX() 
    {
        return block_iv.getX();
    }

    public void setX(double x)
    {
        block_iv.setX(x);
    }

    public double getY()
    {
        return block_iv.getY();
    }

    public void setY(double y)
    {
        block_iv.setY(y);
    }
    
    public int getType()
    {
        return type ; 
    }
    
    public void setType(int type)
    {
        this.type = type ; 
    }
    
    public boolean isDel()
    {
        return deleted ; 
    }
    
    public void setDel(boolean del)
    {
        this.deleted = del ;
    }
    
    public static void DrawBricks(int r,int c) 
    {
       int t = 0; 
       double x=0 , y=101 ; 
        for (int i = 1 ; i <= r ; i++)
        {
           
            for (int j = 1 ; j <= c ; j++)
            {
                
                if ((j%2==0 && i%2==0 ) || (j%2!=0 && i%2!=0) ) t=1 ;
                else       t=2 ;
  
                 //= brick_pos_X +((Block.getWidth()+space)*j);
                 //= brick_pos_Y +((Block.getHeight()+space)*i);
                
                if(j==1) 
                    {y+=45 ; x=65;}
                else 
                    {x+=115 ;}
                Block b = new Block(t,x,y);
                b.block_iv.setX(x);
                b.block_iv.setY(y);
                blockList.add(b);
               
                
            }
        }
    }

    public static void getDefault(int r , int c)
    {
        blockList.clear();
        DrawBricks(r, c);
    }
    
}
