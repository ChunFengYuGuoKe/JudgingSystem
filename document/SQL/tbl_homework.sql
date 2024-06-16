create table if not exists ojdb.tbl_homework
(
    id          bigint auto_increment comment '数据库自增id'
        primary key,
    title       varchar(50)  not null comment '作业标题',
    id_lang     int          not null comment '使用语言',
    description varchar(255) not null comment '题目描述',
    solution    varchar(255) null comment '参考答案文件路径',
    usecases    varchar(255) null comment '测试用例文件路径'
)
    charset = utf8mb3
    row_format = DYNAMIC;

INSERT INTO ojdb.tbl_homework (id, title, id_lang, description, solution, usecases) VALUES (1, 'a+b', 1, '输入两个数a和b,计算a加b的和', null, null);
INSERT INTO ojdb.tbl_homework (id, title, id_lang, description, solution, usecases) VALUES (2, 'n皇后', 1, 'n皇后', null, null);
INSERT INTO ojdb.tbl_homework (id, title, id_lang, description, solution, usecases) VALUES (16, 'axb', 1, 'axb', null, null);
INSERT INTO ojdb.tbl_homework (id, title, id_lang, description, solution, usecases) VALUES (18, '最大公约数', 1, '最大公约数', null, null);
