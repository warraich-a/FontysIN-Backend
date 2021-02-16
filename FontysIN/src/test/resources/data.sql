DROP ALL OBJECTS;
create table if not exists departments
(
    id          int auto_increment
        primary key,
    name        varchar(150) not null,
    description text         not null
);
create table if not exists fontyslocations
(
    id int auto_increment
        primary key,
    streetName varchar(150) not null,
    buildingNumber varchar(50) not null,
    city varchar(50) not null,
    zipcode varchar(20) not null
);
create table if not exists users
(
    id           int auto_increment
        primary key,
    firstName    varchar(150)                               not null,
    lastName     varchar(150)                               not null,
    userType     varchar(150)                               not null,
    email        varchar(150)                               not null,
    password     text                                       not null,
    image        text                                       not null,
    locationId   int                                        not null,
    departmentId int                                        not null,
    userNumber   varchar(150)                               not null,
    constraint departmentIdFK
        foreign key (departmentId) references departments (id)
            on update cascade on delete cascade,
    constraint locationId_fk
        foreign key (locationId) references fontyslocations (id)
            on update cascade on delete cascade
);
create table if not exists profiles
(
    id int auto_increment
        primary key,
    userId int not null,
    language varchar(50) not null,
    constraint fk_user_id
        foreign key (userId) references users (id)
            on update cascade on delete cascade
);
CREATE TABLE contacts
(
    id INTEGER AUTO_INCREMENT,
    userId     INTEGER        NOT NULL,
    friendId   INTEGER        NOT NULL,
    isAccepted tinyint(1)     NOT NULL DEFAULT 0,
    PRIMARY KEY ( id ),
    CONSTRAINT U_ID_FOREIGNKEY FOREIGN KEY (userId) REFERENCES users (id),
    CONSTRAINT FRIEND_ID_FOREIGNKEY FOREIGN KEY (friendId) REFERENCES users (id)
);

create table if not exists departments(
    id int auto_increment
        primary key,
    name varchar(150),
    descriptions text

);

create table if not exists profiles
(
    id int auto_increment
        primary key,
    userId int not null,
    language varchar(50) not null,
    constraint fk_user_id
        foreign key (userId) references users (id)
            on update cascade on delete cascade
);


create table if not exists posts
(
    id           int auto_increment
        primary key,
    userId       int                                        not null,
    content      text                                       not null,
    date         timestamp default CURRENT_TIMESTAMP()      not null,
    image        text                                               ,

    constraint userId_posts_FK
        foreign key (userId) references users (id)
            on update cascade on delete cascade
);
create table if not exists likes
(
    id           int auto_increment
        primary key,
    postId       int                                        not null,
    likerId      int                                        not null,


    constraint postId_like_FK
        foreign key (postId) references posts (id)
            on update cascade on delete cascade,
    constraint likerId_like_FK
        foreign key (likerId) references users (id)
            on update cascade on delete cascade
);
create table if not exists comments
(
    id           int auto_increment
        primary key,
    userId       int                                        not null,
    postId       int                                        not null,
    content      text                                       not null,
    date         timestamp default CURRENT_TIMESTAMP()      not null,

    constraint userId_comments_FK
        foreign key (userId) references users (id)
            on update cascade on delete cascade,
    constraint postId_comments_FK
        foreign key (postId) references posts (id)
            on update cascade on delete cascade
);

create table if not exists about
(
    id int auto_increment
        primary key,
    profileId int               not null,
    content text                not null,
    constraint fk_profileId
        foreign key (profileId) references profiles (id)
            on update cascade on delete cascade
);
create table if not exists educations(
  id  int auto_increment
      primary key ,
  profileId int             not null,
  school text               not null,
  startYear int             not null,
  endYear int               not null,
  degree varchar(250)       not null,
  fieldStudy varchar(250)   not null,
  description text          not null,
  constraint profileId_fk
      foreign key (profileId) references profiles (id)
    on update cascade on delete cascade
);
create table if not exists experiences
(
    id           int auto_increment
        primary key,
    profileId    int                                                    not null,
    title     varchar(150)                                              not null,
    company     varchar(150)                                            not null,
    location        varchar(150)                                        not null,
    employmentType     VARCHAR(150)       not null,
    startDate        int                                                not null,
    endDate   int                                                       not null,
    description varchar(150)                                            not null,
    constraint profileId
        foreign key (profileId) references profiles (id)
            on update cascade on delete cascade
);

create table if not exists skills
(
    id int auto_increment
        primary key,
    profileId int not null,
    name varchar(50) not null,
    constraint fk_profile_Id
        foreign key (profileId) references profiles (id)
            on update cascade on delete cascade
);
CREATE TABLE if not exists privacy
(
    id INTEGER AUTO_INCREMENT,
    userId     INTEGER        NOT NULL,
    educationSetting varchar(250)   NOT NULL,
    experienceSetting varchar(250)   NOT NULL,
    skillSetting varchar(250)   NOT NULL,
    hideFromSearch     INTEGER        NOT NULL,
    PRIMARY KEY ( id )
);

INSERT INTO departments (name, description) VALUES ('ICT', 'ICT General');
INSERT INTO departments (name, description) VALUES ('Pedagogy', 'Pedagogy General');
INSERT INTO departments (name, description) VALUES ('Buisness', 'Buisness General');
INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('RachelsMolen', 'R10', 'Eindhoven', '2647KR');
INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Tegelseweg', 'R1', 'Eindhoven', '1234AB');
INSERT INTO fontyslocations (streetName, buildingNumber, city, zipcode) VALUES ('Beatrixlaan', 'R3', 'Tilburg', '5643LP');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Rawan', 'Abou Dehn', 'Student', 'rawan@student.fontys.nl', '1234', 'rawan image', 1, 1, '123456');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Anas', 'Ahmad', 'Student', 'anas@student.fontys.nl', '12345', 'anas image', 1, 2, '123456');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Beatrice', 'Forslund', 'Student', 'beatrice@student.fontys.nl', '123456', 'bea image', 1, 3, '123456');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Ranim', 'Alayoubi', 'Student', 'ranim@student.fontys.nl', '1234567', 'ranim image', 2, 1, '123456');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Denys', 'Sytnyk', 'Student', 'denys@student.fontys.nl', '12345678', 'denys image', 3, 1, '123456');
INSERT INTO users (firstName, lastName, userType, email, password, image, locationId, departmentId, userNumber) VALUES ('Kalina', 'Petrova', 'Teacher', 'kalina@student.fontys.nl', '123456789', 'kalina image', 1, 1, '123456');
INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 2, 1);
INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 3, 1);
INSERT INTO contacts (userId, friendId, isAccepted) VALUES (1, 4, 0);
INSERT INTO contacts (userId, friendId, isAccepted) VALUES (5, 1, 0);
INSERT INTO contacts (userId, friendId, isAccepted) VALUES (2, 5, 1);


INSERT INTO profiles (userId, language) VALUES (1, 'English');
INSERT INTO profiles (userId, language) VALUES (2, 'Urdu');
INSERT INTO profiles (userId, language) VALUES (3, 'Swedish');
INSERT INTO profiles (userId, language) VALUES (4, 'Arabic');
INSERT INTO profiles (userId, language) VALUES (1, 'Arabic');

INSERT INTO posts ( userId, content, date, image) VALUES (1,'test post','2020-12-15 01:52:51','');
INSERT INTO posts ( userId, content, date, image) VALUES (3,'test post n2','2020-12-15 01:52:51','');
INSERT INTO posts ( userId, content, date, image) VALUES (2,'one more test post','2020-12-15 01:52:51','');
INSERT INTO posts ( userId, content, date, image) VALUES (2,'wow new post','2020-12-15 01:52:51','');

INSERT INTO likes ( postId, likerId) VALUES (2,1);
INSERT INTO likes ( postId, likerId) VALUES (1,1);
INSERT INTO likes ( postId, likerId) VALUES (1,3);

INSERT INTO comments ( userId, postId, content, date) VALUES (2, 1,'great comment','2020-12-15 01:52:51');
INSERT INTO comments ( userId, postId, content, date) VALUES (1, 1,'not bad comment','2020-12-15 01:52:51');

INSERT INTO experiences (profileId, title, company, location, employmentType, startDate, endDate, description) VALUES (1, 'Boss', 'Private', 'Lahore, PK', 'FullTime', 2000, 2005, 'good');
INSERT INTO experiences (profileId, title, company, location, employmentType, startDate, endDate, description) VALUES (2, 'Manager', 'FedEx', 'ISD, PK', 'FullTime', 2000, 2005, 'Very good');
INSERT INTO experiences (profileId, title, company, location, employmentType, startDate, endDate, description) VALUES (3, 'Cook', 'Private', 'MBD, PK', 'PartTime', 2000, 2005, 'Nice');
INSERT INTO educations (profileId, school, startYear, endYear, degree, fieldStudy, description) VALUES (1, 'NJBC', 2012, 2018, 'Inter', 'IT', 'Nice');
INSERT INTO educations (profileId, school, startYear, endYear, degree, fieldStudy, description) VALUES (2, 'AKU', 2012, 2018, 'MCOM', 'Ecnomics', 'Good');
INSERT INTO educations (profileId, school, startYear, endYear, degree, fieldStudy, description) VALUES (3, 'GGHS', 2012, 2018, 'Metric', 'Arts', 'Very good');
INSERT INTO about (profileId, content) VALUES (1, 'This is my profile');
INSERT INTO about (profileId, content) VALUES (2, 'This is also my profile');
INSERT INTO about (profileId, content) VALUES (3, 'And yeah, this one too');
INSERT INTO skills (profileId, name) VALUES (3, 'C#');
INSERT INTO skills (profileId, name) VALUES (2, 'Angular');
INSERT INTO skills (profileId, name) VALUES (2, 'Java');

INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (1, 'EVERYONE','EVERYONE','EVERYONE', 0);
INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (2, 'CONNECTIONS','CONNECTIONS','CONNECTIONS', 0);
INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (3, 'EVERYONE','EVERYONE','ONLYME', 0);
INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (4, 'CONNECTIONS','CONNECTIONS','CONNECTIONS', 0);
INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (5, 'EVERYONE','ONLYME','EVERYONE', 0);
INSERT INTO privacy (userId, educationSetting,experienceSetting,skillSetting,hideFromSearch) VALUES (6, 'ONLYME','ONLYME','ONLYME', 1);


