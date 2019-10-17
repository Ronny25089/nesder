create table Group_Account(
	GID int primary key,
	Group_AccountType int NOT NULL,
	GName varchar NOT NULL,
	CreateDate Date,
	Introduction varchar NOT NULL,
	AvatorUrl varchar NOT NULL
);

create table Account(
	UID int primary key,
	AccountType int NOT NULL,
	UPassword varchar NOT NULL,
	UName varchar NOT NULL,
	Email varchar NOT NULL,
	ChannelNo int ,
	Gender varchar NOT NULL,
	CreateDate Date,
	Age int NOT NULL,
	Introduction varchar NOT NULL,
	AvatorUrl varchar NOT NULL,
	GID int,
	FOREIGN KEY(GID) references Group_Account(GID)
);

create table Module(
	MID int primary key,
	MName varchar NOT NULL,
	CreateDate Date NOT NULL,
	UID int NOT NULL,
	Contact varchar,
	FOREIGN KEY(UID) references Account(UID)
);

create table Channel(
	CID int primary key,
	Name int NOT NULL,
	CreatedBy int NOT NULL,
	MID int NOT NULL,
	FOREIGN KEY(MID) references Module(MID)
);

create table Fllow_Account(
	FllowedID int NOT NULL,
	FllowerID int NOT NULL,
	FOREIGN KEY(FllowerID) references Account(UID),
	FOREIGN KEY(FllowedID) references Account(UID)
);

create table Article(
	Title varchar,
	Marks int,
	Content varchar NOT NULL,
	Browse int DEFAULT 0,
	CreateDate Date,
	ModifyDate Date,
	Edit bool DEFAULT FALSE,
	AID int primary key,
	CID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(CID) references Channel(CID)
);

create table Article_Mark(
	AID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(AID) references Article(AID),
	FOREIGN KEY(UID) references Account(UID)
);

create table Reply(
	RID int primary key,
	Content varchar NOT NULL,
	CreateDate Date,
	ModifyDate Date,
	AID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(AID) references Article(AID)
);

create table Reply_2_Reply(
	Content varchar NOT NULL,
	CreateDate Date,
	ModifyDate Date,
	R2ID int primary key,	
	RID int NOT NULL,
	UID int NOT NULL,
	AID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(RID) references Reply(RID),
	FOREIGN KEY(AID) references Article(AID)
);

create table Browse_History(
	BrowseDate Date,
	UID int NOT NULL,
	AID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(AID) references Article(AID)
);

create table Dialogue(
	DID int primary key,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID)
);

create table Dialogue_Detail(
	DID int NOT NULL,
	Content varchar NOT NULL,
	CreateDate Date,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(DID) references Dialogue(DID)
);

create table Group_Account_Dialogue(
	DID int NOT NULL,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID),
	FOREIGN KEY(DID) references Dialogue(DID)
);

create table Authority(
	Token String primary key,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID)
);

create table Reset_Password(
	Rest String primary key,
	UID int NOT NULL,
	FOREIGN KEY(UID) references Account(UID)
);