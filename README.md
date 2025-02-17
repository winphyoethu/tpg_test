# Rick & Morty Character App

A Rick & Morty App that offers to see characters and save your favourites

## Overview
The project is designed into two parts.
- Native Android module (contains Character List and Saved Character List Screens)
- React Native module (contains Character Detail Screen)

Native Android module is designed by following the MVVM architecture. This project is structured to ensure scalability, maintainability, and separation of concerns. 

Native Android Module and React Native Module communicates by using NativeEventEmitter

📂 project-root<br>
├── 📂 build-logic        # Build script module to be shared across project<br>
├── 📂 app                # Main application module<br>
├── 📂 core               # Core module<br>
├── ├── 📂 common         # Common utilities and helpers<br>
├── ├── 📂 design-system  # UI components and theme<br>
├── ├── 📂 data           # Repository and data sources<br>
├── ├── 📂 model          # Models to be consumed  in Ui<br>
├── ├── 📂 network        # API and network layer<br>
├── 📂 features           # Feature module<br>
├── ├── 📂 recipe         # Recipe Feature<br>
├── 📂 domain             # Domain of the project<br>
├── 📄 settings.gradle.kts<br>
└── 📄 build.gradle.kts<br>

## Modules Description
- Build-Logic Module: Centralized Gradle Build script module that contains build scripts to be shared among modules.
- App Module: The main application that brings all modules together.
- Core Module: Contains shared components and logic used across feature modules.
- common: Shared utilities, extensions, and constants.
- design-system: Shared UI components and theming.
- data: Repository layer handling business logic and data sources.
- network: API services and networking configurations.
- Feature Modules: Independent features that interact with core modules.
- Domain Module: Domain of the project.

## 🛠️ Tech Stack
- Programming Language: Kotlin, Javascript
- UI Framework: Jetpack Compose
- Image Library: Coil
- Architecture: MVVM
- Navigation: Jetpack Navigation
- Dependency Injection: Hilt
- Asynchronous Programming: Coroutines
- Networking: Retrofit, OkHttp
- State Management: StateFlow, SharedFlow
- Testing: JUnit, Mockito
- CI: GitHub Actions, CI will be triggered on main branch is pushed.

## Setup & Installation

1. Clone the repository:
```
git clone git@github.com:winphyoethu/coles_test.git
cd your-repo
```
2. - Install dependencies
```
cd <project_name>
npm install
```
3. Build and run the project
```
npm start
```
4. Open the project in Android Studio.
5. Sync Gradle files and build the project.

## Screenshots
<img src="https://github.com/winphyoethu/tpg_test/blob/main/screenshots/ss1.png?raw=true" width="200" alt="ss1"/><img src="https://github.com/winphyoethu/tpg_test/blob/main/screenshots/ss2.png?raw=true" width="200" alt="ss2"/><img src="https://github.com/winphyoethu/tpg_test/blob/main/screenshots/ss3.png?raw=true" width="200" alt="ss3"/><br/>