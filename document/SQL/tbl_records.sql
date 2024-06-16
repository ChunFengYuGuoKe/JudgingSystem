create table if not exists ojdb.tbl_records
(
    id          int auto_increment comment '提交记录id'
        primary key,
    id_homework bigint       not null comment '提交题目id',
    id_lang     int          not null comment '使用语言',
    username    varchar(50)  not null comment '提交用户',
    answer      varchar(255) null comment '用户解答文件路径',
    score       int          null comment '用户得分',
    black       varchar(255) null comment '测试用例通过点',
    white       varchar(255) null comment 'AST符合度描述',
    time        varchar(255) null comment '提交时间',
    constraint tbl_records_ibfk_2
        foreign key (username) references ojdb.tbl_users (username)
            on update cascade on delete cascade,
    constraint tbl_records_ibfk_3
        foreign key (id_homework) references ojdb.tbl_homework (id)
            on update cascade on delete cascade
)
    charset = utf8mb3
    row_format = DYNAMIC;

create index homework_id
    on ojdb.tbl_records (id_homework);

create index language
    on ojdb.tbl_records (id_lang);

create index username
    on ojdb.tbl_records (username);

INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (1, 1, 1, '2021141420001', '0', 0, 'test', 'test', '2024-05-15 09:30:00');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (2, 1, 1, '2021141420002', '10', 10, null, null, '2024-05-15 09:31:00');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (5, 2, 1, '2021141420001', '0', 5, null, null, '2024-05-15 09:32:00');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (9, 1, 1, '2021141420003', '\\users\\null\\2021141420003\\1\\1718090473763\\Solution.java', null, null, null, '2024-06-11 15:21:13');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (10, 1, 1, '2021141420003', '\\users\\null\\2021141420003\\1\\1718091020100\\Solution.java', null, null, null, '2024-06-11 15:30:20');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (11, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718091183114\\Solution.java', null, null, null, '2024-06-11 15:33:03');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (12, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718091794723\\Solution.java', 100, '[]', null, '2024-06-11 15:43:14');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (13, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718091942521\\Solution.java', 100, '[]', null, '2024-06-11 15:45:42');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (14, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718092091175\\Solution.java', 100, '[]', null, '2024-06-11 15:48:11');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (15, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718092975073\\Solution.java', 100, '[]', null, '2024-06-11 16:02:55');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (16, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718093475726\\Solution.java', 100, '[]', null, '2024-06-11 16:11:15');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (17, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718093609561\\Solution.java', 100, '[]', null, '2024-06-11 16:13:29');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (18, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718093667571\\Solution.java', 34, '[
inputs: 20 30 outputs: 70,
inputs: 20 30 outputs: 0]', null, '2024-06-11 16:14:27');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (19, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718094205772\\Solution.java', 34, '[
inputs: 20 30 expected outputs: 70 your outputs: 50
,
inputs: 20 30 expected outputs: 0 your outputs: 50
]', null, '2024-06-11 16:23:25');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (20, 1, 1, '2021141420003', 'files\\users\\null\\2021141420003\\1\\1718094405951\\Solution.java', 34, '[|输入: 20 30 |预期输出: 70 |你的输出: 50
, |输入: 20 30 |预期输出: 0 |你的输出: 50
]', null, '2024-06-11 16:26:45');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (22, 1, 1, '2021141420001', 'files\\users\\null\\2021141420001\\1\\1718152516383\\Solution.java', 1, '[输入: 20 30 预期输出: 600 你的输出: 50
, 输入: 20 30 预期输出: 70 你的输出: 50
, 输入: 20 30 预期输出: 0 你的输出: 50
]', null, '2024-06-12 08:35:16');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (23, 1, 1, '2021141420001', 'files\\users\\null\\2021141420001\\1\\1718152630161\\Solution.java', 1, '[输入: 20 30 预期输出: 600 你的输出: 50
, 输入: 20 30 预期输出: 70 你的输出: 50
, 输入: 20 30 预期输出: 0 你的输出: 50
]', null, '2024-06-12 08:37:10');
INSERT INTO ojdb.tbl_records (id, id_homework, id_lang, username, answer, score, black, white, time) VALUES (24, 1, 1, '2021141420001', 'files\\users\\null\\2021141420001\\1\\1718152692729\\Solution.java', 1, '[输入: 20 30 预期输出: 600 你的输出: 50
, 输入: 20 30 预期输出: 70 你的输出: 50
, 输入: 20 30 预期输出: 0 你的输出: 50
]', null, '2024-06-12 08:38:12');
