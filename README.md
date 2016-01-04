# Programmeer Project

The goal of this project is to create an app that can do something.

two ideas;

epub reader--
* read epub files
* quick load
* big scrolling view, save position in the scrollpane
* change colors
* change font
* set bookmarks

learn:
* find out how to read epub
* find out how to scroll very large content
* replace font to new file or temporary in memory

solves:
-reading on mobile. mostly slow apps exist right now

![](mainpage.png) ![](readpage.png)

dataset and datasource:
* source is epub file. dataset would be one very large string?
* alternative is lots of strings - paragraphs. in htm book files there is paragraphs
	so might be with epubs just like this. this really depends on how epub works
* reading time if used can be stored locally with the android things (look up name)
* progress through book, perhaps using position of scrollview / total scrollview length?
* maybe rate books 10/10

decomposition:
* menu (choose epub file, maybe options)
* reading screen (read the epub file, scroll down and up, maybe go to options?)
* statistics maybe (keep track of time spent reading?)

platform:
* scrolling is nice
* some way of making it fast load may be necessary
* idea is reading on mobile so platform mobile is exactly what it is for
* but android studio language may have issue with large book files ?

problems:
* have to find out how epub works
* if fast load is easy why is it not everywhere. probably not easy
* if scrollview of entire book, then working with very large data.

similar apps:
* s2reader - simple menu that shows percentage progress into books in some folder.
	loading book takes very long however. is organized into chapters, and every new
	chapter loaded takes several seconds. the reading is all doen by scrolling
	



	
platform maker--
-simple platformer mechanics
-place collision boxes in some editor
-save creations
-load creations and try them out

learn:
-collision detection
-touch movement or with touch buttons


![](doc/test.png)