# 🛒 Proyecto: Carrito de Compras (Migración PHP a Spring Boot Edison Alvarez)

Proyecto desarrollado para el curso de **Programación II** (Universidad Mariano Gálvez de Guatemala - UMG). Consiste en la migración de un repositorio base monolítico en PHP hacia una arquitectura moderna basada en **n-capas** utilizando **Java con Spring Boot** y una API RESTful conectada a base de datos relacional.

## 🚀 Tecnologías Utilizadas
* **Backend:** Java 17+, Spring Boot (Spring Web, Spring Data JPA).
* **Base de Datos:** MariaDB / MySQL.
* **Pruebas Unitarias:** JUnit 5 y Mockito.
* **Frontend:** HTML, CSS, JavaScript / PHP (Consumo de API REST).
* **Control de Versiones:** Git y GitHub.

---

## 🏛️ Arquitectura por Capas (Backend)
El proyecto se encuentra estructurado en capas para separar las responsabilidades:
1. **Model / Entity (`com.umg.carrito.model`):** Define las entidades mapeadas a la base de datos mediante JPA (`Producto.java`).
2. **Repository (`com.umg.carrito.repository`):** Interfaces que extienden de `JpaRepository` para la persistencia y consultas a la base de datos.
3. **Service (`com.umg.carrito.service`):** Contiene la lógica de negocio (gestión del carrito y llamadas al repositorio).
4. **Controller (`com.umg.carrito.controller`):** Expone los endpoints REST para que el frontend pueda consumir los datos (`/api/carrito`, agregar y eliminar).

---

## 🧪 Pruebas Unitarias
El proyecto incluye pruebas unitarias automatizadas utilizando **JUnit 5** y **Mockito** para validar el comportamiento del servicio de carrito simulando el repositorio.

Para ejecutar las pruebas,se debe  utilizar el siguiente comando en la terminal:
```bash
./mvnw test con esto ya se podra ejecutar las pruebas  
## ⚙️ Instrucciones de Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/vinipe264/carrito-compra-basico.git 
   cd carrito-compra-basico
