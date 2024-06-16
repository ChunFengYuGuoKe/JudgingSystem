-- auto-generated definition
create table tbl_class
(
    id       bigint auto_increment comment '自增id'
        primary key,
    class_id varchar(50) not null comment '班级号',
    teacher  varchar(50) not null comment '教师id',
    constraint class_id
        unique (class_id),
    constraint tbl_class_ibfk_1
        foreign key (teacher) references tbl_users (username)
            on update cascade on delete cascade
)
    charset = utf8mb3
    row_format = DYNAMIC;

create index teacher
    on tbl_class (teacher);

INSERT INTO ojdb.tbl_class (id, class_id, teacher) VALUES (1, '2110101', '2021141410001');
INSERT INTO ojdb.tbl_class (id, class_id, teacher) VALUES (2, '2110102', '2021141410001');
