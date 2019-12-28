
package Arkanoid;

import static Arkanoid.Menu.sceneButtons;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PlayerNameFile
{ 
    
    Scene scene_StoreName ;
    Pane pane_storeName ;

    
    TextField textField = new TextField();
    Button btn_start = new Button(); 
    Line sep ;
    
    File file = new File("F:\\Arkanoid-Game--master\\src\\Resources\\Files\\player.txt");
    FileWriter filewriter ;
    BufferedWriter bufferwriter ;
     
    String PlayerName_string;
    
    private String nameofplayer ="";
     
     
     
     // A Function To make The Scene Of Writing Player Name 
     public  Scene MakeScene_PlayerName (Stage stage_menu) throws IOException
     { 
         pane_storeName = new Pane() ;
         scene_StoreName = new Scene(pane_storeName,1370,750) ;
         BaseClsas.check_Escape(scene_StoreName, Menu.stage_menu,Menu.sceneButtons );
         //============Background
        Image playerName_img=new Image("Resources/Images/Background/storeName_back.png",1400,780,false,true);
        ImageView playerName_iv = new ImageView(playerName_img);
        pane_storeName.getChildren().add(playerName_iv);
        //=========================
      
        textField.setTranslateX(470);
        textField.setTranslateY(350);
        textField.setPrefSize(410, 50);
        textField.setFont( Font.font("CoubraFont", FontWeight.BOLD,35 ));
        textField.setPromptText("Enter Your Name");
        textField.setFocusTraversable(false);
        addTextLimiter(textField,14);
        
        btn_start.setTranslateX(470);
        btn_start.setTranslateY(430);
        btn_start.setPrefSize(410, 35);
        btn_start.setFont(new Font("Arial", 30));
        btn_start.setTextFill(Color.BLACK);
        btn_start.setText("START");
        btn_start.setStyle("-fx-border-color:#5f27cd; -fx-background-color: #663333 ");
        btn_start.setCursor(new ImageCursor(Menu.cursor_img_hand));
        
        sep= new Line(300,300,1000,0);
        
        sep.setStroke(Color.LIMEGREEN);
        
        pane_storeName.getChildren().addAll(textField,btn_start);
        //=============================
         
         WriteNameInFile(stage_menu) ;
         
         
        //=============================
        pane_storeName.setCursor(new ImageCursor(Menu.cursor_img));
        stage_menu.setScene(scene_StoreName);   
        stage_menu.setMaximized(true);
        stage_menu.setResizable(false);
         
         return scene_StoreName; 
     }
     
     
     //Write Name in The File
     public  void WriteNameInFile(Stage st ) throws IOException
     {
        filewriter = new FileWriter(file, true);
        bufferwriter = new BufferedWriter(filewriter);
        
        
          btn_start.setOnAction(e -> 
          {
            nameofplayer=textField.getText();
            try
            { 
                PlayerName_string = textField.getText() ;
                
                if( PlayerName_string.length()>0 && !PlayerName_string.contains(" ") ) //Validation
                {
                    bufferwriter.write(textField.getText() + "\n");
                    bufferwriter.close();
                    st.setScene(Arkanoid_main.scene_1 );
                    st.setTitle("Arkanoid");
                    BaseClsas.ShowPlayerNameOnScreen( Arkanoid_main.scene_1 , PlayerName_string) ;
                    Sound.mediaPlayer_background.play();
                }
            } 
            catch (IOException ex) 
            {
                System.out.println("NOT Success");  
            }
           
        });
         
     }
     
     

     
     
     
     public String getPlayerName()
     {
         return PlayerName_string ;
     }
     
     
   
      
      
       //A function to Determine The Limit of The TextField
     public static void addTextLimiter(final TextField tf, final int maxLength)
     {
        tf.textProperty().addListener(new ChangeListener<String>()
         {
        @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) 
                {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
        }
       });
    }
}
