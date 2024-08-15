package menustuff;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public final class GetHardcodedData
{
    private static final Map<String, String> dataMap = new HashMap<>();

    static {
        dataMap.put("openingFileName", "demoPresentation.xml");
        dataMap.put("TESTFILE", "testPresentation.xml");
        dataMap.put("SAVEFILE", "savedPresentation.xml");
        // Add more key-value pairs as needed
    }

    public static String getData(String key) {
        return dataMap.getOrDefault(key, "Key not found");
    }
}
