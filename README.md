# OOPDProject

USER PROFILE DETAILS
People must register if they want to become users of this application. Any person may register and the application is free of charge to use. SmartHealth anticipate having about a million users, but need to be able to expand beyond that if the website becomes a very popular. A user must supply two working email addresses, and must devise a username that is unique within the SmartHealth system. At login they authenticate using their primary email address and a password. Usernames are used for display purposes. About each user we keep:
their username (a string, unique to our system, which is no longer than 20 characters), 
email addresses, 
their real first and last name (for our records, not for public display), 
their postal address (for our records, not for public display), 
a paragraph of “about me” text, and 
the URLs of three profile photos. 
Each user also has a score called their “karma”, which builds as they contribute quality content to the system.
Each user has a “user type”: one at a given time, though it may change over time. New users are created with type “new”; after a month of regular use they become “middle”; after a year, “old”. Professional doctors will be employed to moderate the site: these have user type “mod”. There are also some technical/managerial users who have type “admin”. The system needs to keep track of a user’s type, so that users of different types can be presented with different software features.
Administrators and moderators do not have “karma”; but users of these two types have an emergency contact phone number stored, while moderators also have their professional qualifications stored. We need to keep the qualifications we will recognize – these can be short strings like “M.D.” and “Ph.D.” – and some way to store which person has which qualifications. Note that moderators may have more than one qualification, and that the set of qualifications we recognize is likely to change over time.
Users may choose to quit using the site at any time. However if a user quits, we don’t want to delete them from the system – we simply want to mark them as having quit and make them invisible to other users.

NOTE: This is module 1 bro and I guess we need to do all these things bro
