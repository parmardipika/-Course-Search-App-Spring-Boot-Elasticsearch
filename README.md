# 🚀 Course Search App – Spring Boot + Elasticsearch

This project is a Spring Boot-based RESTful service that indexes and searches course data using Elasticsearch. It supports filters, pagination, and sorting over course listings.

---

## 📦 Features

- 🔍 Full-text search on `title` and `description`
- 🎯 Filters: `category`, `type`, `minAge`, `maxAge`, `minPrice`, `maxPrice`, `startDate`
- 📄 Pagination and sorting (`upcoming`, `priceAsc`, `priceDesc`)
- 🛠 Bulk indexing of sample course data on startup
- 📈 REST API endpoint: `POST /api/search`

---

## 📁 Project Structure
```
├── docker-compose.yml
├── src
│ └── main
│ └── java/com/undoschool/course_search
│ ├── config
│ ├── controller
│ ├── document
│ ├── repository
│ ├── service
│ └── dto
│ └── resources
│ └── sample-courses.json
└── README.md
```

---


---

## 🐳 Elasticsearch Setup

### Step 1: Start Elasticsearch via Docker

```bash
docker-compose up -d
```

### Step 2:  Verify It’s Running
```bash
curl http://localhost:9200
```
You should see a JSON response with cluster_name, version, etc.

---

## 🚀 Run the Spring Boot Application
Make sure Elasticsearch is running. Then start your Spring Boot app:

```bash
./mvnw spring-boot:run
```
Or:

```bash
mvn clean install
java -jar target/course-search-0.0.1-SNAPSHOT.jar

```

---

## 📊 Sample Data Ingestion

-On startup, sample-courses.json is read from src/main/resources
-Courses are bulk-indexed into the courses index
-You can verify indexing with:

```bash
curl -X GET "localhost:9200/courses/_search?pretty"

```

## 🔍 API: Course Search

#### 📌Endpoint :

```http
POST /api/search
```

#### 📥Request Body Parameters

| Param       | Type    | Description                                  |
| ----------- | ------- | -------------------------------------------- |
| `q`         | string  | Search keyword (in title/description)        |
| `category`  | string  | Course category (exact match)                |
| `type`      | string  | Course type: `ONE_TIME`, `COURSE`, or `CLUB` |
| `minAge`    | integer | Minimum age of students                      |
| `maxAge`    | integer | Maximum age of students                      |
| `minPrice`  | double  | Minimum course price                         |
| `maxPrice`  | double  | Maximum course price                         |
| `startDate` | string  | ISO date – Filter courses on/after this date |
| `sort`      | string  | Sort by `upcoming`, `priceAsc`, `priceDesc`  |
| `page`      | integer | Page number (starts from 0, default = 0)     |
| `size`      | integer | Page size (default = 10)                     |

---

## ✅ Example API Request

#### 📤Request

```html
POST /api/search
Content-Type: application/json

{
  "q": "robotics",
  "page": 0,
  "size": 5
}
```

#### 📥Response

```json
{
  "total": 1,
  "courses": [
    {
      "id": "C103",
      "title": "Intro to Robotics",
      "category": "STEM",
      "price": 299.99,
      "nextSessionDate": "2025-08-01T15:00:00Z"
    }
  ]
}
```

---

## 🧪 Testing Suggestions

#### 🔹 Filter by category and price range

```json
{
  "q": "math",
  "category": "STEM",
  "minPrice": 100,
  "maxPrice": 400
}
```

#### 🔹 Sorted by price descending

```json
{
  "q": "coding",
  "sort": "priceDesc"
}
```

---

## 🔄 To Run Full Flow

```bash
docker-compose up -d       # Start Elasticsearch
./mvnw spring-boot:run     # Start the Spring Boot application
```
---

## 🤝 License
#### This project is licensed under the MIT License.

---

## 👩‍💻 Author
#### Dipika Parmar
##### 📧 parmardipika61825@gmail.com
##### 🌐 [GitHub](https://github.com/parmardipika)


















