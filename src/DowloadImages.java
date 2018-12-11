
import edu.nintendo.pojo.Entity;
import edu.nintendo.pojo.PokeEntity;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DowloadImages {


    public static void main(String[] args) {

        List<PokeEntity> data = Entity.inject(PokeEntity.class);

        data.forEach( o -> {

            System.out.println(o.getImg());
            if (o.getImg().contains("https://")) {
                try {
                    // Url con la foto
                    URL url = new URL(o.getImg());

                    // establecemos conexion
                    URLConnection urlCon = url.openConnection();

                    // Sacamos por pantalla el tipo de fichero
                    System.out.println(urlCon.getContentType());

                    // Se obtiene el inputStream de la foto web y se abre el fichero
                    // local.
                    InputStream is = urlCon.getInputStream();
                    FileOutputStream fos = new FileOutputStream("C:\\GitKraken\\PokedexFXML\\src\\resource\\img\\pokedex\\detail\\" + o.getImg().replace("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/", ""));

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
            }
        });

    }

}
