# 🏰 RPG-EX en Java

## 📜 Intro

Este proyecto es un juego de RPG basado en texto, desarrollado en Java. Tenemos un mundo donde podremos explorar diversos lugares, enfrentarnos a enemigos y descubrir tesoros.

---

## 🎮 Características Principales

- 🗺️ Explorar múltiples ubicaciones
- ⚔️ Combatir contra enemigos
- 🎒 Gestionar el inventario
- 🏃‍♂️ Moverse entre diferentes lugares
- 🧪 Recoger y utilizar objetos

---

## 🏗️ Estructura del Proyecto

### 🔧 Clase Principal
- `Juego`: El centro del juego, coordina todas las acciones.

### 👨‍💼 Gestores
- `GestorCombate`: Gestiona las peleas.
- `GestorExploracion`: Guía de la aventura.
- `GestorInventario`: Organiza los objetos.

### 🧱 Modelos
- `Jugador`: El personaje en el juego.
- `Enemigo`: Los enemigos en el juego.
- `Ubicacion`: Los lugares de la aventura.
- `Mapa`: El mundo a explorar.
- `Item`: Objetos que te ayudarán en el juego.

### 🖥️ Interfaz de Usuario
- `Interfaz`: Herramienta que nos muestra el juego.

---

## 🧠 Principios SOLID Aplicados

1. **Principio de Responsabilidad Única (SRP)** 🎯
   - Cada clase tiene un propósito único y bien definido.

2. **Principio de Abierto/Cerrado (OCP)** 🚪
   - `Item` usa `EfectoItem`, permitiendo nuevas funcionalidades sin modificar lo existente.

3. **Principio de Sustitución de Liskov (LSP)** 🔄
   - `Jugador` y `Enemigo` heredan de `Entidad`, manteniendo consistencia en el combate.

---

## 🚀 Cómo Jugar

1. Ejecutá la clase `Juego`.
2. Seguí las instrucciones en pantalla para navegar y tomar decisiones.
3. Explorá, luchá y sobreviví en este mundo de aventuras.

---

## 🤝 Contribuciones

Las contribuciones se organizarán a través de GitHub con issues y pull requests.