CREATE DATABASE IF NOT EXISTS ajp_jdbc_demo;

USE ajp_jdbc_demo;

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    department VARCHAR(50) NOT NULL
);

INSERT INTO employees (name, age, salary, department)
VALUES
    ('Prafful', 23, 28000.00, 'MCA'),
    ('Aditi', 22, 30000.00, 'Development'),
    ('Rohit', 24, 32000.00, 'Testing');
