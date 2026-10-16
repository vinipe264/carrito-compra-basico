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
