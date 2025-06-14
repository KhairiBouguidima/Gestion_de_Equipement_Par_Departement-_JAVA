# Gestion_de_Equipement_Par_Departement-_JAVA


# Gestion Laboratoire

**Gestion Laboratoire** is a robust desktop application built with Java Swing for managing laboratory departments, equipment inventories, user roles, and maintenance workflows.

---

## 🚀 Key Features

- **Department & Laboratory Management**  
  Create and organize departments and labs; track capacity and location.

- **Equipment Inventory**  
  Register equipment with serial numbers, acquisition dates, status, and maintenance schedules.

- **User Roles & Security**  
  - **Administrator**: Full CRUD over departments, users, and maintenance records.  
  - **Employé**: Submit reservations and maintenance requests.  
  - **Technicien**: View assignments, log maintenance actions, and update statuses.  
  All passwords are securely hashed (e.g., bcrypt) and stored in MySQL.

- **Request & Assignment Workflow**  
  Submit, track, and assign “reservation” or “maintenance” tasks with configurable urgency levels and statuses.

- **Maintenance Logging**  
  Record problems, solutions, durations, and cost history for each equipment item.

---

## 🛠️ Tech Stack & Dependencies

- **Language**: Java 11+  
- **UI Framework**: Java Swing (MVC pattern)  
- **Database**: MySQL 8.0  
- **ORM**: Hibernate 5  
- **Build & Dependency Management**: Maven  
- **Security**: Bcrypt for password hashing (`org.mindrot:jbcrypt`)  
- **Logging**: SLF4J + Logback  

---

## 🎯 Architecture Overview


1. **Swing Forms & Panels** display data and capture user actions.  
2. **Service Layer** validates input, applies business rules, hashes passwords.  
3. **Hibernate Entities & Repositories** persist data to MySQL.

---

## 📦 Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/gestion-laboratoire.git
   cd gestion-laboratoire
