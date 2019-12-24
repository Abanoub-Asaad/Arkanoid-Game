package Arkanoid;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class icons {

    public static ArrayList<Integer> numbers = new ArrayList<Integer>(44);

     //Set <ImageView> set_ = new TreeSet<>() ; //Set for Not Repeating the same image view of Capsules
    int[] random_num = new int[10];

    Image img_expand = new Image("Resources/Images/Capsule/expand.png", 100, 20, false, false);
    Image img_shrink = new Image("Resources/Images/Capsule/shrink.png", 100, 20, false, false);
    Image img_slow = new Image("Resources/Images/Capsule/slow.png", 100, 20, false, false);
    Image img_fast = new Image("Resources/Images/Capsule/fast.png", 100, 20, false, false);
    Image img_empty = new Image("Resources/Images/Capsule/empty.png", 100, 20, false, false);
    Image img_heart = new Image("Resources/Images/Capsule/life.png", 35, 35, false, false);
    Image img_shot_gun = new Image("Resources/Images/Capsule/laser.png", 100, 20, false, false);
    Image img_extra50 = new Image("Resources/Images/Capsule/extra50.png", 100, 20, false, false);
    Image img_extra100 = new Image("Resources/Images/Capsule/extra100.png", 100, 20, false, false);

    ImageView i_expand = new ImageView();
    ImageView i_slow = new ImageView();
    ImageView i_extra50 = new ImageView();
    ImageView i_extra100 = new ImageView();
    ImageView i_shoot = new ImageView();
    ImageView i_fast = new ImageView();
    ImageView i_empty = new ImageView();
    ImageView i_shrink = new ImageView();
    ImageView i_heart = new ImageView();

    TranslateTransition translate1 = new TranslateTransition();
    TranslateTransition translate2 = new TranslateTransition();
    TranslateTransition translate3 = new TranslateTransition();
    TranslateTransition translate4 = new TranslateTransition();
    TranslateTransition translate5 = new TranslateTransition();
    TranslateTransition translate6 = new TranslateTransition();
    TranslateTransition translate7 = new TranslateTransition();
    TranslateTransition translate8 = new TranslateTransition();
    TranslateTransition translate9 = new TranslateTransition();
    TranslateTransition translate10 = new TranslateTransition();

    double y_icone, x_icone;
    int num_of_icon;

    Paddle paddl = new Paddle();
    Block bl = new Block();
    score s = new score();

    public void transation_icon() {

        switch (num_of_icon) {
            case 0:
                i_slow.setImage(img_slow);
                i_slow.setX(x_icone);
                i_slow.setY(y_icone);
                translate1.setToY(650);
                translate1.setDuration(Duration.millis(2500));
                translate1.setCycleCount(1);
                translate1.setAutoReverse(false);
                translate1.setNode(i_slow);
                translate1.play();
                break;
            case 1:
                i_expand.setImage(img_expand);
                i_expand.setX(x_icone);
                i_expand.setY(y_icone);
                translate3.setToY(650);
                translate3.setDuration(Duration.millis(2500));
                translate3.setCycleCount(1);
                translate3.setAutoReverse(false);
                translate3.setNode(i_expand);
                translate3.play();
                break;
            case 3:
                i_heart.setImage(img_heart);
                i_heart.setX(x_icone);
                i_heart.setY(y_icone);
                translate4.setToY(650);
                translate4.setDuration(Duration.millis(2500));
                translate4.setCycleCount(1);
                translate4.setAutoReverse(false);
                translate4.setNode(i_heart);
                translate4.play();
                break;
            case 4:
                i_extra100.setImage(img_extra100);
                i_extra100.setX(x_icone);
                i_extra100.setY(y_icone);
                translate5.setToY(650);
                translate5.setDuration(Duration.millis(2500));
                translate5.setCycleCount(1);
                translate5.setAutoReverse(false);
                translate5.setNode(i_extra100);
                translate5.play();
                break;
            case 5:
                i_shoot.setImage(img_shot_gun);
                i_shoot.setX(x_icone);
                i_shoot.setY(y_icone);
                translate6.setToY(650);
                translate6.setDuration(Duration.millis(2500));
                translate6.setCycleCount(1);
                translate6.setAutoReverse(false);
                translate6.setNode(i_shoot);
                translate6.play();
                break;
            case 6:
                i_shrink.setImage(img_shrink);
                i_shrink.setX(x_icone);
                i_shrink.setY(y_icone);
                translate7.setToY(650);
                translate7.setDuration(Duration.millis(2500));
                translate7.setCycleCount(1);
                translate7.setAutoReverse(false);
                translate7.setNode(i_shrink);
                translate7.play();
                break;
            case 7:
                i_extra50.setImage(img_extra50);
                i_extra50.setX(x_icone);
                i_extra50.setY(y_icone);
                translate8.setToY(700);
                translate8.setDuration(Duration.millis(2500));
                translate8.setCycleCount(1);
                translate8.setAutoReverse(false);
                translate8.setNode(i_extra50);
                translate8.play();
                break;
            case 8:
                i_fast.setImage(img_fast);
                i_fast.setX(x_icone);
                i_fast.setY(y_icone);
                translate9.setToY(650);
                translate9.setDuration(Duration.millis(2500));
                translate9.setCycleCount(1);
                translate9.setAutoReverse(false);
                translate9.setNode(i_fast);
                translate9.play();
                break;
            case 9:
                i_empty.setImage(img_empty);
                i_empty.setX(x_icone);
                i_empty.setY(y_icone);
                translate10.setToY(650);
                translate10.setDuration(Duration.millis(2500));
                translate10.setCycleCount(1);
                translate10.setAutoReverse(false);
                translate10.setNode(i_empty);
                translate10.play();
                break;

        }

    }

    public void check_number(int index, Block b) {
        for (int i = 0; i < 9; i++) {
            if (random_num[i] == index) {
                num_of_icon = i;
                x_icone = b.getX();
                y_icone = b.getY();
                bl.blockList.indexOf(b);
                transation_icon();
            }
        }
    }

    public void make_random_var() {
        for (int i = 0; i < Block.blockList.size(); i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int j = 0; j < 9; j++) {
            random_num[j] = numbers.get(j);
        }

    }
}
