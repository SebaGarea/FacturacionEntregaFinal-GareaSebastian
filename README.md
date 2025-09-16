# Facturación - Segunda Entrega

Este proyecto es una aplicación de facturación desarrollada en Java utilizando Spring Boot y Maven. Forma parte de la entrega final para el curso de CoderHouse.

## Características principales
- Gestión de clientes
- Gestión de productos
- Creación y consulta de facturas
- Persistencia de datos con base de datos H2

## Estructura del proyecto
- `src/main/java/edu/coderhouse/example/` - Código fuente principal
- `src/main/resources/` - Archivos de configuración y recursos
- `src/test/java/edu/coderhouse/example/` - Pruebas unitarias
- `data/` - Archivos de base de datos H2

## Requisitos
- Java 17+
- Maven 3.8+

## Instalación y ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/SebaGarea/FacturacionSegundaEntrega-GareaSebastian.git
   ```
2. Ingresa al directorio del proyecto:
   ```bash
   cd FacturacionSegundaEntrega-GareaSebastian
   ```
3. Compila y ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`.

## Notas
- La base de datos H2 se encuentra en la carpeta `data/`.
- Puedes modificar la configuración en `src/main/resources/application.properties`.

## Autor
Sebastián Garea

---

¡Gracias por revisar este proyecto!