# Manual Tests

## GUI Testing
# Testing Empty Grid
 - Test Steps:
    1. Run StarbClient
 - Test Data:
    Null game state.
 - Expected Result:
    Regular grid with no sections. 
 - Actual Result:
    Regular grid with no sections. 

# Testing Start New Puzzle
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates. 
 - Expected Result:
    Grid with specific sections outlined based on puzzle file. 
 - Actual Result:
    Grid with the bolded sections. This test was done for both puzzles and output correct sections. 

# Testing Add Star
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
    3. Click in an empty cell
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates;
    Mouse click location on the grid.  
 - Expected Result:
    Adds a star in the middle of the clicked grid cell. This also updates
    the client game state through the client interaction. Places a '*' in array space. 
 - Actual Result:
    Draws a star in clicked cell and updates client game state. 

# Testing Add Dot
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
    3. Click in a cell with a star
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates;
    Mouse click location on the grid.  
 - Expected Result:
    Removes the previous star and adds a dot in the middle of the clicked grid cell. This also updates
    the client game state through the client interaction. Places a '.' in array space. 
 - Actual Result:
    Draws a dot in clicked cell and updates client game state. 

# Testing Empty Cell
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
    3. Click in a cell with a dot
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates;
    Mouse click location on the grid.  
 - Expected Result:
    Removes the previous dot in the cell. This also updates
    the client game state through the client interaction. Places a ' ' in array space. 
 - Actual Result:
    Removes any drawing in clicked cell and updates client game state. 

# Testing Successful Puzzle
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
    3. Click in a cells to solve puzzle
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates;
    Mouse click location on the grid;
    Solution array for the puzzle.  
 - Expected Result:
     The background of the grid will turn green is the user's solution
     solves the puzzle. If their solution is not correct, nothing will happen.
 - Actual Result:
    The background does turn green if solved, otherwise the GUI is nnot affected. 

## ClientGameState
 - Test Steps:
    1. Run StarbClient
    2. Click Start New Puzzle button
    3. Click in a cell
 - Test Data:
    Non-empty game state that holds information about the 
    grid section coordinates;
    Mouse click location on the grid.  
 - Expected Result:
    When clicked on a cell, the coordinate is translated according to matrix cells
    and based on the previous state of the cell, the client game state is updated with
    either a star, dot, or space. The result is printed after every click.  
 - Actual Result:
    The client game state is updated after every click on the grid, as shown through basic printing to the console. 