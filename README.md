# Resume Mailer Application

A simple web application to send emails with a fixed resume attachment.  
Users can input recipient email, subject, and message body, select from multiple email templates, and send the email directly from the frontend. The backend is built with Spring Boot and handles email sending using Gmail SMTP with an attachment.

---

## Features

- ReactJS frontend with responsive design
- Spring Boot backend REST API for sending emails
- Multiple email templates selectable via dropdown (frontend)
- Sends emails with a fixed resume PDF attachment
- Uses Gmail SMTP with app password authentication
- CORS enabled for frontend-backend communication

---

## Project Structure

mailer/
├── mailer-frontend/ # ReactJS frontend
└── mailer-backend/ # Spring Boot backend


## Prerequisites

- Node.js (v16 or higher recommended)
- Java JDK 11 or higher
- Maven
- Gmail account with App Password enabled (for sending email)

---

## Setup Instructions

### Backend

1. Navigate to `mailer-backend` folder.

2. Configure your Gmail credentials in `src/main/resources/application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
Build and run the backend:

./mvnw clean install
./mvnw spring-boot:run
Backend API runs on http://localhost:8080

Frontend
Navigate to mailer-frontend folder.

Install dependencies:
npm install

Start the frontend development server:
npm start
Frontend runs on http://localhost:3000

Usage
Open frontend at http://localhost:3000

Fill in recipient email, subject, and message body

Select email template from dropdown (if implemented)

Click Send Email button

Check recipient inbox for the email with resume attached

API Endpoint
POST http://localhost:8080/api/mail/send
{
  "to": "recipient@example.com",
  "subject": "Email Subject",
  "body": "Email message body"
}
Notes
Ensure CORS is properly configured in backend for frontend requests.

Use a fixed resume PDF stored in backend resources for attachment.

Update Gmail SMTP settings and enable App Password for your account.

For production, consider securing API and sensitive data.

License
This project is licensed under the MIT License.

Author
Ankit Bhujeja
[GitHub](https://github.com/ankitb2023)

---
