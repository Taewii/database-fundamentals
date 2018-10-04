DROP DATABASE IF EXISTS instagraph_db;
CREATE DATABASE instagraph_db;
USE instagraph_db;

CREATE TABLE pictures(
	id INT PRIMARY KEY AUTO_INCREMENT,
	path VARCHAR(255) NOT NULL,
	size DECIMAL(10, 2) NOT NULL
);

CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(30) UNIQUE NOT NULL,
	`password` VARCHAR(30) UNIQUE NOT NULL,
	profile_picture_id INT,
	CONSTRAINT fk_pic_id FOREIGN KEY (profile_picture_id)
	REFERENCES pictures(id)
);

CREATE TABLE posts(
	id INT PRIMARY KEY AUTO_INCREMENT,
	caption VARCHAR(255) NOT NULL,
	user_id INT NOT NULL,
	picture_id INT NOT NULL,
	CONSTRAINT fk_user_id FOREIGN KEY (user_id)
	REFERENCES users(id),
	CONSTRAINT fk_posts_pic_id FOREIGN KEY (picture_id)
	REFERENCES pictures(id)
);

CREATE TABLE comments(
	id INT PRIMARY KEY AUTO_INCREMENT,
	content VARCHAR(255) NOT NULL,
	user_id INT NOT NULL,
	post_id INT NOT NULL,
	CONSTRAINT fk_comments_user_id FOREIGN KEY (user_id)
	REFERENCES users(id),
	CONSTRAINT fk_post_id FOREIGN KEY (post_id)
	REFERENCES posts(id)
);

CREATE TABLE users_followers(
	user_id INT,
	follower_id INT,
	CONSTRAINT fk_uf_user_id FOREIGN KEY (user_id)
	REFERENCES users(id),
	CONSTRAINT fk_follower_id FOREIGN KEY (follower_id)
	REFERENCES users(id)
);