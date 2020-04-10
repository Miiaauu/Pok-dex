package edu.nintendo.pojo;

import edu.nintendo.annotation.Id;
import edu.nintendo.annotation.Json;
import edu.nintendo.annotation.Key;
import edu.nintendo.model.ReadData;
import javafx.scene.image.Image;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    private static List list = new JSONArray();

    public static List inject(Class clazz) {

        list.clear();

        try {

            Json jsonAnn = (Json) clazz.getAnnotation(Json.class);
            JSONArray info = getData(jsonAnn.path());
            int i = 1;

            for (Object json : info) {

                Object o = clazz.newInstance();
                JSONObject object = (JSONObject) json;

                for (Field field : o.getClass().getDeclaredFields()) {

                    Key key = (Key) field.getAnnotation(Key.class);
                    Id id = (Id) field.getAnnotation(Id.class);
                    field.setAccessible(true);

                    if (key != null) {
                        switch (field.getType().toString()) {
                            case "int":
                                field.set(o,Integer.parseInt(object.get(key.name()).toString()));
                                break;
                            case "class java.lang.String":
                                field.set(o, object.get(key.name()).toString());
                                break;
                            case "class [Ljava.lang.String;":
                                String[] out = object.get(key.name()) == null ? new String[]{} : object.get(key.name()).toString().replace("[", "").replace("]", "").replace("\"","").split(",");
                                field.set(o, out);
                                break;
                        }
                    }

                    if (id != null) {
                        switch (field.getType().toString()) {
                            case "int":
                                field.set(o,i);
                                break;
                            case "class java.lang.String":
                                field.set(o, object.get(key.name()).toString());
                                break;
                            case "class [Ljava.lang.String;":
                                String[] out = object.get(key.name()) == null ? new String[]{} : object.get(key.name()).toString().replace("[", "").replace("]", "").split(",");
                                field.set(o, out);
                                break;
                        }
                    }
                }
                list.add(o);
                i++;
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return list;
    }

    public static List findAllPokemon(Class clazz,Object o){

        List<PokeEntity> list = Entity.list;
        List<PokeEntity> out = new JSONArray();

        for (PokeEntity pk : list){

            if (pk.getName().toUpperCase().contains(o.toString().toUpperCase())) {
                out.add(pk);
            }

            if (pk.getNumber().toUpperCase().contains(o.toString().toUpperCase())) {
                out.add(pk);
            }

        }

        return out;
    }

    public static PokeEntity findPokemon(Class clazz, int id) {
        return (PokeEntity) Entity.list.get(id - 1);
    }

    public static List<String> getCategories(Class clazz) {

        List<PokeEntity> list = Entity.list;
        ArrayList<String> out = new ArrayList<>();

        for (PokeEntity pk : list) {
            for(String type : pk.getType()) {
                if(!out.contains(type)) {
                    out.add(type);
                }
            }
        }

        return out;
    }

    private static JSONArray getData(String path) {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        String pathF = "/" + path.replace(".json","*json").replace(".","/").replace("{","").replace("}","").replace("*",".");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream(pathF), "utf-8"));

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
