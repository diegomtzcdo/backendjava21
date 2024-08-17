# Prueba Técnica Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)

Un API rest de Alumnos y Tareas con pruebas unitarias

## Requisitos

Para construir y ejecutar la aplicación, necesitas:

- [JDK 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- [Gradle](https://gradle.org/install/)

## Descripción del Proyecto

La API es un proyecto sencillo las siguientes características:

- CRUD Alumnos.
- CRUD Tareas.
- Validar la asocación entre alumnos y tareas al momento de creación y eliminación
- Validaciones de parametros de entrada.
- Pruebas Unitarias de controladores y servicios



## Ejecución de la Aplicación Localmente

Para ejecutar la aplicación Spring Boot en tu máquina local, ejecuta el método `main` en la clase `com.pruebin.prueba.PruebaApplication` desde tu IDE.

Alternativamente, puedes usar el plugin de Gradle para Spring Boot:

```shell
./gradlew bootRun
```

## Endpoints de la API

Puedes Ingresar al link para ver los endpoints utilizando swagger
[Swagger UI](http://localhost:8080/swagger-ui/index.html)

Pueden consultarse la coleccion de POSTMAN Adjunta

# Docker

## Prerrequisitos

Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Puedes descargar e instalar Docker desde [docker.com](https://www.docker.com/products/docker-desktop).

## Construcción del Proyecto

1. **Construir el archivo JAR**: Antes de construir la imagen Docker, asegúrate de que el archivo JAR esté construido. Ejecuta el siguiente comando desde la raíz del proyecto:

   ```sh
   ./gradlew build
   ```
Esto generará el archivo JAR en build/libs/app.jar.

Construir y ejecutar los contenedores: 
Usa Docker Compose para construir y ejecutar los contenedores. 
Ejecuta el siguiente comando desde la raíz del proyecto:
```sh
docker compose up --build
```

## Ver Logs
Para ver los logs de los contenedores, puedes usar los siguientes comandos:

- Logs de la aplicación:
```sh
docker-compose logs app
```
- Logs de la base de datos:
```sh
docker-compose logs db
```


## Parar y Eliminar Contenedores
Para detener y eliminar los contenedores, ejecuta:
```sh
docker compose down
```

## Contacto

Para cualquier consulta, por favor contacta al desarrollor en cdodiegomtz@gmail.com.