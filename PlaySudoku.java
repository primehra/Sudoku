/**
* Name: Priya Mehra
* Pennkey: prmehra
* Execution: java PlaySudoku starterFile
*
* Description: creates sudoku board and handles game play. if game is won, displays
* a winning message
**/
public class PlaySudoku {
    
    public static void main(String[] args) {
        String starterFile = args[0];
        
        char[][] startPuzzle = createStarterArray(starterFile);
        Sudoku board = new Sudoku(startPuzzle);
        
        // draw initial state of board and spaces
        board.drawBoard();
        board.drawSpaces();
        
        // start input loop which stops once board.checkWin() becomes true
        while (!board.checkWin()) {
            handleClick(board);
        }
        
        // display winning message
        PennDraw.filledRectangle(0.5, 0.5, 0.25, 0.1);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(0.5, 0.5, "You Win!");
        
    }
    
    /**
    * Inputs: Sudoku board
    * Outputs: n/a
    * Description: handles all user input with adding values to cells
    */
    public static void handleClick(Sudoku board) {
        int row = 0;
        int col = 0;
        char input = (char) 32;
        boolean keyWhile = true;
        
        if (PennDraw.mousePressed()) {
            // calculates row and column of mouse clicked
            row = 8 - (int) (PennDraw.mouseY() / (1.0 / 9));
            col = (int) (PennDraw.mouseX() / (1.0 / 9));
            
            // create a waiting period in which the user is given time to type
            while (keyWhile) {
                if (PennDraw.hasNextKeyTyped()) {
                    // if a key is pressed, store its value;
                    input = PennDraw.nextKeyTyped();
                    keyWhile = false;
                    // check whether input is valid and update space on the board
                    int ascii = input;
                    if (ascii > 48 && ascii < 58) {
                        board.updateSpace(row, col, input);
                    }
                    else if (ascii == 32) {
                        board.clearUserInput();
                        
                    }
                    else {
                        throw new IllegalArgumentException("can only input digits" +
                        " between 1-9");
                    }
                }
            }
        }
    }
    
    /**
    * Inputs: String filename
    * Outputs: char[][] puzzleArray
    * Description: creates a starter array containing initial values given in text
    * file
    */
    public static char[][] createStarterArray(String filename) {
        // creates empty 2d array which initial values will be stored in
        char[][] puzzleArray = new char[9][9];
        
        In inStream = new In(filename);
        
        // starts to iterate through rows of puzzleArray
        for (int row = 0; row < 9; row++) {
            // will act as index of line read by inStream
            int counter = 0;
            String line = inStream.readLine();
            
            // checks that each line in the text file contains correct amt of values
            if (line.length() != 9) {
                throw new IllegalArgumentException("each line must contain exactly" +
                " 9 characters");
            }
            
            // iterates through cols of puzzleArray
            for (int col = 0; col < 9; col++) {
                // converts each char to ascii value
                int ascii = line.charAt(counter);
                
                // check whether digit is valid
                if ((ascii > 48 && ascii < 58) || ascii == 32) {
                    puzzleArray[row][col] = line.charAt(counter);
                }
                else if (ascii == 10) {
                    // do not add anything to array if next char is line break
                }
                else {
                    throw new IllegalArgumentException("starter file can only" +
                    " contain digits 1-9");
                }
                counter++;
            }
        }
        
        // iterates through entire puzzleArray
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // find row and col of top left of respective 3x3 box for i and j
                int iterRow = i / 3 * 3;
                int iterCol = j / 3 * 3;
                
                // iterates through 3x3 box and checks for repetitions
                for (int k = iterRow; k < iterRow + 3; k++) {
                    for (int l = iterCol; l < iterCol + 3; l++) {
                        //checks you are not comparing one value to itself
                        if (k != i || l != j) {
                            if (puzzleArray[i][j] == puzzleArray[k][l] &&
                            puzzleArray[i][j] != (char) 32) {
                                throw new IllegalArgumentException("cannot have" +
                                " same value in box 2 times");
                            }
                        }
                    }
                }
                //check for row repetions
                for (int k = j + 1; k < 9; k++) {
                    if (puzzleArray[i][j] == puzzleArray[i][k] &&
                    puzzleArray[i][j] != (char) 32 && k != j) {
                        throw new IllegalArgumentException("cannot have same " +
                        "value in row 2 times");
                    }
                }
                //check for col repetions
                for (int k = i + 1; k < 9; k++) {
                    if (puzzleArray[i][j] == puzzleArray[k][j] &&
                    puzzleArray[i][j] != (char) 32 && k != i) {
                        throw new IllegalArgumentException("cannot have same " +
                        "value in col 2 times");
                    }
                }
            }
            
        }
        
        return puzzleArray;
    }
}
