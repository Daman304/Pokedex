# ⚡ Pokédex

A modern Android Pokédex application built using **Kotlin** and **Jetpack Compose**. Browse Pokémon, search for specific Pokémon, view detailed stats, and enjoy a clean animated UI.

## 📱 Features

- 🔍 Search Pokémon by name
- 📋 Browse Pokémon in a two-column grid
- ♾️ Pagination / infinite scrolling
- 📊 View detailed Pokémon information
- 💪 View Pokémon base stats
- 🏷️ View Pokémon types
- ⚖️ View Pokémon height and weight
- 🌐 Fetch Pokémon data from the PokéAPI
- 🖼️ Asynchronous image loading
- ⏳ Loading states
- ❌ Error handling with retry functionality
- 📍 Preserves browsing state when navigating between screens
- ✨ Animated Pokémon cards
- 🎬 Detail screen entrance animations
- 🌙 Modern dark-themed UI

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **MVVM Architecture**
- **ViewModel**
- **StateFlow**
- **Kotlin Coroutines**
- **Retrofit**
- **Coil**
- **PokéAPI**

---

## 🏗️ Architecture

The project follows a simplified MVVM architecture:

```text
Jetpack Compose UI
        │
        ▼
     ViewModel
        │
        ▼
    Repository
        │
        ▼
      PokéAPI
```

### UI

Responsible for displaying:

- Pokémon grid
- Search bar
- Loading states
- Error states
- Pokémon details
- Animations

### ViewModel

Responsible for:

- Managing UI state
- Loading Pokémon
- Pagination
- Searching Pokémon
- Handling asynchronous operations

### Repository

Responsible for communicating with the API and providing Pokémon data to the ViewModel.

---

## 📂 Project Structure

```text
com.selfKotlin.pokedex
│
├── data
│   ├── model
│   │   └── Pokemon.kt
│   │
│   └── repository
│       └── PokemonRepository.kt
│
├── ui
│   ├── components
│   │   ├── PokemonCard.kt
│   │   └── StatBar.kt
│   │
│   ├── screens
│   │   ├── PokedexScreen.kt
│   │   └── PokemonDetailScreen.kt
│   │
│   └── state
│       └── PokemonUiState.kt
│
├── viewModels
│   └── PokemonViewModel.kt
│
└── MainActivity.kt
```

---

## 🔍 Search Functionality

The app supports both local and API-based searching.

- If a Pokémon is already loaded, the app searches locally.
- If no matching Pokémon is found, the app attempts to fetch it from the API.
- Search input is debounced to reduce unnecessary API requests.

---

## ♾️ Pagination

Pokémon are loaded in batches as the user scrolls.

```text
Load Pokémon
     ↓
Display Pokémon
     ↓
User approaches the bottom
     ↓
Load next batch
     ↓
Append new Pokémon to the list
```

---

## 📊 Pokémon Details

Selecting a Pokémon opens a detail screen containing:

- Pokémon image
- Pokémon ID
- Name
- Types
- Weight
- Height
- Base stats

The detail screen also includes smooth entrance animations for a better user experience.

---

## ✨ UI and Animations

The app includes:

- Animated Pokémon card appearance
- Fade-in animations
- Slide-in animations
- Card press feedback
- Animated detail screen entrance
- Rounded UI components
- Dark-themed interface

---

## 🚀 Getting Started

### Prerequisites

- Android Studio
- JDK
- Android SDK
- Internet connection

### Installation

1. Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

2. Open the project in Android Studio.

3. Allow Gradle to sync.

4. Run the app on an Android emulator or physical device.

---

## 🔮 Future Improvements

- ❤️ Favorite Pokémon
- 💾 Offline caching
- 🎨 Light and dark theme switching
- 🧬 Filter Pokémon by type
- 🔢 Sort Pokémon by ID or name
- 📖 Pokémon abilities and moves
- 🗺️ Pokémon regions and generations
- 🔊 Pokémon cries
- 🏆 Pokémon stat comparison

---

## 📸 Screenshots
<p align="center">
  <img src="screenshot/Screenshot_20260823_232644.png" width="250" />
  <img src="screenshot/Screenshot_20260823_232704.png" width="250" />
</p>

<p align="center">
  <b>Pokédex Home Screen</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <b>Pokémon Details Screen</b>
</p>

---

## 👨‍💻 Author

**Damanpreet Singh**

Built as a learning project while exploring **Kotlin, Jetpack Compose, MVVM architecture, API integration, and modern Android development**.
