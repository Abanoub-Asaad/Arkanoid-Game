
package Arkanoid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class score 
{ 
    
    HashMap<String, Integer> map = new HashMap<>();        
    List <Map.Entry<String, Integer>> list ; 
    
    Scanner scanner ;
    Iterator iterator ;  
    
    Scene ScoreBorad_scene ;
    Pane pane_scoreBoard ;
    
    private PlayerNameFile player_obj = new PlayerNameFile();
    
    
    public int score = 0 ;
    Text score_text = new Text();
    Text score_t = new Text();
    Text level_t = new Text();
    Text level_text = new Text();   
    
    Text Player_Name_txt = new Text() ; 
    Text Score_value_txt = new Text() ;
    Text Level_value_txt = new Text() ;
    
  
    private Text t1_name = new Text() ;  
    private Text t1_score = new Text() ;
    Text lives_txt       = new Text() ;
    String st; 
    
    public int level = 1; 

    
    
    public void score(int value , int l)
    {
        if(level == l+1)
            level++;

        level_t.setX(350);  
        level_t.setY(40);
        level_t.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        level_t.setFill(Color.RED);// setting colour of the text to blue   
        level_t.setStroke(Color.CYAN); // setting the stroke for the text    
        level_t.setText("LEVEL :  ");

        level_text.setX(450);  
        level_text.setY(40);
        level_text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        level_text.setFill(Color.RED);// setting colour of the text to blue   
        level_text.setStroke(Color.CYAN); // setting the stroke for the text    
        level_text.setText(Integer.toString(level));


        score_t.setX(70);  
        score_t.setY(40);
        score_t.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        score_t.setFill(Color.RED);// setting colour of the text to blue   
        score_t.setStroke(Color.CYAN); // setting the stroke for the text    
        score_t.setText("SCORE : ");

        score_text.setX(170);  
        score_text.setY(40);
        score_text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        score_text.setFill(Color.RED);// setting colour of the text to blue   
        score_text.setStroke(Color.CYAN); // setting the stroke for the text    
        score_text.setText(Integer.toString(score));
        
        lives_txt.setX(940);  
        lives_txt.setY(40);
        lives_txt.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        lives_txt.setFill(Color.RED);// setting colour of the text to blue   
        lives_txt.setStroke(Color.CYAN); // setting the stroke for the text    
        lives_txt.setText("LIVES : ");
        
        switch(value)
        {
            case 1 :
                score+=20;
                  break;
            case 2 :
                score+=70;
                System.out.print("extra 50/////////////// \n");
                  break;
            case 3 :
                score+=120;
                  break;
            case 4 :
                score+=220;
                  break;
            case 5 :
                score+=500;
                  break;
            default :
                score = 0 ;
                break ; 
        }
       
    }
    
    //Constructor For Puting Scene of The High Scores in the Stage Of The Menu 
     score(Stage stage_menu) throws IOException
    {
        pane_scoreBoard = new Pane() ; 
        ScoreBorad_scene = new Scene(pane_scoreBoard);
       
        Image ScoreBoardBack_img=new Image("Resources/Images/leaderboard.jpg",1400,780,false,true);
        ImageView ScoreBoardBack_iv = new ImageView(ScoreBoardBack_img);
        pane_scoreBoard.getChildren().add(ScoreBoardBack_iv);

        ReadFromFile();
        
        BaseClsas.check_Escape(ScoreBorad_scene, Menu.stage_menu,Menu.sceneButtons );
        stage_menu.setTitle("Arkanoid");
        stage_menu.setScene(ScoreBorad_scene);

    }
     //=============================================================
    
     //================================================================
    
     
    public int getScore()
    {
        return score ;
    } 
    
     score()
     {
     
     }
    
   public  void ReadFromFile() throws FileNotFoundException, IOException
     {
         BufferedReader br = new BufferedReader(new FileReader(player_obj.file)); 
  
            int i=1 ;
            
            while ((st = br.readLine()) != null) 
            { 
                t1_name = new Text () ;
                t1_score = new Text();
                   if(i%2!=0)
                {
                    t1_name.setX(350);  
                    t1_name.setY(200+((i+1)/2)*40);
                    t1_name.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,35));
                    t1_name.setFill(Color.CHOCOLATE);// setting colour of the text to blue    
                    t1_name.setText(st);
                    pane_scoreBoard.getChildren().add(t1_name) ;
                }
                else 
                {
                    t1_score.setX(960);  
                    t1_score.setY(200+(i/2)*40);
                    t1_score.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,35));
                    t1_score.setFill(Color.CHOCOLATE);// setting colour of the text to blue    
                    t1_score.setText(st);
                    pane_scoreBoard.getChildren().add(t1_score) ; 
                }
                
                ++i; 
                System.out.println(st);
            } 
     }  
   
    
}
