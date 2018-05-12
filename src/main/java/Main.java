import java.io.IOException;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("URL a analizar: ");
        String url = scanner.next();

        try {
            Connection conexion = Jsoup.connect(url);

            // Conectandose a la URL y obteniendo el recurso al que pertenece la URL
            Connection.Response respuesta = conexion.execute();

            // Obteniendo documento HTML de la pagina
            Document documento = conexion.get();

            System.out.println("\nTarea #1:");
            System.out.println("La cantidad de lineas del recurso es de: " + getCantidadLineasRecurso(respuesta));

            System.out.println("\nTarea #2:");
            System.out.println("#2 - La cantidad de <p> es de: " + getCantidadParrafos(documento));

            getCantidadImagenesParrafo(documento);

            getCantidadForm(documento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tarea #1 - Indicar la cantidad de líneas del recurso retornado.
     * @param recurso El recurso que se esta leyendo de la URL.
     * @return La cantidad de lineas del recurso.
     */
    private static int getCantidadLineasRecurso(Connection.Response recurso) {
        return recurso.body().split("\n").length;
    }

    /**
     * Tarea #2 - Indicar la cantidad de parrafos <p> que contiene el documento HTML.
     * @param html El DOM HTML adquirido.
     * @return Cantidad de <p> encontrados en el HTML.
     */
    private static int getCantidadParrafos(Document html) {
        return html.getElementsByTag("p").size();
    }

    /**
     * Tarea #3 - Indicar la cantidad de imágenes <img> dentro de los parrafos que contiene el archivo HTML.
     * @param html El DOM HTML adquirido.
     */
    private static void getCantidadImagenesParrafo(Document html) {
        int indice = 1;

        System.out.println("\nTarea #3:");
        for(Element parrafo : html.getElementsByTag("p")) {
            int cantidadImg = parrafo.getElementsByTag("img").size();
            System.out.println("El parrafo #" + indice + " conteniene " + cantidadImg + " imagenes.");
            indice++;
        }
    }

    /**
     * Tarea #4 - Indicar la cantidad de formularios <form> que contiene el HTML, categorizando por el metodo implementado (POST o GET).
     * @param html El DOM HTML adquirido.
     */
    private static void getCantidadForm(Document html) {
        int cantidadFormPost = 0;
        int cantidadFormGet = 0;

        for(Element form : html.getElementsByTag("form")) {
            if(form.attr("method").equalsIgnoreCase("post"))
                cantidadFormPost++;

            if(form.attr("method").equalsIgnoreCase("get"))
                cantidadFormGet++;
        }

        System.out.println("\nTarea #4:");
        System.out.println("La cantidad de <form method='post'> es de: " + cantidadFormPost);
        System.out.println("La cantidad de <form method='get'> es de: " + cantidadFormGet);
    }
}
