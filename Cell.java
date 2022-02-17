/**
* Name: Priya Mehra
* Pennkey: prmehra
* Execution: n/a
*
* Description: creates an individual cell, handles getting and setting of each
* field variable
**/

public class Cell {
    
    private char digit;          // stores value being placed in each cell
    private boolean mutable;     // checks if value is able to be changed
    private int row;             // int representation of row of the cell
    private int col;             // int representation of col of the cell
    private int gridRow;         // int representation of row of respective 3x3 box
    private int gridCol;         // int representation of col of respective 3x3 box
    private double xCoord;       // x coordinate of the cell placement
    private double yCoord;       // y coordinate of the cell placement
    private boolean correct;     // boolean representation of whether cell is correct
    
    public Cell(char digit, boolean mutable, int row, int col) {
        this.digit = digit;
        this.mutable = mutable;
        this.row = row;
        this.col = col;
        // calculate xCoord and yCoord based on given row and col
        xCoord = 0.1111 * col + 0.05555;
        yCoord = 1.0 - (0.1111 * row + 0.05555);
        // calculate top left row and col of respective 3x3 box
        gridCol = ((int) ((0.1111 * col + 0.05555) * 3)) * 3;
        gridRow =  ((int) ((row * 0.1111 + 0.05555) * 3)) * 3;
        // sets default value of correct boolean
        correct = true;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: draws value in cell
    */
    public void draw() {
        // if value is correct, draw in black. else, in red
        if (correct) {
            PennDraw.setPenColor(PennDraw.BLACK);
        }
        else {
            PennDraw.setPenColor(PennDraw.RED);
        }
        
        String boxText = "" + digit;
        PennDraw.text(xCoord, yCoord, boxText);
        PennDraw.setPenColor(PennDraw.BLACK);
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns whether cell is mutable
    */
    public boolean checkMutable() {
        return mutable;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns char digit stored in cell
    */
    public char getDigit() {
        return digit;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns top left col of respective 3x3 box
    */
    public int getGridRow() {
        return gridRow;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns top left row of respective 3x3 box
    */
    public int getGridCol() {
        return gridCol;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns yCoord of cell
    */
    public double getYPos() {
        return yCoord;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns xCoord of cell
    */
    public double getXPos() {
        return xCoord;
    }
    
    /**
    * Inputs: boolean newCorrect
    * Outputs: n/a
    * Description: changes correctness of cell
    */
    public void changeCorrect(boolean newCorrect) {
        correct = newCorrect;
    }
    
    /**
    * Inputs: n/a
    * Outputs: n/a
    * Description: returns whether cell is correct
    */
    public boolean checkCorrect() {
        return correct;
    }
    
}
