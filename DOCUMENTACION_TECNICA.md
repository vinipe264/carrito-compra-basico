# 📄 Documentación Técnica - Proyecto CarritoGT (Migración PHP a Spring Boot)

* **Curso:** Programación II
* **Universidad:** Universidad Mariano Gálvez de Guatemala (UMG)
* **Estudiante:** Edison Alvarez[cite: 2]
* **Versión del Documento:** 1.0

---

## 1. Evidencia de Virtualización y Recursos
* **Hipervisor Utilizado:** VirtualBox (Tipo 2)[cite: 14]
* **Sistema Operativo Invitado:** Lubuntu (GNU/Linux)[cite: 14]
* **Justificación de Recursos:** Se asignaron 2 GB de RAM y 2 núcleos de CPU para garantizar la estabilidad tanto en el sistema operativo Host (Windows) como en el invitado, permitiendo ejecutar Apache, MariaDB y los servicios de desarrollo simultáneamente sin bloqueos[cite: 14, 17].

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
    
    E -.->|"<<include>>"| F[Iniciar sesión]
    D -.->|"<<extend>>"| G[Filtrar por categoría]
