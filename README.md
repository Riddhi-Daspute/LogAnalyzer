# 📊 Log Analyzer for Large Files

A Full-Stack Log Analyzer developed using **Spring Boot**, **MySQL**, **HTML**, **CSS**, **JavaScript**, and **Bootstrap**. The application allows users to upload large log files, parse log entries, store them in a MySQL database, and analyze them through an interactive dashboard.

---

## 🚀 Features

- Upload large log files (.txt)
- Parse log entries automatically
- Store logs in MySQL database
- Display uploaded logs in a responsive table
- Search logs by message
- Filter logs by log level
- View log statistics dashboard
- Clear all stored logs
- REST API tested using Bruno

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Maven

### Frontend
- HTML5
- CSS3
- JavaScript
- Bootstrap 5

### Database
- MySQL

### Tools
- VS Code
- MySQL Workbench
- Bruno API Client
- Git
- GitHub

---

## 📂 Project Structure

```
loganalyzer
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── controller
│   │   │   ├── model
│   │   │   ├── repository
│   │   │   └── service
│   │   ├── resources
│   │   │   ├── static
│   │   │   ├── templates
│   │   │   └── application.properties
│
├── pom.xml
└── README.md
```

---

## 📸 Application Preview

### Dashboard

![Dashboard](screenshots/dashboard.jpeg)

### API Testing (Bruno)

![Bruno](screenshots/bruno.png)

### MySQL Database

![Database](screenshots/mysql.png)

---

## 📊 Sample Log Format

```
2026-07-11 10:15:30 INFO User logged in
2026-07-11 10:16:12 ERROR Database connection failed
2026-07-11 10:17:45 WARN Disk space low
2026-07-11 10:18:22 INFO File uploaded successfully
```

---

## ⚙️ REST API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/logs/upload` | Upload log file |
| GET | `/api/logs` | Fetch all logs |
| GET | `/api/logs/stats` | Get log statistics |
| DELETE | `/api/logs/clear` | Delete all logs |

---

## ▶️ How to Run

### Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/LogAnalyzer.git
```

### Open Project

```
Open in VS Code or IntelliJ IDEA
```

### Configure Database

Create a MySQL database named

```
loganalyzer
```

Update the database credentials inside

```
application.properties
```

### Run Application

```bash
mvn spring-boot:run
```

Open

```
http://localhost:8080
```

---

## 📈 Future Enhancements

- Support multiple log file formats
- Upload multiple files simultaneously
- Charts and graphs for analytics
- Date range filtering
- Export logs to Excel or PDF
- User authentication
- Pagination for millions of records

---

## 👩‍💻 Author

**Riddhi Kuntal Daspute**

MCA Student

---

## 📄 License

This project is developed for educational and learning purposes.