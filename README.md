# Restaurant Management System and Delivery App

This project is a comprehensive backend management system and frontend delivery app for a restaurant. It enables the administration of internal workers, management of deliveries, and provides a user-friendly interface for customers to place orders.
This project showcases best practices for building a robust backend management system and a user-friendly frontend delivery app using Spring Boot MVC. It also focuses on deploying the application on Linux, implementing master-slave MySQL database architecture, and utilizing Redis for caching. By applying these technologies and techniques, the project aims to deliver an efficient and reliable restaurant management and delivery solution.

## Features

### Backend Management System
- Employee Management: Admin can add, edit, and disable employee information.
- Dish Category Management: Supports adding, editing, and deleting dish categories, such as Sichuan Cuisine, Hunan Cuisine, etc.
- Combo Meal Category Management: Admin can add, edit, and delete combo meal categories, such as Business meals, kids meals, etc.
- Dish Management: Provides functionality to manage dishes within each category, including file upload and download for dish images.
- Order Management: Displays order details and supports search by order number and date range.
- Pagination: Supports pagination for business categories using MyBatis Plus.
- Caching: Utilizes Redis to cache user verification and dish categories, automatically removing the cache on updates.
- Master-Slave Database: Implements a master-slave MySQL database setup to distribute the read-write load effectively.

### Frontend Delivery App
- User Authentication: Allows users to log in with text verification using Twilio Service.
- Dish Selection and Cart: Users can browse dishes, add them to the cart, and manage the cart contents.
- Checkout: Provides a seamless checkout process, ensuring a smooth ordering experience.
- Real-time Order Sync: Updates made in the backend management system are reflected in the delivery app in real-time.

## Technologies Used
- Spring Boot: Utilizes Spring Boot MVC framework to implement RESTful APIs and CRUD operations.
- MyBatis Plus: Provides enhanced functionality for database operations and simplifies data access layer implementation.
- Redis: Used for caching user verification and dish categories, improving application performance.
- MySQL: Utilizes a master-slave database setup to distribute the read-write load effectively.
- Twilio: Integrates Twilio Service for user authentication via text verification.
- Vue: Interact with variables and api calls.
- Nginx: Hosts the frontend pages and implements reverse proxy for API calls and redirect traffic with load balancer, ensuring seamless integration.
- Linux Deployment: The backend and frontend are deployed on separate Linux servers.
- GitHub Integration: Utilizes a shell script to automatically pull the latest code from the main branch on GitHub and redeploy on the Linux servers.

## Deployment
Everything is deployed on 2 CentOS linux in VMware:
1. Set up the backend on a Linux server. Created a shell script, which pulls the latest code from the main branch on GitHub and performs the necessary redeployment steps.
2. Host the frontend pages using Nginx on a separate Linux server, configuring the reverse proxy for API calls to ensure seamless integration.
3. Create master-slave relationship between the Mysql database on each linux system.

## License
This project is licensed under the [MIT License](LICENSE).
