package busmode.message;

/**
 * Created by Feiyu on 2015/6/27 0027.
 */
public class Bus {
    private static Bus ourInstance = new Bus();

    public static Bus getInstance() {
        return ourInstance;
    }

    private Bus() {
    }


}
