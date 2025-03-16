# Keto Live 2.0

**Keto Live 2.0** is an application designed to support the keto diet and intermittent fasting. It helps users easily transition to a keto lifestyle by providing recipes, product lists, a fasting timer, and personalized recommendations.

---

## Key Features

- **Registration and Authorization**: Users can create accounts and log in.
- **Recipes**: Simple and delicious recipes that adhere to keto principles.
- **Product Lists**: Categorization of products into "allowed," "not allowed," and "sometimes."
- **Intermittent Fasting**: A timer to track fasting periods.
- **Personalization**: A questionnaire to collect user data and provide recommendations based on it.
- **Activity Feed**: Tracking achievements and progress.

---

## Technologies

- **Frontend**: React, Tailwind CSS
- **Backend**: Spring Boot, Java
- **Database**: MongoDB
- **Authentication**: JWT (JSON Web Tokens)
- **Additional Tools**: Docker, Swagger, Postman

---

## How to Run the Project

### Prerequisites

- Java 17
- Node.js (for the frontend)
- MongoDB

### Installation and Setup

1. **Clone the repository**:
   
   git clone https://github.com/LinaGoebel/Keto_Live_2.0.git
   cd Keto_Live_2.0

2. **Set up the database**:
Ensure MongoDB is running locally on the default port (27017).

Create a database named ketolive.

3. **Run the backend**:
Navigate to the backend folder:
cd backend

Build and run the project using Maven:
mvn clean install
mvn spring-boot:run

4. **Run the frontend**:
Navigate to the frontend folder:
cd ../frontend

Install dependencies:
npm install

Start the project:
npm start

5. **Open the application**:
Go to http://localhost:3000 in your browser.

6. **API Documentation**
API documentation is available via Swagger UI:

After starting the backend, navigate to: http://localhost:8080/swagger-ui.html.

7. **How to Contribute**
If you'd like to contribute to the project, follow these steps:

Fork the repository.

Create a new branch:
git checkout -b feature/feature-name

Make your changes and commit them:
git commit -m "Add: description of changes"

Push your changes to your branch:
git push origin feature/feature-name

Create a Pull Request.

**License**
This project is licensed under the MIT License. For more details, see the LICENSE file.

**Contact**
If you have any questions or suggestions, feel free to reach out:

Email: your-email@example.com

GitHub: LinaGoebel

   


