package Arkanoid;

import static Arkanoid.BaseClsas.check_Escape;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Arkanoid_main extends Application {

    public Stage primaryStage;
    Timeline timeLine_shrink = new Timeline();
    Timeline timeLine_expand = new Timeline();
    Timeline time_shoots = new Timeline();
    Image img_icon = new Image("Resources/Images/Icon/ball.png");
    Image cursor_img = new Image("Resources/Images/yellowCursor1.png", 200, 150, false, false);
    Text pressToStart = new Text();

    private boolean writeScoreOnlyOnce = false;
    private boolean clickOnlyOnce = false, Play_Animation = false;
    public static Group group = new Group();
    final Pane pane = new Pane();
    public static Scene scene_1;

    private Ball ball_obj = new Ball();
    private Paddle paddle_obj = new Paddle();
    private Block block_obj = new Block();
    private score score_obj = new score();
    private icons icons_obj = new icons();
    private shooter shoot_obj = new shooter();

    private PlayerNameFile player_obj = new PlayerNameFile();

    boolean goLeft = false;
    boolean goRight = false;
    boolean BallDoesnotMove = false;
    int level = 1;
    public Image heart_img = new Image("Resources/Images/Capsule/life.png", 30, 30, false, false);
    public ImageView heart_ivv = new ImageView(heart_img);

    public ArrayList<ImageView> heart_array = new ArrayList<>();

    public double mouse_pos_x, orgTranslateX, newTranslateX;
    public int lives = 5;
    HashMap<String, Integer> hm = new HashMap<String, Integer>();
    List<Map.Entry<String, Integer>> list;

    Scanner scanner;
    Iterator iterator;

    File file = new File("F:\\ds\\ARKANOID (Last Version)Git\\player.txt");
    boolean check_extra50 = false, check_extra100 = false;

    @Override
    public void start(Stage primaryStage) {

        block_obj.create_backgroundForBricks(pane);
        scene_1 = new Scene(pane, 1370, 750, Color.BLUEVIOLET);
        scene_1.setCursor(new ImageCursor(cursor_img));
        PressEnterToStart();
        Intialize();

        paddle_obj.dragPaddleUsingMouse(paddle_obj);

        StartMovement(scene_1);
 
        DrawHeart();

        Menu menu_obj = new Menu(scene_1);

        check_Escape(scene_1, Menu.stage_menu, Menu.sceneButtons);

        primaryStage = menu_obj.getMainStage();
        primaryStage.setTitle("Arkanoid");
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(img_icon);
        primaryStage.show();

    }

//==============================================================================================================
    public void checkBlocks() {
        for (Block b : Block.blockList) {

            //Here We determine If the ball is under or above or on left or on rigth of the block
            boolean ball_down_block = ball_obj.getX() <= b.getX() + Block.getWidth() && ball_obj.getX() >= b.getX() - ball_obj.getWidth() && ball_obj.getY() <= b.getY() + Block.getHeight() + 3 && ball_obj.getY() >= b.getY() + Block.getHeight() - 3;
            boolean ball_above_block = ball_obj.getX() <= b.getX() + Block.getWidth() && ball_obj.getX() >= b.getX() - ball_obj.getWidth() && ball_obj.getY() <= b.getY() - ball_obj.getHeight() + 3 && ball_obj.getY() >= b.getY() - ball_obj.getHeight() - 3;
            boolean ball_left_block = ball_obj.getY() <= b.getY() + Block.getHeight() && ball_obj.getY() >= b.getY() - ball_obj.getHeight() && ball_obj.getX() <= b.getX() - ball_obj.getWidth() + 3 && ball_obj.getX() >= b.getX() - ball_obj.getWidth() - 3;
            boolean blockRight = ball_obj.getY() <= b.getY() + Block.getHeight() && ball_obj.getY() >= b.getY() - ball_obj.getHeight() && ball_obj.getX() <= b.getX() + Block.getWidth() + 3 && ball_obj.getX() >= b.getX() + Block.getWidth() - 3;

            boolean iv_shoot1_brick = shoot_obj.getx_iv1() <= b.getX() + Block.getWidth() && shoot_obj.getx_iv1() >= b.getX() - shoot_obj.getWidth() && shoot_obj.gety_iv1() <= b.getY() + Block.getHeight() + 3 && shoot_obj.gety_iv1() >= b.getY() + Block.getHeight() - 3;
            boolean iv_shoot2_brick = shoot_obj.getx_iv2() <= b.getX() + Block.getWidth() && shoot_obj.getx_iv2() >= b.getX() - shoot_obj.getWidth() && shoot_obj.gety_iv2() <= b.getY() + Block.getHeight() + 3 && shoot_obj.gety_iv2() >= b.getY() + Block.getHeight() - 3;

            if (iv_shoot1_brick) {
                System.out.print(" ok " + "\n");
                if (!b.isDel()) {
                    System.out.print("shoooots " + block_obj.blockList.indexOf(b) + "\n");
                    //  icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);
                    if (check_extra50) {
                        score_obj.score(1, level);
                        System.out.print("50 \n");
                        check_extra50 = false;
                    } else {
                        score_obj.score(1, level);
                    }

                    group.getChildren().remove(b.block_iv);
                    // group.getChildren().remove(shoot_obj.shoots_iv1);

                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;
                        }
                    }
                }
            }

            if (iv_shoot2_brick) {
                System.out.print(" ok " + "\n");
                if (!b.isDel()) {
                    System.out.print("shoooots " + block_obj.blockList.indexOf(b) + "\n");
                    //  icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);
                    score_obj.score(1, level);
                    group.getChildren().remove(b.block_iv);
                    // group.getChildren().remove(shoot_obj.shoots_iv2);

                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;
                        }
                    }
                }

            }

            //==========================
            if (ball_down_block) {

                if (!b.isDel()) {
                    System.out.print("block number " + block_obj.blockList.indexOf(b) + "\n");
                    icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);

                    Sound.playsound(); //Sounds
                    if (check_extra50) {
                        score_obj.score(2, level);
                        System.out.print("50 \n");
                        check_extra50 = false;
                    } else if (check_extra100) {
                        score_obj.score(3, level);
                        System.out.print("100 \n");

                        check_extra100 = false;

                    } else {
                        score_obj.score(1, level);
                    }
                    group.getChildren().remove(b.block_iv);
                    ball_obj.setStepY(1);

                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;
                        }
                    }
                }

            } else if (ball_above_block) {

                if (!b.isDel()) {
                    icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);

                    Sound.playsound();

                    if (check_extra50) {
                        score_obj.score(2, level);
                        System.out.print("50 \n");
                        check_extra50 = false;
                    } else if (check_extra100) {
                        score_obj.score(3, level);
                        System.out.print("100 \n");

                        check_extra100 = false;

                    } else {
                        score_obj.score(1, level);
                    }
                    group.getChildren().remove(b.block_iv);
                    ball_obj.setStepY(-1);

                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;

                        }
                    }
                }

            } else if (ball_left_block) {
                if (!b.isDel()) {
                    icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);

                    Sound.playsound();

                    if (check_extra50) {
                        score_obj.score(2, level);
                        System.out.print("50 \n");
                        check_extra50 = false;
                    } else if (check_extra100) {
                        score_obj.score(3, level);
                        System.out.print("100 \n");

                        check_extra100 = false;

                    } else {
                        score_obj.score(1, level);
                    }

                    group.getChildren().remove(b.block_iv);
                    ball_obj.setStepX(-1);
                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;
                        }
                    }
                }

            } else if (blockRight) {

                if (!b.isDel()) {
                    icons_obj.check_number(block_obj.blockList.indexOf(b), b);
                    b.setDel(true);

                    Sound.playsound();

                    if (check_extra50) {
                        score_obj.score(2, level);
                        System.out.print("50 \n");
                        check_extra50 = false;
                    } else if (check_extra100) {
                        score_obj.score(3, level);
                        System.out.print("100 \n");

                        check_extra100 = false;

                    } else {
                        score_obj.score(1, level);
                    }
                    group.getChildren().remove(b.block_iv);
                    ball_obj.setStepX(1);
                    if (checkWin()) {
                        if (!BallDoesnotMove) {
                            finish(true);
                            BallDoesnotMove = true;
                        }
                    }
                }

            }

        }

    }

    public boolean checkWin() {
        for (Block b : Block.blockList) {
            if (!b.isDel()) {
                return false;
            }
        }

        Sound.mediaPlayer_background.pause();
        Sound.mediaPlayer_win.play();
        return true;

    }

    public void Intialize() {
        //Fill ArrayList With Objects
        Block.DrawBricks(4, 11);

        //Make what in The ArrayList appear on The Screen
        for (int i = 0; i < Block.blockList.size(); i++) {
            group.getChildren().add(Block.blockList.get(i).block_iv);
        }

        //To Make Arry of Random Indices (indices of The ArrayList "Bricks")
        icons_obj.make_random_var();

        paddle_obj.setX((scene_1.getWidth() / 2) - paddle_obj.getWidth());
        paddle_obj.setY((scene_1.getHeight() - paddle_obj.getWidth()));

        ball_obj.setX(paddle_obj.getX() + (paddle_obj.getWidth() / 2) - (ball_obj.getWidth() / 2) + 50);
        ball_obj.setY(paddle_obj.getY() - ball_obj.getHeight() - 1);

        group.getChildren().addAll(paddle_obj.paddle_iv, ball_obj.ball_iv,
                score_obj.score_text, score_obj.score_t, score_obj.level_t, score_obj.level_text,
                icons_obj.i_empty,
                icons_obj.i_expand, icons_obj.i_extra100, icons_obj.i_extra50,
                icons_obj.i_fast, icons_obj.i_heart, icons_obj.i_shoot, icons_obj.i_shrink, icons_obj.i_slow,
                shoot_obj.shoots_iv1, shoot_obj.shoots_iv2, shoot_obj.paddle_gun_iv, score_obj.lives_txt);

        pane.getChildren().add(group);
        score_obj.score(0, level);

        shoot_obj.shoots_iv1.setVisible(false);
        shoot_obj.shoots_iv2.setVisible(false);
        shoot_obj.paddle_gun_iv.setVisible(false);
    }

    public void StartMovement(Scene scene_1) {

        scene_1.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ENTER && clickOnlyOnce == false) {
                    GameMovement(true);
                    clickOnlyOnce = true;
                    group.getChildren().remove(pressToStart);
                }

            }
        });

    }

    public void GameMovement(boolean play_Animation) {

        AnimationTimer at;
        at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (goLeft && paddle_obj.getX() > 0 && !BallDoesnotMove) {
                    paddle_obj.setX(paddle_obj.getX() - paddle_obj.speed);
                }
                if (goRight && paddle_obj.getX() < scene_1.getWidth() - paddle_obj.getWidth() * 2 && !BallDoesnotMove) {
                    paddle_obj.setX(paddle_obj.getX() + paddle_obj.speed);
                }

                checkBorders();
                BaseClsas.checkPaddle(ball_obj, paddle_obj, goLeft, goRight);
                checkBlocks();

                Shrink.checkShrink(icons_obj, paddle_obj);
                Expand.checkExpand(icons_obj, paddle_obj, ball_obj);
                Slow.checkSlow(icons_obj, paddle_obj, ball_obj);
                Fast.checkFast(icons_obj, paddle_obj, ball_obj);
                BaseClsas.checkEmpty(icons_obj, paddle_obj);
                BaseClsas.checkPause(scene_1, ball_obj, paddle_obj, group, null);
//                    extrascore.checkExtra50(icons_obj, paddle_obj, score_obj);
                checkExtra50();
                checkExtra100();
                checkHeart();
                shoot_obj.checkShootCapsuleTouchesPaddle(icons_obj, paddle_obj);
                dragUsingKeyboard(scene_1);

                paddle_obj.Animation_Paddle(true);
                paddle_obj.dragPaddleUsingMouse(paddle_obj);

                if (!BallDoesnotMove) {
                    ball_obj.setX(ball_obj.getX() + ball_obj.getStepX() * ball_obj.getSpeed());
                    ball_obj.setY(ball_obj.getY() + ball_obj.getStepY() * ball_obj.getSpeed());
                }

            }
        };
        if (play_Animation == true) {
            at.start();
        } else {
            at.stop();
        }
    }

    //===================================== Check Borders
    public void checkBorders() {

        boolean atRightBorder = ball_obj.getX() >= (scene_1.getWidth() - ball_obj.getWidth());
        boolean atLeftBorder = ball_obj.getX() <= 0;
        boolean atTopBorder = ball_obj.getY() <= (ball_obj.getWidth()) / 2;
        boolean atBottomBorder = ball_obj.getY() >= (scene_1.getHeight() - ball_obj.getWidth());

        //Here we change the Direction of the Ball 
        if (atRightBorder) {
            ball_obj.setStepX(-1);
        }

        if (atLeftBorder) {
            ball_obj.setStepX(1);
        }

        if (atTopBorder) {
            ball_obj.setStepY(1); //ball_obj.getStepY()*-1
        }
        if (atBottomBorder) {
            ball_obj.setStepX(0);
            ball_obj.setStepY(0);
            if (!BallDoesnotMove) {
                finish(false);
                BallDoesnotMove = true;

                Sound.mediaPlayer_ball_out.play();
                Sound.mediaPlayer_background.pause();

                goRight = false;
                goLeft = false;

            }
        }

    }

    public void PressEnterToStart() {
        pressToStart.setX(scene_1.getWidth() / 2 - 250);
        pressToStart.setY(scene_1.getHeight() / 2 + 150);
        pressToStart.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 50));
        pressToStart.setFill(Color.DEEPSKYBLUE);// setting colour of the text to blue   
        pressToStart.setText("Press \" Enter \" to Start");
        group.getChildren().add(pressToStart);
    }
    //============================= (Finish)

    private void finish(boolean win) {
        if (writeScoreOnlyOnce == false) {
                    try {
                        WriteScoreInFile();
                    } catch (IOException ex) {
                        System.out.println("Doesn't Enter The Function");
                    }
                }
        Stage finishStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene_finish = new Scene(root, 400, 100);
        HBox Label = new HBox(15);
        Label.setAlignment(Pos.BOTTOM_CENTER);
        String labels;

        if (win) {
            labels = "You Win";
        } else {
            labels = "Game Over";
            Sound.mediaPlayer_ball_out.play();
        }

        Label label = new Label(labels);
        final double MAX_FONT_SIZE = 30.0; // define max font size you need
        label.setFont(new Font(MAX_FONT_SIZE)); // set to Label
        Label.getChildren().add(label);
        root.setCenter(Label);
        HBox bBox = new HBox(20);
        String restartText;
        if (win) {
            restartText = "Next Level";
        }

        Button close = new Button("Close");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();

                
            }
        }
        );

        bBox.getChildren().add(close);
        root.setBottom(bBox);
        finishStage.setScene(scene_finish);
        finishStage.show();
    }

    ;
    
   
        
    //==================================== Drag Using Keyboard
    private void dragUsingKeyboard(Scene scene1) {
        scene_1.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    goLeft = true;
                }
                if (e.getCode() == KeyCode.RIGHT) {
                    goRight = true;
                }
            }
        });
        scene_1.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    goLeft = false;
                }
                if (e.getCode() == KeyCode.RIGHT) {
                    goRight = false;
                }
            }

        });

    }

    public void WriteScoreInFile() throws IOException {
        FileWriter filewriter = new FileWriter(player_obj.file, true);
        BufferedWriter bufferwriter = new BufferedWriter(filewriter);

        try {
            bufferwriter.write(score_obj.getScore() + "\n");
            bufferwriter.close();
            System.out.println("Success");
        } catch (IOException ex) {
            System.out.println("NOT Success");
        }

        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String name = scanner.next();
            System.out.print("name" + name + "\n");
            int score = scanner.nextInt();
            System.out.print("score " + score + "\n");
            hm.put(name, score);
        }
        Map<String, Integer> hm1 = sortByValue(hm);

        try {
            filewriter = new FileWriter(file);
            bufferwriter = new BufferedWriter(filewriter);
            bufferwriter.write(" ");
            bufferwriter.close();
            filewriter = new FileWriter(file);
            bufferwriter = new BufferedWriter(filewriter);
            for (Map.Entry<String, Integer> en : hm1.entrySet()) {
                bufferwriter.write(en.getKey()
                        + "\n" + en.getValue() + "\n");
            }
            bufferwriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public void DrawHeart() {

        for (int i = 0; i < 5; i++) {
            heart_ivv = new ImageView(heart_img);
            heart_ivv.setX(1060 + i * 35);
            heart_ivv.setY(20);
            group.getChildren().add(heart_ivv);
            heart_array.add(heart_ivv);
        }
    }

    public void checkHeart() {

        if (icons_obj.i_heart.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent())) {
            icons_obj.i_heart.setImage(null);

            Sound.playsound_capsule();
            icons_obj.i_heart.setImage(null);
            ++lives;

            heart_ivv = new ImageView(heart_img);
            heart_ivv.setX(1060 + 5 * 35);
            heart_ivv.setY(20);
            group.getChildren().add(heart_ivv);
        }
    }

    public void checkExtra50() {
        if (icons_obj.i_extra50.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent())) {
            Sound.playsound_capsule();
            icons_obj.i_extra50.setImage(null);
            check_extra50 = true;
        }

    }

    public void checkExtra100() {
        if (icons_obj.i_extra100.getBoundsInParent().intersects(paddle_obj.paddle_iv.getBoundsInParent())) {
            Sound.playsound_capsule();
            icons_obj.i_extra100.setImage(null);
            check_extra100 = true;

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
