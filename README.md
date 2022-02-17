# Sudoku
Object-based implementation of classic sudoku game

/**********************************************************************
 *  Instructions for how to run your program
 **********************************************************************/
 
Open the playSudoku class, and run using a starter sudoku file.
To input a value, you click on the square you are trying 
to change and then type the value you want to input. Any error messages and 
illegalArgumentExceptions will be shown in the terminal.


/**********************************************************************
 *  Any additional features                     
 **********************************************************************/
 
Space button - will clear all user-inputted values

/**********************************************************************
 *  A brief description of each file and its purpose                                          
 **********************************************************************/
 
 sudokuExample.txt - starter file for the sudoku table
 playSudoku.java -  class used to run the game, handle all mouse clicks and user
     inputs, and create the board and 2d starter array
 Sudoku.java - object class which creates individual cells within sudoku board, 
     handles drawing of the board and spaces, updates values, checks values, and 
     checks whether the game has been won. implemented in playSudoku.java
 Cell.java - object class which creates an individual cell, handles getting and 
     setting of each field variable. implemented in Sudoku.java
