# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

# Process Book

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
* Design choice: ??
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
* Cancel on title page (by the way menupage is now named titlepage)
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
* Items list is now an arraylist again (allowing duplicates)
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
* Improve insufficient magic view for spell list in combat
* Fix moving equippable items in parcel and shared preference storage
* Add check if name already exists when creating/renaming character
* Fixed nasty bug with loading player that has equipped item (required temp storage and two methods)
* Design problem: keeping stack small

Day 13:

Day 14:

Day 15:

Day 16:

Day 17:

Day 18:

Day 19:

Day 20: