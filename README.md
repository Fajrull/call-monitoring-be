# Call Monitoring App - Backend API

This repository contains the backend implementation for the Call Monitoring application. It is built using **Java Spring Boot** and **PostgreSQL**, providing a RESTful API to manage and monitor call data.

## 🛠 Technology Stack

- **Backend Framework:** Java Spring Boot
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Version Control:** Git

## 🚀 How to Run the Application

### 1. Prerequisites
- Java 21 (or compatible version) installed.
- PostgreSQL installed and running.
- Maven installed (optional, as the project includes Maven Wrapper).

### 2. Database Setup
Create a PostgreSQL database for the application. You can use the following command in your PostgreSQL terminal:
```sql
CREATE DATABASE call_monitoring_db;
```
Update the `src/main/resources/application.properties` file with your database credentials (default is set to):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/call_monitoring_db
spring.datasource.username=postgres
spring.datasource.password=adm123
```

### 3. Running the App
Karena menjalankan via Maven wrapper (`mvnw`) kadang terkendala oleh *environment* lokal, cara yang direkomendasikan adalah menjalankan langsung dari **IntelliJ IDEA**:

1. Buka project ini di IntelliJ IDEA.
2. Tunggu proses Maven *sync* dan *indexing* selesai.
3. Buka file utama Spring Boot (berakhiran `Application.java`, yang memiliki method `main`).
4. Klik tombol **Play (Run)** berwarna hijau yang ada di sebelah kiri baris `public static void main(...)`, lalu pilih **Run**.
*Note: The application includes a database seeder that will automatically populate the database with seed data upon startup.*

## 🧪 How to Run Tests

Untuk menjalankan unit test melalui **IntelliJ IDEA**:

1. Buka file test yang ingin dijalankan di dalam folder `src/test/java/...` (contoh: `CallMonitoringServiceImplTest.java`).
2. Klik tombol **Play (Run)** berwarna hijau di pinggir kiri kode, baik pada level deklarasi *class* maupun *method*, lalu pilih **Run**.
*(The tests include validations for pagination, searching, sorting, and data mapping in the Service layer).*

## 🤖 AI Usage

*Bagian ini menjelaskan penggunaan tool AI selama proses pengerjaan sesuai persyaratan dokumen.*

- **AI Tool yang digunakan:** Antigravity IDE (Gemini)
- **Bagian pekerjaan yang dibantu AI:** 
  - Membuat dan menyusun file `README.md` sesuai format dari pedoman.
  - Membuat data seeder (`DatabaseSeeder.java`) untuk tabel Call Monitoring.
- **Contoh prompt utama:** 
  - *"Buatkan README.md untuk project ini sesuai dengan requirement berikut: harus ada penjelasan prerequisites, cara setup database, cara menjalankan aplikasi, dan cara menjalankan test."*
  - *"Tolong buatkan 10 data seeder (dummy data) untuk class DatabaseSeeder dengan struktur kolom berikut: callId, callTimestamp, csName, customerName, dan sentimentScore."*
- **Cara kandidat memeriksa dan memverifikasi hasil AI:** 
  - Saya memverifikasi sintaks `DatabaseSeeder` hasil dari AI dengan mengecek apakah tipe datanya sudah sesuai dengan Entity saya, kemudian menjalankan Spring Boot untuk mengecek log, dan mengecek di PostgreSQL untuk memastikan 10 baris data berhasil di-*insert*.
  - Saya membaca ulang isi `README.md` dan menguji instruksi yang ada di dalamnya secara langsung di *local environment* saya.

---
*Created for the Take-Home Test.*
