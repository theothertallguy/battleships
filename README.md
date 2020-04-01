# CPSC 210 Personal Project: *Battleships*

#### Nate Sternberg

***

## Project Overview
In this project, I am aiming to develop a working version of the classic game *Battleships*. The idea is that the game
will be set up so that the user can start a new game against a computer or another human, or continue their previous
game. The game will be set up using the classic 10x10 grid style, and players will be able to place their ships on the 
grid where they want to put them.

This project is of interest to me because *Battleships* is one of the games that I remember particularly from my
childhood. I remember playing it a lot in a variety of different settings, and I decided it would be a fun way
to pay homage to one of my favourite games. The target audience for this game is anyone who wants to play a game
of *Battleships*! My hope is that the game will run well, and that it will be a good way to expand my Java knowledge.

***
## User Stories

Phase 1 Stories
- As a user, I want to be able to add a ship to my grid.
- As a user, I want to be able to shoot at my opponent's grid.
- As a user, I want to be able to see what the outcome of my shots are.
- As a user, I want to be able to start a new game after the game ends.

Phase 2 Stories
- As a user, I want to choose whether or not I want to save my game upon quitting out.
- As a user, I want the choice of reloading my saved game when I start the program.

***
## Grading Information

Phase 3 Instructions for Grader
- To add ships to the grid, select a new game and select the appropriate ship tool. When you do so, you wil be able to
    click on a valid grid square and then click an arrow to choose the direction you want the ship to point.
- The visual component can be accessed by loading the game, you will see the game. You click on the opponent's grid to fire shots.
- You can load the game by clicking the load game on the main menu.
- You can save the game by clicking the save and exit button in the pause menu.

Phase 4: Task 2
- I chose to implement a type hierarchy for this task.
- The associated classes are Screen being the superclass, and all the other screens in the screen package being the subtypes.
- In many of the subclasses, the handleMouseClicked method is overridden from the Screen supertype, and Screen 
    itself extends JPanel, so all of the screens override the paintComponent method from the JPanel supertype.