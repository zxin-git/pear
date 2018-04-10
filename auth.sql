
    drop table if exists auth_role

    drop table if exists auth_user

    create table auth_role (
        roleId integer not null,
        created bigint not null,
        description varchar(255),
        modified datetime,
        rolename varchar(255),
        primary key (roleId)
    ) ENGINE=InnoDB

    create table auth_user (
        id integer not null,
        createTime bigint not null,
        dept varchar(255),
        description varchar(255),
        email varchar(255),
        lastLoginTime bigint not null,
        modifyTime bigint not null,
        password varchar(255),
        phone varchar(255),
        realname varchar(255),
        status integer not null,
        type integer not null,
        username varchar(255),
        primary key (id)
    ) ENGINE=InnoDB
