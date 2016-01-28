# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

# Project Report

### Casual Combat

An app for a turn based combat game.

In this game, the player fights turn-based battles, becoming more powerful after each battle. After a battle, gold and level up points are earned, and the player is taken to a shop. Here, gold can be used to buy items, equipment and spells, and level up points can used to improve the player character's skills. They then continue to the next battle.

-----

###### Inspiration

The app is based on old school role-playing games that featured turn-based combat, where characters had access to various spells and abilities that they could use in battles. These became stronger as the characters progressed the game and leveled up various skills. The battles encompassed only half of the overall scope of most of these games, for the player characters would often traverse some world and complete various tasks. This app, however, focuses only on the combat part. It features combat after combat, these battles only interrupted by brief visits to a shop, where progress can be saved.

-----

###### Functionality

The app is built around the interaction between a *PlayPage* activity, and a *Combat* object. This is the core of the game. The *Combat* object keeps track of various *Combatant*s (one *PlayerCharacter* vs various *Foe*s) that take turns using *Move*s that lower the *health* value of their target. How much the *health* is lowered (the damage), is affected by skills:

* Regular attacks deal damage based on the attacker's *strength* and the defender's *defense*
* Spells deal damage based on the attacker's *willpower* and the defender's *resistance*

Who among the *Combatant*s gets to use their *Move* first depends on their *speed*. If their *health* reaches zero, the *Combatant* is defeated. Spells are *Move*s that cost *magic*, and if a *Combatant*'s *magic* gets too low, they can no longer cast that spell. Some spells can hit more than one target, but the secondary targets will take less damage than the primary target.

The *PlayerCharacter* also gets *UsableItem*s that have their own *Moves* which ignore any defensive skills. They also can have *EquippableItem*s which provide a passive bonus to their skills, of which there are three types:

* Weapons, which improve *strength* or *willpower*
* Armor, which improve *defense* or *resistance*
* Boots, which improve *speed*

A *PlayerCharacter* can only have one weapon, one piece of armor, and one pair of boots at any one time.

Most *Combatant*s (including the *PlayerCharacter*) can also use a basic defensive *Move*, which doubles their defensive skill during that round of combat.

If the *PlayerCharacter* manages to defeat all the *Foe*s, they earn a level up point, as well as gold (though the amount of gold depends on the *Foe*s). Afterwards, the *PlayPage* Activity finishes, and the user is brought to a *ShopPage*, where they can spend their level up point to increase a skill, and spend their gold to buy *UsableItem*s, *EquippableItem*s, and new spells (*Move*s). They can then save their progress (the *PlayerCharacter* is stored in *SharedPreferences*), and start a new battle (which starts a new *PlayPage*, which in turn creates a new *Combat* object).

The *Foe*s that are faced in *Combat* come in various groups, and these groups are randomly chosen from a predefined list of possible groups that is based on the level of the *PlayerCharacter* (the level being how many rounds of combat the character has won). The higher the level, the more powerful the opponents. These opponents all have predefined skills and usable *Move*s, but what *Move* they use is randomly chosen.

The selection of items and spells buyable at the shop works similarly to how groups of *Foe*s are chosen: a predefined list of possible buyable items and spells that depends on the level of the *PlayerCharacter*.

In addition to the *PlayPage* and *ShopPage*, there is a third Activity, the *TitlePage*, which is the first *Activity* the app creates. Here a user can create a new character, or load an existing character, after which they can start the game (ultimately taking them to another *PlayPage*). They can also go to a *LeaderboardPage* from here, where all stored *PlayerCharacter*s are shown (and where they can be deleted).

Finally, there is an *InfoPage*, which contains images and text that explain how to use the app.

-----

###### Challenges

I did not really face any challenges during the first week (apart from originally having a different app idea). It wasn't until the second week that I ran into some head-scratchers.

Starting off the second week, having implemented very basic combat functionality for my prototype, I needed a way to display combat messages. The user needs to be aware of what is going on. A little blurb of text is shown for each *Move* that is used during combat. The problem was - how would I implement this, since the combat happens in the *Combat* object, while the displaying is in the *PlayPage* activity. I envisioned two queues (LinkedLists), one in *PlayPage* and one in *Combat*, and would have messages be pushed onto the *Combat* queue, which were popped whenever the user touched the log window at the bottom of their screen, and displayed in said window.

Later I had the idea that the *Combat* class did not need a queue. It could simply store one string, and only the *PlayPage* needed a queue. Soon, however, I began seeing that this limited how much information I could give to the user, because I could only show one message per *Move*. How would I handle messages not linked to *Move*s (such as a message for when all *Foe*s are defeated)? A week after this new implementation, I reintroduced a queue to the *Combat* class, and rewrote how *PlayPage* interacts with *Combat* (now only urging *Combat* to advance when it did not have any messages left to pop). This coincided with me deciding to make the log window scrollable, allowing all combat messages to be saved (instead of only the most recent). This is way better, because users can scroll up if they forgot what previously happened.

Another issue at the start of the second week was how I would implement the storing of the player characters. I had thought of using a database (maybe even online, with something like Parse.com), but eventually discovered a library (well, really it's just one file) called ComplexPreferences, which allows storing any object in an app's SharedPreferences (by using JSON). I had some trouble implementing it (due to lack of clear documentation), but it worked on the first try, so I was happy with it.

A week later, however, after having relied on the ComplexPreferences for so long, I discovered that it was crashing my app! This had to do with the JSON methods not knowing how to recreate abstract objects (I'll get to this in a second). Since I had other important features that I still needed to implement, and was just three days away from the Beta deadline, I decided to just use a workaround (temporarily changing the guilty objects to ints representing IDs, and restoring them to proper objects after finalizing the JSON load). It is unfortunate that I had to use a workaround, but I am glad I did use SharedPreferences, because the app can easily and quickly work with it, and it makes the app not require the internet (the app is now fully offline). It also probably spared me from a day or two of debugging errors with Parse.

But the whole problem with the abstract object stems from another challenge I faced: how would I implement the many different types of *Foe*s, *Item*s and *Move*s. This also began in the second week, but it wasn't until the end of that week that I settled on a solution (after having discussed it with my project group mates). I decided to make abstract classes (*Foe*, *Item* and *Move*), and every "type" of one of these classes would be its own subclass. A *Goblin* extends *Foe*, and I simply put the predefined skill values that I envisioned a *Goblin* to have in its constructor. The downside to this was that there were going to be a lot of classes (so I made packages to contain these), but it made it easy to add new subclasses and change existing ones. I felt that there was probably an even better solution out there somewhere, but, acknowledging how much other work still had to be done, I implemented this, and was pleasantly surprised by my implementation, and pleased overall.

...until my app was crashing in the third week! *Foe*s and *Move*s were fine, but as I added equipment (which was itself an abstract class *EquippableItem* that extended *Item*) to the *PlayerCharacter* (meaning the equipment had to be stored), I started realizing the downsides of my implementation. It should be noted that I had not thought of any of this when I made my design document. The one thing I would probably seek to change if I would make a similar app in the future, is the implementation of having these different types of *Foe*s, *Item*s and *Move*s.

There is one other major challenge I faced, which was another thing I hadn't thought about when I wrote my design document: the Activity stack. The way may app worked was that *PlayPage* instances would create *ShopPage* instances which would create *PlayPage* instances and so forth. Just writing that line makes it seem so obvious, that it troubles me that I did not realize it earlier - the Activity stack would grow so large that the app will eventually crash! The problem was that I had a reliable structure between my activities where I could return to the *TitlePage* from the *ShopPage* but also from the *PlayPage*, depending on whether the user had just started a session with the app, or was already playing for a while. I poured my heart out on *stackoverflow*, explaining how I needed to be able to keep my structure intact, start more activities, and keep the stack from growing too large!

Ultimately it was my own sudden insight that I could have my *TitlePage* create a *ShopPage* as opposed to a *PlayPage*, but then redirect the *ShopPage* immediately to a *PlayPage* for the first session, and then have the *PlayPage*s finish instead of creating more activities, that solved my problem. My structure was kept mostly intact (apart from having to move some stuff around, like onCreate methods to onActivityResult), and most importantly, the Activity stack was kept nice and concise! This decision is easy to defend: it won't crash, and it's much cleaner.

These four challenges were probably the biggest ones I faced, but I learned a lot about making sure to properly create an extendable framework, how to implement random libraries, and how the Activity stack works.

I'll now go into some minor changes between the final state of my app and how I envisioned it in my design document:

* In the design document, I wrote how an "InformationPage" would display information about what various *Move*s do in combat. In the process of making the *Move*s, however, I noticed that the *Move*s themselves already explain enough, and so the *InfoPage* instead is simply a screen with lots of images and text that explains to a user how to play the game.

* Another difference with the design document is how I envisioned ProgressBars displaying the *health* and *magic* of the *Foes*. I ultimately went with plain old text, which I liked more because it is simpler - the display for an enemy does not need to be as detailed as the display for the player character. I also like completely hiding the knowledge of how much *magic* the *Foe* has. It creates a feeling that the player has to improvise, making the game more exciting.

Mostly though, I implemented what I wrote in my design document. The problem was that my design document was comprehensive enough! I often had to think about various features that I had not thought enough about when I wrote my design document. In the future, I should probably spend a little more time thinking about all parts of the design of a project, instead of what I did this time: I rushed ahead writing code, and spent only one day on the design document.

I've definitely experienced that thorough planning goes a long way, and not spending enough time thinking over one concept, in order to save time in the short run, will only cost more time in the long run.

-----