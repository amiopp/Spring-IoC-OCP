# Spring IoC & OCP Demo

A comprehensive demonstration of **Spring Framework's Inversion of Control (IoC)** container and the **Open-Closed Principle (OCP)** using Spring profiles and configuration.

## 📋 Overview

This project showcases how Spring's dependency injection enables flexible, maintainable code that adheres to SOLID principles, specifically the Open-Closed Principle. The application demonstrates multiple DAO implementations that can be switched at runtime without modifying existing code.

## 🎯 Key Concepts Demonstrated

- **Inversion of Control (IoC)**: Spring manages object creation and dependency injection
- **Open-Closed Principle (OCP)**: System is open for extension but closed for modification
- **Spring Profiles**: Environment-specific bean configuration
- **Component Scanning**: Automatic bean discovery
- **Property-driven Configuration**: External configuration via properties files

## 🏗️ Architecture

```
src/main/java/
├── dao/                    # Data Access Layer
│   ├── IDao.java          # DAO interface
│   ├── DaoImpl.java       # Implementation 1 (returns 100.0)
│   ├── DaoImpl2.java      # Implementation 2 (returns 200.0)
│   ├── DaoFile.java       # File-based implementation (returns 360.0)
│   └── DaoApi.java        # API-based implementation (returns 440.0)
├── metier/                # Business Logic Layer
│   ├── IMetier.java       # Business interface
│   └── MetierImpl.java    # Business implementation (doubles DAO value)
├── config/                # Configuration Classes
│   ├── PropertyDrivenConfig.java  # Property-based bean selection
│   └── DaoAliasConfig.java        # Profile-based configurations
└── presentation/          # Presentation Layer
    └── Presentation2.java # Main application entry point
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/amiopp/Spring-IoC-OCP.git
cd Spring-IoC-OCP
```

2. Build the project:
```bash
mvn clean install
```

### Running the Application

The application uses Spring profiles to switch between different DAO implementations:

```bash
# Run with default profile (configured in Presentation2.java)
mvn exec:java -Dexec.mainClass="presentation.Presentation2"
```

## 🔧 Configuration

### Available Profiles

Edit `Presentation2.java` to activate different profiles:

| Profile | DAO Implementation | Expected Result |
|---------|-------------------|-----------------|
| `dev`   | DaoImpl           | 200.0 (100 × 2) |
| `prod`  | DaoImpl2          | 400.0 (200 × 2) |
| `file`  | DaoFile           | 720.0 (360 × 2) |
| `api`   | DaoApi            | 880.0 (440 × 2) |

### Property-based Configuration

Modify `src/main/resources/app.properties`:

```properties
dao.target=dao2
```

This allows runtime selection of DAO implementation without code changes.

## 📦 Dependencies

- **Spring Context** 6.1.2 - Core Spring IoC container
- **JUnit** 4.13.2 - Unit testing framework

## 🧪 Testing

Run the tests:

```bash
mvn test
```

The test suite includes `OcpSelectionTest.java` which validates the profile-based bean selection mechanism.

## 💡 How It Works

1. **Dependency Injection**: `MetierImpl` depends on `IDao` interface, not concrete implementations
2. **Profile Activation**: Spring profiles determine which DAO bean to create
3. **Runtime Flexibility**: Switch implementations by changing the active profile
4. **OCP Compliance**: Add new DAO implementations without modifying existing code

### Example Flow

```java
// 1. Activate a profile
ctx.getEnvironment().setActiveProfiles("api");

// 2. Spring creates the appropriate DAO bean (DaoApi)
// 3. Spring injects it into MetierImpl
// 4. Business logic executes without knowing the concrete implementation
IMetier metier = ctx.getBean(IMetier.class);
System.out.println("Result = " + metier.calcul()); // Output: 880.0
```

## 🎓 Learning Outcomes

This project demonstrates:

- ✅ Loose coupling through interfaces
- ✅ Dependency injection via Spring annotations
- ✅ Configuration externalization
- ✅ Profile-based environment management
- ✅ SOLID principles in practice

## 📝 License

This project is for educational purposes.

## 👤 Author

**amiopp**

- GitHub: [@amiopp](https://github.com/amiopp)
- Repository: [Spring-IoC-OCP](https://github.com/amiopp/Spring-IoC-OCP)

---

⭐ Star this repository if you found it helpful!
