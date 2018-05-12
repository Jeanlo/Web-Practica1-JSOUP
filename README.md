# Practica 1

#### Especificaciones

- Tecnologías utilizadas:
  - Java
  - Gradle
  - Apache HTTP Client
  - JSOUP

#### Objetivo general

Crear una aplicación de consola que reciba vía la entrada estándar una URL valida y una vez consultada realice diferentes tareas, especificadas en la sección [Tareas específicas a realizar](#tareas-especificas-a-realizar).

#### Tareas específicas a realizar

- [X] Indicar la cantidad de líneas del recurso retornado.
- [X] Indicar la cantidad de párrafos `<p>` que contiene el documento HTML.
- [X] Indicar la cantidad de imágenes `<img>` dentro de los párrafos que contiene el archivo HTML.
- [X] Indicar la cantidad de formularios `<form>` que contiene el HTML, categorizando por el método implementado (POST o GET).
- [X] Para cada formulario mostrar los campos de `<input>` y su respectivo `[type]` que contiene en el documento HTML.
- [ ] Para cada formulario parseado, identificar el método de envío del formulario, si está utilizando el método POST enviar una petición al servidor con un parámetro-valor `asignatura: practica1` y mostrar la respuesta por la salida estándar.
