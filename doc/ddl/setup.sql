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

DROP SEQUENCE if exists public.Account_Id;
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

--用户账号
create table Account(
	Id int DEFAULT nextval('public.Account_Id'::regclass) primary key,
	Account_Id varchar NOT NULL UNIQUE,
	Role varchar NOT NULL DEFAULT 'USER',-- USER:一般用户 ADMIN:管理者
	Password varchar NOT NULL,
	Nick_Name varchar NOT NULL,
	Email varchar NOT NULL,
	Gender int NOT NULL,--0:男 1:女
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Age int NOT NULL,
	Introduction varchar,
	login_Auth int NOT NULL DEFAULT 0,--登陆权限 0:一般用户 -1:禁言用户
	AvatarUrl varchar NOT NULL
);
--ALTER TABLE Account ALTER COLUMN Id SET DEFAULT nextval('public.Account_Id'::regclass);


--板块
create table Module(
	Id int DEFAULT nextval('public.Module_Id'::regclass) primary key,
	MName varchar NOT NULL UNIQUE,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar,
	Created_Account int NOT NULL,
	FOREIGN KEY(Created_Account) references Account(Id)
);

--频道
create table Channel(
	Id int DEFAULT nextval('public.Channel_Id'::regclass) primary key,
	Name int NOT NULL UNIQUE,
	Created_Account int,
	MID int NOT NULL,
	FOREIGN KEY(MID) references Module(Id),
	FOREIGN KEY(Created_Account) references Account(Id)
);

--用户关注_关系表
create table Fllow_Account(
	Fllowed_ID int NOT NULL,
	Fllower_ID int NOT NULL,
	FOREIGN KEY(Fllower_ID) references Account(Id),
	FOREIGN KEY(Fllowed_ID) references Account(Id)
);

--发表文章
create table Article(
	Id int DEFAULT nextval('public.Article_Id'::regclass) primary key,
	Title varchar,
	Marks int,
	Content text NOT NULL,
	Browse int DEFAULT 0,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP,
	enable_Edit bool DEFAULT TRUE,
	Channel_Id int NOT NULL,
	Created_Account int NOT NULL,
	FOREIGN KEY(Created_Account) references Account(Id),
	FOREIGN KEY(Channel_Id) references Channel(Id)
);
create trigger T_Article before update on Article for each row execute procedure upd_timestamp();

--关注文章
create table Article_Mark(
	AID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(AID) references Article(Id),
	FOREIGN KEY(UID) references Account(Id)
);

--回复文章
create table Reply(
	Id int DEFAULT nextval('public.Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(Id),
	FOREIGN KEY(AID) references Article(Id)
);
create trigger T_Reply before update on Reply for each row execute procedure upd_timestamp();

--回复评论
create table Reply_2_Reply(
	Id int DEFAULT nextval('public.Reply_2_Reply_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Modify_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	RID int NOT NULL,
	UID int NOT NULL,
	AID int NOT NULL,
	FOREIGN KEY(UID) references Account(Id),
	FOREIGN KEY(RID) references Reply(Id),
	FOREIGN KEY(AID) references Article(Id)
);

--浏览记录
create table Browse_History(
	BrowseDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UID int NOT NULL,
	AID int NOT NULL,
	FOREIGN KEY(UID) references Account(Id),
	FOREIGN KEY(AID) references Article(Id)
);


--聊天组
create table Chat_Group(
	Id int DEFAULT nextval('public.Chat_Group_Id'::regclass) primary key,
	Group_Account_Type int NOT NULL,
	GName varchar NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Introduction varchar NOT NULL,
	AvatorUrl varchar NOT NULL,
	Created_Account int NOT NULL,
	Chat_Type int NOT NULL DEFAULT 0,--0:私聊  1:群聊
	FOREIGN KEY(Created_Account) references Account(Id)
);

--单条聊天内容
create table Chat_Content(
	Id int DEFAULT nextval('public.Chat_Content_Id'::regclass) primary key,
	Content text NOT NULL,
	Create_Date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	Chat_Group_Id int NOT NULL,
	Created_Account int NOT NULL,
	FOREIGN KEY(Created_Account) references Account(Id),
	FOREIGN KEY(Chat_Group_Id) references Chat_Group(Id)
);
