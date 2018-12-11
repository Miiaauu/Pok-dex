package edu.nintendo.pojo;

import edu.nintendo.annotation.Id;
import edu.nintendo.annotation.Json;
import edu.nintendo.annotation.Key;
import edu.nintendo.model.Stringfy;
import javafx.scene.image.Image;

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
        return !name.isEmpty() ? name : "Sin nombre";
    }

    public Image getImg(int w,int h) {
        return new Image(img,w, h, true, true);
    }

    public String getImgPath() {
        return img;
    }

    public String getNumber() {
        return !number.isEmpty() ?  number : "N.º***";
    }

    public String[] getType() {
        return type != null ? type : new String[]{"Sin tipo"};
    }

    public String[] getWeaknesses() {
        return weaknesses != null ? weaknesses : new String[]{"No hay debilidades"};
    }

}
