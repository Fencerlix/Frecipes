# 📱 Frecipes – Deine persönliche Rezept-App  

Frecipes ist eine Android-App, mit der du deine eigenen Rezepte speichern und verwalten kannst. Die App ist mit **Jetpack Compose** entwickelt und nutzt **Room** für die Speicherung der Rezepte.  

---

## 🚀 Features  
✅ **Rezepte erstellen, bearbeiten & löschen**  
✅ **Zutaten mit Menge & Einheit verwalten**  
✅ **Schritt-für-Schritt-Anleitungen hinzufügen**  
✅ **Datenbank-Speicherung mit Room**  
✅ **Moderne UI mit Jetpack Compose**  

---

## 🛠️ Technologie-Stack  
- **Programmiersprache:** Kotlin  
- **UI:** Jetpack Compose  
- **Datenbank:** Room (mit KSP für Annotation Processing)  
- **Navigation:** Jetpack Navigation  
- **Serialization:** Gson  

---

## 📸 Screenshots  
| Startbildschirm  | Rezept hinzufügen |
|---|---|
| ![Startscreen](screenshots/startscreen.png) | ![Rezept hinzufügen](screenshots/add_recipe.png) |

---

## 📦 Installation  
### 🔹 APK auf Handy installieren  
1. **APK-Datei generieren:**  

       ./gradlew assembleDebug

Die APK findest du dann unter app/build/outputs/apk/debug/app-debug.apk.
2. APK auf dein Handy übertragen (z. B. per USB oder Google Drive).
3. Installation auf dem Handy erlauben (Einstellungen → Sicherheit → Unbekannte Apps zulassen).
### 🔹 App im Emulator starten

Falls du Android Studio nutzt:

1. Emulator starten
2. Im Terminal:

        ./gradlew installDebug

---

## 🏗️ Entwicklung & Mitwirken
### 🔹 Projekt einrichten

Falls du das Projekt klonen möchtest:
    
    git clone https://github.com/deinusername/frecipes.git
    cd frecipes

Dann das Projekt in Android Studio öffnen und Run drücken.
### 🔹 Code-Struktur

    app/
     ├── src/main/java/com/example/frecipes/  # Hauptcode
     │   ├── ui/           # UI-Elemente mit Jetpack Compose
     │   ├── data/         # Datenbank (Room) & Models
     │   ├── viewmodel/    # ViewModel für die Datenlogik
     │   ├── navigation/   # Jetpack Navigation
     ├── build.gradle.kts  # Gradle-Konfiguration

---

## 📜 Lizenz

Dieses Projekt steht unter der MIT-Lizenz – siehe LICENSE für Details.

---

## 🙌 Mitwirken

Hast du Ideen oder Vorschläge?

1. Forke das Repo
2. Erstelle einen neuen Branch (git checkout -b feature-meineIdee)
3. Mache deine Änderungen und committe sie
4. Erstelle einen Pull Request! 🚀

---

## 📧 Kontakt:
Falls du Fragen hast, schreib mir auf GitHub oder erstelle ein Issue. 😊

---

## ✅ Was ist hier drin?  
- **Projektbeschreibung**  
- **Features**  
- **Technologien**  
- **Installation & Nutzung**  
- **Screenshots**  
- **Code-Struktur**  
- **Lizenz & Mitwirkungsanleitung**  

Falls du noch was Spezielles hinzufügen möchtest, sag Bescheid! 😊
