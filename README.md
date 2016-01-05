# Programmeer Project

<sub>Joseph Weel, 10321624, Universiteit van Amsterdam</sub>

The goal of this project is to create an app for a turn based combat game.

*App still needs a name.*

This will be a game app. Inspired by old school RPG games in which a protagonist
often has to fight foes in order to progress a story, the turn based combat of these
games can as a standalone game be a great way to entertain. A user would use this app
to quickly pass time.
Here is a list of potential features:
* Different skills that depend on certain skill values
* Enemies are controlled by the computer
* Individual fights can be short
* Save progress in-between fights

I have not seen an app with standalone turn based rpg combat, so this will be a new
experience for users.

Some extremely basic sketches:

![](doc/menu.png) ![](doc/play.png)

Required data sets and data sources:
* A character with skill values, stored in some character object
* Enemies with their own skill values
* Storage of the character for save/load

Decomposition:
* Menu, from which a new game can be started or an existing one loaded
* Play page, from which the game can be played
* Information page, to display information about skills and attacks
* Score page, to keep track of how many fights saved characters have won
* Shop page, between fights a character can restock here

Platform and components:
* Buttons can be used to pick attacks and traverse a menu
* This app is meant as a quick and easy way to pass time, and that works great on
	mobile devices because it can then be done anywhere.

Problems, or some things I will have to learn before being able to implement all features:
* 

A similar app:
* 