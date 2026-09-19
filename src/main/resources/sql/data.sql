insert into sample(id, str) values ('1', 'hello');

/** ユーザーマスタ */
insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('system@example.com', 'password01', 'システム管理者', '1990-01-15', 36, 1, 1, 'ROLE_ADMIN');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user01@example.com', 'password01', '山田太郎', '1990-01-15', 36, 1, 1, 'USER');

insert into m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
values ('user02@example.com', 'password02', '佐藤花子', '1995-05-20', 31, 2, 2, 'USER');

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