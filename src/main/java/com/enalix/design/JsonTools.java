package com.enalix.design;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by enali on 6/1/15.
 */
public class JsonTools {

    private static final String[] types = {"o", "a", "s", "b", "d", "i", "l"};

    public static String[][] parse(String jsonData, String[] selects) {
        JSONArray jsonArray = new JSONArray(jsonData);
        int lenJson = jsonArray.length();
        int lenSelect = selects.length;
        String[][] values = new String[lenJson][lenSelect];
        for (int i=0; i<lenJson; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            values[i] = parseSelect(obj, selects);
        }
        return values;
    }

    public static String parseSelect(JSONObject obj, String select) {
        if (!validateSelect(select)) {
            System.err.printf("%s is not validate select", select);
            System.exit(-1);
        }
        String[] paths = select.split("->");
        int len = paths.length;
        String[][] temp = new String[len][2];
        for (int i=0; i<len; i++) {
            temp[i] = paths[i].split("\\$");
        }
        Object abc = obj;
        if (len > 1) {
            abc = obj.getJSONObject(temp[0][1]);
            for (int i = 1; i < len - 1; i++) {
                switch (temp[i][0]) {
                    case "o":
                        switch (temp[i - 1][0]) {
                            case "o":
                                abc = ((JSONObject) abc).getJSONObject(temp[i][1]);
                                break;
                            case "a":
                                abc = ((JSONArray) abc).getJSONObject(Integer.parseInt(temp[i][1]));
                                break;
                        }
                        break;
                    case "a":
                        switch (temp[i - 1][0]) {
                            case "o":
                                abc = ((JSONObject) abc).getJSONArray(temp[i][1]);
                                break;
                        }
                        break;
                }
            }
        }
        switch (temp[len-1][0]) {
            case "s": return ((JSONObject) abc).getString(temp[len-1][1]);
            case "b": return ((Boolean) ((JSONObject) abc).getBoolean(temp[len-1][1])).toString();
            case "d": return ((Double) ((JSONObject) abc).getDouble(temp[len-1][1])).toString();
            case "i": return ((Integer) ((JSONObject) abc).getInt(temp[len-1][1])).toString();
            case "l": return ((Long) ((JSONObject) abc).getLong(temp[len-1][1])).toString();
        }
        //TODO:错误处理
        return null;
    }

    public static String[] parseSelect(JSONObject obj, String[] selects) {
        int len = selects.length;
        String[] values = new String[len];
        for (int i=0; i<len; i++)
            values[i] = parseSelect(obj, selects[i]);
        return values;
    }

    public static boolean validateSelect(String select) {
        String ele = "([a-z]+_)*[a-z]+";
        String pat = "^(o\\$ele->|a\\$ele->o\\$[0-9]+->)*[sbdil]\\$ele$";
        if (select.matches(pat.replaceAll("ele", ele)))
            return true;
        return false;
    }

    public static void parseToFile(String readFile, String toFile, String[] selects) {

    }

    public static void parseToFile(String readFile, String toFile) {
        File file = new File(toFile);
        if (!file.exists()) {
            System.err.println(toFile+"doesn't exist");
            System.exit(-1);
        }

    }

    public static void main(String[] args) throws IOException {
        String file = "/home/enali/design-data/10212-tweets.json";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String jsonData = br.readLine();
        String select = "s$text";
        JSONArray jsonArray = new JSONArray(jsonData);
        int len = jsonArray.length();
        for (int i=0; i<len; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String temp = JsonTools.parseSelect(obj, select);
            System.out.println(temp);
        }
        br.close();
    }
}
