package ros;


import reader.CSV_Reader;

import java.util.HashMap;

public class UserProperty {

    public static HashMap<String, String> property;

    public static void read() {
        String config = System.getenv("USER_ROS_CONFIG");
        if ( config == null ) {
            return;
        }
        String[][] cells = CSV_Reader.read(config);
        UserProperty.property = toMap(cells);
    }

    private static HashMap<String, String> toMap(String[][] cells) {
        HashMap<String, String> map = new HashMap<>();
        for (String[] rows : cells) {
            if ( rows[0].contains(".dir") ) {
                rows[1] = System.getProperty("user.home") + "/" + rows[1];
            }
            map.put(rows[0], rows[1]);
        }
        return map;
    }

    public static String getKey(String key) {
        return property.get(key);
    }


}