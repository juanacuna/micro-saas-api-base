# Micro SaaS Conciliation - Backend

Servicio de conciliación desarrollado con **Java 21**, **Spring Boot 3.4**, y **Arquitectura Hexagonal**.

## Estructura del Proyecto

El código está estructurado siguiendo los principios de la Arquitectura Hexagonal para mantener la lógica de negocio aislada de las dependencias externas (bases de datos, servicios cloud, frameworks):

```
├── .github/workflows/deploy.yml
├── docker-compose.yml
├── pom.xml
├── src/main/java/com/tuservicio/
│   ├── core/
│   │   ├── domain/        # Entidades puras (Transaction, Conciliation)
│   │   ├── port/          # Interfaces de entrada y salida (Use Cases, Repositories)
│   │   └── service/       # Lógica del algoritmo de conciliación
│   ├── infrastructure/    # Adaptadores (OracleDatabaseAdapter, S3StorageAdapter, RestControllerAdapter)
│   └── application/       # Configuraciones de Spring/Security y punto de entrada (MainApplication)
└── README.md
```

### Componentes Core
- **Domain**: Entidades puras e inmutables del negocio. No tienen anotaciones de frameworks (como JPA o Spring).
- **Port**:
  - *Inbound Ports (Use Cases)*: Interfaces que definen los casos de uso accesibles desde fuera.
  - *Outbound Ports (Repositories/External Services)*: Interfaces de salida que la lógica de negocio requiere para guardar datos o interactuar con el exterior.
- **Service**: Implementación pura de los casos de uso (algoritmos de conciliación) coordinando con los puertos de salida.

### Componentes de Infraestructura
- **Adapters**:
  - *OracleDatabaseAdapter*: Implementa la persistencia de transacciones utilizando Spring Data JPA y base de datos Oracle.
  - *S3StorageAdapter*: Adaptador para la subida/descarga de archivos a Amazon S3/Localstack.
  - *RestControllerAdapter*: Expone las interfaces REST del caso de uso.

### Componentes de Aplicación
- **MainApplication**: Punto de partida de Spring Boot.
- **Configuration (Config)**: Registro y cableado manual de Beans del Core en Spring, y configuración de Spring Security.

---

## Requisitos Previos

- **Java 21**
- **Maven 3.9+**
- **Docker y Docker Compose** (para arrancar la base de datos Oracle y LocalStack/S3)

## Instrucciones para Ejecutar

### 1. Iniciar la Infraestructura (Docker)

En la raíz de la carpeta `backend`, ejecuta el siguiente comando para levantar la base de datos Oracle y LocalStack (S3):

```bash
docker-compose up -d
```

### 2. Construir la Aplicación

Para compilar y empaquetar el proyecto ejecutando los tests integrados:

```bash
mvn clean package
```

### 3. Ejecutar Localmente

Para arrancar el servidor Spring Boot en el puerto `8080`:

```bash
mvn spring-boot:run
```

El endpoint principal expuesto para la conciliación de pruebas es:
- **POST** `http://localhost:8080/api/conciliation/reconcile`
