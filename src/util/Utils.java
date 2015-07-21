package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Feiyu on 2015/6/28 0028.
 */
public final class Utils {

    public final static class Lists {

        public interface InnerListEqual<M, N>  {
            boolean equal(M listElement, N obj);
        }

        public static <E,U> boolean innerContains(List<E> list, U obj,
                                                  InnerListEqual<E, U> equalHandler){
            for (E aList : list) {
                if (equalHandler.equal(aList, obj))
                    return true;
            }
            return false;
        }

        public static <E,U> int getFirstIndexInList(List<E> list, U obj,
                                                    InnerListEqual<E, U> equalHandler){
            for (E element : list) {
                if (equalHandler.equal(element, obj))
                    return list.indexOf(element);
            }
            return -1;
        }
    }

    private static File file = new File("screen.log");
    public static FileWriter writer;

    static {
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean logToFile = true;


    public static void log(String log) {
        System.out.println(log);
        if (logToFile) {
            try {
                writer.append(log).append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
