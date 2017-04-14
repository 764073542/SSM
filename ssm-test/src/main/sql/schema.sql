--------Windows-MySql默认字符内容不区分大小写，可以通过BINARY来指定字段区分大小写
--<!--
--使用 mvn dependency:copy-dependencies -DoutputDirectory=./src/main/webapp/WEB-INF/lib -DincludeScope=compile
--将jar包导入lib目录
---->

-----------------------------------------创建数据库
CREATE DATABASE ssm
--切换到ssm数据库
USE DATABASE ssm

--创建user表
--添加BINARY字段使本字段内容区分大小写
CREATE TABLE user(
    `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户自增id',
    `user_name` VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    `user_password` VARCHAR(128) BINARY NOT NULL COMMENT '用户密码',
    PRIMARY KEY(`user_id`),
    KEY `idx_user_name` (`user_name`)
)ENGINE=InnoDB AUTO_INCREMENT=1000,DEFAULT CHARSET=utf8 COMMENT '用户信息表'

--插入数据
insert ignore into user(user_name,user_password) values
    ('root','root'),
    ('admin','admin'),
    ('Allen','Allen'),
    ('Sla','Sla'),
    ('Kit','Kit');
select * from user;


--创建books表
CREATE TABLE books(
    `book_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '书籍自增id',
    `book_name` VARCHAR(120) NOT NULL UNIQUE COMMENT '书籍名称',
    `book_number` INT NOT NULL COMMENT '书籍馆存数量',
    PRIMARY KEY (`book_id`),
	/*创建时间索引是为了以后时间查询的业务提供方便*/
	KEY `idx_book_name` (`book_name`),
	KEY `idx_book_number` (`book_number`)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='书籍信息表'

--插入测试数据
insert ignore into books(book_name,book_number) values
    ('Java',100),
    ('Python',50),
    ('Scala',80),
    ('Ruby',45),
    ('.Net',3);
select * from books;

--添加一个还书时间字段 主键(`user_name`,`book_name`,`return_time`,`state`),
--保证不会出现多次正常借阅同一本书，还书时产生主键冲突
--创建用户借阅表
CREATE TABLE user_book_borrow(
     `id` BIGINT NOT NULL AUTO_INCREMENT UNIQUE COMMENT '自增id',
     `user_name` VARCHAR(20) NOT NULL COMMENT '用户名',
     `book_name` VARCHAR(120) NOT NULL COMMENT '书籍名',
     `start_time` DATE NOT NULL COMMENT '借阅时间',
     `end_time` DATE NOT NULL COMMENT '到期时间',
     `state` INT NOT NULL COMMENT '借阅状态{1：正常状态;0：即将到期;-1:超期;-2：归还状态；}',
     KEY `idx_start_time` (`start_time`),
     KEY `idx_end_time` (`end_time`),
     PRIMARY KEY(`user_name`,`book_name`,`state`),
     FOREIGN KEY(`user_name`) REFERENCES user(`user_name`),
     FOREIGN KEY(`book_name`) REFERENCES books(`book_name`)
)ENGINE=InnoDB AUTO_INCREMENT=1000,DEFAULT CHARSET=utf8 COMMENT '借阅信息表'

--插入测试数据
insert ignore into user_book_borrow(user_name,book_name,start_time,end_time,state) values
    ('root','Java',now(),'2017-6-1',1),
    ('root','Python','2017-5-21','2017-7-21',1),
    ('Allen','Scala','2017-2-1','2017-4-1',-1),
    ('Allen','Java','2017-2-5','2017-4-5',0),
    ('Allen','Ruby','2016-12-20',DATE_ADD('2016-12-20',INTERVAL 2 MONTH),-2);


select * from user_book_borrow;


--------------------------------------------补充----------------------------------------------------

--http://blog.csdn.net/u011532367/article/details/50015235

----------------------------------------基本操作
--修改数据库密码
set password for 'root'@'localhost'=password('newpassword')

--查看创建表时的详情
SHOW CREATE TABLE books \G

--修改表结构
ALTER TABLE user MODIFY `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户自增id'
ALTER TABLE user CHANGE `userid` `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户自增id'
ALTER TABLE user DROP COLUMN `user_id`
ALTER TABLE user ADD register_time TIMESTAMP

-----------修改主键
--先删除主键
alter table user_book_borrow drop primary key
--然后再添加主键
alter table user_book_borrow add primary key(user_name,book_name,state)

-----------------------------------------用户权限管理 TODO
--授权查询、插入、删除、修改权限
--创建用户
create user  manager_books   IDENTIFIED by 'password'; --identified by 会将纯文本密码加密作为散列值存储
--删除用户
drop user manager_books;
--查看用户权限
show grants for manager_books; --show grants for 'root'@'localhost';
--授予所有权限
GRANT ALL PRIVILEGES ON *.* TO 'manager_books'@'%' IDENTIFIED BY '283410' WITH GRANT OPTION;
--授权操作 数据库.表名
grant select,insert,update on ssm.*  to manager_books;
--回收权限
revoke  select on ssm.*  from  manager_books;  --如果权限不存在会报错
--更新当前操作
flush  privileges;

-----------------------------------------数据库导入导出
--导入操作：
source *.sql
--导出数据库to sql
mysqldump -uroot -p ssm > ssm.sql


---------------------------------------常用日期操作
select DAYOFWEEK('1998-02-03');  --返回星期几
select DAYOFYEAR('2017-05-01'); --返回date是一年中的第几日(在1到366范围内)
--日期计算 SECOND,MINUTE,HOUR,DAY,MONTH,YEAR
SELECT DATE_ADD('2016-12-31 23:59:59',INTERVAL 1 SECOND); --日期增加
SELECT DATE_ADD('2017-3-31',INTERVAL 2 MONTH);
SELECT DATE_SUB('2017-3-31',INTERVAL 2 MONTH);


---------------------------------------常用计算函数
SUM() , AVG() , length() , lower() , upper() , abs() , ceil() ， round()
max() , min() , count()

select length('demo')length from dual;
select lower('ABcEDFG') lower from dual;
select abs(10),abs(-5) from dual; --取绝对值
select ceil(5.9),ceil(6.1) from dual; -- 上取整 => 6，7
select round(9.9)from dual; -- 四舍五入

---------------------------------------常用查询
select user_name,book_name,start_time,end_time,state,count(book_name) as books from user_book_borrow
 group by user_name having books >=1; --group by 和 having
select * from books order by book_number desc; -- 排序 DESC降序 ASC升序
--多表联合查询 TODO
join , left out join , inner join ……
select u.user_id,u.user_name,b.book_name from user u,books b where u.user_id = b.book_id; --联合查询
select u.user_id,u.user_name,b.book_name from user u inner join books b on u.user_id = b.book_id; --内连接查询
--不同的 SQL JOIN
--JOIN: 如果表中有至少一个匹配，则返回行
--LEFT JOIN: 即使右表中没有匹配，也从左表返回所有的行
--RIGHT JOIN: 即使左表中没有匹配，也从右表返回所有的行
--FULL JOIN: 只要其中一个表中存在匹配，就返回行
--左外连接查询
select u.user_id,u.user_name,b.book_name from user u left outer join books b on u.user_id = b.book_id;
--右外连接
select u.user_id,u.user_name,b.book_name from user u right outer join books b on u.user_id = b.book_id;




---------------------------------------创建存储过程
--书籍借阅存储过程
--首先将借阅信息存入user_book_borrow表，如果不产生冲突，再执行book_number-1的操作
--需要传入 user_name,book_name,输出一个状态值

DELIMITER $$  --将结束符替换为$$
-- 定义存储过程（运行时去掉注释）
-- 参数：in 输入参数; out 输出参数
-- row_count():返回上一条修改类型sql(delete,insert,upodate)的影响行数
-- row_count: 0:未修改数据; >0:表示修改的行数; <0:sql错误/未执行修改sql则执行回滚
--r_result: -2，-1，0:操作失败，事物回滚 1：操作成功
CREATE PROCEDURE ssm.borrow(IN v_user_name VARCHAR(20),IN v_book_name VARCHAR(120),OUT r_result INT)
    BEGIN
        --声明一个变量用于记录插入影响的行数
        DECLARE insert_count INT DEFAULT 0;
        --声明一个变量用于记录插入影响的行数
        START TRANSACTION;
        INSERT IGNORE INTO user_book_borrow(user_name,book_name,start_time,end_time,state)
        VALUES (v_user_name,v_book_name,now(),DATE_ADD(now(),INTERVAL 2 MONTH),1);
        SELECT ROW_COUNT() INTO insert_count;
        IF (insert_count = 0) then
            ROLLBACK;
            SET r_result=-1;
        ELSEIF (insert_count < 0) THEN
            ROLLBACK;
            SET r_result=-2;
        ELSE
            UPDATE books SET book_number = book_number-1
            WHERE book_name=v_book_name;
            SELECT ROW_COUNT() INTO insert_count;
            IF (insert_count = 0) THEN
                ROLLBACK;
                SET r_result=0;
            ELSEIF (insert_count < 0) THEN
                ROLLBACK;
                SET r_result=-2;
            ELSE
                COMMIT;
                SET r_result=1;
            END IF;
        END IF;
    END;
$$
-- 代表存储过程定义结束

DELIMITER ;

SET @r_result = -3;
-- 执行存储过程
call borrow('Allen','Python', @r_result);
-- 获取结果
SELECT @r_result;

-- 存储过程
-- 1.存储过程优化：事务行级锁持有的时间
-- 2.不要过度依赖存储过程
-- 3.简单的逻辑可以应用存储过程
-- 4.QPS:一行记录6000/qps

