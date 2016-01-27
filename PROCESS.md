# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

# Process Book

#### Day 1:
Came up with idea for epub reader application. Wrote proposal. Discovered it was not a good idea for this project.

#### Day 2:
Came up with idea for turn based combat game based on old school rpg games. Wrote proposal. Started working on some layout files.

Biggest issue today was thinking of how to structure the app. What classes are needed, which ones interact with which. Today I decided that the Game class would work independently from whichever Activity would interact with it.

#### Day 3:
Continued the layout files and started on some actual code.

Biggest issue was how to handle turns, rounds and moves in the turn based combat. Came up with this idea: All Combatants have one move per round. Who goes first depends on their *speed* stat. Moves lower health of whoever is targeted by move.

#### Day 4:
Wrote the design document, with how all classes should interact with each other. The MenuPage leads to a PlayPage, which creates a ShopPage, that can create a PlayPage, etc. A Game object is created by the PlayPage, which interacts with it.

Rewrote some code to match the new design. Biggest issue was how to implement lists. Combatants have lists of moves, and the PlayerCharacter also has a list of items. The adapters for the ListViews interact with these lists.

#### Day 5:
Prepared the prototype, by adding basic combat functionality, where opponent can attack the player and the player can attack back. At the end of combat the ShopPage is opened.

During the presentation I thought of adding two more skills for the combatants - *willpower* and *resistance*, which affect spells (*strength* and *defense* affect regular attacks). This adds a new dimension to the game, in that a user needs to balance their skills to be able to deal with both types of attacks, hopefully making the game much more interesting.

#### Day 6:
I worked on streamlining the interaction between the Game and the PlayPage.

Two big issues today: How to handle "log messages". This is the text that appears at the bottom of the PlayPage to tell the user what is happening during combat. I made two queues (LinkedLists), one in Game and one in PlayPage, and the PlayPage pops message from Game, which it adds to its own LinkedList and displays them. After surpassing a threshold, it removes messages from its own LinkedList, in order to not make the log too long.

The other issue was how to handle the player character creation and leveling up. I added a level point system where "points" are spent to level up one of the seven skills (the five regular skills, as well as health and magic). Points are awarded at character creation as well as after winning combat.

#### Day 7:
I implemented the Parcelable system for the PlayerCharacter class in order to be able to pass the character between Activities. I also improved the character creation process, and ran in to two problems:

The first problem was how to handle the "database" of Moves, Foes and items. There should be different kinds, predefined, that hopefully scale with the character's level. But I had not thought of how to implement this "database".

The other problem was how to store the PlayerCharacter. It could be in a database, or it could be turned into a bundle, or made serializable, and stored in Shared Preferences. I also found a library called ComplexPreferences that can store objects using JSON.

I did not find a solution for these problems today, choosing to implement temporary hardcoded systems instead in order to be able to continue to work on other things.

#### Day 8:
I Implemented the loading and saving system for PlayerCharacters (and a matching adapter for a ListView from which they can be selected). I added the leveling system to the ShopPage. I also decided on a solution for the storing PlayerCharacter problem, choosing to implement ComplexPreferences.

The biggest issue was implementing this ComplexPreferences library. There is very little documentation on it, aside from the Git repository from which it can be downloaded. Installations instructions were also missing. Luckily, once it was implemented, it worked on the first try.

#### Day 9:
I added the LeaderboardPage where player characters can be deleted from SharedPreferences. I also began on adding the actual game mechanics (damage based on stats and distance). I improved the log system (more strings), and added a dynamic naming system for Foes.

Though it wasn't really that big of an issue, I had to think of the formula for damage. It became: Move damage + (attacker *strength*) - (defender *defense*). If the Move is a spell, *willpower* and *resistance* are used instead. If the defender is using the Defend move, their defensive stat is temporarily multiplied.

I also ran in the same problem of before of not knowing how to implement the database for Moves and Foes and Items. Still no solution today.

#### Day 10:
I worked on preparing for the Alpha version, implementing lots of small new features. For example, passing the player character to the ShopPage, implementing the use of spells in combat, adding dynamic progress bars instead of plain text, and rewards for winning combat (level points and money).

I also renamed MenuPage to TitlePage.

Most important of all, though, is that I settled on a solution for the problem of the "database" of Foes, Moves and Items, based on a suggestion of one of my project group mates. Foe, Move and Item are abstract classes, and every predefined type of one of these extends that subclass. For example, a Goblin is a type of Foe, that has its own predefined skill values.

#### Day 11:
Some bug-fixing today, and I changed how Spells and Items are stored in Combatants (instead of Lists, they are now Sets, because there are no duplicates). The app as a whole became more cohesive as I added the Save system to the ShopPage, and new combat can be started from there.

I added the solution from Day 10 to Items as well, but overhauled Items as a whole by dividing it into two types: EquippableItems (which provide passive bonuses to the skill of the player character), and UsableItems, which can be used by the player character during combat, and are essentially a Move that have no Magic cost (they also ignore any defense during damage calculation).

One of the UsableItems restores magic, so I had to change some mechanics of combat to allow this. This also meant I had to rewrite the usability of Spells - they can only be used while there is enough magic, after which they are removed. When they can be used again they need to be added again. I implemented this by putting the List of Moves into the Adapter, which sets the Moves to null if they cannot be cast, and restores them when they can.

#### Day 12:
I changed the text that the Game sends to the log, to account for new moves, as well as for when Combatants are defending (and added defending functionality to Foes).

More importantly, interaction between activities was improved (I implemented result and request codes, and onResult methods). For example, auto closing activities when the player character is dead. I added functionality for interaction between the TitlePage and the LeaderboardPage to account for deleting characters.

I ran into a very nasty bug today, where the ComplexPreferences no longer worked because the JSON could not load a character, even though it had no problem saving it. This came down to the fact that Equipment was an abstract class. My "database" solution had seemed great so far, because extending was easy and changing only had to be done in one file, yet here stood this big problem. I decided on a workaround, in order to keep my system intact, by temporarily storing the equipment as ints (their ID was stored), saving that instead, and then reverting to the actual items afterwards. If I had more time, it would have been nice to think of a less "hacky" solution, or to find yet another whole way of implementing my "databases". But for now, I settled on this.

But that was not all today! Another big issue that arose was that the app could theoretically create an infinite stack. I had created a nice system of interaction between activities, but since the PlayPage created a new ShopPage activity, which created new PlayPage activity, that meant that after prolonged use of the app, the Activity stack would become too large and the app would crash. I hadn't actually encountered this in practice, but I knew it was going to be a problem theoretically. I had not found a solution for this problem today.

#### Day 13:
Waking up with high expectations for answers to my Activity Stack problem that I had posted on stackoverflow the night before, I was quickly disappointed as I found no easy solution. The problem was that I wanted to keep my structure intact where the PlayPage could return to the ShopPage, but also (on session of app) to the TitlePage.

I came up with my own solution, where TitlePage created a ShopPage activity, but it automatically redirected to a PlayPage. The PlayPage would then finish instead of creating a new ShopPage, and this system ensured that the Activity Stack never grew larger than 3. The app could now be run forever!!

I also improved shop functionality (like when whatever is sold is already owned, and similar messages). Lots of small quality of life improvements and bug-fixes today, after the big stack breakthrough.

Another big issue came up then: how to implement how Foes pick their moves. My solution was that a list is generated that contains all moves that the Foe can perform, and then one of those is randomly chosen. As their *magic( decreases, some of those moves are removed from the list that moves are randomly picked from.

But wait there's more! I redid the log. The Game object now has a LinkedList that can add contain many messages, and it simply does not advance the combat while there are unpopped messages. The log in the PlayPage however now can contain all text in the combat! I made it scrollable, so there is no need to delete any messages.

#### Day 14:
More bug-fixing today, and other detail improvements. For example, equipment can now be seen on the title page. Today's two most important additions were also two big design problems:

How would I add the random encounter system? It is boring if the same Foes appear on the same levels. My solution works as follows: There are is a list of lists. A method has a very large switch case, where based on the level of the player character, various lists of Foes are added to the list of lists. Then at the end of the method, one of these lists is randomly chosen, and every Foe in this list is added to the Game, which the player character will face in combat. My only issue with this is that it doesn't look very pretty (one large method), and if I had more time I may have looked for a way to put the possible "encounters" in an XML file or something similar.

I also changed many things based on input from other people that I had try the app. For example, I noticed that upon dying (which they did a lot), they wouldn't see the death screen because of clicking it too fast. So I changed that click listener to a longClick.

#### Day 15:
I added more little things such as sorting in the LeaderboardPage by level and in the TitlePage by name, changing the names of the screens, adding dialogs to back pressing, adding a final boss (though the game can still be played after beating the final boss. It's an open ended experience!), and made some other changes to prepare for the Beta.

The biggest issue today was how to implement a Help/Information screen (for users that don't understand what everything is). I decided on a simple activity that contains nothing but ImageViews and TextViews, that could be called from all activities and simply canceled to return to wherever they called it from. Making all the images was very tedious, though. But it seemed to impress the audience at the Beta presentation!

At this point my app was feature complete.

#### Day 16:
Since my app was pretty much complete, there was not much to change today. Some very small tweaks and bug-fixes (nothing app-breaking), and I renamed the Game class to Combat. I then started the fun part of projects called commenting! (Luckily I had already commented a lot while writing code)

The biggest issue today was, unfortunately, trying to rename the packages to *nl.mprog.package*. Nothing seemed to work, and even google didn't know what to do with my errors. Eventually I just settled on making an entirely new project, and copy pasting all the code from the original project. Luckily the app still works!

#### Day 17:
Today I finished commenting, and I added a back press to the TitlePage (to cancel list load).

I then started working on the report files.

#### Day 18:
Some very minor comment tweaking. Afterwards I continued with the report files.

Day 19:

Day 20: