/* table Utilisateur */

CREATE TABLE users (
 user_id int PRIMARY KEY AUTO_INCREMENT,
 mail varchar(100) UNIQUE NOT NULL,
 password varchar(255) NOT NULL,
 firstname varchar(100),
 lastname varchar(100),
 phone varchar(15),
 sexe varchar(10),
 birthday DATE,
 aderess varchar(100),
 city varchar(100),
 country varchar(100),
 postal_code varchar(5),
 points int(11) NOT NULL DEFAULT 0,
 image_url varchar(200)
 );


/* Table Livre  */

CREATE TABLE books (
 book_id int PRIMARY KEY AUTO_INCREMENT,
 ISBN varchar(100) NOT NULL UNIQUE,
 title varchar(255) NOT NULL,
 published_date DATE,
 price float NOT NULL DEFAULT 0,
 EAN varchar(100),
 publisher varchar(200),
 description varchar(500),
 page_count int(11),
 langage varchar(50),
 image_url varchar(255)
);

/* Table Auteurs */

CREATE TABLE author_books (
author_books_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(250) NOT NULL,
book int NOT NULL,
FOREIGN KEY (book) REFERENCES books(book_id)
); 

/* TABLE categorie */

CREATE TABLE category_books (
category_books_id int PRIMARY KEY AUTO_INCREMENT,
name varchar(250) NOT NULL,
book int NOT NULL,
FOREIGN KEY (book) REFERENCES books(book_id)
); 

/* table livre_utilisateur */

CREATE TABLE user_books (
user_books_id int PRIMARY KEY AUTO_INCREMENT,
user_id int NOT NULL,
book_id int NOT NULL,
book_state varchar(20),
points float DEFAULT 0,
status varchar(20) DEFAULT 'SELL',
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (book_id) REFERENCES books(book_id)
);

/* table image */ 

CREATE TABLE images (
image_id int PRIMARY KEY AUTO_INCREMENT,
user_books_id int NOT NULL,
image_url varchar(255) NOT NULL,
title varchar(255),
FOREIGN KEY (user_books_id) REFERENCES user_books(user_books_id) 
);


/*  Table livres souhaités  */

CREATE TABLE user_books_wish (
 user_books_wish_id int PRIMARY KEY AUTO_INCREMENT,
 user_id int NOT NULL,
 book_id int NOT NULL,
 FOREIGN KEY (user_id) REFERENCES users( user_id),
 FOREIGN KEY (book_id) REFERENCES books(book_id)
);

/* table commandes */


CREATE TABLE commandes (
 
 commande_id int PRIMARY KEY AUTO_INCREMENT,
 user_books int NOT NULL,
 buyer_id int NOT NULL,
 status varchar(20) not null DEFAULT 'command',
 note float,
 code varchar(100) NOT NULL,
 FOREIGN KEY (user_books) REFERENCES user_books(user_books_id),
 FOREIGN KEY (buyer_id) REFERENCES users(user_id)
);
 
 