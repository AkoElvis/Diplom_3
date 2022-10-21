package stellarburgers.TestData;

import java.util.Random;

public class CreatingRandomData {
    public static String getRandomAlekseyString() {
        return "Aleksey" + new Random().nextInt(10);
    }

    public static String getRandomShortAlexString() {
        return "Alex" + new Random().nextInt(10);
    }

    public static String getRandomAlekseyEmail() {
        return "Aleksey" + new Random().nextInt(10) + "@sample" + new Random().nextInt(10) + ".net";
    }
}
