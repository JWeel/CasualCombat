# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

# Project Report

### Casual Combat

An app for a turn based combat game.

In this game, the player fights turn-based battles, becoming more powerful after each battle. After a battle, gold and level up points are earned, and the player is taken to a shop. Here, gold can be used to buy items, equipment and spells, and level up points can used to improve the player character's skills. They then continue to the next battle.

-----

###### Inspiration

The app is based on old school role-playing games that featured turn-based combat, where characters had access to various spells and abilities that they could use in battles. These became stronger as they progressed the game and leveled up various skills. The battles encompassed only half of the overall scope of most of these games, though, for the player characters would often traverse some world and complete various tasks. This app, however, focuses only on the combat part. However, its various battles are interrupted by brief visits to a shop, where progress can be saved.

-----

###### Functionality

The app is built around the interaction between a *PlayPage* activity, and a *Combat* object. This is the core of the game. The *Combat* object has various *Combatant*s (one *PlayerCharacter* vs various *Foe*s) that take turns using *Move*s that lower the *health* value of their target. How much the *health* is lowered (the damage), is affected by skills:

* Regular attacks deal damage based on the attacker's *strength* and the defender's *defense*
* Spells deal damage based on the attacker's *willpower* and the defender's *resistance*

Who among the *Combatant*s gets to use their *Move* first depends on their *speed*. If their *health* reaches zero, the *Combatant* is defeated. Spells cost *magic*, and if a *Combatant*'s *magic* gets too low, they can no longer cast that Spell.

The *PlayerCharacter* also gets *UsableItem*s that have their own *Moves* which ignore any defensive skills. They also can have *EquippableItem*s which provide a passive bonus to their skills, of which there are three types:

* Weapons, which improve *strength* or *willpower*
* Armor, which improve *defense* or *resistance*
* Boots, which improve *speed*

A *PlayerCharacter* can only have one weapon, one piece of armor, and one pair of boots at any one time.

Most *Combatant*s (including the *PlayerCharacter*) can also use a basic defensive *Move*, which doubles their defensive skill during that round of combat.

If the *PlayerCharacter* manages to defeat all the *Foe*s, they earn a level up point, as well as gold (though the amount of gold depends on the *Foe*s). Afterwards, the *PlayPage* finishes, and the user is brought to a *ShopPage*, where they can spend their level up point to increase a skill, and spend their gold to buy *UsableItem*s, *EquippableItem*s, or new spells (*Move*s). They can then save their progress (the *PlayerCharacter* is stored in *SharedPreferences*), and start a new battle (which starts a new *PlayPage*, which in turns creates a new *Combat* object).

The *Foe*s that are faced in *Combat* come in various groups, and these groups are randomly chosen from a predefined list of possible groups that is based on the level of the *PlayerCharacter* (the level being how many rounds of combat the character has won). The higher the level, the more powerful the opponents. These opponents all have predefined skills and usable *Move*s, but what *Move* they use is randomly chosen.

The selection of items and spells buyable at the shop works similarly to how groups of *Foe*s are chosen: a predefined list of possible buyable items and spells that depends on the level of the *PlayerCharacter*.

Aside from the *PlayPage* and *ShopPage*, there is the *TitlePage*, which is the first *Activity* the app creates. Here a user can create a new character, or load an existing character, after which they can start the game (ultimately taking them to another *PlayPage*). They can also go to a *LeaderboardPage*, where all stored *PlayerCharacter*s are shown (and where they can be deleted).

Finally, there is an *InfoPage*, which contains images and text that explain how to use the app.

-----

###### Challenges

... in the design document, I wrote how an "InformationPage" would display information about what various moves do in combat. In the process of making the moves, however, I noticed that the moves themselves already explain enough, and so the InfoPage instead is simply a screen with lots of images and text that explains to a user how to play the game.