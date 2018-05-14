import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("URL a analizar: ");
        String url = scanner.next();

        try {
            // Estableciendo conexión con la URL
            Connection conexion = Jsoup.connect(url);

            // Obteniendo el recurso al que pertenece la URL
            Connection.Response respuesta = conexion.execute();

            // Obteniendo documento HTML de la URL
            Document documento = conexion.get();

            System.out.println("\nTarea #1:");
            System.out.println("La cantidad de lineas del recurso es de: " + getCantidadLineasRecurso(respuesta));

            System.out.println("\nTarea #2:");
            System.out.println("La cantidad de <p> es de: " + getCantidadParrafos(documento));

            System.out.println("\nTarea #3:");
            System.out.println("La cantidad de <img> dentro de <p> es de: " + getCantidadImagenesParrafo(documento));

            System.out.println("\nTarea #4:");
            System.out.println("La cantidad de <form method='post'> es de: " + getCantidadForm(documento, "post"));
            System.out.println("La cantidad de <form method='get'> es de: " + getCantidadForm(documento, "get"));

            System.out.println("\nTarea #5:");
            getCantidadInputForm(documento);

            System.out.println("\nTarea #6:");
            getRespuestaPeticion(documento);
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
        return html.select("p").size();
    }

    /**
     * Tarea #3 - Indicar la cantidad de <img> dentro de los <p> que contiene el archivo HTML.
     * @param html El DOM HTML adquirido.
     */
    private static int getCantidadImagenesParrafo(Document html) {
        return html.select("p img").size();
    }

    /**
     * Tarea #4 - Indicar la cantidad de <form> que contiene el HTML, categorizando por el [method] implementado (POST|GET).
     * @param html El DOM HTML adquirido.
     * @param metodo El metodo del que se quieren contabilizar los forms.
     */
    private static int getCantidadForm(Document html, String metodo) {
        return html.select("form[method='" + metodo + "']").size();
    }

    /**
     * Tarea #5 - Para cada formulario mostrar los campos de <input> y su respectivo [type] que contiene en el documento HTML.
     * @param html El DOM HTML adquirido.
     */
    private static void getCantidadInputForm(Document html) {
        int indiceSuperior = 1;
        for(Element form : html.select("form")) {
            int indiceInferior = 1;
            System.out.println("\tForm #" + indiceSuperior + ":");
            for(Element input : form.select("input")) {
                System.out.println("El input #" + indiceInferior
                        + " es de tipo " + input.attr("type"));
                indiceInferior++;
            }

            indiceSuperior++;
        }
    }

    /**
     * Tarea #6 - Para cada formulario parseado, identificar el [method] de envío del formulario,
     * si es [method='post'] enviar una petición al servidor con un parámetro-valor
     * asignatura: practica1 junto con el header "matricula" (al cuál se le debe asignar un valor)
     * y mostrar la respuesta por la salida estándar.
     * @param html El DOM HTML adquirido.
     */
    private static void getRespuestaPeticion(Document html) {
        int indice = 1;

        for(Element form: html.select("form[method='post']")) {
            System.out.println("\tForm #" + indice + ":");
            String urlAbsoluta = form.absUrl("action");
            try {
                Document documento = Jsoup.connect(urlAbsoluta).data("asignatura", "practica1")
                        .header("matricula", "20131459").post();

                System.out.println("Respuesta: " + documento.body().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            indice++;
        }

        if(indice == 1) {
            System.out.println("No hay form[method='post'] en este recurso.");
        }
    }
}
