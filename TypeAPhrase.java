import java.awt.*;
import java.util.Random;

/**
 * This is a Discord AutoTyper
 *
 * @author Jonathan Wiggins
 * @version 10/10/2019
 */
public class TypeAPhrase {

    /**
     * An example of how to use this bot
     * Creates a new Typer with some settings and runs it
     */
    public static void main(String[] args) {
        String[] phrases = new String[]{"Phrase1", "Phrase2", "Phrase3"};
        long timesToRun = Long.MAX_VALUE;
        long timeOut = 11500;
        long startTimeout = 3000;
        boolean printStatus = true;
        boolean randomOrder = true;


        Typer typer = new Typer(phrases, timesToRun, timeOut, startTimeout, printStatus, randomOrder);

        try {
            typer.run();
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Typer {
    private String[] phrases;
    private long startTimeout;
    private long timesToRun;
    private long sleepTimeout;
    private boolean printStatus;
    private boolean randomOrder;
    private Random rng;


    /**
     * Creates a new Typer
     *
     * @param phrase a single phrase to type
     * @param timesToRun The number of times to type a phrase (0 or Long.MAX_Value for infinity)
     * @param sleepTimeout The number of milliseconds to sleep between phrases
     * @param startTimeout The number of milliseconds to sleep after running
     * @param printStatus Prints status after each printing and when completed if true
     */
    public Typer(String phrase, long timesToRun, long sleepTimeout, long startTimeout, boolean printStatus) {
        this.phrases = new String[]{phrase};
        this.timesToRun = timesToRun;
        this.printStatus = printStatus;
        this.sleepTimeout = sleepTimeout;
        this.randomOrder = false;
        this.startTimeout = startTimeout;

        formatPhrases();
    }

    /**
     * Creates a new Typer
     *
     * @param phrases a single phrase to type
     * @param timesToRun The number of times to type a phrase (0 or Long.MAX_Value for infinity)
     * @param sleepTimeout The number of milliseconds to sleep between phrases
     * @param startTimeout The number of milliseconds to sleep after running
     * @param printStatus Prints status after each printing and when completed if true
     * @param randomOrder Types the phrases iteratively if false, randomly if false
     */
    public Typer(String[] phrases, long timesToRun, long sleepTimeout, long startTimeout, boolean printStatus, boolean randomOrder) {
        this.phrases = phrases;
        this.sleepTimeout = sleepTimeout;
        this.timesToRun = timesToRun;
        this.printStatus = printStatus;
        this.randomOrder = randomOrder;
        this.startTimeout = startTimeout;

        if (this.randomOrder)
            this.rng = new Random();

        formatPhrases();
    }

    /**
     * Ensures that each phrase in this.phrases ends with "\n"
     */
    private void formatPhrases(){
        for (int index = 0; index < this.phrases.length; index++){
            if (!this.phrases[index].endsWith("\n"))
                this.phrases[index] = this.phrases[index] + "\n";
        }
    }

    /***
     * Runs this typer
     *
     * @throws AWTException if a keyboard typer cannot be created
     * @throws InterruptedException if the thread cannot sleep
     */
    public void run() throws AWTException, InterruptedException {
        int timesRun = 0;
        Keyboard keyboard = new Keyboard();

        Thread.sleep(this.startTimeout);

        while (this.timesToRun == 0 || this.timesToRun == Long.MAX_VALUE || timesRun < this.timesToRun) {
            String nextPhrase;
            if (randomOrder)
                nextPhrase = this.phrases[rng.nextInt(phrases.length)];
            else
                nextPhrase = this.phrases[timesRun % (phrases.length)];

            keyboard.type(nextPhrase);

            if (printStatus)
                System.out.println("Count: " + timesRun + "\tTyped: " + nextPhrase);
            timesRun++;
            Thread.sleep(this.sleepTimeout);
        }
        if (printStatus)
            System.out.println("Completed");
    }
}

