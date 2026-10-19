# Poll Automation System — REST API

A backend application built using Spring Boot and PostgreSQL to create and manage polls, collect votes, and view poll results.

## Features

- Create polls with multiple options.
- Retrieve all polls and individual polls.
- Update and delete polls.
- Cast votes for poll options.
- View vote counts for each option.
- Validate poll creation data.
- Restrict voting to the poll's active time window.
- Handle errors using centralized exception handling.

## Tech Stack

- **Language:** Java
- **Backend:** Spring Boot
- **Database:** PostgreSQL
- **Data Access:** Spring Data JPA / Hibernate
- **API Testing:** Postman
- **Build Tool:** Maven

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/polls` | Create a poll |
| GET | `/api/polls` | Retrieve all polls |
| GET | `/api/polls/{id}` | Retrieve a poll by ID |
| PUT | `/api/polls/{id}` | Update a poll |
| DELETE | `/api/polls/{id}` | Delete a poll |
| POST | `/api/votes` | Cast a vote |
| GET | `/api/polls/{id}/results` | Retrieve poll results |

## Setup and Run

1. Install Java and PostgreSQL.
2. Create a PostgreSQL database named `pollautomationDB`.
3. Configure your database URL, username, and password in `application.properties`.
4. Open a terminal in the project root.
5. Run the application using Maven:

   ```bash
   ./mvnw spring-boot:run
   ```

6. The API runs on port `8085` with the `/api` context path, based on the current project configuration.

## Example Requests

### Create a poll

`POST /api/polls`

```json
{
  "question": "Which language do you prefer?",
  "startTime": "2027-01-01T10:00:00",
  "endTime": "2027-01-01T11:00:00",
  "options": ["Java", "Python", "C++"]
}
```

### Cast a vote

`POST /api/votes`

```json
{
  "optionId": 1
}
```

### View poll results

`GET /api/polls/1/results`

Returns each option with its vote count.

## Current Scope

This version implements the core poll management, voting, and results APIs. Authentication, role-based authorization, and duplicate-vote prevention are planned for a future enhancement.
