package stellarburgers.TestData;

import java.util.Random;

public class CreatingRandomData {
    public static String getRandomEdeltanString() {
        return "Edeltan" + new Random().nextInt(10);
    }

    public static String getRandomShortAlexString() {
        return "Alex" + new Random().nextInt(10);
    }

    public static String getRandomEdeltanEmail() {
        return "Edeltan" + new Random().nextInt(10) + "@dev" + new Random().nextInt(10) + ".dev";
    }

}
