drop table if exists Browse_History;
drop table if exists Article_Mark;
drop table if exists Fllow_Account;
drop table if exists Reply_2_Reply;
drop table if exists Reply;
drop table if exists Article;
drop table if exists Channel;
drop table if exists Module;
drop table if exists Chat_Content;
drop table if exists Chat_Group;
drop table if exists Account;
drop table if exists apikey;

DROP SEQUENCE if exists public.Account_Id;
DROP SEQUENCE if exists public.ApiKey_Id;
DROP SEQUENCE if exists public.Module_Id;
DROP SEQUENCE if exists public.Channel_Id;
DROP SEQUENCE if exists public.Article_Id;
DROP SEQUENCE if exists public.Reply_Id;
DROP SEQUENCE if exists public.Reply_2_Reply_Id;
DROP SEQUENCE if exists public.Chat_Group_Id;
DROP SEQUENCE if exists public.Chat_Content_Id;

create or replace function upd_timestamp() returns trigger as
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
create table apikey(
	ApiKey_Id int DEFAULT nextval('public.ApiKey_Id'::regclass) primary key,
	Account_Id int NOT NULL,
	ApiKey varchar NOT NULL,
  Authority_key varchar NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--ALTER TABLE Account ALTER COLUMN Id SET DEFAULT nextval('public.Account_Id'::regclass);

--用户账号
create table Account(
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
create table Module(
	Module_Id int DEFAULT nextval('public.Module_Id'::regclass) primary key,
	MName varchar NOT NULL UNIQUE,
	Create_Date Date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Created_Account int NOT NULL--创建者Id
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE
);

--频道
create table Channel(
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
create table Fllow_Account(
	Fllowed_ID int NOT NULL,--被关注者id
	Fllower_ID int NOT NULL,--粉丝id
  Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	-- FOREIGN KEY(Fllower_ID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Fllowed_ID) references Account(Id) ON DELETE CASCADE
);

--发表文章
create table Article(
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
create trigger T_Article before update on Article for each row execute procedure upd_timestamp();

--关注文章
create table Article_Mark(
	AID int NOT NULL,--文章id
	UID int NOT NULL,--创建者id
  Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE
);

--回复文章
create table Reply(
	Reply_Id int DEFAULT nextval('public.Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	AID int NOT NULL,-- 目标文章id
	UID int NOT NULL--用户id
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE
);
create trigger T_Reply before update on Reply for each row execute procedure upd_timestamp();

--回复评论
create table Reply_2_Reply(
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
create trigger T2_Reply before update on Reply_2_Reply for each row execute procedure upd_timestamp();

--浏览记录
create table Browse_History(
  UID int NOT NULL primary key, --用户id
	AID int NOT NULL primary key--文章id
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	-- FOREIGN KEY(UID) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(AID) references Article(Id) ON DELETE CASCADE
);


--聊天组
create table Chat_Group(
	Chat_Group_Id int DEFAULT nextval('public.Chat_Group_Id'::regclass) primary key,
  Created_Account int NOT NULL primary key,
	Group_Account_Type int NOT NULL,
	GName varchar NOT NULL,
	Introduction varchar NOT NULL,
	AvatorUrl varchar NOT NULL,
	Chat_Type int NOT NULL DEFAULT 0--0:私聊  1:群聊
  Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE
);

--单条聊天内容
create table Chat_Content(
	Chat_Content_Id int DEFAULT nextval('public.Chat_Content_Id'::regclass) primary key,
  Chat_Group_Id int NOT NULL primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Created_Account int NOT NULL
	-- FOREIGN KEY(Created_Account) references Account(Id) ON DELETE CASCADE,
	-- FOREIGN KEY(Chat_Group_Id) references Chat_Group(Id) ON DELETE CASCADE
);
