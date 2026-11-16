CREATE DATABASE IF NOT EXISTS APPOINTMENT_BOOKING_SYSTEM;
USE APPOINTMENT_BOOKING_SYSTEM;

CREATE TABLE IF NOT EXISTS customers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(120) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS appointments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  appointment_date DATE NOT NULL,
  appointment_time TIME NOT NULL,
  service_name VARCHAR(120) NOT NULL,
  status ENUM('BOOKED','CANCELLED','COMPLETED') NOT NULL DEFAULT 'BOOKED',
  notes VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_appointment_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
  UNIQUE KEY unique_active_slot (appointment_date, appointment_time, status)
);

-- Demo customer for local testing. Use your own details when testing.
INSERT IGNORE INTO customers(full_name,email,phone)
VALUES ('Demo Customer','demo@example.com','9999999999');
