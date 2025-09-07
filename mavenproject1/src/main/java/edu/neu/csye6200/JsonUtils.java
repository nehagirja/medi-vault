package edu.neu.csye6200;

import java.util.Map;


public class JsonUtils {
    public static String toJsonString(Map<String, String> data) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        int size = data.size();
        int index = 0;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            jsonBuilder.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\"");
            if (++index < size) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}