CREATE TABLE railway_crossings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE KEY,
    address VARCHAR(255) NOT NULL,
    landmark VARCHAR(255),
    train_schedule DATETIME,
    platform_in_charge VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL ,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE railway_crossing_mapping (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    railway_crossing_id INT NOT NULL,
    CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES users(email),
    CONSTRAINT fk_railway_crossing FOREIGN KEY (railway_crossing_id) REFERENCES railway_crossings(id)
);


