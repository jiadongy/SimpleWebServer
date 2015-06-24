package examples.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Feiyu on 2015/6/25 0025.
 */
public class Logger {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    {
        logger.error("first log4j");
    }

    static public void main(String... args){
        Logger logger = new Logger();
    }
}

