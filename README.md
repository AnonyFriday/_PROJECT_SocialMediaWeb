# PRJ Project

This is an open source social media platform based on Java Servlet.

## Team name
LIT

## Team members
Vũ Kim Duy - SE182407
Lê Quang Khánh - SE182420
Võ Nguyên Minh Nhật - SE182355
Nguyễn Trung Hưng - SE182394

# Requirements

## Overview
**LoveIt**
Social media platform to find lovers or friends. Only for 18 and up users.

## Features
### Account setup
 - Login
 - Register
 - Oauth Login (Using Google)
 - Setup personal info such as name, age, email, location, gender, preferred gender.

### Welcome page (for new users)
If user is already login, go to homepage.

First time users will setup personal relationship preference, 
then homepage will show users/posts with selected preference (unregistered user will not be able to love posts or comments).

### Homepage/Find Lover/Feed
- Scroll through posts of people looking for relationship.
- Users can make posts. Original posters can delete and modify their posts.
- Ability to filter posts/users by gender, location.
- "Love" button to save post for later
- Comments (1 layer nested comment, each new comment can reply to one parent)

TODO: Clearly define what a feed is? How to determine what post will the users see?

### Chat (Optional)
- Each user is able to direct message others.
- Message is updated in real time.

### Profile
- Show personal info (Name, age, gender, preference, avatar)
- If the user is owner of profile, they will have ability to change gender or love preference (not email or name due to impersonations).

### Favorites (Loved posts)
- List of favourite post

### Admin page
- See reported posts/users.
- Ability to delete, modify posts, delete users.
- Dashboard to see analytics of website content.
