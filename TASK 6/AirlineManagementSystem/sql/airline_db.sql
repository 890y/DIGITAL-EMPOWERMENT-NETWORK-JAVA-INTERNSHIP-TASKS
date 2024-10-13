-- Create Database
CREATE DATABASE IF NOT EXISTS airline_management;

USE airline_management;

-- Create Customers Table
CREATE TABLE IF NOT EXISTS customers (
    cust_id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(100)
);

-- Create Flights Table
CREATE TABLE IF NOT EXISTS flights (
    id VARCHAR(50) PRIMARY KEY,
    airline_name VARCHAR(100),
    fare_price DECIMAL(10, 2),
    flight_type VARCHAR(50),
    flight_duration VARCHAR(50)
);
