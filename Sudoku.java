/**
* Name: Priya Mehra
* Pennkey: prmehra
* Execution: n/a
*
* Description: creates individual cells within sudoku board, handles drawing of
* the board and spaces, updates values, checks values, and checks whether the game
* has been won.
**/
public class Sudoku {
    
    private char[][] puzzle;     // 2d array with initial char values
    private Cell[][] spaces;     // 2d array holding individual cells
    
    public Sudoku(char[][] puzzle) {
        this.puzzle = puzzle;
        spaces = new Cell[puzzle.length][puzzle[0].length];
        // populate 2d cell array with cells containing initial values
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                /* if initial value is anything other than a space, that
                * cell will not be mutable
                * */
                if (puzzle[i][j] == (char) 32) {
                    spaces[i][j] = new Cell(puzzle[i][j], true, i, j);
                }
                else {
                    spaces[i][j] = new Cell(puzzle[i][j], false, i, j);
                }
                
            }
        }
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: draws grid for the sudoku board
    */
    public void drawBoard() {
        int counter = 1;
        for (double i = 0.1111; i < 1.0; i += 0.1111) {
            // if line is boundary between 3x3 boxes, draw with larger pen radius
            if (counter % 3 == 0) {
                PennDraw.setPenRadius(0.004);
            }
            else {
                PennDraw.setPenRadius(0.002);
            }
            PennDraw.line(i, 0, i, 1);
            PennDraw.line(0, i, 1, i);
            counter++;
        }
        
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: draws values for each cell
    */
    public void drawSpaces() {
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j].draw();
            }
        }
    }
    
    /**
    * Inputs: int row, int col, char digit, of cell being updated
    * Outputs: n/a
    * Description: updates chosen space with new Cell object
    */
    public void updateSpace(int row, int col, char digit) {
        // if chosen space is mutable, populate space with new Cell
        if (spaces[row][col].checkMutable()) {
            spaces[row][col] = new Cell(digit, true, row, col);
        }
        
        // clear board and redraw grid
        PennDraw.clear();
        drawBoard();
        
        // if chosen space is mutable, re-check all values on board
        if (spaces[row][col].checkMutable()) {
            checkValues();
        }
        
        // redraw spaces
        drawSpaces();
        
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: check all values on board and highlights errors
    */
    public void checkValues() {
        resetCorrect();
        
        //iterate through the entire sudoku grid
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int boxRow = spaces[i][j].getGridRow();
                int boxCol = spaces[i][j].getGridCol();
                
                // check for box repetitions by iterating through each respective box
                for (int iterRow = boxRow; iterRow < boxRow + 3; iterRow++) {
                    for (int iterCol = boxCol; iterCol < boxCol + 3; iterCol++) {
                        // check to ensure you're not comparing one value to itself
                        if (iterRow != i || iterCol != j) {
                            // check that two values are the same/not empty spaces
                            if (spaces[i][j].getDigit() ==
                            spaces[iterRow][iterCol].getDigit() &&
                            spaces[i][j].getDigit() != (char) 32) {
                                // highlight incorrect box
                                PennDraw.setPenColor(255, 0, 0, 90);
                                PennDraw.filledRectangle(boxCol * 0.1111 + 0.16665,
                                1 - (boxRow * 0.1111 + 0.16665),
                                0.16665, 0.16665);
                                
                                spaces[iterRow][iterCol].changeCorrect(false);
                                spaces[i][j].changeCorrect(false);
                            }
                        }
                    }
                }
                
                // check for column repetitions
                for (int k = j + 1; k < 9; k++) {
                    if (spaces[i][j].getDigit() == spaces[i][k].getDigit() &&
                    spaces[i][j].getDigit() != (char) 32 && k != j) {
                        // highlight incorrect col
                        PennDraw.setPenColor(255, 0, 0, 90);
                        PennDraw.filledRectangle(0.5, spaces[i][j].getYPos(), 0.5,
                        0.05555);
                        
                        spaces[i][j].changeCorrect(false);
                        spaces[i][k].changeCorrect(false);
                    }
                }
                
                // check for row repetitions
                for (int k = i + 1; k < 9; k++) {
                    if (spaces[i][j].getDigit() == spaces[k][j].getDigit() &&
                    spaces[i][j].getDigit() != (char) 32 && k != i) {
                        // highlight incorrect row
                        PennDraw.setPenColor(255, 0, 0, 90);
                        PennDraw.filledRectangle(spaces[i][j].getXPos(),
                        0.5, 0.05555, 0.5);
                        
                        spaces[i][j].changeCorrect(false);
                        spaces[k][j].changeCorrect(false);
                    }
                }
            }
            
        }
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: resets all cell correct booleans to correct
    */
    public void resetCorrect() {
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j].changeCorrect(true);
            }
        }
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: checks whether all values have been inputted and whether all are
    * correct
    */
    public boolean checkWin() {
        int incorrectCount = 0;
        int spaceCount = 0;
        
        //counts number of incorrect and spaces in 2d array of spaces
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                if (!spaces[i][j].checkCorrect()) {
                    incorrectCount++;
                }
                if (spaces[i][j].getDigit() == (char) 32) {
                    spaceCount++;
                }
                
            }
        }
        if (incorrectCount > 0 || spaceCount > 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Inputs: n/a
     * Outputs: n/a
     * Description: clears all user inputted values
    */
    public void clearUserInput() {
        
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                // if space is mutable, reset value to default space
                if (spaces[i][j].checkMutable()) {
                    spaces[i][j] = new Cell((char) 32, true, i, j);
                }
            }
        }
        // clear and redraw board
        PennDraw.clear();
        drawBoard();
        checkValues();
        drawSpaces();
    }
}
