create table if not exists ojdb.tbl_users
(
    id       bigint auto_increment comment '自增id'
        primary key,
    username varchar(50)  not null comment '用户名（学生学号、教师工号）',
    password varchar(255) not null comment '密码',
    identity int          not null comment '用户身份（0学生 1教师）',
    name     varchar(255) not null comment '姓名',
    class    varchar(255) null comment '班级',
    email    varchar(255) null comment '邮箱',
    constraint username
        unique (username) comment '用户名唯一',
    constraint tbl_users_ibfk_1
        foreign key (class) references ojdb.tbl_class (class_id)
            on update cascade on delete cascade
)
    charset = utf8mb3
    row_format = DYNAMIC;

create index class
    on ojdb.tbl_users (class);

INSERT INTO ojdb.tbl_users (id, username, password, identity, name, class, email) VALUES (1, '2021141410001', '123456', 1, '张三', null, '123@scu.edu.cn');
INSERT INTO ojdb.tbl_users (id, username, password, identity, name, class, email) VALUES (2, '2021141420001', '123456', 0, '李四', '2110101', null);
INSERT INTO ojdb.tbl_users (id, username, password, identity, name, class, email) VALUES (3, '2021141420002', '123456', 0, '王五', '2110101', null);
INSERT INTO ojdb.tbl_users (id, username, password, identity, name, class, email) VALUES (4, '2021141420003', '123456', 0, 'Michael', '2110102', '666@gmail.com');
