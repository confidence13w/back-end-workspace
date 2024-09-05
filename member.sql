DROP TABLE member;

CREATE TABLE member(
	id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(60),
    name VARCHAR(20),
    role VARCHAR(20)
);

SELECT * FROM member;
