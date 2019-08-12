package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class getProperty {

    private static Properties configProperties = new Properties();

    static {

    InputStream inputStream = getProperty.class.getClassLoader().getResourceAsStream("config.properties");
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);


        try {
            configProperties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigProperty (String propertyName)
    {
        return configProperties.getProperty(propertyName);
    }




}
