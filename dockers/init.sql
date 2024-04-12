-- Create a new database
CREATE DATABASE IF NOT EXISTS spring_mysql;

-- Use the created database
USE spring_mysql;

-- Create a new table
CREATE TABLE IF NOT EXISTS TableName (
    pk_id INT
    , column1 VARCHAR(255)
    , column2 VARCHAR(255)
);

-- Insert values into the table
INSERT INTO TableName (column1, column2) VALUES ('value1', 'value2');