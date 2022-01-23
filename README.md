# Kotlin REST Retrofit
Sencillo cliente para consumir una API REST con Retrofit usando Kotlin. Para ello se consumen servicios REST de una API de prueba.

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-REST-Retrofit)


![imagen](https://www.adesso-mobile.de/wp-content/uploads/2021/02/kotlin-einfu%CC%88hrung.jpg)

## API REST
Una API de REST, o API de RESTful, es una interfaz de programación de aplicaciones (API o API web) que se ajusta a los límites de la arquitectura REST y permite la interacción con los servicios web de RESTful. El informático Roy Fielding es el creador de la transferencia de estado representacional (REST).

REST no es un protocolo ni un estándar, sino más bien un conjunto de límites de arquitectura. Los desarrolladores de las API pueden implementarlo de distintas maneras.

Cuando el cliente envía una solicitud a través de una API de RESTful, esta transfiere una representación del estado del recurso requerido a quien lo haya solicitado o al extremo. La información se entrega por medio de HTTP en uno de estos formatos: JSON (JavaScript Object Notation), HTML, XLT...Comunicación entre el cliente y el servidor sin estado, lo cual implica que no se almacena la información del cliente entre las solicitudes de GET y que cada una de ellas es independiente y está desconectada del resto.

Los elementos más importantes son:
- Recurso: Se identifica a través de una URL o endpoint donde podemos consultar y operar sobre dicho recurso obteniendo respuestas.
- Métodos de petición: HTTP define un conjunto de métodos de petición para indicar la acción que se desea realizar para un recurso determinado. Aunque estos también pueden ser sustantivos, estos métodos de solicitud a veces son llamados HTTP verbs. Cada uno de ellos implementan una semántica diferente. Los más usados son:
  - GET: Solicitud de lectura de recursos.
  - POST: Solicitud de creación de recursos.
  - PUT: Solicitud de actualización de recursos.
  - PATH: Solicitud de actualización parcial de un recurso.
  - DELETE: Solicitud de eliminación de recursos.
- Códigos de respuesta: Los códigos de estado de respuesta HTTP indican si se ha completado satisfactoriamente una solicitud HTTP específica. Destacamos si el l recurso se ha obtenido y se transmite en el cuerpo del mensaje o si El recurso que describe el resultado de la acción se transmite en el cuerpo del mensaje. Las respuestas se agrupan en cinco clases:
  - Respuestas informativas (100–199),
  - Respuestas satisfactorias (200–299),
  - Redirecciones (300–399),
  - Errores de los clientes (400–499),
  - Errores de los servidores (500–599).

Principalmente destacamos:
- 200 OK: La solicitud ha tenido éxito. El significado de un éxito varía dependiendo del método HTTP. Puede devolver o no una representación del recurso solicitado. Esl a más genérica para indicar que se ha tenido éxito en cualquier acción.
- 201 Created: La solicitud ha tenido éxito y se ha creado un nuevo recurso como resultado de ello. Ésta es típicamente la respuesta enviada después de una petición POST/PUT.
- 204 No Content: La petición se ha completado con éxito pero su respuesta no tiene ningún contenido, aunque los encabezados pueden ser útiles. El agente de usuario puede actualizar sus encabezados en caché para este recurso con los nuevos valores.
- 400 Bad Request: Esta respuesta significa que el servidor no pudo interpretar la solicitud dada una sintaxis inválida.
- 401 Unauthorized: Es necesario autenticar para obtener la respuesta solicitada. Esta es similar a 403, pero en este caso, la autenticación es posible.
- 403 Forbidden: El cliente no posee los permisos necesarios para cierto contenido, por lo que el servidor está rechazando otorgar una respuesta apropiada.
- 404 Not Found: El servidor no pudo encontrar el contenido solicitado. Este código de respuesta es uno de los más famosos dada su alta ocurrencia en la web.
- 500 Internal Server Error: El servidor ha encontrado una situación que no sabe cómo manejarla.
503 Service Unavailable: El servidor no está listo para manejar la petición. Causas comunes puede ser que el servidor está caído por mantenimiento o está sobrecargado.


## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://distreau.com/github.svg" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>


## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su uso docente y educativo.