# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

# Changelog

Day 1:
* Make up idea for app, write proposal

Day 2:
* Discover first idea was not good
* Make up different idea for app, write new proposal
* Design problem: What classes to make (how to structure project)
* Design choice: Game class works on its own

Day 3:
* Start towards prototype
* Design problem: How to handle turns, rounds, moves
* Design choice: During every round one by one all Combatants have a move, sorted by speed value

Day 4:
* Write design doc
* Re-write some code based on design doc

Day 5:
* Prepare prototype
* Get new ideas during presentation - not all moves in round at once. One "move" handled per button touch.
* Add two new skill values

Day 6:
* Streamline interaction between Game and PlayPage
* Design problem: How to handle "log messages"
* Design choice: Only one message in Game, PlayPage has Queue (LinkedList)
* Design problem: How to handle player character creation and "leveling"
* Design choice: Points can be spent to improve skills at creation and after combat

Day 7:
* Design problem: How to handle "database" of Moves, Foes, Items, etc.
* Design choice: ?? (see day 10 for solution)
* Implemented Parcelable for PlayerCharacter, added new creation mechanics to main page
* Creation now persists to new activity, added dialog to name player character
* Design problem: How to store player character (SharedPreferences) ?
* Design choice: Bundle or serializable or ComplexPrefs?

Day 8:
* Implemented adapter
* Implemented loading character from stored characters
* Resize character creation buttons and views (big layout overhaul as a whole)
* Add player character leveling layout to shop page
* Design choice for storing player character: ComplexPreferences
* Implement ComplexPreferences 

Day 9:
* Add leaderboard list as well as removing from storage
* Start adding true game mechanics, including stat modifier and distance modifier
* Better game log (can handle more strings), and PlayPage interaction with it
* Auto name duplicate foes
* Design problem: Once again how to handle database of moves, etc

Day 10:
* Send character from play to shop
* Move adapter
* Prepare alpha
* Overhaul structure of Moves, Foes, etc.
* Design decision for ^ : everything is classes. Specific moves extend abstract Move
* Implemented using spells
* Start on layout overhaul for playpage and shoppage
* Cancel character on title page (by the way menupage is now named titlepage)
* Dynamic progress bar now uses xml layout colors
* Possibly temporary icon for player character
* Successful game end now awards player character with points and money
* Fail game end now brings player character to fail screen

Day 11:
* Fixed issues with duplicate spells
* Improved auto foe naming, and is done now in Foe (static)
* Spells and Items are now HashSets instead of ArrayList
* Fixed level points not being awarded
* Self spells now work correctly
* Implemented save progress after combat
* Overhauled items-> now are classes as well. Equippable and usable
* Usable items use a matching move
* SpellAdapter now has methods to check for player character sufficient magic
* TextViews that display if item or spell list are empty
* Dynamically implement ^ for spells
* Implement ^ for Items
* Usable Items list is now an arraylist again (to allow duplicates)
* Implement using UsableItems
* Implement restoring magic (with items), and restoring usability of spells
* Implement coloring the avatar
* Add new spell
* Improve display of health and magic in combat
* Implement restore health and magic buttons in shop
* Implement showing equipment in shop
* Start new fights from shop page

Day 12:
* Add notification for defending, and potential for foes to defend
* Add three new foes
* Fix bug with rename foes
* Fix wrong stats display on PlayPage
* Fix sold out message on shop
* Handle interaction between activities - now auto finishes activities as needed.
* Playerlist is updated in titlepage if changed in leaderboardpage
* Warning for when exiting shop page - can choose where to go
* Now when deleting in leaderboard page character selected on titlepage, cleared from titlepage
* ^ if titlepage has a new character or someone not deleted from leaderboard, they remain on titlepage
* Add better health/magic display for shoppage and titlepage
* Implement buying items, equipment and spells
* Improve insufficient magic view for spell list in combat (textview from day 11 for spell list view removed)
* Fix moving equippable items in parcel and shared preference storage
* Add check if name already exists when creating/renaming character
* Fixed nasty bug with loading player that has equipped item (required temp storage and two methods)
* Design problem: keeping stack small

Day 13:
* Add functionality to shop if already own sold equipment or know sold spell
* Hopefully fixed stack now (design choice: redirect to play via shop, always finish play. never more than 3 total activities on stack (4 with info))
* Fix correctly sending character back to shop from play
* fix already own gear check if don't own gear
* Add foe move pick ai
* Design problem: how to implement foes picking moves
* Design choice: each foe gets a list with indexes of moves. randomly picks from that list. list is updated as magic is used, removing access to moves that need magic.
* Improve move log in combat (now says what is being used)
* Fix on return to shop resupplied item being invisible
* Redo the log in combat, it is now scrollable and no longer has limit (all combat history is now viewable)
* Foes now can no longer cast spells if they don't have enough magic for it
* Fix when coming back from leaderboard to title and not have any character view

Day 14:
* Start implement random encounter generation
* Design problem: how to get random encounters
* Design choice: use a large switch that checks levels and adds monster ids to arraylist based on level, then selects from there
* Fix bug with not being able to spend level points after first time in shop
* Fix wrong color shown when previously had insufficient gold but on later visit have enough
* Fix wrong message with previously buyable but now sold out. also added new color for sold out
* Add equipment view to title page
* Shop now first checks already own message before insufficient gold message
* Fix bug where player could click next in shop before spending points, breaking game on new load
* Added some new items and spells
* Changed death screen click to LongClick
* Can now save if buying items or spell after already had saved
* Foe layout tweaks
* Start implement random shop buyables
* Design problem: how to get random buyables
* Design choice: similar to random encounters (but with if instead of switch). three lists, one for each type of buyable
* Add more foes
* Slightly change dynamic layout in buyable part of shop, name (now at top) now displays even with sold out/insufficient gold/already owned
* More layout tweaks

Day 15:
* Sort in leaderboard by level and in title screen by name
* Change display names of pages
* Add back button and back dialog to play page
* Added another foe (possible final boss? game does not end after beating him though, infinite playability!)
* Design problem: How to add info screen (fragment or regular activity?)
* Design choice: regular activity, since it is not interactive it can just be cancelled to return to wherever it was started from
* Add new info/help page
* Change menu layout for all pages, adding link to new info page (help page)

Day 16:
* Fix bug where spiderbat never show up
* Fix app name
* Change buyable item system- now 5 buyable
* Slightly change final boss stats
* Change some info page text
* Change folder structure (so it is now nl.mprog)
* Begin commenting
* Game class is now Combat class

Day 17:
* Finish commenting
* Add back press to title page (to cancel list load)
* Make actual PROCESS.md
* Start on new README.md
* Start REPORT.md

Day 18:
* Very minor commenting tweaks
* More REPORT.md

Day 19:
* Finish REPORT.md
* Tweak size of save and next buttons in shop
* Publish final version to GIT

Day 20:
* Final Presentation