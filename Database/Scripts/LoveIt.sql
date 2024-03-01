?--===================================
--          DATABASE CREATION
--===================================
CREATE DATABASE LoveIt;
USE LoveIt;


--===================================
--          TABLE CREATION
--===================================

--GENDER TABLE
CREATE TABLE Gender (
	Id NUMERIC(19,0) IDENTITY(1,1) PRIMARY KEY,
	[Name] NVARCHAR(50)
);

--USER TABLE
CREATE TABLE [User] (
	Id NUMERIC(19,0) IDENTITY(1,1) PRIMARY KEY,
	Fullname NVARCHAR(50),
	Nickname NVARCHAR(50),
	Email NVARCHAR(100),
	[Password] NVARCHAR(64),
	Age NUMERIC(19,0),
	[Status] NVARCHAR(50),
	[Role] VARCHAR(50),
	Gender_Id NUMERIC(19,0),
	Preference_Id NUMERIC(19,0),
	Created_At DATETIME,
	Image_Url VARCHAR(200),
	FOREIGN KEY (Gender_Id) REFERENCES Gender(Id),
	FOREIGN KEY (Preference_Id) REFERENCES Gender(Id),
);

--POST TABLE
CREATE TABLE Post (
	Id NUMERIC(19,0) IDENTITY(1,1) PRIMARY KEY,
	[User_Id] NUMERIC(19,0),
	Content NVARCHAR(4000),
	Created_At DATETIME,
	Hearts_Total NUMERIC(19,0),
	Comment_Total NUMERIC(19,0),
	[Status] NVARCHAR(50),
	Image_Url VARCHAR(200),
	FOREIGN KEY ([User_Id]) REFERENCES [User](Id),
);

--COMMENT TABLE
CREATE TABLE Comment (
	Id NUMERIC(19,0) IDENTITY(1,1) PRIMARY KEY,
	[Post_Id] NUMERIC(19,0),
	[User_Id] NUMERIC(19,0),
	Content NVARCHAR(2000),
	Created_At DATETIME,
	[Status] NVARCHAR(50),
	Reply_Id NUMERIC(19,0),
	FOREIGN KEY ([User_Id]) REFERENCES [User](Id),
	FOREIGN KEY ([Post_Id]) REFERENCES [Post](Id),
	FOREIGN KEY (Reply_Id) REFERENCES Comment(Id)
);

--FAVORITE TABLE
CREATE TABLE Favorite (
	[Post_Id] NUMERIC(19,0),
	[User_Id] NUMERIC(19,0),
	Created_At DATETIME,
	FOREIGN KEY ([User_Id]) REFERENCES [User](Id),
	FOREIGN KEY ([Post_Id]) REFERENCES [Post](Id),
	PRIMARY KEY([User_Id], Post_Id)
);





--===================================
--          SEED DATA
--===================================

--GENDERS
INSERT INTO Gender ([Name]) 
VALUES 
('Male'),
('Female'),
('Non-binary'),
('Genderqueer'),
('Agender'),
('Bigender'),
('Genderfluid'),
('Demiboy');

--USERS
INSERT INTO [User] (Fullname, Nickname, Email, [Password], Age, [Status], [Role], Gender_Id, Preference_Id, Created_At, Image_Url)
VALUES 
('Alice Johnson', 'Ali', 'alice@example.com', 'password123', 30, 'Active', 'User', 1, 2, '2024-02-13 08:00:00', 'http://example.com/alice.jpg'),
('Bob Smith', 'Bob', 'bob@example.com', 'secure456', 25, 'Active', 'User', 2, 4, '2024-02-13 08:15:00', 'http://example.com/bob.jpg'),
('Charlie Brown', 'Chuck', 'charlie@example.com', 'pass123word', 35, 'Active', 'User', 3, 2, '2024-02-13 08:30:00', 'http://example.com/charlie.jpg'),
('Diana Martinez', 'Di', 'diana@example.com', '123456', 28, 'Active', 'User', 6, 8, '2024-02-13 08:45:00', 'http://example.com/diana.jpg'),
('Ethan Wilson', 'E', 'ethan@example.com', 'qwerty789', 33, 'Active', 'User', 7, 5, '2024-02-13 09:00:00', 'http://example.com/ethan.jpg');

--POSTS
INSERT INTO Post ([User_Id], Content, Created_At, Hearts_Total, Comment_Total, [Status], Image_Url)
VALUES
(1, 'Excited to start my new journey! ?? #newbeginnings', '2024-02-13 10:00:00', 25, 10, 'Active', 'http://example.com/post1.jpg'),
(2, 'Enjoying a relaxing Sunday afternoon ???? #weekendvibes', '2024-02-13 10:15:00', 30, 15, 'Active', 'http://example.com/post2.jpg'),
(3, 'Feeling grateful for all the love and support! ?? #gratitude', '2024-02-13 10:30:00', 20, 8, 'Active', 'http://example.com/post3.jpg'),
(4, 'Exploring new places and making memories ????? #adventure', '2024-02-13 10:45:00', 35, 12, 'Active', 'http://example.com/post4.jpg'),
(5, 'Challenging myself to learn something new every day ???? #growthmindset', '2024-02-13 11:00:00', 28, 10, 'Active', 'http://example.com/post5.jpg'),
(1, 'Missing the sunny days at the beach! ??? #throwback', '2024-02-13 11:15:00', 40, 18, 'Active', 'http://example.com/post6.jpg'),
(2, 'Cooking up a storm in the kitchen tonight! ???? #chefmode', '2024-02-13 11:30:00', 22, 9, 'Active', 'http://example.com/post7.jpg'),
(3, 'Embracing each moment with positivity and joy! ??? #positivity', '2024-02-13 11:45:00', 31, 14, 'Active', 'http://example.com/post8.jpg'),
(4, 'Dreaming of future travels and adventures! ???? #wanderlust', '2024-02-13 12:00:00', 26, 11, 'Active', 'http://example.com/post9.jpg'),
(5, 'Pushing myself out of my comfort zone to grow stronger! ???? #growth', '2024-02-13 12:15:00', 29, 13, 'Active', 'http://example.com/post10.jpg'),
(1, 'Excited for the weekend ahead! ???? #weekendvibes', '2024-02-13 12:30:00', 32, 16, 'Active', 'http://example.com/post11.jpg'),
(2, 'Reflecting on the past and looking forward to the future! ???? #reflection', '2024-02-13 12:45:00', 27, 12, 'Active', 'http://example.com/post12.jpg'),
(3, 'Enjoying a cup of coffee and a good book this afternoon! ??? #relaxation', '2024-02-13 13:00:00', 23, 10, 'Active', 'http://example.com/post13.jpg'),
(4, 'Feeling inspired by nature''s beauty all around me! ???? #naturelover', '2024-02-13 13:15:00', 36, 15, 'Active', 'http://example.com/post14.jpg'),
(5, 'Practicing mindfulness and gratitude every day ???? #mindfulness', '2024-02-13 13:30:00', 33, 14, 'Active', 'http://example.com/post15.jpg'),
(1, 'Exploring new hobbies and interests to expand my horizons! ???? #hobbies', '2024-02-13 13:45:00', 25, 11, 'Active', 'http://example.com/post16.jpg'),
(2, 'Feeling motivated to crush my goals this week! ???? #motivation', '2024-02-13 14:00:00', 29, 13, 'Active', 'http://example.com/post17.jpg'),
(3, 'Grateful for the little moments that bring joy to my day! ???? #gratitude', '2024-02-13 14:15:00', 28, 12, 'Active', 'http://example.com/post18.jpg'),
(4, 'Spending quality time with loved ones is priceless! ????????????? #familytime', '2024-02-13 14:30:00', 31, 15, 'Active', 'http://example.com/post19.jpg'),
(5, 'Planning my next adventure and counting down the days! ????? #travelplans', '2024-02-13 14:45:00', 30, 14, 'Active', 'http://example.com/post20.jpg');

--COMMENTS
INSERT INTO Comment ([Post_Id], [User_Id], Content, Created_At, [Status], Reply_Id)
VALUES 
(1, 2, 'Congratulations on your new journey! ??', '2024-02-13 10:05:00', 'Active', NULL),
(1, 3, 'Wishing you all the best! ??', '2024-02-13 10:10:00', 'Active', 1),
(1, 4, 'You got this! ??', '2024-02-13 10:20:00', 'Active', NULL),
(1, 5, 'Good luck on your new adventure! ??', '2024-02-13 10:25:00', 'Active', 2),
(1, 1, 'You''re going to do amazing! ??', '2024-02-13 10:30:00', 'Active', 3),
(1, 2, 'Keep us posted on how it goes! ??', '2024-02-13 10:35:00', 'Active', NULL),
(1, 3, 'We believe in you! ??', '2024-02-13 10:40:00', 'Active', 5),
(1, 4, 'You''re unstoppable! ??', '2024-02-13 10:45:00', 'Active', 6),
(2, 1, 'Looks like a perfect day! Enjoy ??', '2024-02-13 10:25:00', 'Active', NULL),
(4, 3, 'Where are you exploring next? ???', '2024-02-13 10:50:00', 'Active', NULL),
(6, 5, 'Can''t wait for more beach days! ???', '2024-02-13 11:20:00', 'Active', NULL),
(7, 2, 'Save me a plate! ?????', '2024-02-13 11:35:00', 'Active', NULL),
(8, 4, 'Your positivity is inspiring! ???', '2024-02-13 11:50:00', 'Active', NULL),
(9, 1, 'Let me join your travel plans! ????', '2024-02-13 12:05:00', 'Active', NULL),
(10, 3, 'You are doing amazing things! ????', '2024-02-13 12:20:00', 'Active', NULL),
(5, 2, 'Amazing shot! ????', '2024-02-13 12:35:00', 'Active', NULL),
(3, 4, 'Keep pushing forward! ??', '2024-02-13 12:50:00', 'Active', NULL),
(6, 3, 'Beach vibes all the way! ????', '2024-02-13 13:05:00', 'Active', NULL),
(7, 1, 'Count me in for the next feast! ????', '2024-02-13 13:20:00', 'Active', 9),
(9, 5, 'Let''s explore together sometime! ????', '2024-02-13 13:35:00', 'Active', NULL),
(4, 2, 'Is that your favorite spot? ???', '2024-02-13 13:50:00', 'Active', NULL),
(8, 3, 'Your journey is so inspiring! ????', '2024-02-13 14:05:00', 'Active', NULL),
(10, 1, 'You''re making a difference! ????', '2024-02-13 14:20:00', 'Active', NULL),
(2, 4, 'Adventure awaits! ???', '2024-02-13 14:35:00', 'Active', 4),
(5, 3, 'Love the sunset view! ????', '2024-02-13 14:50:00', 'Active', 11),
(11, 2, 'Magical winter wonderland! ???', '2024-02-13 20:05:00', 'Active', NULL),
(12, 3, 'Skiing adventures! ?????', '2024-02-13 20:20:00', 'Active', NULL),
(13, 4, 'Cozy by the fireplace! ?????', '2024-02-13 20:35:00', 'Active', NULL),
(14, 5, 'Hot cocoa time! ????', '2024-02-13 20:50:00', 'Active', NULL),
(15, 1, 'Historical marvels! ?????', '2024-02-13 21:05:00', 'Active', NULL),
(16, 2, 'Stargazing adventures! ????', '2024-02-13 21:20:00', 'Active', NULL),
(17, 3, 'Blossoming beauty! ????', '2024-02-13 21:35:00', 'Active', NULL),
(18, 4, 'Creative expression! ????', '2024-02-13 21:50:00', 'Active', NULL),
(19, 5, 'Dreams under the stars! ???', '2024-02-13 22:05:00', 'Active', NULL),
(20, 1, 'Relaxing by the shore! ?????', '2024-02-13 22:20:00', 'Active', NULL),
(11, 3, 'Icy adventures! ????', '2024-02-13 22:35:00', 'Active', NULL),
(12, 4, 'Snowball fights! ?????', '2024-02-13 22:50:00', 'Active', NULL),
(13, 5, 'Winter coziness! ????', '2024-02-13 23:05:00', 'Active', NULL),
(14, 1, 'Fireplace warmth! ?????', '2024-02-13 23:20:00', 'Active', NULL),
(15, 2, 'Architectural wonders! ????', '2024-02-13 23:35:00', 'Active', NULL),
(16, 3, 'Cosmic revelations! ????', '2024-02-13 23:50:00', 'Active', NULL),
(17, 4, 'Floral enchantment! ????', '2024-02-14 00:05:00', 'Active', NULL),
(18, 5, 'Artistic expressions! ???', '2024-02-14 00:20:00', 'Active', NULL),
(19, 1, 'Starry dreams! ????', '2024-02-14 00:35:00', 'Active', NULL),
(20, 2, 'Seashore serenity! ?????', '2024-02-14 00:50:00', 'Active', NULL),
(11, 1, 'Love the snowy vibes! ????', '2024-02-14 01:05:00', 'Active', NULL),
(12, 3, 'I miss skiing in the mountains! ?????', '2024-02-14 01:20:00', 'Active', NULL),
(13, 4, 'Fireplace and hot cocoa, the perfect combo! ??????', '2024-02-14 01:35:00', 'Active', NULL),
(13, 2, 'Absolutely cozy! Can I join you by the fireplace? ???????', '2024-02-14 01:40:00', 'Active', 6),
(14, 1, 'Hot cocoa is my go-to winter drink! ??????', '2024-02-14 01:45:00', 'Active', NULL),
(15, 3, 'Exploring historical sites is always fascinating! ??????', '2024-02-14 01:55:00', 'Active', NULL),
(16, 5, 'Stargazing under clear skies is magical! ?????', '2024-02-14 02:05:00', 'Active', NULL),
(16, 1, 'I agree! The universe never fails to awe us! ??????', '2024-02-14 02:10:00', 'Active', NULL),
(17, 2, 'The beauty of nature in bloom is mesmerizing! ??????', '2024-02-14 02:15:00', 'Active', NULL),
(17, 3, 'Nature truly is an artist! Let''s appreciate it together! ??????', '2024-02-14 02:20:00', 'Active', 14),
(18, 4, 'Express yourself through art! It''s liberating! ??????', '2024-02-14 02:25:00', 'Active', NULL),
(18, 5, 'Art is life! Let''s create art together! ??????', '2024-02-14 02:30:00', 'Active', 16),
(19, 1, 'Dreaming under the stars is so peaceful! ??????', '2024-02-14 02:35:00', 'Active', NULL),
(19, 2, 'Let''s have a stargazing night soon! Count me in! ??????', '2024-02-14 02:40:00', 'Active', 18),
(20, 3, 'The seashore brings such tranquility! ???????', '2024-02-14 02:45:00', 'Active', NULL),
(20, 4, 'I could listen to the waves all day! ??????', '2024-02-14 02:50:00', 'Active', NULL);

--FAVORITES
INSERT INTO Favorite ([Post_Id], [User_Id], Created_At)
VALUES
(1, 2, '2024-02-15 08:00:00'),
(2, 3, '2024-02-16 10:15:00'),
(3, 4, '2024-02-17 12:30:00'),
(4, 5, '2024-02-18 14:45:00'),
(5, 1, '2024-02-19 17:00:00'),
(6, 2, '2024-02-20 19:15:00'),
(7, 3, '2024-02-21 21:30:00'),
(8, 4, '2024-02-22 23:45:00'),
(9, 5, '2024-02-23 01:00:00'),
(10, 1, '2024-02-24 03:15:00'),
(1, 3, '2024-02-25 05:30:00'),
(2, 4, '2024-02-26 07:45:00'),
(3, 5, '2024-02-27 09:00:00'),
(4, 1, '2024-02-28 11:15:00'),
(5, 2, '2024-02-29 13:30:00'),
(6, 3, '2024-03-01 15:45:00'),
(7, 4, '2024-03-02 18:00:00'),
(8, 5, '2024-03-03 20:15:00'),
(9, 1, '2024-03-04 22:30:00'),
(10, 2, '2024-03-05 23:59:59'),
(12, 4, '2024-03-07 03:15:00'),
(13, 5, '2024-03-08 05:30:00'),
(15, 2, '2024-03-10 09:00:00'),
(11, 3, '2024-03-16 22:30:00'),
(14, 1, '2024-03-19 03:15:00'),
(16, 3, '2024-03-21 07:45:00'),
(17, 4, '2024-03-22 09:00:00'),
(18, 5, '2024-03-23 11:15:00'),
(19, 1, '2024-03-24 13:30:00'),
(20, 2, '2024-03-25 15:45:00');


--===================================
--            RESET SEEDS
--===================================

delete from Favorite
delete from Comment
delete from Post
delete from [User]
delete from Gender

DBCC CHECKIDENT ('Gender', RESEED, 0);
DBCC CHECKIDENT ('User', RESEED, 0);
DBCC CHECKIDENT ('Comment', RESEED, 0);
DBCC CHECKIDENT ('Post', RESEED, 0);
DBCC CHECKIDENT ('Favorite', RESEED, 0);


--===================================
--            SCRIPTS
--===================================

--GET GENDERS
SELECT * FROM Gender



--GET GENDER BY ID
SELECT [Name]
FROM Gender



--GET USER BY CREDENTIALS
SELECT Id, Fullname, Nickname, Email, Age, [Status], [Role], Gender_Id, Preference_Id
FROM [User]
WHERE Email = 'alice@example.com' AND [Password] = 'password123'



--GET USER BY ID
SELECT Id, Fullname, Nickname, Email, Age, [Status], [Role], Gender_Id, Preference_Id
FROM [User]
WHERE Id = 1



--GET POSTS
DECLARE @PageSize INT = 5;
DECLARE @LastId NUMERIC(19,0) = NULL;
DECLARE @Search NVARCHAR(200) = NULL;
DECLARE @Age NUMERIC(19,0) = NULL;
DECLARE @Gender_Id NVARCHAR(50) = NULL;

SELECT TOP (@PageSize) p.Id, p.[User_Id], p.Content, p.Created_At, p.Hearts_Total, p.Comment_Total, p.[Status], p.Image_Url		--THE NUMBER OF POSTS THAT WILL BE RETRIEVED IN A PAGE
FROM Post p
JOIN [User] u ON p.[User_Id] = u.Id
WHERE 
	(
		(@Gender_Id IS NULL OR u.Preference_Id = @Gender_Id) AND
		(@Age IS NULL OR (u.Age >= @Age - 5  OR u.Age <= @Age + 5)) AND				--FILTERING THE POSTS
		(@Search IS NULL OR Content LIKE CONCAT('%', @Search, '%'))
	) 
	AND (@LastId IS NULL OR p.Id < @LastId)											--THIS WILL BE THE ID OF THE LAST RETRIEVED POST ON THE PREVIOUS PAGE, IF LAST ID IS NULL THEN IT'S THE FIRST PAGE
ORDER BY p.Id DESC



--GET FAVORITE POSTS
DECLARE @PageSizeFav INT = 5;
DECLARE @User_Id NUMERIC(19,0) = 1;
DECLARE @SearchFav NVARCHAR(200) = NULL;
DECLARE @Created_At DATETIME = NULL;

SELECT TOP (@PageSizeFav) p.Id, p.[User_Id], p.Content, p.Created_At, p.Hearts_Total, p.Comment_Total, p.[Status], p.Image_Url
FROM Favorite f
JOIN Post p ON f.Post_Id = p.Id
WHERE
	(f.[User_Id] = @User_Id) AND												--GET FAVORITE POSTS BASED ON USER ID
	(@SearchFav IS NULL OR Content LIKE CONCAT('%', @SearchFav, '%')) AND
	(@Created_At IS NULL OR f.Created_At < @Created_At)
ORDER BY f.Created_At DESC


--GET COMMENTS BY POST ID
DECLARE @PageSizeCmt INT = 5;
DECLARE @Post_Id NUMERIC(19,0) = 1;
DECLARE @LastCmt_Id NUMERIC(19,0) = NULL;

SELECT TOP (@PageSizeCmt) *
FROM Comment
WHERE (Post_Id = @Post_Id) AND
	  (@LastCmt_Id IS NULL OR Id < @LastCmt_Id)
ORDER BY Id DESC


--GET REPLIES BY REPLY ID
DECLARE @ParentCmtId NUMERIC(19,0) = 11;

SELECT r.Id, r.Post_Id, r.[User_Id], r.Content, r.Created_At, r.[Status]
FROM Comment c
INNER JOIN Comment r ON c.Id = r.Reply_Id
WHERE c.Id = @ParentCmtId



select *
from Gender
