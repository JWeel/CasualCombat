# Design Document - Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

CozyCombat is an app for a game of turn-based combat.

*Minimum viable product*

The app should be minimally viable if it can handle the most basic mechanics of old
school turn-based combat games. These are:
* Combatants take turns choosing actions to defeat their opponent
* Chosen actions affect the "health points" of whatever was targeted by the action
* When a combatant's "health points" reach zero, they are defeated
* A combatant wins by defeating all opponents

With these underlying mechanics in place, most old school turn-based combat games add:
* Combatants can "level" up by defeating opponents, which gives them access to more actions
* Progress can be saved in-between fights
* Combatants have certain "skill values" that affect their actions

Once the mechanics are in place there are lots of optional features that are found in
these games, including:
* Usable items that can be used in combat as an action
* Gear that can be equipped in-between fights which passively affects the combat
* Items and gear can be bought in shops in-between fights
* There are different types of player characters and opponent characters, each with different strengths and weaknesses

The CozyCombat app should handle all of the first seven points, but may seem somewhat stale
until it also handles the four additional points. However, the app will not have reached
its design goal unless it also features quick combat, which means that
* Individual fights should not have to take long


*Framework*

Now follows an overview of the framework that can achieve the points written above.

These are the most important activities (screens) :

* MenuPage - this is the title screen. A user can start a new game, load an existing game, or check high scores, by clicking buttons on this screen.
* PlayPage - this is the page on which the game takes place. Various buttons handle various actions, and eventually they lead to a rest screen, or the game can be cancelled to return to the menu screen.
* RestPage - this is where a user arrives after finishing combat on the PlayPage, either by winning or losing. If they win they can save their progress, restock their supplies, start a new combat session, or go back to the menu screen.

Some secondary activities :

* LeaderboardPage - this is where a user can see how many fights of all the saved characters have won, which is essentially their "score". Users can return to the menu screen from this screen. They can also delete characters.
* InformationPage - this screen can be accessed from other screens and displays information about every usable action in combat.

Apart from these activities, there are other classes :

* Combatant - abstract. Various return methods that return values that belong to a combatant, such as their "skill values" and their name.
* Foe - extends Combatant. These are the opponents of the player character. There will be several templates of foes (types of foes), each with different skill values and usable actions.
* PlayerCharacter - extends Combatant. This is the character that is controlled by the user. Like the Foe, it will have a collection of usable actions, but it will also have usable items and gear. There may also be templates for various PlayerCharacters.
* Move - this is an action that a Combatant uses. It affects the skill values of other Combatants (usually the "health points")
* Item - this is either a usable item in combat (which means it is linked to a certain Move), or an item which increases the skill values of a PlayerCharacter for as long as they have equipped it in-between fights.
* Game - this class handles the state of the game. It keeps track of the "health points" of all combatants, and handles their actions one by one (turn-based).


![](doc/designMenu.png)