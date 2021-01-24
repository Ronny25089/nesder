CREATE USER nesder WITH PASSWORD 'nesder';
CREATE DATABASE nesder OWNER nesder;
GRANT ALL PRIVILEGES ON DATABASE nesder TO nesder;
\c nesder

drop table if exists public.Browse_History;
drop table if exists public.Article_Mark;
drop table if exists public.Fllow_Account;
drop table if exists public.Reply_2_Reply;
drop table if exists public.Reply;
drop table if exists public.Article;
drop table if exists public.Channel;
drop table if exists public.Module;
drop table if exists public.Chat_Content;
drop table if exists public.Chat_Group;
drop table if exists public.Account;
drop table if exists public.apikey;

DROP SEQUENCE if exists public.Account_Id;
DROP SEQUENCE if exists public.ApiKey_Id;
DROP SEQUENCE if exists public.Module_Id;
DROP SEQUENCE if exists public.Channel_Id;
DROP SEQUENCE if exists public.Article_Id;
DROP SEQUENCE if exists public.Reply_Id;
DROP SEQUENCE if exists public.Reply_2_Reply_Id;
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

CREATE SEQUENCE public.Module_Id
    INCREMENT 1
    START 10000001
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

CREATE SEQUENCE public.Channel_Id
    INCREMENT 1
    START 10000001
    MINVALUE 1
    MAXVALUE 99999999
    CACHE 1;

CREATE SEQUENCE public.Article_Id
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

CREATE SEQUENCE public.Reply_2_Reply_Id
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
create table public.apikey(
	ApiKey_Id int DEFAULT nextval('public.ApiKey_Id'::regclass) primary key,
	Account_Id int NOT NULL,
	ApiKey varchar NOT NULL,
  Authority_key varchar NOT NULL,
  remarck varchar,
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
	AvatarUrl varchar NOT NULL
);
--ALTER TABLE Account ALTER COLUMN Id SET DEFAULT nextval('public.Account_Id'::regclass);


--板块
create table public.Module(
	Module_Id int DEFAULT nextval('public.Module_Id'::regclass) primary key,
	MName varchar NOT NULL UNIQUE,
	Create_Date Date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Created_Account int NOT NULL--创建者Id
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE
);

--频道
create table public.Channel(
	Channel_Id int DEFAULT nextval('public.Channel_Id'::regclass) primary key,
	Name varchar NOT NULL UNIQUE,
	Create_Date Date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Created_Account int,
	MID int NOT NULL--父板块Id
	-- FOREIGN KEY(MID) references Module(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE
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
create table public.Article(
	Article_Id int DEFAULT nextval('public.Article_Id'::regclass) primary key,
	Title varchar NOT NULL,
	Marks int,
	Content text NOT NULL,
	Browse int DEFAULT 0,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	enable_Edit bool DEFAULT TRUE,
	Channel_Id int NOT NULL,--发表的频道id
	Created_Account int NOT NULL--发表者id
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Channel_Id) references Channel(Id) ON DELETE CASCADE
);
create trigger T_Article before update on Article for each row execute procedure public.upd_timestamp();

--关注文章
create table public.Article_Mark(
	AID int NOT NULL,--文章id
	UID int NOT NULL,--创建者id
  	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE
);

--回复文章
create table public.Reply(
	Reply_Id int DEFAULT nextval('public.Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	AID int NOT NULL,-- 目标文章id
	UID int NOT NULL--用户id
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE
);
create trigger T_Reply before update on Reply for each row execute procedure public.upd_timestamp();

--回复评论
create table public.Reply_2_Reply(
	Reply_2_Reply_Id int DEFAULT nextval('public.Reply_2_Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	RID int NOT NULL,-- 回复目标的用户id
	UID int NOT NULL,-- 发表回复的用户id
	AID int NOT NULL -- 回复文章id
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(RID) references Reply(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE
);
create trigger T2_Reply before update on Reply_2_Reply for each row execute procedure public.upd_timestamp();

--浏览记录
create table public.Browse_History(
  	UID int NOT NULL , --用户id
	AID int NOT NULL ,--文章id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE
);


--聊天组
create table public.Chat_Group(
	Chat_Group_Id int DEFAULT nextval('public.Chat_Group_Id'::regclass) primary key,
  Created_Account int NOT NULL,
	Group_Account_Type int NOT NULL,
	GName varchar NOT NULL,
	Introduction varchar NOT NULL,
	AvatorUrl varchar NOT NULL,
	Chat_Type int NOT NULL DEFAULT 0,--0:私聊  1:群聊
  	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE
);

--单条聊天内容
create table public.Chat_Content(
	Chat_Content_Id int DEFAULT nextval('public.Chat_Content_Id'::regclass) primary key,
  	Chat_Group_Id int NOT NULL,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Created_Account int NOT NULL
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Chat_Group_Id) references Chat_Group(Id) ON DELETE CASCADE
);

INSERT INTO public.apikey(account_id, apikey, authority_key, remarck) VALUES ( 9999, 9999, '127001', '共同apikey');

INSERT INTO public.module(mname, introduction, created_account) VALUES ('板块A', '板块A', 10000001);
INSERT INTO public.module(mname, introduction, created_account) VALUES ('板块B', '板块B', 10000001);
INSERT INTO public.module(mname, introduction, created_account) VALUES ('板块C', '板块C', 10000001);
INSERT INTO public.module(mname, introduction, created_account) VALUES ('板块D', '板块D', 10000001);
INSERT INTO public.module(mname, introduction, created_account) VALUES ('板块E', '板块E', 10000001);

INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('全部', '全部', 10000001, 10000000);
INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('频道A', '频道A', 10000001, 10000001);
INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('频道B', '频道B', 10000001, 10000002);
INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('频道C', '频道C', 10000001, 10000003);
INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('频道D', '频道D', 10000001, 10000004);
INSERT INTO public.channel(name, introduction, created_account, mid) VALUES ('频道E', '频道E', 10000001, 10000005);

GRANT ALL PRIVILEGES on SCHEMA public TO nesder;
GRANT ALL PRIVILEGES on ALL tables in SCHEMA public TO nesder;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to nesder;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to nesder;
