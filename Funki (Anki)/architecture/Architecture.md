## Flashcards App Architecture

### Overall Architecture

```text
                    User
                      │
                      ▼
                 JavaFX (FXML)
                      │
                      ▼
               MainController
                      │
                      ▼
                StudySession
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
  FlashcardService         StorageService
          │
          ▼
      Flashcard
```

---

## Project Structure

# Project Structure

```text
Funki (Anki)
│
├── architecture
│   ├── .obsidian
│   └── Architecture.md
│
└── flashcards
    │
    ├── src
    │   │
    │   ├── main
    │   │   │
    │   │   ├── java
    │   │   │   │
    │   │   │   ├── com
    │   │   │   │   └── funki
    │   │   │   │       │
    │   │   │   │       ├── controller
    │   │   │   │       │   ├── MainController.java
    │   │   │   │       │   └── SettingsController.java
    │   │   │   │       │
    │   │   │   │       ├── model
    │   │   │   │       │   └── Flashcard.java
    │   │   │   │       │
    │   │   │   │       ├── service
    │   │   │   │       │   ├── DeckLoader.java
    │   │   │   │       │   ├── FileService.java
    │   │   │   │       │   ├── FlashcardService.java
    │   │   │   │       │   ├── StatisticsService.java
    │   │   │   │       │   └── StudySession.java
    │   │   │   │       │
    │   │   │   │       ├── view
    │   │   │   │       │   (reserved for future custom JavaFX views)
    │   │   │   │       │
    │   │   │   │       ├── App.java
    │   │   │   │       └── SystemInfo.java
    │   │   │   │
    │   │   │   └── module-info.java
    │   │   │
    │   │   └── resources
    │   │       │
    │   │       └── com
    │   │           └── funki
    │   │               │
    │   │               ├── css
    │   │               │   └── style.css
    │   │               │
    │   │               ├── decks
    │   │               │   └── example_deck.csv
    │   │               │
    │   │               ├── images
    │   │               │
    │   │               └── view
    │   │                   ├── MainView.fxml
    │   │                   └── SettingsView.fxml
    │   │
    │   └── test
    │
    ├── target
    │
    ├── pom.xml
    └── README.md
```

---

# Package Responsibilities

```text
controller/
    Handles user interactions.
    Connects the UI (FXML) with the application logic.

model/
    Contains only data models.
    No JavaFX code or business logic.

service/
    Contains business logic.
    Manages flashcards, study sessions, file loading,
    statistics, and other application behavior.

view/
    Reserved for reusable JavaFX components (if needed).

resources/view/
    FXML files describing the application's UI.

resources/css/
    Application styling.

resources/decks/
    Flashcard deck files (.csv).

resources/images/
    Icons and images used by the application.
```
