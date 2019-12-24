
package Arkanoid;

import Arkanoid.MenuDesign.MenuBox;
import Arkanoid.MenuDesign.MenuItem;
import Arkanoid.MenuDesign.Title;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;


public class Menu 
{
   
    PlayerNameFile playerFile_obj = new PlayerNameFile() ;
    
    private static final int width=1350; 
    private static final int height=700;
    static   Stage stage_menu;
    static  Scene sceneButtons;
    private AnchorPane pane1_buttons;

    private MenuItem b1 , b2 , b3 , b4 , b5 ,b6; 
    
    setting setting_obj  ;
   
   static Image cursor_img = new Image("Resources/Images/yellowCursor2.png",200,150,false,false);
   static Image cursor_img_hand = new Image("Resources/Images/hoverCursor2.png",200,150,false,false);

      public Menu(Scene scene)
      {    
        
        stage_menu=new Stage();
        stage_menu.setMaximized(true);
        stage_menu.setResizable(false);
        pane1_buttons=new AnchorPane();
    
        Title title = new Title("A R K A N O I D");
        title.setTranslateX(100);
        title.setTranslateY(200);

        
        Intialize();
        MenuBox VBox= new MenuBox(b1,b2,b3,b4,b6,b5);

        VBox.setTranslateX(100);
        VBox.setTranslateY(300);

        
        AccessMenu(scene);
        createBackground();
        Sound.mediaPlayer_Menu_Back.stop();
        Sound.mediaPlayer_Menu_Back.play();
        pane1_buttons.getChildren().addAll(title,VBox);
        sceneButtons.setCursor(new ImageCursor(cursor_img));
        
      }


      
    public Stage getMainStage()
      {
          return stage_menu; 
      }
    
    private void Intialize()
    {
        b1 = new MenuItem("START ARKANOID");
        b2 = new MenuItem("HIGH SCORES");
        b3 = new MenuItem("SETTINGS");
        b4 = new MenuItem("DRAW YOUR LEVEL");
        b5 = new MenuItem("EXIT");
        b6 = new MenuItem("ENEMY");
    }

  
   private void createBackground()
    {
        Image Imageback=new Image("Resources/Images/Background/bb.jpg",1500,780,false,true);
        BackgroundImage background = 
        new BackgroundImage(Imageback,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        pane1_buttons.setBackground(new Background(background));
       
    } 
    //namefile.enter_name_scene
  public void AccessMenu(Scene scene)
  {
       b1.setOnMousePressed(event ->
        { 
            Sound.mediaPlayer_menu.stop();
            Sound.mediaPlayer_menu.play();
           try
           {
               stage_menu.setScene(playerFile_obj.MakeScene_PlayerName(stage_menu));
               stage_menu.setTitle("Arkanoid - Enter Your Name");
           } 
           catch (IOException ex) 
           {
               Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        } );    
       
        b2.setOnMousePressed(e->
         { 
            Sound.mediaPlayer_menu.stop();
            Sound.mediaPlayer_menu.play();
             try 
             {
                 score score_obj = new score(stage_menu) ;
             }
             catch (IOException ex) 
             {
                 Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
             }
         }); 
        b3.setOnMousePressed(e->
         { 
            Sound.mediaPlayer_menu.stop();
            Sound.mediaPlayer_menu.play();
            setting_obj = new setting (stage_menu) ;
         }); 
        
          b4.setOnMousePressed(e->
         { 
            Sound.mediaPlayer_menu.stop();
            Sound.mediaPlayer_menu.play();
            DrawYourLevel drawLevel = new DrawYourLevel (stage_menu) ;  
         }); 
          
          b5.setOnMousePressed(e->
         { 
            Sound.mediaPlayer_menu.play();
            stage_menu.hide(); 
         }); 
          
           b6.setOnMousePressed(e->
         { 
            Sound.mediaPlayer_menu.stop();
            Sound.mediaPlayer_menu.play();
            Enemy enemy_obj = new Enemy (stage_menu) ;   
         }); 
           
        sceneButtons=new Scene(pane1_buttons,1370,750);
        stage_menu.setScene(sceneButtons);  
  }
}
