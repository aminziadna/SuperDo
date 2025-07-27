# 🏋️ Gym Management System - KMP

A comprehensive Kotlin Multiplatform (KMP) gym management system with separate frontends for users and management, built with modern technologies and best practices.

## 🏗️ Architecture

This project follows a clean architecture pattern with KMP for maximum code sharing:

```
GymKMP/
├── shared/                 # Shared business logic (KMP)
│   ├── domain/
│   │   ├── model/         # Data models
│   │   ├── repository/    # Repository interfaces
│   │   └── usecase/       # Business use cases
│   └── data/
│       └── repository/    # Repository implementations
├── androidUserApp/        # Android app for gym members
├── androidManagementApp/  # Android app for gym management
├── iosApp/               # iOS app for gym members
└── webApp/               # Web dashboard for management
```

## 🚀 Features

### 👥 User App (Members) - Android & iOS
- **Dashboard**: Personal workout stats, upcoming classes, quick actions
- **Class Booking**: Browse and book fitness classes with real-time availability
- **Profile Management**: Personal information, membership details, emergency contacts
- **Modern UI**: Material 3 design with intuitive navigation
- **Cross-Platform**: Native apps for both Android and iOS using Compose Multiplatform

### 🏢 Management App (Staff/Admin)
- **Dashboard**: Key metrics, alerts, recent activity overview
- **Member Management**: Search, view, and manage gym members
- **Class Management**: Schedule and manage fitness classes
- **Equipment Tracking**: Monitor equipment status and maintenance
- **Reports & Analytics**: Business insights and reporting tools

### 🌐 Web Dashboard
- **Responsive Design**: Works on desktop and tablet devices
- **Management Interface**: Web-based access to gym management features
- **Modern Web Technologies**: Built with Compose for Web

## 🛠️ Technology Stack

### Core Technologies
- **Kotlin Multiplatform**: Shared business logic across platforms
- **Jetpack Compose**: Modern UI for Android apps
- **Compose for Web**: Web frontend with Kotlin
- **Material 3**: Modern design system

### Libraries & Frameworks
- **Ktor**: HTTP client for API communication
- **Koin**: Dependency injection
- **Kotlinx Serialization**: JSON serialization
- **Kotlinx DateTime**: Date and time handling
- **Kotlinx Coroutines**: Asynchronous programming

### Architecture Components
- **MVVM Pattern**: Clear separation of concerns
- **Repository Pattern**: Data access abstraction
- **Use Cases**: Business logic encapsulation
- **Clean Architecture**: Maintainable and testable code

## 📱 Data Models

### Core Entities
- **User**: Members, trainers, and admin users
- **Membership**: Subscription plans and status
- **GymClass**: Fitness classes and schedules
- **ClassBooking**: Class reservations and attendance
- **Equipment**: Gym equipment and maintenance tracking

### Key Features of Models
- **Serializable**: JSON serialization support
- **Type Safety**: Enums for status and categories
- **Validation**: Built-in data validation
- **Extensible**: Easy to add new fields and features

## 🎨 UI/UX Features

### User Experience
- **Intuitive Navigation**: Bottom navigation for easy access
- **Material Design**: Consistent and modern interface
- **Responsive Layouts**: Adapts to different screen sizes
- **Accessibility**: Screen reader and keyboard navigation support

### Visual Design
- **Dynamic Colors**: Material You color theming
- **Dark Mode**: Automatic theme switching
- **Smooth Animations**: Polished user interactions
- **Card-based Layout**: Clean and organized information display

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Latest stable version
- **JDK**: Version 11 or higher
- **Gradle**: Version 8.0 or higher

### Building the Project

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd GymKMP
   ```

2. **Build the shared module**
   ```bash
   ./gradlew :shared:build
   ```

3. **Run the Android User App**
   ```bash
   ./gradlew :androidUserApp:installDebug
   ```

4. **Run the Android Management App**
   ```bash
   ./gradlew :androidManagementApp:installDebug
   ```

5. **Run the iOS App**
   ```bash
   ./gradlew :iosApp:iosSimulatorArm64Test
   ```
   *Note: iOS app requires Xcode and macOS for compilation*

6. **Run the Web App**
   ```bash
   ./gradlew :webApp:jsBrowserDevelopmentRun
   ```

## 📁 Project Structure

### Shared Module
```
shared/src/commonMain/kotlin/
├── domain/
│   ├── model/
│   │   ├── User.kt
│   │   ├── Membership.kt
│   │   ├── GymClass.kt
│   │   └── Equipment.kt
│   ├── repository/
│   │   ├── UserRepository.kt
│   │   ├── MembershipRepository.kt
│   │   └── GymClassRepository.kt
│   └── usecase/
│       └── BookClassUseCase.kt
└── data/
    └── repository/
        └── FakeUserRepository.kt
```

### Android Apps Structure
```
src/main/java/
├── ui/
│   ├── screens/
│   ├── theme/
│   └── navigation/
└── MainActivity.kt
```

## 🔧 Configuration

### Gradle Configuration
- **Kotlin Version**: 1.9.20
- **Compose Version**: 1.5.4
- **Target SDK**: 34
- **Min SDK**: 24

### Dependencies
- Modern versions of all libraries
- Regular security updates
- Optimized for performance

## 🚦 Current Status

### ✅ Completed Features
- [x] Project structure and configuration
- [x] Shared data models and repositories
- [x] Android User App with core screens
- [x] Android Management App with dashboard
- [x] Web app foundation
- [x] Material 3 theming
- [x] Navigation implementation

### 🚧 In Progress
- [ ] Real API integration
- [ ] Database implementation
- [ ] Advanced class management
- [ ] Equipment tracking system
- [ ] Reporting and analytics

### 📋 Upcoming Features
- [ ] Push notifications
- [ ] Payment integration
- [ ] Advanced search and filtering
- [ ] Offline support
- [ ] Performance optimizations

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

1. **Fork** the repository
2. **Create** a feature branch
3. **Commit** your changes
4. **Push** to the branch
5. **Create** a Pull Request

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex business logic
- Write unit tests for new features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **JetBrains** for Kotlin Multiplatform and Compose
- **Google** for Material Design and Android development tools
- **Open Source Community** for the amazing libraries and tools

---

**Built with ❤️ using Kotlin Multiplatform**