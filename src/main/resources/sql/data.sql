insert into sample(id, str) values ('1', 'hello');

/** ユーザーマスタ */
insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('system@example.com', 'password01', 'システム管理者', '1990-01-15', 36, 1, 1, 'ROLE_ADMIN');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user01@example.com', 'password01', '山田太郎', '1990-01-15', 36, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user02@example.com', 'password02', '佐藤花子', '1995-05-20', 31, 2, 2, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user03@example.com', 'password03', '鈴木一郎', '1988-11-02', 38, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user04@example.com', 'password04', '高橋美香', '1992-07-18', 34, 2, 2, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user05@example.com', 'password05', '田中健太', '1997-03-13', 29, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user06@example.com', 'password06', '伊藤さくら', '2000-09-10', 26, 2, 2, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user07@example.com', 'password07', '渡辺翔', '1985-12-27', 41, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user08@example.com', 'password08', '小林由美', '1994-04-08', 32, 2, 2, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user09@example.com', 'password09', '松本大輔', '1989-06-30', 37, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user10@example.com', 'password10', '中村愛', '1998-02-14', 28, 2, 2, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user11@example.com', 'password11', '加藤悠斗', '1991-08-21', 35, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user12@example.com', 'password12', '吉田梨奈', '1996-10-05', 30, 2, 2, 'USER');

/** 部署マスタ */
insert into m_department(department_id, department_name)
values (1, '営業部');

insert into m_department(department_id, department_name)
values (2, '開発部');

/** 給料テーブル */
insert into t_salary(user_id, year_month, salary)
values ('user01@example.com', '2026/01', 300000);

insert into t_salary(user_id, year_month, salary)
values ('user01@example.com', '2026/02', 400000);

insert into t_salary(user_id, year_month, salary)
values ('user02@example.com', '2026/01', 280000);

insert into t_salary(user_id, year_month, salary)
values ('user02@example.com', '2026/02', 380000);

insert into t_salary(user_id, year_month, salary)
values ('user03@example.com', '2026/01', 320000);

insert into t_salary(user_id, year_month, salary)
values ('user03@example.com', '2026/02', 420000);

insert into t_salary(user_id, year_month, salary)
values ('user04@example.com', '2026/01', 310000);

insert into t_salary(user_id, year_month, salary)
values ('user04@example.com', '2026/02', 410000);

insert into t_salary(user_id, year_month, salary)
values ('user05@example.com', '2026/01', 290000);

insert into t_salary(user_id, year_month, salary)
values ('user05@example.com', '2026/02', 390000);

insert into t_salary(user_id, year_month, salary)
values ('user06@example.com', '2026/01', 300000);

insert into t_salary(user_id, year_month, salary)
values ('user06@example.com', '2026/02', 360000);

insert into t_salary(user_id, year_month, salary)
values ('user07@example.com', '2026/01', 340000);

insert into t_salary(user_id, year_month, salary)
values ('user07@example.com', '2026/02', 440000);

insert into t_salary(user_id, year_month, salary)
values ('user08@example.com', '2026/01', 330000);

insert into t_salary(user_id, year_month, salary)
values ('user08@example.com', '2026/02', 430000);

insert into t_salary(user_id, year_month, salary)
values ('user09@example.com', '2026/01', 350000);

insert into t_salary(user_id, year_month, salary)
values ('user09@example.com', '2026/02', 450000);

insert into t_salary(user_id, year_month, salary)
values ('user10@example.com', '2026/01', 280000);

insert into t_salary(user_id, year_month, salary)
values ('user10@example.com', '2026/02', 370000);

insert into t_salary(user_id, year_month, salary)
values ('user11@example.com', '2026/01', 315000);

insert into t_salary(user_id, year_month, salary)
values ('user11@example.com', '2026/02', 415000);

insert into t_salary(user_id, year_month, salary)
values ('user12@example.com', '2026/01', 305000);

insert into t_salary(user_id, year_month, salary)
values ('user12@example.com', '2026/02', 405000);