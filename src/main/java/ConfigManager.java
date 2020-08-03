import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    Properties properties = new Properties();

    private static ConfigManager instance = null;

    public static ConfigManager getInstance(){
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private ConfigManager(){
        try{
            String propFileName = "conf.prop";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public <T> T getValue(String key,T defaultVal){
        Object result = defaultVal;
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                if(defaultVal instanceof Integer){
                    result = Integer.parseInt(value);
                }
                else if(defaultVal instanceof String){
                    result = value;
                }
                else if(defaultVal instanceof Double){
                    result = Double.parseDouble(value);
                }
                else if(defaultVal instanceof Boolean){
                    result = Boolean.parseBoolean(value);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return (T)result;
    }

    public Object setValue(String key, Object val){
        return properties.setProperty(key, String.valueOf(val));
    }

    public static void main(String[] args) {
        ConfigManager configManager = ConfigManager.getInstance();
        String fileName = configManager.getValue("fileName", "fileName");
        System.out.println(fileName);
        Integer k2 = configManager.getValue("k2",2);
        System.out.println(k2);
    }

}
