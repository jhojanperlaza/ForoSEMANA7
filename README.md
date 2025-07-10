# Contador de pasos 📱🏃‍♂️

¡Bienvenido a **StepCounterApp**!  
Una aplicación Android (Kotlin) que registra tus pasos utilizando el acelerómetro del dispositivo, guarda los conteos en una base de datos **Room** y muestra tu progreso diario / histórico con **MPAndroidChart**.


## ✨ Características

- **Conteo de pasos en tiempo real** con un detector simple de picos del acelerómetro.  
- **Almacenamiento local** por fecha con Room.
- **Historial** en gráfico de líneas (librería MPAndroidChart).
- Arquitectura **MVVM + Coroutines + LiveData**.
- **View Binding** para vistas seguras en tiempo de compilación.
- Tema **Material 3** en rojo/negro (personalizable en `themes.xml`).

---

## 🏗️ Tecnologías & librerías

| Grupo | Versión | Notas |
|-------|---------|-------|
| **Gradle (AGP)** | 8.3.0 | compileSdk 34 |
| **Kotlin** | 1.9.23 | BOM para unificar dependencias |
| **AndroidX Core / AppCompat** | 1.13.0 / 1.6.1 |  |
| **Material 3** | 1.12.0 |  |
| **Lifecycle** | 2.7.0 | runtime-ktx, viewmodel-ktx |
| **Coroutines** | 1.7.3 | core + android |
| **Room** | 2.7.1 | runtime + ktx + kapt compiler |
| **MPAndroidChart** | 3.1.0 | gráficos |

---

## 🚀 Instalación rápida

```bash
git clone https://github.com/TU_USUARIO/StepCounterApp.git
cd StepCounterApp
./gradlew assembleDebug          # o abre en Android Studio 2024.1+