CREATE TABLE board(
	no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20),
    content TEXT,
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    url VARCHAR(200)
);

SELECT * FROM board
LIMIT 0, 10; -- 첫 번째 페이지 

SELECT * FROM board
LIMIT 10, 10; -- 두 번째 페이지 

SELECT * FROM board
LIMIT 20, 10; -- 세 번째 페이지 

INSERT INTO board(title, content) VALUES('테스트', '테스트 정보입니다');
