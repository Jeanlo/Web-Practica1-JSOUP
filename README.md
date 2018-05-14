# Práctica 1 - jsoup

![PUCMMM-logo](https://i.imgur.com/9eEIci9.png)

Primera práctica realizada para la asignatura **Programación Web (ISC-415)** perteneciente a la carrera **Ingeniería de Sistemas y Computación** de la **Pontificia Universidad Católica Madre y Maestra (PUCMM)** en el ciclo **Mayo-Agosto 2018**.

## Objetivo general

Crear una aplicación de consola que reciba vía la entrada estándar una URL válida para ser consultada, y basado en este recurso ejecutar las tareas especificadas en la sección [Lista de tareas](#lista-de-tareas).

## Tecnologías utilizadas

- Java SE
- Gradle
- jsoup
- Apache HTTP Client/Components

## Lista de tareas

- [X] Indicar la cantidad de líneas del recurso retornado.
- [X] Indicar la cantidad de `<p>` que contiene el documento HTML.
- [X] Indicar la cantidad de `<img>` dentro de los `<p>` que contiene el archivo HTML.
- [X] Indicar la cantidad de `<form>` que contiene el HTML, categorizando por el `[method]` implementado (POST|GET).
- [X] Para cada formulario mostrar los campos de `<input>` y su respectivo `[type]` que contiene en el documento HTML.
- [X] Para cada formulario *parseado*, identificar el `[method]` de envío del formulario, si es `[method='post']` enviar una petición al servidor con un parámetro-valor `asignatura: practica1` junto con el header "matricula" (al cuál se le debe asignar un valor) y mostrar la respuesta por la salida estándar.
