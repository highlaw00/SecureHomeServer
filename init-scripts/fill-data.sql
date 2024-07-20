DROP TABLE IF EXISTS testdb.member;
CREATE TABLE testdb.member
(
    id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Insert some dummy data
INSERT INTO member ( name, password, role ) VALUES("admin", "$2a$10$wOAqXjtIMyCKR2oVy3mPlu4OYikW9iq9/novbYHfShxKPCESP44R.", "ADMIN");
INSERT INTO member ( name, password, role ) VALUES("test", "$2a$10$bofKBprKo8m5OOa/nhzM8O7lx3e6.gmQfvkBM67dMT6OWC75bgWSa", "USER");
