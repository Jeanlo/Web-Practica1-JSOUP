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
            // Conectandose a la URL
            Connection conexion = Jsoup.connect(url);

            // Obteniendo el recurso al que pertenece la URL
            Connection.Response respuesta = conexion.execute();

            // Obteniendo documento HTML de la URL
            Document documento = conexion.get();

            System.out.println("\nTarea #1:");
            System.out.println("La cantidad de lineas del recurso es de: " + getCantidadLineasRecurso(respuesta));

            System.out.println("\nTarea #2:");
            System.out.println("#2 - La cantidad de <p> es de: " + getCantidadParrafos(documento));

            System.out.println("\nTarea #3:");
            getCantidadImagenesParrafo(documento);

            System.out.println("\nTarea #4:");
            getCantidadForm(documento);

            System.out.println("\nTarea #5:");
            getCantidadInputForm(documento);
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
     * Tarea #2 - Indicar la cantidad de <p> que contiene el documento HTML.
     * @param html El DOM HTML adquirido.
     * @return Cantidad de <p> encontrados en el HTML.
     */
    private static int getCantidadParrafos(Document html) {
        return html.getElementsByTag("p").size();
    }

    /**
     * Tarea #3 - Indicar la cantidad de <img> dentro de los <p> que contiene el archivo HTML.
     * @param html El DOM HTML adquirido.
     */
    private static void getCantidadImagenesParrafo(Document html) {
        int indice = 1;
        for(Element parrafo : html.getElementsByTag("p")) {
            int cantidadImg = parrafo.getElementsByTag("img").size();
            System.out.println("El parrafo #" + indice + " conteniene " + cantidadImg + " imagenes.");
            indice++;
        }
    }

    /**
     * Tarea #4 - Indicar la cantidad de <form> que contiene el HTML, categorizando por el [method] implementado (POST|GET).
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

        System.out.println("La cantidad de <form method='post'> es de: " + cantidadFormPost);
        System.out.println("La cantidad de <form method='get'> es de: " + cantidadFormGet);
    }

    /**
     * Tarea #5 - Para cada formulario mostrar los campos de <input> y su respectivo [type] que contiene en el documento HTML.
     * @param html El DOM HTML adquirido.
     */
    private static void getCantidadInputForm(Document html) {
        int indiceSuperior = 1;
        for(Element form : html.getElementsByTag("form")) {
            int indiceInferior = 1;
            System.out.println("\tForm #" + indiceSuperior + ":");
            for(Element input : form.getElementsByTag("input")) {
                System.out.println("El input #" + indiceInferior + " es de tipo " + input.attr("type"));
                indiceInferior++;
            }

            indiceSuperior++;
        }
    }
}
