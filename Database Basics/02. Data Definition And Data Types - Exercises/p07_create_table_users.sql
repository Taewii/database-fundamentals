CREATE TABLE users(
id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
username VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(26) NOT NULL,
profile_picture VARBINARY(900),
last_login_time DATE,
is_deleted BOOLEAN,
CONSTRAINT pk_users PRIMARY KEY (id)
);

INSERT INTO users(username, `password`, profile_picture, last_login_time, is_deleted) 
VALUES
('test', '12wdaw345', 01001, NOW(), FALSE),
('test1', 'asd', 10110, NOW(), FALSE),
('test2', '123awd45', 01100, NOW(), FALSE),
('test3', '123awdwd45', 1101, NOW(), FALSE),
('test4', '123wgrg45', 00011, NOW(), TRUE);