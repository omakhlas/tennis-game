# Tennis Game Application

This project implements a simple **Tennis Game score tracker** using **hexagonal architecture**. The application integrates with **Kafka** to publish score update events, allowing you to view updates via a Kafka consumer or retrieve scores through a REST endpoint.

---

## Prerequisites

- **Docker & Docker Compose**
- **Java 21**
- **Gradle 8.12.1**

---

## Getting Started

### 1. Start the Kafka Cluster

Use **Docker Compose** to start your Kafka broker and the Confluent Control Center:

```bash
docker compose up -d
```

This command launches the Kafka broker and the Confluent Control Center.

---

### 2. Configure the Application

1. In your `application.yml`, replace `MY_IP_ADDRESS` with your **public IP address** :

   ```yaml
   spring:
     kafka:
       bootstrap-servers: MY_IP_ADDRESS:9092
   ```

2. Compile and run the application by executing:

   ```bash
   ./gradlew compileJava
   ```
   ```bash
   ./gradlew bootRun
   ```

---

### 3. Verify the Kafka Cluster

1. Open your browser and navigate to **[http://localhost:9021](http://localhost:9021)**.
2. This should load the **Confluent Control Center**.
3. Verify that the Kafka cluster is running and click on **Topics** (initially, it should be empty).

---

### 4. Test the Tennis Game API

Use a **REST client** such as **Postman** to test the API endpoint:

- **Endpoint:** `http://localhost:8080/tennis/play`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "input": "ABABABABABAA"
  }
  ```

When you send the request, the API returns a list of **score updates**. These updates are also logged in the console by a Kafka consumer that processes score update events.

---

### 5. Check Real-time Updates

#### Console Output

The **Kafka consumer** (implemented as part of the secondary adapter) logs the score updates to the **console**. Check the logs for messages showing the **current score**.

---

## Hexagonal Architecture

This project follows a **hexagonal architecture** (**Ports and Adapters**), which helps keep the **core business logic isolated** and **independent** from infrastructure concerns. The structure is as follows:

- **Domain:**

    - Contains the business logic is completely **independent of external systems**.

- **Use Case Layer:**

    - Orchestrates domain operations (`TennisGameUseCase`), ensuring that **business rules** are applied.

- **Adapters:**

    - **Primary Adapters:** (e.g., REST controllers) that expose the API.
    - **Secondary Adapters:** (e.g., Kafka publishers and consumers) that integrate with Kafka.


