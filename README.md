# LiterAlura 📚

**Versión:** 1.0.0  
**Licencia:** MIT  
**Desarrollador:** Santiago Gabriel Cabrera

---

## Descripción del Proyecto

**LiterAlura** es una aplicación basada en Java y Spring Boot diseñada para la gestión de libros y autores. Permite buscar y gestionar información de libros y autores, integrándose con la API pública de **Gutendex** para enriquecer los datos almacenados en una base de datos local. Este proyecto es ideal para aprender sobre desarrollo backend, manejo de bases de datos y consumo de APIs.

---

## Funcionalidades

1. **Buscar libros por título:**  
   Permite buscar libros almacenados en la base de datos o realizar consultas a la API de Gutendex en caso de no encontrarlos.

2. **Buscar autor por nombre y/o apellido:**  
   Permite buscar autores por coincidencia parcial de nombre o apellido.

3. **Mostrar todos los libros:**  
   Lista todos los libros disponibles en la base de datos.

4. **Mostrar todos los autores:**  
   Lista todos los autores disponibles en la base de datos.

5. **Listar libros por idioma:**  
   Permite listar libros según el idioma especificado por el usuario.

6. **Listar autores vivos en un año específico:**  
   Lista los autores que estaban vivos en un año determinado, considerando sus fechas de nacimiento y fallecimiento.

7. **Eliminar libros:**  
   Elimina libros de la base de datos solicitando confirmación al usuario.

8. **Persistencia de datos:**  
   Los datos consultados se guardan automáticamente en una base de datos PostgreSQL para futuros accesos rápidos.

9. **Validación de datos:**  
   La aplicación realiza validaciones para asegurar la consistencia de los datos ingresados y mostrados al usuario.

---

## Tecnologías Utilizadas

- **Lenguaje de Programación:** Java 17
- **Framework:** Spring Boot 3.4.1
- **Base de Datos:** PostgreSQL
- **Consumo de API:** RestTemplate (Gutendex API)
- **Serialización y Deserialización de JSON:** Jackson
- **Validación:** Hibernate Validator
- **Gestión de Dependencias:** Maven
- **IDE Recomendado:** IntelliJ IDEA / Visual Studio Code
- **Integración y Construcción:** Spring Data JPA

---

## Instalación y Configuración

### **Prerrequisitos**
- Java 17
- PostgreSQL configurado

### Configuración del Proyecto

Clonar el repositorio:
   ```bash
   git clone https://github.com/SntXHub/literalura.git
   cd literalura
   ```
---

### Configurar la base de datos PostgreSQL

Crear una base de datos llamada literalura y actualizar las credenciales en el archivo application.properties:
   ```plain
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
   ```

---

## Uso de la Aplicación
Al iniciar la aplicación, se mostrará un menú principal en consola. El usuario puede interactuar con las siguientes opciones:

   ```plain
*** Menú Principal ***
1. Buscar libros por título
2. Buscar autor por nombre y/o apellido
3. Mostrar todos los libros
4. Mostrar todos los autores
5. Listar libros por idioma
6. Listar autores vivos en un año específico
7. Eliminar un libro por ID
8. Salir

Seleccione una opción:
   ```
---

## Posibles Mejoras

- Implementar una interfaz gráfica de usuario para mayor accesibilidad.
- Internacionalización de la aplicación para soportar múltiples idiomas.
- Integración con servicios de almacenamiento en la nube para la base de datos.
- Adición de autenticación y autorización para control de accesos.

## Licencia
Este proyecto está licenciado bajo la licencia MIT

---

## Créditos

Este proyecto fue desarrollado por **Santiago Gabriel Cabrera** como parte de un aprendizaje y práctica continua en 
desarrollo backend con Java y Spring Boot. Toda colaboración es bienvenida.

---

## Contacto
- Santiago Gabriel Cabrera

   https://github.com/SntXHub

---






   
