# 🧪 Testing con JUnit 5, Mockito y Spring Boot

Repositorio de práctica y aprendizaje enfocado en **pruebas unitarias** y **testing en Spring Boot**, utilizando buenas prácticas y herramientas estándar del ecosistema Java moderno.

Este proyecto forma parte de un curso de testing orientado a desarrollo backend profesional.

---

## 🚀 Tecnologías utilizadas

* **Java 17**
* **Spring Boot**
* **Maven**
* **JUnit 5**
* **Mockito**
* **Spring Boot Test**
* **IntelliJ IDEA**
* **Git / GitHub**

---

## 🎯 Objetivo del proyecto

* Aplicar **testing unitario** en aplicaciones Spring Boot
* Aprender a **aislar dependencias** usando Mockito
* Validar reglas de negocio mediante pruebas automatizadas
* Incorporar prácticas reales utilizadas en entornos productivos

---

## 🧩 Contenidos cubiertos

✅ JUnit 5

* Ciclo de vida de tests
* Assertions
* Tests parametrizados

✅ Mockito

* `@Mock` y `@InjectMocks`
* Stubbing (`when / thenReturn`)
* Verificación de interacciones (`verify`)
* Manejo de excepciones

✅ Spring Boot Test

* `@SpringBootTest`
* `@WebMvcTest`
* Mock de servicios y repositorios
* Testing de controllers REST

---

## 📂 Estructura del proyecto

```
src
 ├── main
 │    ├── java
 │    │     └── ... código productivo
 │    └── resources
 │          └── application.properties
 └── test
      ├── java
      │     └── ... tests unitarios
      └── resources
```

---

## ▶️ Cómo ejecutar los tests

```bash
mvn test
```

Ejecutar un test específico:

```bash
mvn -Dtest=NombreDelTest test
```

---

## 📈 Buenas prácticas aplicadas

* Tests **independientes y repetibles**
* Bajo acoplamiento mediante mocks
* Nombres de tests descriptivos
* Evitar dependencias innecesarias al contexto Spring
* Separación clara entre lógica de negocio y testing

---

## 🧠 Motivación

Como desarrollador backend enfocado en **Java y Spring**, considero el testing automatizado una habilidad clave para construir software robusto, mantenible y confiable.

Este repositorio refleja mi proceso de aprendizaje continuo y compromiso con las buenas prácticas profesionales.

---

## 📬 Contacto

**Mariano Villalba**
📍 Santa Fe, Argentina
💼 Software Engineer - Backend Java
🔗 [LinkedIn](https://www.linkedin.com/marianojvillalba
---

## ✅ Estado del proyecto

📌 En progreso – se irán incorporando nuevos ejemplos y casos de testing.

---

### 🔥 Tip final para GitHub

Fijá este repo como **Pinned Repository** en tu perfil.
Eso aumenta muchísimo la visibilidad para recruiters.
