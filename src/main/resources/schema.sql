create table if not exists internship (
    id integer primary key not null auto_increment,
    companyName varchar(255) not null,
    description varchar(255) not null,
    hasApplied boolean not null
);