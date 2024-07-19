DROP TABLE IF EXISTS testdb.member;
CREATE TABLE testdb.member
(
    id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Insert some dummy data
INSERT INTO member ( name, password, role ) VALUES("Test", "12345", "ADMIN");
INSERT INTO member ( name, password, role ) VALUES("Data", "12345", "USER");
