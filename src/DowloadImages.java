import edu.nintendo.model.ReadData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DowloadImages {


    public static void main(String[] args) {

        JSONArray data = ReadData.getData();

        data.forEach( o -> {

            JSONObject object = (JSONObject) o;

            System.out.println(object.get("img"));

            try {
                // Url con la foto
                URL url = new URL(object.get("img").toString());

                // establecemos conexion
                URLConnection urlCon = url.openConnection();

                // Sacamos por pantalla el tipo de fichero
                System.out.println(urlCon.getContentType());

                // Se obtiene el inputStream de la foto web y se abre el fichero
                // local.
                InputStream is = urlCon.getInputStream();
                FileOutputStream fos = new FileOutputStream("C:\\GitKraken\\PokedexFXML\\src\\resource\\img\\pokedex\\detail\\" + object.get("img").toString().replace("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/",""));

                // Lectura de la foto de la web y escritura en fichero local
                byte[] array = new byte[1000]; // buffer temporal de lectura.
                int leido = is.read(array);
                while (leido > 0) {
                    fos.write(array, 0, leido);
                    leido = is.read(array);
                }

                // cierre de conexion y fichero.
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

}
