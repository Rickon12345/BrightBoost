-- Create a table for students
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    status varchar(10) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_of_birth TIMESTAMP
);

-- Create a table for teachers
CREATE TABLE IF NOT EXISTS teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    status varchar(10) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_of_birth TIMESTAMP
);

-- Create a table to associate courses with teachers
CREATE TABLE IF NOT EXISTS course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a table to associate students with courses
CREATE TABLE IF NOT EXISTS student_course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    calendar VARCHAR(500),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create a table to associate student with course
CREATE TABLE IF NOT EXISTS teacher_course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_id INT,
    course_id INT,
    calendar VARCHAR(500),
    FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    FOREIGN KEY (course_id) REFERENCES course(id),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);