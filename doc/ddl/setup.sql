CREATE USER nesder WITH PASSWORD 'nesder';
CREATE DATABASE nesder OWNER nesder;
GRANT ALL PRIVILEGES ON DATABASE nesder TO nesder;
\c nesder

drop table if exists public.Browse_History;
drop table if exists public.Post_Mark;
drop table if exists public.Post_MediaFIles;
drop table if exists public.Reply_like;
drop table if exists public.Reply;
drop table if exists public.Post;
drop table if exists public.Channel;
drop table if exists public.Forum;
drop table if exists public.Chat_Content;
drop table if exists public.Chat_Group;
drop table if exists public.User_Chat_Group;
drop table if exists public.Fllow_Account;
drop table if exists public.Account;
drop table if exists public.apikey;

DROP SEQUENCE if exists public.Account_Id;
DROP SEQUENCE if exists public.ApiKey_Id;
DROP SEQUENCE if exists public.Forum_Id;
DROP SEQUENCE if exists public.Channel_Id;
DROP SEQUENCE if exists public.Post_Id;
DROP SEQUENCE if exists public.Reply_Id;
DROP SEQUENCE if exists public.Chat_Group_Id;
DROP SEQUENCE if exists public.Chat_Content_Id;

create or replace function public.upd_timestamp() returns trigger as
$$
begin
	new.Modify_Date = current_timestamp;
	return new;
end
$$
language plpgsql;

CREATE SEQUENCE public.Account_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

CREATE SEQUENCE public.ApiKey_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

CREATE SEQUENCE public.Forum_Id
		INCREMENT 1
		START 101
		MINVALUE 1
		MAXVALUE 999
		CACHE 1;

CREATE SEQUENCE public.Channel_Id
		INCREMENT 1
		START 1001
		MINVALUE 1
		MAXVALUE 9999
		CACHE 1;

CREATE SEQUENCE public.Post_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

CREATE SEQUENCE public.Reply_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

CREATE SEQUENCE public.Chat_Group_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

CREATE SEQUENCE public.Chat_Content_Id
		INCREMENT 1
		START 10000001
		MINVALUE 1
		MAXVALUE 99999999
		CACHE 1;

--apikey
create table public.Apikey(
	ApiKey_Id int DEFAULT nextval('public.ApiKey_Id'::regclass) primary key,
	Account_Id int NOT NULL,
	ApiKey varchar NOT NULL,
	Authority_key varchar NOT NULL,
	remark varchar,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--ALTER TABLE Account ALTER COLUMN Id SET DEFAULT nextval('public.Account_Id'::regclass);

--用户账号
create table public.Account(
	Account_Id int DEFAULT nextval('public.Account_Id'::regclass) primary key,
	Role varchar NOT NULL DEFAULT 'USER',-- USER:一般用户 ADMIN:管理者
	Password varchar NOT NULL,
	Nick_Name varchar NOT NULL UNIQUE,
	Email varchar NOT NULL,
	Gender int NOT NULL,--0:男 1:女
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	BirthDay Date NOT NULL,
	Introduction varchar,
	login_Auth int NOT NULL DEFAULT 0,--登陆权限 0:一般用户 -1:禁言用户
	AvatarUrl varchar NOT NULL DEFAULT 'avatar/default.png'
);
--ALTER TABLE Account ALTER COLUMN Id SET DEFAULT nextval('public.Account_Id'::regclass);

--板块
create table public.Forum(
	Forum_Id int DEFAULT nextval('public.Forum_Id'::regclass) primary key,
	Forum_Name varchar NOT NULL UNIQUE,
	Create_Date Date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Account_Id int NOT NULL--创建者Id
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--频道
create table public.Channel(
	Channel_Id int DEFAULT nextval('public.Channel_Id'::regclass) primary key,
	Channel_Name varchar NOT NULL UNIQUE,
	Create_Date Date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Account_Id int,
	FID int NOT NULL--父板块Id
	-- FOREIGN KEY(FID) references Forum(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--用户关注_关系表
create table public.Fllow_Account(
	Fllowed_ID int NOT NULL,--被关注者id
	Fllower_ID int NOT NULL,--粉丝id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Fllower_ID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Fllowed_ID) references Account(Id) ON DELETE CASCADE
);

--发表文章
create table public.Post(
	Post_Id int DEFAULT nextval('public.Post_Id'::regclass) primary key,
	Title varchar NOT NULL,
	Content text NOT NULL,
	Browse int DEFAULT 0,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	Enable_Edit bool DEFAULT TRUE,
	Channel_Id int NOT NULL,--发表的频道id
	Account_Id int NOT NULL--发表者id
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Channel_Id) references Channel(Id) ON DELETE CASCADE
);
create trigger T_Post before update on Post for each row execute procedure public.upd_timestamp();

--关注文章
create table public.Post_Mark(
	Post_Id int NOT NULL primary key,--文章id
	Marks bool DEFAULT FALSE,
	Likes bool DEFAULT FALSE,
	Account_Id int NOT NULL,--创建者id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Post_Id) references Post(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--文章中的媒体文件
create table public.Post_MediaFIles(
	Post_Id int NOT NULL primary key,--文章id
	Image_1 varchar,
	Image_2 varchar,
	Image_3 varchar,
	Image_4 varchar,
	Image_5 varchar,
	Image_6 varchar,
	Image_7 varchar,
	Image_8 varchar,
	Image_9 varchar,
	Video varchar,
	Account_Id int NOT NULL,--创建者id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Post_Id) references Post(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--回复文章
create table public.Reply(
	Reply_Id int DEFAULT nextval('public.Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	Enable_Edit bool DEFAULT TRUE,
	Reply_To_Account_Id int,-- 回复目标的用户id（回复其他用户评论的时候，加入对象用户id）
	Reply_To_Reply_Id int,-- 回复目标的评论id（回复其他用户评论的时候，加入评论id）
	Post_Id int NOT NULL,-- 目标的文章id
	Account_Id int NOT NULL -- 发表评论的用户id
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Post_Id) references Post(Id) ON DELETE CASCADE
);
create trigger T_Reply before update on Reply for each row execute procedure public.upd_timestamp();

--点赞文章
create table public.Reply_like(
	Reply_Id int NOT NULL primary key,
	Likes bool DEFAULT FALSE,
	Account_Id int NOT NULL,--创建者id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Post_Id) references Post(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--浏览记录
create table public.Browse_History(
	Account_Id int NOT NULL, --用户id
	Post_Id int NOT NULL,--文章id
	Modify_Date TIMESTAMP,	
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Post_Id) references Post(Id) ON DELETE CASCADE
);

--聊天组
create table public.Chat_Group(
	Chat_Group_Id int DEFAULT nextval('public.Chat_Group_Id'::regclass) primary key,
	Account_Id int NOT NULL,
	Chat_Group_Name varchar,
	Introduction varchar,
	AvatorUrl varchar,
	Chat_Type int NOT NULL DEFAULT 0,--0:私聊	1:群聊
	Create_Account_Id int Not NUll,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--用户加入的聊天组
create table public.User_Chat_Group(
	Chat_Group_Id int NOT NULL primary key,
	Account_Id int NOT NULL,
	Create_Account_Id int Not NUll,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE
);

--单条聊天内容
create table public.Chat_Content(
	Chat_Content_Id int DEFAULT nextval('public.Chat_Content_Id'::regclass) primary key,
	Chat_Group_Id int NOT NULL,
	Content text NOT NULL,
	Image varchar,
	Video varchar,
	Account_Id int NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Account_Id) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Chat_Group_Id) references Chat_Group(Id) ON DELETE CASCADE
);

INSERT INTO public.apikey(account_id, apikey, authority_key, remark) VALUES ( 9999, 9999, '127001', '共同apikey');

INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '葫芦娃小朋友', 'huluwa@nesder.com', 1, '1990/01/01', '葫芦娃本娃', 'avatar/Multiavatar-1.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '阿凡提大叔叔', 'afanti@nesder.com', 1, '1990/01/01', '阿凡提本提', 'avatar/Multiavatar-2.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '买买提大哥哥', 'maimaiti@nesder.com', 1, '1990/01/01', '不买菜的买买提', 'avatar/Multiavatar-3.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '薰悟空', 'xunwukong@nesder.com', 1, '1990/01/01', '烧焦的薰悟空', 'avatar/Multiavatar-4.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '拘八戒戒', 'jubajie@nesder.com', 1, '1990/01/01', '拘八戒减肥中', 'avatar/Multiavatar-5.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '南波湾', 'nanbowan@nesder.com', 1, '1990/01/01', '外婆的南波湾', 'avatar/Multiavatar-6.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '猫国庆', 'maoguoqing@nesder.com', 1, '1990/01/01', '奔跑中的国庆', 'avatar/Multiavatar-7.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '七色鹿', 'qiselu@nesder.com', 1, '1990/01/01', '上天了的七色鹿', 'avatar/Multiavatar-8.png');
INSERT INTO public.account(password, nick_name, email, gender, birthday, introduction, avatarurl)
VALUES ('123456', '南郭先生', 'nanguo@nesder.com', 1, '1990/01/01', '滥竽充数中', 'avatar/Multiavatar-9.png');

INSERT INTO public.forum(forum_name, introduction, Account_Id) VALUES ('板块A', '板块A', 10000001);
INSERT INTO public.forum(forum_name, introduction, Account_Id) VALUES ('板块B', '板块B', 10000001);
INSERT INTO public.forum(forum_name, introduction, Account_Id) VALUES ('板块C', '板块C', 10000001);
INSERT INTO public.forum(forum_name, introduction, Account_Id) VALUES ('板块D', '板块D', 10000001);
INSERT INTO public.forum(forum_name, introduction, Account_Id) VALUES ('板块E', '板块E', 10000001);

INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('全部', '全部', 10000001, 100);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道A', '频道A', 10000001, 101);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道A-1', '频道A-1', 10000001, 101);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道B', '频道B', 10000001, 102);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道B-1', '频道B-1', 10000001, 102);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道B-2', '频道B-2', 10000001, 102);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道C', '频道C', 10000001, 103);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道D', '频道D', 10000001, 104);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道E', '频道E', 10000001, 105);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道E-1', '频道E-1', 10000001, 105);
INSERT INTO public.channel(channel_name, introduction, Account_Id, fid) VALUES ('频道F', '频道F', 10000001, 105);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试01', '因为疫情再起，为了不让掘友在假期太无聊，掘金酱准备了正在进行的技术专题！！！', 1002, 10000001);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试02', '双击烙铁666啊！！！', 1002, 10000002);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试03', '错了，应该是双击老铁666！！！', 1002, 10000003);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试04', '错了，应该是老铁，双击666！！！', 1002, 10000004);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试02', '双击烙铁666啊！！！', 1003, 10000005);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试03', '错了，应该是双击老铁666！！！', 1003, 10000006);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试04', '错了，应该是老铁，双击666！！！', 1003, 10000007);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '争议永久性取消GDP增长目标02', '从今年开始应该永久性取消GDP增长目标，而把稳定就业和控制通货膨胀作为宏观政策最主要的目标。', 1004, 10000009);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '争议永久性取消GDP增长目标03', '近日，央行货币政策委员会委员、北京绿色金融与可持续发展研究院院长马骏的一番言论，一石激起千层浪。', 1004, 10000001);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试01', '这一公开表态也被市场解读为货币政策转向的信号：如果不设目标，意味着货币政策稳增长的压力减少，进一步指向政策可能收紧。', 1005, 10000008);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试02', '岁末年初，往往是新一年政策的例行讨论期。', 1005, 10000009);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '测试03', '2021年中国春运正式启幕，石家庄火车站出站口与出站大厅，民警与相关工作人员认真查验着每一位到达的旅客。', 1005, 10000007);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '该市公交、地铁、出租车处于停运状态，相关部门采取多种措施，确保返乡旅客顺利回家。', 1006, 10000005);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '过年', '年终岁末，一年一度的春运又快到了。', 1006, 10000004);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '路通了，回家的脚步更快了。', 1011, 10000001);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '过年了', '今年春运，更多的动车组将投入运营。', 1007, 10000003);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '回家过年', '一张票根，是接触春运的第一道“窗口”。', 1008, 10000002);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '过年', '退票方面，线上退票业务办理时间优化至全天候24小时。', 1008, 10000003);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '该市公交、地铁、出租车处于停运状态，相关部门采取多种措施，确保返乡旅客顺利回家。', 1008, 10000007);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '准备过年', '年终岁末，一年一度的春运又快到了。', 1008, 10000008);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '路通了，回家的脚步更快了。', 1009, 10000004);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '春节快乐', '今年春运，更多的动车组将投入运营。', 1009, 10000009);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '此外，为了让广大旅客出行安全放心。', 1010, 10000003);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '铁路部门', '铁路部门全面贯彻冬春季常态化疫情防控要求。国铁集团运输部有关负责人表示，将从售票源头抓起，严格控制列车超员率，预留发热旅客隔离席位,对发热旅客及时下交。。', 1010, 10000008);

INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '有钱没钱回家过年', '路通了，回家的脚步更快了。', 1011, 10000007);
INSERT INTO public.post(title, content, channel_id, Account_Id)
VALUES ( '过年', '起铁路部门还在成渝高铁、京沪高铁试行推出了计次票和定期票产品。作为铁路部门推出的新型票制产品，持有者可在规定的有效期内，乘坐规定次数的、购买产品时指定发到站及席别的列车。', 1011, 10000003);

INSERT INTO public.reply(content, post_id, account_id)VALUES ('沙发，吃瓜围观！', 10000001, 10000002);
INSERT INTO public.reply(content, post_id, account_id)VALUES ('板凳，吃瓜围观！', 10000001, 10000005);
INSERT INTO public.reply(content, reply_to_account_id, post_id, account_id)VALUES ('围观啥？', 10000002, 10000001, 10000008);
INSERT INTO public.reply(content, reply_to_account_id, post_id, account_id)VALUES ('围观吃瓜呀！', 10000008, 10000001, 10000006);

GRANT ALL PRIVILEGES on SCHEMA public TO nesder;
GRANT ALL PRIVILEGES on ALL tables in SCHEMA public TO nesder;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to nesder;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to nesder;
