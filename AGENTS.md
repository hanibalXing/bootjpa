# Spring Boot Java Project AGENT Guidelines

## Project Structure

```
src/
├── main/java/com/gx/bootjpa/
│   ├── BootjpaApplication.java
│   ├── controller/
│   │   └── *.java
│   ├── service/
│   │   └── *.java
│   ├── model/
│   │   └── *.java
│   └── repository/
│       └── *.java
├── main/resources/
│   ├── application.properties
│   └── static/
└── test/java/com/gx/bootjpa/
    ├── BootjpaApplicationTests.java
    └── controller/
        └── *.java
```

## Key Dependencies (pom.xml)

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database (for testing)
- Spring Boot Starter Test
- Lombok (for boilerplate code reduction)

## Build Commands

```bash
# Compile project
mvn compile

# Run tests
mvn test

# Build jar file
mvn package

# Clean build
mvn clean install
```

## Linting and Formatting

- Use Spotless for code formatting (configured in pom.xml)
- Follow Google Java Style Guide
- Import ordering:
  1. Static imports
  2. Java standard library packages
  3. Third-party libraries
  4. Project-specific packages

## Testing

```bash
# Run all tests
mvn test

# Run single test class
mvn test -Dtest=HelloControllerTest

# Run single test method
mvn test -Dtest=HelloControllerTest#testGetGreeting
```

## Code Style Guidelines

### Imports
- Use wildcard imports only for static imports with many constants
- Order imports alphabetically by package name

### Formatting
- 100 character line width
- Tabs for indentation (2 spaces)
- No trailing whitespace
- Blank lines between methods

### Types
- Prefer interfaces over abstract classes
- Use Optional to handle null values properly
- Avoid raw types in generics

### Naming Conventions
- Class names: PascalCase
- Method/variable names: camelCase
- Constants: UPPER_SNAKE_CASE
- Package names: lowercase

### Error Handling
- Use custom exceptions for business logic errors
- Log errors with appropriate log levels (error, warn)
- Return proper HTTP status codes in REST controllers

### Spring-Specific Practices
- Use @RestController for REST endpoints
- Annotate services with @Service
- Annotate repositories with @Repository
- Use constructor injection over field injection
- Prefer @Autowired on constructors

## Development Workflow

1. Create feature branch from main
2. Implement changes following code style guidelines
3. Run tests locally before committing
4. Push to remote and create PR for review
5. Address feedback and rebase if necessary
