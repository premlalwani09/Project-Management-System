# Project Management System

The Project Management System is a web application designed to streamline the management of projects. It offers essential CRUD functionalities, allowing users to create, read, update, and delete projects with ease.

## Features

- **Create:** Add new projects to the system effortlessly.
- **Read:** View existing projects stored in the database.
- **Update:** Modify project details as required.
- **Delete:** Remove projects from the system when they are no longer needed.

## Technologies Used

- Java 21
- Spring Boot 3.2.5
- H2 Database (in-memory)
- Spring Web
- Spring Data JPA
- Mockito (for testing)

## Installation

1. Clone the repository: `git clone https://github.com/premlalwani09/Project-Management-System`
2. Navigate to the project directory: `cd project-management-system`
3. Build the project: `mvn clean install`

## Usage

1. Start the application by running the main class `ProjectManagementSystemApplication.java`.
2. Access the application through your web browser or API testing tool.
3. Utilize the provided API endpoints to perform CRUD operations on projects.

## API Endpoints

- `POST /projects`: Create a new project.
- `GET /projects`: Retrieve all projects.
- `GET /projects/{id}`: Retrieve a specific project by its ID.
- `PUT /projects/{id}`: Update an existing project by its ID.
- `DELETE /projects/{id}`: Delete a specific project by its ID.

## Testing

The project includes both unit tests and integration tests.

### Unit Tests

- **Location:** `src/test/java`
- **Framework:** JUnit and Mockito
- **Execution:** Run `mvn test`

### Integration Tests

- **Location:** `src/test/java`
- **Framework:** SpringBootTest and MockMvc
- **Execution:** Run `mvn test`

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues if you encounter any bugs or have suggestions for improvements.

