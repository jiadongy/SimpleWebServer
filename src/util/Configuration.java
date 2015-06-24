package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Feiyu on 2015/6/22 0022.
 */
public class Configuration {
    static Properties defaultConf = new Properties();
    static {
        defaultConf.setProperty("callbackmode.FileManager.useChannel","true");
    }

    private Properties properties = new Properties(defaultConf);

    public Configuration(String confPath) {
        loadProperties(confPath);
    }

    public Configuration() {
    }

    public String get(String key){
        return properties.getProperty(key);
    }

    private void loadProperties(String confPath){
        try(FileReader reader = new FileReader(confPath)){
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

}
