package edu.nintendo.pojo;

import edu.nintendo.annotation.Id;
import edu.nintendo.annotation.Json;
import edu.nintendo.annotation.Key;
import edu.nintendo.model.Stringfy;

@Json(path = "resource.db.{data.json}")
public class PokeEntity {

    @Id
    private int id;

    @Key(name = "name")
    private String name;

    @Key(name = "img")
    private String img;

    @Key(name = "number")
    private String number;

    @Key(name = "type")
    private String[] type;

    @Key(name = "weaknesses")
    private String[] weaknesses;

    @Override
    public String toString() {
        return "{\n" +
                "   \"id\": "+id+",\n" +
                "   \"name\":\""+name+"\",\n" +
                "   \"img\":\""+img+"\",\n" +
                "   \"number\":\""+number+"\",\n" +
                "   \"type\":\""+ Stringfy.arrayToString(type) +"\",\n" +
                "   \"weaknesses\":\""+ Stringfy.arrayToString(weaknesses) +"\"\n" +
                "}";
    }



    //GETS AND SETS
    public int getId() {
        return id;
    }

    public String getName() {
        return name != null ? name : "Sin nombre";
    }

    public String getImg() {
        return img != null ? img : "/resource/img/pokedex/detail/default.png";
    }

    public String getNumber() {
        return number != null ?  number : "N.º***";
    }

    public String[] getType() {
        return type != null ? type : new String[]{"Sin tipo"};
    }

    public String[] getWeaknesses() {
        return weaknesses != null ? weaknesses : new String[]{"No hay debilidades"};
    }

}
