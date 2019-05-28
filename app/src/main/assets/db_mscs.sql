
drop table if exists [tb_CourseStatus];

CREATE TABLE [tb_CourseStatus] (
  [course_status_id] TINYINT(4) NOT NULL,
  [course_status] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_CourseStatus_1] PRIMARY KEY ([course_status_id]));

insert into [tb_CourseStatus] values(1, '已开放被选');
insert into [tb_CourseStatus] values(0, '未开放被选');


drop table if exists [tb_Floor];

CREATE TABLE [tb_Floor] (
  [floor_id] CHAR(1) NOT NULL,
  [floor_name] VARCHAR(2) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Floor_1] PRIMARY KEY ([floor_id]));

insert into [tb_Floor] values('1', '01');
insert into [tb_Floor] values('2', '02');
insert into [tb_Floor] values('3', '03');
insert into [tb_Floor] values('4', '04');
insert into [tb_Floor] values('5', '05');
insert into [tb_Floor] values('6', '06');
insert into [tb_Floor] values('7', '07');
insert into [tb_Floor] values('8', '08');
insert into [tb_Floor] values('9', '09');


drop table if exists [tb_Building];

CREATE TABLE [tb_Building] (
  [building_id] CHAR(1) NOT NULL,
  [building_name] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Building_1] PRIMARY KEY ([building_id]));

insert into [tb_Building] values('A', '二教B2');
insert into [tb_Building] values('B', '二教B4');
insert into [tb_Building] values('C', '二教B1');
insert into [tb_Building] values('D', '科技楼');
insert into [tb_Building] values('E', '钟海楼');
insert into [tb_Building] values('F', '主楼');
insert into [tb_Building] values('G', '游泳池');
insert into [tb_Building] values('H', '足球场');
insert into [tb_Building] values('I', '篮球场');
insert into [tb_Building] values('J', '二教B3');


drop table if exists [tb_Room];

CREATE TABLE [tb_Room] (
  [room_id] CHAR(2) NOT NULL,
  [room_name] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Room_1] PRIMARY KEY ([room_id]));

insert into [tb_Room] values('A', '001');
insert into [tb_Room] values('B', '002');
insert into [tb_Room] values('C', '003');
insert into [tb_Room] values('D', '004');
insert into [tb_Room] values('E', '005');
insert into [tb_Room] values('F', '006');
insert into [tb_Room] values('G', '007');
insert into [tb_Room] values('H', '008');
insert into [tb_Room] values('I', '009');
insert into [tb_Room] values('J', '010');
insert into [tb_Room] values('K', '011');
insert into [tb_Room] values('L', '012');
insert into [tb_Room] values('M', '013');
insert into [tb_Room] values('N', '014');
insert into [tb_Room] values('O', '015');
insert into [tb_Room] values('P', '016');
insert into [tb_Room] values('Q', '017');
insert into [tb_Room] values('R', '018');
insert into [tb_Room] values('S', '019');
insert into [tb_Room] values('T', '020');
insert into [tb_Room] values('U', '021');
insert into [tb_Room] values('V', '022');
insert into [tb_Room] values('W', '023');
insert into [tb_Room] values('X', '024');
insert into [tb_Room] values('Y', '025');
insert into [tb_Room] values('Z', '026');


drop table if exists [tb_Classroom];

CREATE TABLE [tb_Classroom] (
  [classroom_id] INT(11) NOT NULL,
  [classroom_name] VARCHAR(50) DEFAULT NULL,
  [building_id] CHAR(1) NOT NULL ,
  [floor_id] CHAR(1) NOT NULL ,
  [room_id] CHAR(2) NOT NULL ,
  CONSTRAINT [sqlite_autoindex_tb_Classroom_1] PRIMARY KEY ([classroom_id]));

insert into [tb_Classroom] values(1, null, 'C', '5', 'S');
insert into [tb_Classroom] values(2, null, 'D', '4', 'L');
insert into [tb_Classroom] values(3, null, 'E', '6', 'k');
insert into [tb_Classroom] values(4, null, 'A', '7', 'M');
insert into [tb_Classroom] values(5, null, 'I', '3', 'K');
insert into [tb_Classroom] values(6, null, 'D', '5', 'M');
insert into [tb_Classroom] values(7, null, 'F', '1', 'Y');
insert into [tb_Classroom] values(8, null, 'E', '8', 'D');
insert into [tb_Classroom] values(9, null, 'H', '3', 'H');
insert into [tb_Classroom] values(10, '', 'G', '6', 'R');


drop table if exists [tb_CourseTime];

CREATE TABLE [tb_CourseTime] (
  [course_time_id] VARCHAR(50) NOT NULL,
  [class_time_id] CHAR(2) NOT NULL,
  [week_set_id] CHAR(2) NOT NULL DEFAULT 01,
  CONSTRAINT [sqlite_autoindex_tb_CourseTime_1] PRIMARY KEY ([course_time_id]));

/* Data [tb_CourseTime] */
insert into [tb_CourseTime] values('1', '4', '04');
insert into [tb_CourseTime] values('2', '4', '02');
insert into [tb_CourseTime] values('3', '3', '01');
insert into [tb_CourseTime] values('4', '6', '05');
insert into [tb_CourseTime] values('5', '1', '04');
insert into [tb_CourseTime] values('6', '20', '03');
insert into [tb_CourseTime] values('7', '20', '01');
insert into [tb_CourseTime] values('8', '29', '02');
insert into [tb_CourseTime] values('9', '29', '01');


drop table if exists [tb_CourseType];

CREATE TABLE [tb_CourseType] (
  [course_type_id] TINYINT(4) NOT NULL,
  [course_type_name] VARCHAR(50) DEFAULT NULL,
  CONSTRAINT [sqlite_autoindex_tb_CourseType_1] PRIMARY KEY ([course_type_id]));

insert into [tb_CourseType] values(1, '自然科学类');
insert into [tb_CourseType] values(2, '文学类');
insert into [tb_CourseType] values(3, '专业任选类');
insert into [tb_CourseType] values(4, '艺术鉴赏类');
insert into [tb_CourseType] values(5, '体育类');


drop table if exists [tb_Course];

CREATE TABLE [tb_Course] (
  [course_id] VARCHAR(50) NOT NULL,
  [course_name] VARCHAR(50) NOT NULL,
  [course_type_id] TINYINT(4) NOT NULL ,
  [course_status_id] TINYINT(4) NOT NULL,
  [class_hour] SMALLINT(6) DEFAULT NULL,
  [credit] TINYINT(4, 1) DEFAULT NULL,
  [maxnum] TINYINT(4) NOT NULL,
  [course_time_id] VARCHAR(50) NOT NULL,
  [classroom_id] VARCHAR(50) NOT NULL ,
  [time_and_classroom] VARCHAR(50) NOT NULL UNIQUE,
  CONSTRAINT [sqlite_autoindex_tb_Course_2] PRIMARY KEY ([course_id]));
CREATE UNIQUE INDEX [timeandclass] ON [tb_Course] ([time_and_classroom]);

insert into [tb_Course] values('cur001', 'kecheng1', 1, 0, 36, 2, 50, '5', '5', '5-5');
insert into [tb_Course] values('cur002', 'kecheng2', 2, 1, 24, 1, 3, '4', '5', '4-5');
insert into [tb_Course] values('cur003', 'kecheng3', 3, 1, 36, 3, 2, '5', '4', '5-4');
insert into [tb_Course] values('cur004', 'kecheng4', 4, 1, 10, 1, 2, '4', '4', '4-4');
insert into [tb_Course] values('cur005', 'kecheng5', 5, 1, 24, 2, 3, '3', '1', '3-1');


drop table if exists [tb_Announcement];

CREATE TABLE [tb_Announcement] (
  [id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  [user_id] CHAR(20) NOT NULL,
  [comments] VARCHAR(50) DEFAULT NULL,
  [course_id] VARCHAR(50) DEFAULT NULL);

insert into [tb_Announcement] values(1, 'tea1001', 'announcement1', null);
insert into [tb_Announcement] values(2, 'mng01', 'announcement2', null);
insert into [tb_Announcement] values(3, 'tea1001', 'announcement3', null);
insert into [tb_Announcement] values(4, 'tea1002', 'ann', null);
insert into [tb_Announcement] values(5, 'tea1003', 'ANNOUNCEMENT5', null);


drop table if exists [tb_Class];

CREATE TABLE [tb_Class] (
  [class_id] INT(11) NOT NULL,
  [class_name] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Class_1] PRIMARY KEY ([class_id]));

insert into [tb_Class] values(1, '计科1153');
insert into [tb_Class] values(2, '新闻1174');
insert into [tb_Class] values(3, '软件1151');
insert into [tb_Class] values(4, '信管1152');
insert into [tb_Class] values(5, '通信1151');
insert into [tb_Class] values(6, '通信1152');
insert into [tb_Class] values(7, '物联1152');
insert into [tb_Class] values(8, '机械1155');
insert into [tb_Class] values(9, '能源1182');
insert into [tb_Class] values(10, '植保1163');


drop table if exists [tb_Section];

CREATE TABLE [tb_Section] (
  [section_id] CHAR(1) NOT NULL,
  [section] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Section_1] PRIMARY KEY ([section_id]));

insert into [tb_Section] values('1', '第1、2节');
insert into [tb_Section] values('2', '第3、4节');
insert into [tb_Section] values('3', '第5、6节');
insert into [tb_Section] values('4', '第7、8节');
insert into [tb_Section] values('5', '第9、10节');


drop table if exists [tb_Weekday];

CREATE TABLE [tb_Weekday] (
  [weekday_id] CHAR(1) NOT NULL,
  [weekday] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Weekday_1] PRIMARY KEY ([weekday_id]));

insert into [tb_Weekday] values('A', '星期一');
insert into [tb_Weekday] values('B', '星期二');
insert into [tb_Weekday] values('C', '星期三');
insert into [tb_Weekday] values('D', '星期四');
insert into [tb_Weekday] values('E', '星期五');
insert into [tb_Weekday] values('F', '星期六');
insert into [tb_Weekday] values('G', '星期日');


drop table if exists [tb_ClassTime];

CREATE TABLE [tb_ClassTime] (
  [class_time_id] CHAR(2) NOT NULL,
  [weekday_id] CHAR(1) NOT NULL ,
  [section_id] CHAR(1) NOT NULL ,
  CONSTRAINT [sqlite_autoindex_tb_ClassTime_1] PRIMARY KEY ([class_time_id]));

insert into [tb_ClassTime] values('1', 'A', '1');
insert into [tb_ClassTime] values('2', 'A', '2');
insert into [tb_ClassTime] values('3', 'A', '3');
insert into [tb_ClassTime] values('4', 'A', '4');
insert into [tb_ClassTime] values('5', 'A', '5');
insert into [tb_ClassTime] values('6', 'B', '1');
insert into [tb_ClassTime] values('7', 'B', '2');
insert into [tb_ClassTime] values('8', 'B', '3');
insert into [tb_ClassTime] values('9', 'B', '4');
insert into [tb_ClassTime] values('10', 'B', '5');
insert into [tb_ClassTime] values('11', 'C', '1');
insert into [tb_ClassTime] values('12', 'C', '2');
insert into [tb_ClassTime] values('13', 'C', '3');
insert into [tb_ClassTime] values('14', 'C', '4');
insert into [tb_ClassTime] values('15', 'C', '5');
insert into [tb_ClassTime] values('16', 'D', '1');
insert into [tb_ClassTime] values('17', 'D', '2');
insert into [tb_ClassTime] values('18', 'D', '3');
insert into [tb_ClassTime] values('19', 'D', '4');
insert into [tb_ClassTime] values('20', 'D', '5');
insert into [tb_ClassTime] values('21', 'E', '1');
insert into [tb_ClassTime] values('22', 'E', '2');
insert into [tb_ClassTime] values('23', 'E', '3');
insert into [tb_ClassTime] values('24', 'E', '4');
insert into [tb_ClassTime] values('25', 'E', '5');
insert into [tb_ClassTime] values('26', 'F', '1');
insert into [tb_ClassTime] values('27', 'F', '2');
insert into [tb_ClassTime] values('28', 'F', '3');
insert into [tb_ClassTime] values('29', 'F', '4');
insert into [tb_ClassTime] values('30', 'F', '5');
insert into [tb_ClassTime] values('31', 'G', '1');
insert into [tb_ClassTime] values('32', 'G', '2');
insert into [tb_ClassTime] values('33', 'G', '3');
insert into [tb_ClassTime] values('34', 'G', '4');
insert into [tb_ClassTime] values('35', 'G', '5');


drop table if exists [tb_College];

CREATE TABLE [tb_College] (
  [college_id] TINYINT(4) NOT NULL,
  [college_name] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_College_1] PRIMARY KEY ([college_id]));

insert into [tb_College] values(1, '体育与休闲学院');
insert into [tb_College] values(2, '法政学院');
insert into [tb_College] values(3, '化学与环境学院');
insert into [tb_College] values(4, '电子与信息工程学院');
insert into [tb_College] values(5, '数学与计算机学院');
insert into [tb_College] values(6, '管理学院');
insert into [tb_College] values(7, '经济学院');
insert into [tb_College] values(8, '农学院');
insert into [tb_College] values(9, '食品科技学院');
insert into [tb_College] values(10, '中歌艺术学院');
insert into [tb_College] values(11, '外国语学院');
insert into [tb_College] values(12, '文学与新闻传播学院');
insert into [tb_College] values(13, '软件学院');
insert into [tb_College] values(14, '航海学院');
insert into [tb_College] values(15, '海洋工程学院');
insert into [tb_College] values(16, '机械动力与工程学院');
insert into [tb_College] values(17, '海洋与气象学院');
insert into [tb_College] values(18, '水产学院');


drop table if exists [tb_Sex];

CREATE TABLE [tb_Sex] (
  [sex_id] TINYINT(4) NOT NULL,
  [sex] VARCHAR(50) NOT NULL);

insert into [tb_Sex] values(0, '女');
insert into [tb_Sex] values(1, '男');


drop table if exists [tb_UserStatus];

CREATE TABLE [tb_UserStatus] (
  [status_id] TINYINT(4) NOT NULL DEFAULT ('1'),
  [status] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_UserStatus_1] PRIMARY KEY ([status_id]));

insert into [tb_UserStatus] values(0, '已被开除');
insert into [tb_UserStatus] values(1, '正常');
insert into [tb_UserStatus] values(2, '已毕业');
insert into [tb_UserStatus] values(3, '已退学');


drop table if exists [tb_UserType];

CREATE TABLE [tb_UserType] (
  [type_id] TINYINT(4) NOT NULL,
  [type] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_UserType_1] PRIMARY KEY ([type_id]));

insert into [tb_UserType] values(0, '管理员');
insert into [tb_UserType] values(1, '学生');
insert into [tb_UserType] values(2, '教师');


drop table if exists [tb_Person];

CREATE TABLE [tb_Person] (
  [user_id] CHAR(20) NOT NULL,
  [name] VARCHAR(50) NOT NULL,
  [user_key] CHAR(20) NOT NULL DEFAULT 123456,
  [type_id] TINYINT(4) NOT NULL,
  [sex_id] TINYINT(4) DEFAULT NULL,
  [tel] BIGINT(20) DEFAULT NULL,
  [email] VARCHAR(50) DEFAULT NULL,
  [class_id] INT(11) DEFAULT NULL,
  [college_id] TINYINT(11) DEFAULT NULL,
  [status_id] TINYINT(4) NOT NULL ,
  [course_type_set] VARCHAR(20) DEFAULT NULL);
CREATE INDEX [PK_userid] ON [tb_Person] ([user_id]);

insert into [tb_Person] values('stu12301', 'wayne', '123456', 1, 1, 12301, 'waynemail', 3, 2, 1, '1,5');
insert into [tb_Person] values('stu12302', 'fish', '123456', 1, 0, 12302, 'fishmail', 3, 2, 1, '1,2,3,4,5,');
insert into [tb_Person] values('stu12303', 'tom', '123456', 1, 1, 12303, 'tommail', 3, 2, 1, '4,5,');
insert into [tb_Person] values('tea1001', 'lisi', '123456', 2, 1, 1001, 'lisimail', null, 5, 1, null);
insert into [tb_Person] values('tea1002', 'zhangsan', '123456', 2, 1, 1002, 'zhangshanmail', null, 6, 1, '');
insert into [tb_Person] values('stu12304', 'selana', '123456', 1, 0, 12304, 'selanamail', 3, 2, 1, '2,3,4,');
insert into [tb_Person] values('stu12305', 'ella', '123456', 1, 0, 12305, 'ellamail', 3, 2, 1, '1,');
insert into [tb_Person] values('tea1003', 'wangwu', '123456', 2, 0, 1003, 'wangwu', null, 1, 1, null);
insert into [tb_Person] values('mng01', 'xiaohong', '123456', 0, 1, 1, 'xiaohongmail', null, null, 1, null);


drop table if exists [tb_StuCourse];

CREATE TABLE [tb_StuCourse] (
  [user_id] CHAR(20) NOT NULL,
  [course_id] CHAR(20) NOT NULL,
  [grade1] INT(11),
  [grade2] INT(11),
  [sum] INT(11));

insert into [tb_StuCourse] values('stu12301', 'cur001', null, null, null);
insert into [tb_StuCourse] values('stu12301', 'cur002', null, null, null);
insert into [tb_StuCourse] values('stu1002', 'cur001', null, null, null);


drop table if exists [tb_TeaCourse];

CREATE TABLE [tb_TeaCourse] (
  [user_id] CHAR(20) NOT NULL,
  [course_id] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_TeaCourse_1] PRIMARY KEY ([course_id]));

insert into [tb_TeaCourse] values('tea1001', 'cur001');
insert into [tb_TeaCourse] values('tea1003', 'cur002');
insert into [tb_TeaCourse] values('tea1002', 'cur003');
insert into [tb_TeaCourse] values('tea1002', 'cur004');
insert into [tb_TeaCourse] values('tea1003', 'cur005');


drop table if exists [tb_Week];

CREATE TABLE [tb_Week] (
  [week_id] INT(2) NOT NULL,
  [week_statement] VARCHAR(50) NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_Week_1] PRIMARY KEY ([week_id]));

insert into [tb_Week] values(1, '第一周');
insert into [tb_Week] values(2, '第二周');
insert into [tb_Week] values(3, '第三周');
insert into [tb_Week] values(4, '第四周');
insert into [tb_Week] values(5, '第五周');
insert into [tb_Week] values(6, '第六周');
insert into [tb_Week] values(7, '第七周');
insert into [tb_Week] values(8, '第八周');
insert into [tb_Week] values(9, '第九周');
insert into [tb_Week] values(10, '第十周');
insert into [tb_Week] values(11, '第十一周');
insert into [tb_Week] values(12, '第十二周');
insert into [tb_Week] values(13, '第十三周');
insert into [tb_Week] values(14, '第十四周');
insert into [tb_Week] values(15, '第十五周');
insert into [tb_Week] values(16, '第十六周');
insert into [tb_Week] values(17, '第十七周');
insert into [tb_Week] values(18, '第十八周');
insert into [tb_Week] values(19, '第十九周');
insert into [tb_Week] values(20, '第二十周');


drop table if exists [tb_WeekSet];

CREATE TABLE [tb_WeekSet] (
  [week_set_id] CHAR(2) NOT NULL,
  [week_set] VARCHAR NOT NULL,
  CONSTRAINT [sqlite_autoindex_tb_WeekSet_1] PRIMARY KEY ([week_set_id]));

insert into [tb_WeekSet] values('01', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,');
insert into [tb_WeekSet] values('02', '1,3,5,7,9,11,13,15');
insert into [tb_WeekSet] values('03', '2,4,6,8,10,12,14');
insert into [tb_WeekSet] values('04', '5,6,7,8,9,10,11,12,13,14,15,16');
insert into [tb_WeekSet] values('05', '1,2,3,4,5,6,7');
insert into [tb_WeekSet] values('06', '9,10,11,12');
insert into [tb_WeekSet] values('07', '1,10,');
insert into [tb_WeekSet] values('08', '3,7,');
insert into [tb_WeekSet] values('09', '1,20');
insert into [tb_WeekSet] values('10', '6,7,8,');

