# Documentación Técnica - Proyecto CarritoGT (Migración a Spring Boot)

* **Curso:** Programación II
* **Universidad:** Universidad Mariano Gálvez de Guatemala
* **Estudiante:** Edison Alvarez
* **Versión del Documento:** 1.0

---

## 1. Evidencia de Virtualización y Recursos
* **Hipervisor Utilizado:** VirtualBox (Tipo 2)
* **Sistema Operativo Invitado:** Lubuntu (GNU/Linux)
* **Justificación de Recursos:** Se asignaron 2 GB de RAM y 2 núcleos de CPU para garantizar la estabilidad tanto en el sistema operativo Host (Windows) como en el invitado, permitiendo ejecutar Apache, MariaDB y los servicios de desarrollo simultáneamente sin bloqueos.

---

## 2. Diagrama de Casos de Uso
A continuación se representa el flujo de interacción del cliente con el sistema del carrito de compras:

```mermaid
graph TD
    Cliente([Cliente]) --> A[Buscar artículos]
    Cliente --> B[Agregar artículos al carrito]
    Cliente --> C[Ver carrito de compra]
    Cliente --> D[Mostrar artículos]
    Cliente --> E[Pagar carrito de compra]

    E .->|<<include>>| F[Iniciar sesión]
    D .->|<<extend>>| G[Filtrar por categoría]
```

## 3. Arquitectura del Sistema (Spring Boot)
Estructura en capas del microservicio backend en Java Spring Boot:

```mermaid
graph TD
    Client[Cliente / Frontend] -->|HTTP REST / JSON| Controller[CarritoController]
    Controller -->|Inyección de Dependencias| Service[CarritoService]
    Service -->|Operaciones Lógicas| Model[Modelos / Entidades]
    Service -->|Simulación de Datos / DB| Repository[Repositorio de Productos]
```

## 4. Diagrama Entidad-Relación (Base de Datos)
Estructura de las tablas y entidades del modelo de carrito de compras:

```mermaid
erDiagram
    USUARIO ||--o{ CARRITO : posee
    CARRITO ||--|{ DETALLE_CARRITO : contiene
    PRODUCTO ||--o{ DETALLE_CARRITO : incluye
    CATEGORIA ||--o{ PRODUCTO : clasifica

    USUARIO {
        int id PK
        string nombre
        string email
    }
    PRODUCTO {
        int id PK
        string nombre
        double precio
        int categoria_id FK
    }
    CARRITO {
        int id PK
        int usuario_id FK
        date fecha_creacion
    }
    DETALLE_CARRITO {
        int id PK
        int carrito_id FK
        int producto_id FK
        int cantidad
        double subtotal
    }
```

## 5. Diagrama de Secuencia (Agregar al Carrito)
Flujo de comunicación sincrónica al agregar un producto al carrito:

```mermaid
sequenceDiagram
    autonumber
    actor Cliente
    participant Controller as CarritoController
    participant Service as CarritoService
    participant DB as Base de Datos

    Cliente->>Controller: POST /api/carrito/agregar (item)
    Controller->>Service: agregarProducto(item)
    Service->>DB: Validar Stock / Buscar Producto
    DB-->>Service: Producto Confirmado
    Service->>Service: Calcular Subtotal y Total
    Service-->>Controller: Carrito Actualizado
    Controller-->>Cliente: Respuesta 200 OK (JSON)
```

## 12. Bitácora de Desarrollo

A continuación se registra el historial de actividades, decisiones técnicas, dificultades superadas y commits relacionados a lo largo de las distintas fases del proyecto CarritoGT:

| Fecha | Avance realizado | Problema o decisión | Solución / aprendizaje | Commit relacionado |
| :--- | :--- | :--- | :--- | :--- |
| 02/08/2025 | Instalación y preparación del hipervisor en el sistema operativo Host. | Selección entre distribuciones Linux según disponibilidad de recursos de RAM/CPU. | Se eligió Lubuntu 22.04 por su bajo consumo de recursos, asignando 2 GB de RAM y 2 núcleos. | `Configurar máquina virtual en VirtualBox` |
| 16/08/2025 | Instalación de servicios del servidor web y control de versiones. | Dificultad para gestionar paquetes y permisos en la máquina virtual. | Se instaló Git, Apache, PHP y MariaDB utilizando comandos en la terminal de Lubuntu. | `Instalar stack LAMP y configurar Git` |
| 30/08/2025 | Clonación del repositorio PHP y pruebas iniciales. | Configuración de credenciales y acceso a la base de datos MySQL/MariaDB. | Se importó el script de la base de datos con las 2 tablas y se ajustaron las credenciales en PHP. | `Clonar repositorio e importar base de datos` |
| 13/09/2025 | Configuración del Virtual Host en Apache. | Las imágenes y recursos estáticos daban error 404 al navegar fuera de `localhost`. | Se configuró el archivo Virtual Host `carrito-compra.localhost` y se ajustaron los permisos en Apache. | `Configurar Virtual Host y rutas estaticas` |
| 27/09/2025 | Creación del proyecto en Java Spring Boot. | Definición del estilo arquitectónico y organización del proyecto backend. | Se implementó la arquitectura en n-capas (Controller, Service, Repository, Entity) bajo el patrón MVC. | `Inicializar proyecto Spring Boot con capas` |
| 04/10/2025 | Desarrollo de endpoints RESTful y persistencia de datos. | Manejo de peticiones HTTP, respuestas en formato JSON y consultas a la base de datos. | Se usó JPA/Hibernate en la capa Repository para conectar con MariaDB y mapear las entidades. | `Implementar endpoints REST y persistencia JPA` |
| 11/10/2025 | Integración del Frontend y adaptación con JavaScript. | Sustitución de las plantillas PHP/Twig por consumo asíncrono desde el navegador. | Se utilizó JavaScript Vanilla (`fetch` API) para consultar los endpoints REST e interactuar con el carrito. | `Integrar frontend con fetch API y REST` |
| 24/10/2025 | Redacción de la documentación técnica y diagramas en Mermaid. | Errores de sintaxis y renderizado en GitHub al cerrar los bloques de código. | Se corrigió la sintaxis de Mermaid asegurando el cierre adecuado de bloques con tres acentos graves (```). | `Corregir cierres de diagramas con acentos graves` |

