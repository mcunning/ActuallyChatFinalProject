ACTUALLYCHAT -- Final Project
============


Overview
=========
ACTUALLY CHAT is a clone of the Assignment 3 chat bot that can carry a conversation with a real person while simulating a first date conversation. 
The ChatBot is a bit self-absorbed. If you try to tell the ChatBot about yourself, it will generally lead the conversation back to itself. 
If you are able to get the ChatBot to ask you about yourself, it will ask you your zodiac sign and will tell you some traits based on your specific sign. 


How to Compile and Run the Code
=========
All files in repository must be downloaded (including all jar files) and then ran from main.java.chat.Main.java.
The console window that opens up can then be used to interact with the chatBot.

New Implemented Features
=======================

Facebook API
------------
With the Facebook API implemented, users can now get information about different people on Facebook from the chatBot.
The user can bring up Facebook to the chatBot, which will then ask the user who they would like to search.
After the user enters the username of the person they are searching for, the chatBot will ask the user what they would like to know about the specified person.
The user can then choose from first name, last name, full name, facebook id, and gender.
The chatBot will then return the information back to the user. 

Example:
*I'd like to know about somebody on facebook*
Who do you want to search? (By username)
*mcunning*
What would you like to know about this person? (First name, last name, full name, gender, id)
*full name*
Maddie Cunning is that person's full name.


Yahoo! Answers API
-----------------
With the Yahoo! Answers API implemented, users can now get answers to their basic queries from Yahoo! Answers.
The user must first ask the chatBot to answer something for them. 
The chatBot will then ask the user what they would like to know about.
The user can then enter a basic query.
The chatBot will then find a question similar to the users query and output the chosen answer.

Example:
*I'm looking for an answer to something*
What would you like to know about?
*how to cook chicken*
I just did this five minutes ago.  Take a patrially defrosted boneless skinless chicken breast and slice it in 1/4 inch pieces... (*and so on*)


Class Organization
=========

main.java.chat
--------------

ActuallyChat.java
*Initializes responder that reads the configuration file.

ActuallyResponder.java
*Reads configuration file for keywords.
*Differentiates between keywords being a question/statement as well as if the entire input consists of/ends with/starts with the keyword/key phrase.
*If user inputs the keyword "facebook" a scanner will be used to get user input and will use the Facebook API to output information to the user.
*If user inputs the keyword "answer" a scanner will be used to scan the user query and use the Yahoo! Answers API to output information to the user based on their query.
*Generates a random response if user input is outside of topic.

Chat.java
*Get the sentence from the user return the String of the sentence.

Main.java
*Initializes and runs chat.

Responder.java
*Reads the configuration file from the system.

main.java.chat.apis
-------------------

ActuallyFacebook.java
*Facebook API
*Method to get and return first name of a specified Facebook user
*Method to get and return last name of a specified Facebook user
*Method to get and return Facebook ID of a specified Facebook user
*Method to get and return gender of a specified Facebook user
*Method to get and return full name of a specified Facebook user

ActuallyYahoo.java
*Yahoo! Answers API
*Method that uses the user query to find a similar question on Yahoo! Answers and output the chosen answer from that question


main.java.chat.components
-------------------------

Keyword.java
*Creates specifics to differentiate between keywords.

Response.java
*Looks for response based on keywords.

POS.java (Not fully functional)
*Reads the user input, splits the sentence and analyzes each individual word, and categorizes them as their specific sentence parts (verb, noun, etc.)


main.java.chat.util
-------------------

ConfigReader.java
*Reads the configuration file containing all of the keywords and responses.

Util.java
*Checks if a given string starts with specific input and chooses a random element from a string array to return if not.


main.resources
---------------

config.chat
*Holds all keywords and responses


Features from A3
----------------
An extra topic has been implemented.
-Improves the conversation for when the user gets tired of asking the chatBot about itself.
-If the user hints at the chatBot asking them a question, chatBot will ask the user their zodiac sign
and give the user personality characteristics depending on their specified sign. 
-Example: (Words in italics are the chatBot)

Shouldn't you ask me something about myself..?
*What's your sign?*
I'm a leo
*Oh, a Leo! That means that you are loyal and generous!*


5 different responses when user input is outside of topic.
-If the user inputs anything that is outside of the chatBot's topic or does not trip any keywords
the chatBot will output one of five different responses at random. 
-The different responses were chosen to emphasize the chatBot's self centered persona and will lead the user back to asking the chatBot questions.
-Example:

What kind of food do you like?
*I like Italian food, what do you like?* 
I like steak.
*Let's talk about me some more.*
But you asked what I like...
*That doesn't interest me.*

POS Tagging (not fully functional)
-Uses OpenNLP jar files
-Reads the user input, splits the sentence and analyzes each individual word, and categorizes them as their specific sentence parts (verb, noun, etc.)
-(Should) Check if user input was outside of topic, have a predetermined output that will put the users noun and/or verb into the sentence
ie. I like (noun).
*I love (noun)!*

Limitations
-----------
-ChatBot does not know about a lot of specific topics and will therefore resort back to the randomized responses a lot. 
-Example:
What is your favorite color?
*I'd rather not talk about that.*
It's sunny out today
*Can we talk about something else?*
I can touch my toes
*Let's talk about me some more.*
