import java.awt.*;

/**
 * This is an Autotyper to boost my XP in a discord bot
 *
 * @author Jonathan Wiggins
 * @version 7/8/2017
 */
public class TypeAPhrase {


    public static void main(String[] args) {
        String phraseToType = "t!slots\n";
        int timeOut = 11500;
        int timesToRun = 0;


        Keyboard keyboard = null;
        int timesRun = 0;
        try {
            keyboard = new Keyboard();
            Thread.sleep(500);
            while (timesToRun == 0 || timesToRun > timesToRun){
                keyboard.type(phraseToType);

                System.out.println("Count: " + timesRun +"\t Approx XP Gained: " +timesRun * 2);
                timesRun++;
                Thread.sleep(timeOut);


            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
