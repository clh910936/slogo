# Design Plan

## Introduction
The primary goal our this project is to implement a MVC structure, with controller classes that co-ordinate the Model with the View. We want to make the code flexible so that we can internally create more commands and different GUI components. The View allows the user to enter commands, then the Controller parses the commands, and updates the Model. The View then queries the Model and displays the changes on-screen.

## Design overview

#### Backend internal API
- we must update the TurtleModel with changes to the turtle
- we must update the HistoryModel with the latest commands
- we must update the VariablesModel

#### Backend external API
