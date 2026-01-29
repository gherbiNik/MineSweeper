# Minesweeper — Software Engineering Course Project

Version: 1.0.0


## Executive summary

This document describes a group project delivered for a university course in Software Engineering. It provides a formal overview of the delivered product, the applied software engineering practices, architectural decisions, build and distribution instructions, testing and quality assurance activities, known limitations, and recommendations for review during job interview evaluations.

## Product description

The deliverable is a standalone desktop application that implements the classic Minesweeper gameplay on a fixed 9×9 grid. The application provides an interactive graphical user interface, facilities to save and later resume games, customization of bomb count, multilingual UI and feedback, and an application information panel (version, build date, developer credits).

## Key features

- Graphical 9×9 Minesweeper grid with interactive gameplay (JavaFX).
- Open a cell by clicking (left click); place flag with right click.
- Contextual user feedback (tooltips, status messages, error messages, confirmations).
- Save current game state to a file and load a saved game to resume play.
- Configure the number of bombs within valid game limits while keeping a fixed 9×9 grid.
- Change UI language and all user feedback text (internationalization i10n).
- View application information: version number, build date, developer credits.
- Distributed as a standalone GUI application.

## System requirements

- JDK: Java 17 (minimum required)
- Build tool: Maven
- Runtime libraries: JavaFX (bundled or provided by runtime), Jackson (for JSON serialization)
- Supported platforms: Windows, macOS, Linux (executable JAR compatible across platforms)

## Technology stack

- Language: Java 17
- UI framework: JavaFX
- Serialization / persistence: Jackson (JSON)
- Build & dependency management: Maven
- Artifact: self-contained JAR

## Architecture and design

- The system is designed with clear separation of concerns (SoC) to maximize maintainability, testability and ease of review.
- Front-end / Back-end separation

### Front-end follows the MVC pattern:

- Model: game state and domain entities (cells, grid, game status).
- View: JavaFX UI components (grid view, dialogs, menus).
- Controller: handles user events and mediates between View and Model.

### Back-end follows a layered architecture:

- Application layer: API that allows the comunication between front-end and back-end's lower layers
- Business layer: business logic of the application and the data used.
- Persistence layer: save/load responsibilities (JSON serialization for the game state) and file acces (save preferences).

## Design principles

- Single Responsibility Principle (SRP), separation of concerns (SoC), dependency injection (DI) where appropriate.
- Minimal, well-defined interfaces between layers. DTOs are used for persistence boundaries.
- Internationalization and Localization (i18n, l10n).

## Development approach
### Lifecycle model 
Iterative and incremental development (short iterations with intermediate demos and reviews).

