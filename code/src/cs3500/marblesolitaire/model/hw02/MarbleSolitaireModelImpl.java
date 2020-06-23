package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * An implementation of the MarbleSolitaireModel interface.
 * This class represents one possible implementation of a game of marble solitaire
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private final int width;
  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private final ArrayList<ArrayList<Space>> gameBoard;

  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);

  }


  public MarbleSolitaireModelImpl(int aRow, int aCol) {
    this(3, aRow, aCol);

  }


  public MarbleSolitaireModelImpl(int thickness) {
    this(thickness, ((thickness * 3) - 2) / 2, ((thickness * 3) - 2) / 2);
  }

  /**
   * Constructs a {@code MarbleSolitaireModelImpl} object.
   * @param thickness    the thickness of the arms of the board
   * @param aRow         the row where the beginning empty space is to be
   * @param aCol         the column where the beginning empty space is to be
   */
  public MarbleSolitaireModelImpl(int thickness, int aRow, int aCol) {
    if (thickness % 2 == 0 || thickness < 3) {
      throw new IllegalArgumentException("Invalid arm thickness given: "
              + String.valueOf(thickness));
    }
    this.width = (thickness * 3) - 2;
    this.sRow = aRow;
    this.sCol = aCol;
    this.armThickness = thickness;
    this.gameBoard = new ArrayList<ArrayList<Space>>();
    this.initBoard();
    if (this.isGameOver()) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + String.valueOf(this.sCol) + "," + String.valueOf(this.sRow) + ")");
    }

  }

  /**
   * Initializes the game board according to the paramaters, and checks to make sure
   * That the empty space is placed in a valid space.
   */
  private void initBoard() {
    for (int i = 0; i < this.width; i++) {
      // 3 - > 4
      // 5 - > 8
      // 7 - > 12
      //if we are on a top or bottom row, only make the center spaces
      this.gameBoard.add(new ArrayList<Space>());
      if (i < this.armThickness - 1 || i > (this.armThickness - 1) * 2) {
        for (int j = 0; j < this.armThickness - 1; j++) {
          this.gameBoard.get(i).add(Space.INVALID);

        }
        for (int j = this.armThickness - 1; j <= (this.armThickness - 1) * 2; j++) {
          this.gameBoard.get(i).add(Space.MARBLE);
        }
      } else {
        for (int j = 0; j < this.width; j++) {
          this.gameBoard.get(i).add(Space.MARBLE);
        }
      }

    }

    if (this.sRow > this.width || this.sRow < 0 || this.sCol > this.width || this.sCol < 0
            || this.sRow >= this.gameBoard.size()
            || this.sCol >= this.gameBoard.get(this.sRow).size()
            || this.gameBoard.get(this.sRow).get(this.sCol) == Space.INVALID) {
      throw new IllegalArgumentException("Invalid empty cell position ("
              + String.valueOf(this.sCol) + "," + String.valueOf(this.sRow) + ")");

    }

    this.gameBoard.get(this.sRow).set(this.sCol, Space.EMPTY);

  }

  /**
   *  Determine if a move is valid, that is:
   *     it's moving from a space with a marble to a space without a marble
   *     those two spaces have one space between them, which contains a marble.
   *
   * @param fromRow The row of the starting space
   * @param fromCol The column of the starting space
   * @param midRow  The row of the space in between the starting and ending spaces
   * @param midCol  The column of the space in between the starting and ending spaces
   * @param toRow   The row of the ending space
   * @param toCol   The row of the ending col
   * @return        True if and only if the move is valid as defined above
   */

  private boolean isValidMove(int fromRow, int fromCol, int midRow, int midCol,
                              int toRow, int toCol) {

    return toCol >= 0 && fromCol >= 0 && midCol >= 0 && toRow >= 0 && fromRow >= 0 && midRow >= 0
            && fromRow < this.gameBoard.size()
            && fromCol < this.gameBoard.get(fromRow).size()
            && toRow < this.gameBoard.size()
            && toCol < this.gameBoard.get(toRow).size()
            && midRow < this.gameBoard.size()
            && midCol < this.gameBoard.get(midRow).size()
            && this.gameBoard.get(midRow).get(midCol) == Space.MARBLE
            && this.gameBoard.get(fromRow).get(fromCol) == Space.MARBLE
            && this.gameBoard.get(toRow).get(toCol) == Space.EMPTY
            && ((Math.abs(fromRow - toRow) == 2 && fromCol == toCol)
                    || (fromRow == toRow && Math.abs(fromCol - toCol) == 2));

  }

  /**
   * Make a move if it is valid, otherwise throw an illegal argument exception.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow the row number of the position to be moved to
   *              (starts at 0)
   * @param toCol the column number of the position to be moved to
   *              (starts at 0)
   * @throws IllegalArgumentException if the move is not valid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    //assume given values are 2 apart, if they are not the check will fail later on
    int midRow;
    int midCol;
    if (fromRow > toRow) {
      midRow = fromRow - 1;
      midCol = toCol;
    } else if (fromRow < toRow) {
      midRow = toRow - 1;
      midCol = toCol;
    } else if (fromCol > toCol) {
      midRow = toRow;
      midCol = fromCol - 1;
    } else {
      midRow = toRow;
      midCol = toCol - 1;
    }

    if (!isValidMove(fromRow, fromCol, midRow, midCol, toRow, toCol)) {
      throw new IllegalArgumentException("Illegal Move");
    }

    this.gameBoard.get(fromRow).set(fromCol, Space.EMPTY);
    this.gameBoard.get(midRow).set(midCol, Space.EMPTY);
    this.gameBoard.get(toRow).set(toCol, Space.MARBLE);


  }

  /**
   * Determine if the game is over.
   * @return true if and only if there are no valid moves left to be made
   */
  @Override
  /*
  Iterate over every game piece and check if its 4 possible moves are valid
  if at least one piece has at least one valid move, the game is not over
   */
  public boolean isGameOver() {
    //row i column j
    for (int i = 0; i < this.gameBoard.size(); i++) {
      for (int j = 0; j < this.gameBoard.get(i).size(); j++) {
        if (isValidMove(i, j, i + 1, j, i + 2, j)
                || isValidMove(i, j, i - 1, j, i - 2, j)
                || isValidMove(i, j, i, j + 1, i, j + 2)
                || isValidMove(i, j, i, j - 1, i, j - 2)) {

          return false;

        }
      }
    }
    return true;
  }

  /**
   * Get the current state of the game.
   * @return the state of the game in the form of a string
   */
  @Override
  public String getGameState() {
    String returnString = "";
    for (int i = 0; i < this.gameBoard.size() - 1; i++) {
      for (int j = 0; j < this.gameBoard.get(i).size(); j++) {
        returnString += this.gameBoard.get(i).get(j).getVal();
        if (j < this.gameBoard.get(i).size() - 1) {
          returnString += " ";
        }
      }
      returnString += "\n";
    }
    for (int j = 0; j < this.gameBoard.get(this.width - 1).size(); j++) {
      returnString += this.gameBoard.get(this.width - 1).get(j).getVal();
      if (j < this.gameBoard.get(this.width - 1).size() - 1) {
        returnString += " ";
      }
    }
    return returnString;
  }

  /**
   * Get the current score of the game.
   * @return the current score of the game, defined as the number of pieces in play.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.gameBoard.size(); i++) {
      for (int j = 0; j < this.gameBoard.get(i).size(); j++) {
        if (this.gameBoard.get(i).get(j) == Space.MARBLE) {
          score += 1;
        }
      }
    }
    return score;
  }
}
