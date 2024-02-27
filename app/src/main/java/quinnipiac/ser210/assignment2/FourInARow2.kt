/*
 * Four in a row implementation of IGame
 * @author aarmellino
 * @date 2/5/2024
 */

class FourInARow2
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }

    public fun getCell(location: Int): Int {
        return board[location / GameConstants.ROWS][location % GameConstants.COLS]
    }

    //sets all board spaces to empty
    override fun clearBoard()
    {
        for (row in 0 until GameConstants.ROWS)
        {
            for (col in 0 until GameConstants.COLS)
            {
                board[row][col] = GameConstants.EMPTY
            }
        }
    }
    //places the player's color in a specified location
    override fun setMove(player: Int, location: Int)
    {
        var cols = location % GameConstants.COLS
        var rows = location / GameConstants.COLS
        if (board[rows][cols] == GameConstants.EMPTY)
        {
            board[rows][cols] = player
        }
        else
        {
            println("Please select an empty space.")
        }

    }
    //The AI chooses the next open space
    override val computerMove: Int
        get()
        {
            for (row in 0 until GameConstants.ROWS)
            {
                for (col in 0 until GameConstants.COLS)
                {
                    if (board[row][col] == GameConstants.EMPTY)
                    {
                        return col + (row * GameConstants.COLS)
                    }
                }
            }
            return 0
        }

    //checks for a winner with a diagonal, all in a column, or all in a row set of four.
    //If the board fills up with no winner, declared a tie.
    override fun checkForWinner(): Int
    {
        //Diagonal
        for (row in 0 until GameConstants.ROWS - 3)
        {
            for (col in 0 until GameConstants.COLS - 3)
            {
                if (board[row][col] != GameConstants.EMPTY && board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2] && board[row][col] == board[row + 3][col + 3])
                {
                    return if (board[row][col] == GameConstants.BLUE)
                    {
                        GameConstants.BLUE_WON
                    }
                    else
                    {
                        GameConstants.RED_WON
                    }
                }
            }
        }
        for (row in 0 until GameConstants.ROWS - 3)
        {
            for (col in 3 until GameConstants.COLS)
            {
                if (board[row][col] != GameConstants.EMPTY && board[row][col] == board[row + 1][col - 1] && board[row][col] == board[row + 2][col - 2] && board[row][col] == board[row + 3][col - 3])
                {
                    return if (board[row][col] == GameConstants.BLUE)
                    {
                        GameConstants.BLUE_WON
                    }
                    else
                    {
                        GameConstants.RED_WON
                    }
                }
            }
        }
        //Column
        for (row in 0 until GameConstants.ROWS - 3)
        {
            for (col in 0 until GameConstants.COLS)
            {
                if (board[row][col] != GameConstants.EMPTY && board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col] && board[row][col] == board[row + 3][col])
                {
                    return if (board[row][col] == GameConstants.BLUE)
                    {
                        GameConstants.BLUE_WON
                    }
                    else
                    {
                        GameConstants.RED_WON
                    }
                }
            }
        }
        //Row
        for (row in 0 until GameConstants.ROWS)
        {
            for (col in 0 until GameConstants.COLS - 3)
            {
                if (board[row][col] != GameConstants.EMPTY && board[row][col] == board[row][col + 1] && board[row][col] == board[row][col + 2] && board[row][col] == board[row][col + 3])
                {
                    return if (board[row][col] == GameConstants.BLUE)
                    {
                        GameConstants.BLUE_WON
                    }
                    else
                    {
                        GameConstants.RED_WON
                    }
                }
            }
        }
        //Continues game with empty spaces
        for (row in 0 until GameConstants.ROWS)
        {
            for (col in 0 until GameConstants.COLS)
            {
                if (board[row][col] == GameConstants.EMPTY)
                {
                    return GameConstants.PLAYING
                }
            }
        }
        //Tie
        return GameConstants.TIE
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("-----------") // print horizontal partition
            }
        }
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}