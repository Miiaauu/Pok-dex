package edu.nintendo.model;

public class Stringfy {

    public static String arrayToString(String[] stgs) {

        String out = "";
        for (String st : stgs) {
            out += st + ",";
        }

        if (out.length() != 0) {
            out = out.substring(0,out.length() - 1);
        }

        return "[" + out + "]";
    }

    public static String[] stringToArray(String stg) {
        return stg.replace("[","").replace("]","").replace("\"","").split(",");
    }
}
