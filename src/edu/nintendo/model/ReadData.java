package edu.nintendo.model;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ReadData {

    public static JSONArray getData() {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream("/resource/db/data.json"), "utf-8"));

            String string;
            while ((string = in.readLine())!=null) {
                out += string;
            }

            array = (JSONArray) parser.parse(out);

            return array;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            array = (JSONArray) parser.parse("[{\"error\":\"No se pudieron obtener los datos.\"}]");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return array;
    }

}
