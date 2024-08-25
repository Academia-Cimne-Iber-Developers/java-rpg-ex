
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
- 📜 Completar misiones mientras exploras
- 🎚️ Sistema de Dificultad Ajustable - Nuevo: Elige entre diferentes niveles de dificultad para personalizar tu experiencia de juego


---

## 🏗️ Estructura del Proyecto

### 🔧 Clase Principal
- `Juego`: El centro del juego, coordina todas las acciones.

### 👨‍💼 Gestores
- `GestorCombate`: Gestiona las peleas.
- `GestorExploracion`: Guía de la aventura.
- `GestorInventario`: Organiza los objetos.
- `GestorMisiones`: Gestiona las misiones activas y completadas del jugador.

### 🧱 Modelos
- `Jugador`: El personaje en el juego.
- `Enemigo`: Los enemigos en el juego.
- `Ubicacion`: Los lugares de la aventura.
- `Mapa`: El mundo a explorar.
- `Item`: Objetos que te ayudarán en el juego.
- `MisionExploracion`: Un tipo de misión que requiere que el jugador explore ubicaciones específicas.

### 🖥️ Interfaz de Usuario
- `Interfaz`: Herramienta que nos muestra el juego.

### 🎚️ Sistema de Dificultad

El juego ahora incluye un **Sistema de Dificultad** que permite a los jugadores ajustar el nivel de desafío. 

#### Niveles de Dificultad Disponibles

- **Fácil:** Los enemigos reciben más daño del jugador, facilitando los combates (Multiplicador de daño: 1.2).
- **Normal:** El daño infligido y recibido es estándar, sin modificaciones (Multiplicador de daño: 1.0).
- **Difícil:** Los enemigos reciben menos daño del jugador, aumentando la dificultad del combate (Multiplicador de daño: 0.6).

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
2. Selecciona el nivel de dificultad.
3. Seguí las instrucciones en pantalla para navegar y tomar decisiones.
4. Explorá, luchá y sobreviví en este mundo de aventuras.

---

## 🤝 Contribuciones

Las contribuciones se organizarán a través de GitHub con issues y pull requests.
